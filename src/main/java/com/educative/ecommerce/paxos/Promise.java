package com.educative.ecommerce.paxos;

import com.educative.ecommerce.common.Transaction;

import java.util.LinkedHashMap;

public class Promise{
    private boolean promise;
    private Transaction transaction;

    public Promise(boolean promise, Transaction transaction) {
        this.promise = promise;
        this.transaction = transaction;
    }

    public Promise(LinkedHashMap<String, Object> map) {
        this.promise = (boolean) map.get("promise");
        this.transaction = (Transaction) map.get("transaction");
    }

    public Promise() {
    }

    public boolean isPromise() {
        return promise;
    }

    public void setPromise(boolean promise) {
        this.promise = promise;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    @Override
    public String toString() {
        return "Promise{" +
                "promise=" + promise +
                ", transaction=" + transaction +
                '}';
    }
}
