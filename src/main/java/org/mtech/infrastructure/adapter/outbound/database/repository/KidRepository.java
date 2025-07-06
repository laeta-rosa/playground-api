package org.mtech.infrastructure.adapter.outbound.database.repository;

import org.mtech.domain.Kid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface KidRepository extends JpaRepository<Kid, Long> {

    @Query("SELECT k" +
            " FROM Kid k" +
            " WHERE k.playsite.id = :playsiteId" +
            " AND k.status = 'WAITING'" +
            " ORDER BY k.ticketDateTime ASC" +
            " LIMIT 1"
    )
    Optional<Kid> getFirstInWaitingLine(Long playsiteId);

    Optional<Kid> findByTicketNumber(String ticketNumber);

}