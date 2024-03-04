package com.example.seminar8springAOP.models;

import jakarta.persistence.*;

import java.util.ArrayList;

@Entity
public class Command {

    public enum Status {Running, Completed, Disabled}
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String description;

    @Column(nullable = true)
    private Status status = Status.Disabled;

    @Column(nullable = true)
    private String runningDate = "no Date";
    @Column(nullable = true)
    private ArrayList<Long> performersId = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getRunningDate() {
        return runningDate;
    }

    public void setRunningDate(String runningDate) {
        this.runningDate = runningDate;
    }
    public ArrayList<Long> getPerformersId() {
        return performersId;
    }
    public void setPerformersId(ArrayList<Long> performersId) {
        this.performersId = performersId;
    }

    @Override
    public String toString(){
        return this.id+" "+this.description+" "+this.status+" "+this.runningDate+" "+this.performersId;
    }
    public static class CommandBuilder {
        private final Command command = new Command();
        public CommandBuilder description(String description){
            command.setDescription(description);
            return this;
        }
        public Command build(){
            return command;
        }

    }

}
