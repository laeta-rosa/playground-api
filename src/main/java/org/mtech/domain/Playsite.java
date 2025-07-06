package org.mtech.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.mtech.domain.vo.AttractionType;
import org.mtech.domain.vo.KidStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.util.UUID.randomUUID;
import static org.mtech.domain.vo.KidStatus.WAITING;

@Entity
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class Playsite {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer capacity = 0;

    @OneToMany(mappedBy = "playsite", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Attraction> attractions = new ArrayList<>();

    @OneToMany(mappedBy = "playsite", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Kid> kids = new ArrayList<>();

    public void addAttraction(Attraction attraction) {
        if (attraction == null || attractions.contains(attraction)) {
            return;
        }
        attractions.add(attraction);
        attraction.setPlaysite(this);
        capacity += AttractionType.getCapacity(attraction.getName());
    }

    public void removeAttraction(Attraction attraction) {
        if (attraction == null || !attractions.contains(attraction)) {
            return;
        }
        attractions.remove(attraction);
        attraction.setPlaysite(null);
        capacity -= AttractionType.getCapacity(attraction.getName());
    }

    public void addKid(Kid kid, KidStatus status) {
        if (kid == null || kids.contains(kid)) {
            return;
        }
        kids.add(kid);
        kid.setStatus(status);
        kid.setTicketNumber(randomUUID().toString());
        kid.setTicketDateTime(LocalDateTime.now());
        kid.setPlaysite(this);
    }

    public void removeKid(Kid kid) {
        if (kid == null || !kids.contains(kid)) {
            return;
        }
        kids.remove(kid);
        kid.setPlaysite(null);
    }

    public List<Kid> getKidsQueue() {
        return kids.stream()
                .filter(kid -> WAITING.equals(kid.getStatus()))
                .toList();
    }

}
