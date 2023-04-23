package com.educative.ecommerce.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Participants")
public class Participant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "participant")
    private Server server;

    private int state;

    private int port;

    public Participant(Server serverName, int state) {
        this.server = serverName;
        this.port = serverName.getPort();
        this.state = state;
    }

    public Server getServer() {
        return server;
    }

    public void setName(Server serverName) {
        this.server = serverName;
    }

    public int getPort() {
        return port;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
