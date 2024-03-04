package com.example.seminar8springAOP.models;

import jakarta.persistence.*;

import java.util.ArrayList;

@Entity
public class Performer {
    public enum Status {Working, Waiting}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Performer.Status status = Status.Waiting;
    @Column(nullable = true)
    private ArrayList<Long> commandsId = new ArrayList<>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setCommandsId(ArrayList<Long> commandsId) {
        this.commandsId = commandsId;
    }

    public ArrayList<Long> getCommandsId(){
        return commandsId;
    }

    @Override
    public String toString() { return this.id+" "+this.name+" "+this.status+" "+this.commandsId; }

    public static class PerformerBuilder {
        private final Performer performer = new Performer();
        public PerformerBuilder name(String name){
            performer.setName(name);
            return this;
        }
        public Performer build(){
            return performer;
        }

    }

}
