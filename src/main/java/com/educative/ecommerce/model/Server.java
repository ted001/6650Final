package com.educative.ecommerce.model;

import javax.persistence.*;

@Entity
@Table(name = "servers")
public class Server {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String host;
    private int port;
    private long raTimestamp;

    public Server(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public Server() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public String toString() {
        return "Server{" +
                "id=" + id +
                ", host='" + host + '\'' +
                ", port=" + port +
                '}';
    }

    public long getRaTimestamp() {
        return raTimestamp;
    }

    public void setRaTimestamp(long raTimestamp) {
        this.raTimestamp = raTimestamp;
    }
}

