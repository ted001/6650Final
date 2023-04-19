package com.educative.ecommerce.repository;

import com.educative.ecommerce.model.Server;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ServerRepository extends JpaRepository<Server, Integer> {
    Optional<Server> getServerByHostAndPort(String host, int port);

    Optional<Server> deleteServerByHostAndPort(String host, int port);
}
