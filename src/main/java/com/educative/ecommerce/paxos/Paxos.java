package com.educative.ecommerce.paxos;


import com.educative.ecommerce.common.Transaction;

import javax.persistence.*;

@Entity
public class Paxos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private long minTransaction;
    private @Transient Transaction transaction;

    public Paxos(int minTransaction, Transaction password) {
        this.minTransaction = minTransaction;
        this.transaction = password;
    }

    public Paxos() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public long getMinTransaction() {
        return minTransaction;
    }

    public void setMinTransaction(long minTransaction) {
        this.minTransaction = minTransaction;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }
}
