package com.educative.ecommerce.model;

import javax.persistence.*;

@Entity
@Table(name = "myserver")
public class MyServer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String host;
    private int port;
    private int globalIndex;

    public MyServer(String host, int port, int globalIndex) {
        this.host = host;
        this.port = port;
        this.globalIndex = globalIndex;
    }

    public MyServer() {

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
        return "MyServer{" +
                "id=" + id +
                ", host='" + host + '\'' +
                ", port=" + port +
                '}';
    }

    public int getGlobalIndex() {
        return globalIndex;
    }

    public void setGlobalIndex(int globalIndex) {
        this.globalIndex = globalIndex;
    }
}
