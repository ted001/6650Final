package com.educative.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.educative.ecommerce.model.Participant;

public interface TwoPCRepository extends JpaRepository<Participant, Integer> {
    Participant getParticipantById(int id);
}
