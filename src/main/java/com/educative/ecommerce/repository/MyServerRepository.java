package com.educative.ecommerce.repository;

import com.educative.ecommerce.model.MyServer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyServerRepository extends JpaRepository<MyServer, Integer> {
    MyServer getMyServerById(int id);
}