package com.example.demo.model;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public enum EntityManagerProvider {

    INSTANCE;

    private EntityManagerFactory emf=null;

    EntityManagerProvider(){
        emf = Persistence.createEntityManagerFactory("MyApplicationPU");
    }
}
