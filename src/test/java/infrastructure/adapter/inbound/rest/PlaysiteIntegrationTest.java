package infrastructure.adapter.inbound.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mtech.infrastructure.adapter.outbound.database.repository.AttractionRepository;
import org.mtech.infrastructure.adapter.outbound.database.repository.KidRepository;
import org.mtech.infrastructure.adapter.outbound.database.repository.PlaysiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.*;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mtech.domain.vo.KidStatus.PLAYING;
import static org.mtech.domain.vo.KidStatus.WAITING;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
@Sql(
        value = "/sql/cleanup.sql",
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
)
public class PlaysiteIntegrationTest {

    private final MockMvc mvc;
    private final PlaysiteRepository playsiteRepository;
    private final AttractionRepository attractionRepository;
    private final KidRepository kidRepository;

    @Mock
    private Random random;

    @Autowired
    public PlaysiteIntegrationTest(
            MockMvc mvc,
            PlaysiteRepository playsiteRepository,
            AttractionRepository attractionRepository,
            KidRepository kidRepository
    ) {
        this.mvc = mvc;
        this.playsiteRepository = playsiteRepository;
        this.attractionRepository = attractionRepository;
        this.kidRepository = kidRepository;
    }

    @Configuration
    @ComponentScan(basePackages = "org.mtech")
    static class IntegrationTestConfig {

        @Bean
        public MockMvc mockMvc(WebApplicationContext context) {
            return MockMvcBuilders.webAppContextSetup(context).build();
        }

        @Bean
        @Primary
        public Random random() {
            Random random = mock(Random.class);
            doReturn(true).when(random).nextBoolean();
            return random;
        }

    }

    @BeforeEach
    public void setUp() {
        doReturn(true).when(random).nextBoolean();
    }

    @Test
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    void givenPlaysiteWithAttractionsIsCreated_whenExcessKidsAreAdded_thenQueueIsFilled() throws Exception {
        var playsiteId = 1L;

        assertEquals(0, playsiteRepository.count());

        String emptyPlaysiteResponse = mvc.perform(get("/playsites")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        assertEquals(minifyJson(
                """
                            {
                              "playsites": []
                            }
                        """), emptyPlaysiteResponse);

        // 1. Add playsite
        String playsiteResponse = mvc.perform(post("/playsites:add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(
                            """
                                {
                                  "attractions": [
                                    {
                                      "name": "double swings",
                                      "count": 1
                                    }
                                  ]
                                }
                            """.strip()))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        assertEquals(minifyJson(
                """
                    {
                      "id": 1,
                      "status": "ADDED",
                      "attractions": [
                        {
                          "id": 1,
                          "name": "double swings"
                        }
                      ]
                    }
                """), playsiteResponse);

        assertEquals(1, playsiteRepository.count());
        assertEquals(1, attractionRepository.count());
        assertEquals(0, kidRepository.count());

        // 2. Add kid
        mvc.perform(post("/playsite/{id}/kids:add", playsiteId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(
                                """
                                    {
                                      "kid": {
                                        "name": "Laura",
                                        "age": 4
                                      }
                                    }
                                """.strip()))
                .andDo(print())
                .andExpect(status().isOk());

        mvc.perform(post("/playsite/{id}/kids:add", playsiteId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(
                                """
                                    {
                                      "kid": {
                                        "name": "Anna",
                                        "age": 5
                                      }
                                    }
                                """.strip()))
                .andDo(print())
                .andExpect(status().isOk());

        mvc.perform(post("/playsite/{id}/kids:add", playsiteId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(
                                """
                                    {
                                      "kid": {
                                        "name": "Peter",
                                        "age": 2
                                      }
                                    }
                                """.strip()))
                .andDo(print())
                .andExpect(status().isOk());

        // 4. Assert previously added kids have valid statuses
        var kids = kidRepository.findAll();

        assertThat(kids).satisfies(p -> {
            assertThat(kids).hasSize(3);
            assertThat(kids.get(0).getName()).isEqualTo("Laura");
            assertThat(kids.get(0).getStatus()).isEqualTo(PLAYING);
            assertThat(kids.get(1).getName()).isEqualTo("Anna");
            assertThat(kids.get(1).getStatus()).isEqualTo(PLAYING);
            assertThat(kids.get(2).getName()).isEqualTo("Peter");
            assertThat(kids.get(2).getStatus()).isEqualTo(WAITING);

        });

        var firstTicketNumber = kids.getFirst().getTicketNumber();

        // 5. Remove first kid
        mvc.perform(delete("/playsite/kids:remove", firstTicketNumber)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(
                                """
                                    {
                                      "ticketNumber": "%s"
                                    }
                                """.strip().formatted(firstTicketNumber)
                        ))
                .andExpect(status().isOk());


        // 6. Assert previously queued kid is playing
        assertThat(kidRepository.findAll()).hasSize(2)
                .anySatisfy(kid -> assertThat(kid.getName()).isEqualTo("Anna"))
                .anySatisfy(kid -> assertThat(kid.getName()).isEqualTo("Peter"))
                .allSatisfy(kid -> assertThat(kid.getStatus()).isEqualTo(PLAYING));
    }

    private static String minifyJson(String prettyJson) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Object json = objectMapper.readValue(prettyJson, Object.class);
        return objectMapper.writeValueAsString(json);
    }

}
