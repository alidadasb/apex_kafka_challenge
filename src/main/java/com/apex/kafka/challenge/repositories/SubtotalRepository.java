package com.apex.kafka.challenge.repositories;

import com.apex.kafka.challenge.entities.SubTotalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubtotalRepository extends JpaRepository<SubTotalEntity, String> {
}
