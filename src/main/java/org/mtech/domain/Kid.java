package org.mtech.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.mtech.domain.vo.KidStatus;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@Accessors(chain = true)
@ToString(exclude = "playsite")
public class Kid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String ticketNumber;

    @Column(nullable = false)
    private String name;

    private Integer age;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private KidStatus status;

    @Column(nullable = false)
    private LocalDateTime ticketDateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "playsite_id", nullable = false)
    private Playsite playsite;
}
