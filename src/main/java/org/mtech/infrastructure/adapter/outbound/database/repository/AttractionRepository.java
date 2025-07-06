package org.mtech.infrastructure.adapter.outbound.database.repository;

import org.mtech.domain.Attraction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttractionRepository extends JpaRepository<Attraction, Long> {}