package com.educative.ecommerce.repository;

import com.educative.ecommerce.paxos.Paxos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaxosRepository extends JpaRepository<Paxos, Integer> {
    Paxos getPaxosById(int id);
}
