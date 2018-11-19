package com.codecool.airbnbmanager.model;

import com.codecool.airbnbmanager.util.Status;

import javax.persistence.*;
import java.util.Date;

@Entity
public class ToDo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @ManyToOne
    private Lodgings lodgings;
    @Temporal(TemporalType.DATE)
    private Date deadline;
    private String description;
    private long price;
    @Column(name = "Status")
    @Enumerated(EnumType.STRING)
    private Status status = Status.NEW;
    private boolean obsolete = false;

    public ToDo() {
    }

    public ToDo(String name, Lodgings lodgings, Date deadline, String description, long price) {
        this.name = name;
        this.lodgings = lodgings;
        this.deadline = deadline;
        this.description = description;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Lodgings getLodgings() {
        return lodgings;
    }

    public void setLodgings(Lodgings lodgings) {
        this.lodgings = lodgings;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public boolean isObsolete() {
        return obsolete;
    }

    public void setObsolete(boolean obsolete) {
        this.obsolete = obsolete;
    }
}
