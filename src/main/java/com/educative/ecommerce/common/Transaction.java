package com.educative.ecommerce.common;

import com.educative.ecommerce.common.command.Command;

import java.io.Serializable;

public class Transaction implements Serializable {

    private final long id;
    private final Command command;

    public Transaction(long id, Command command) {
        this.id = id;
        this.command = command;
    }

    public Transaction(Transaction other) {
        this.id = other.getId();
        this.command = other.getCommand();
    }

    public long getId() {
        return id;
    }

    public Command getCommand() {
        return command;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", command=" + command +
                '}';
    }
}