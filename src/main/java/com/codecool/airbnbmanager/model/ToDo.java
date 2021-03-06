package com.codecool.airbnbmanager.model;

import com.codecool.airbnbmanager.util.enums.ToDoStatusType;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
public class ToDo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    @JsonIgnore
    @ManyToOne
    private Lodgings lodgings;
    @Temporal(TemporalType.DATE)
    private Date deadline;
    private String description;
    private long price;
    @Column(name = "Status")
    @Enumerated(EnumType.STRING)
    private ToDoStatusType status = ToDoStatusType.NEW;
    @JsonIgnore
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

    public ToDo(String name, Date deadline, String description, long price) {
        this.name = name;
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

    public ToDoStatusType getStatus() {
        return status;
    }

    public void setStatus(ToDoStatusType status) {
        this.status = status;
    }

    public boolean isObsolete() {
        return obsolete;
    }

    public void setObsolete(boolean obsolete) {
        this.obsolete = obsolete;
    }

    @Override
    public String toString() {
        return "ToDo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", deadline=" + deadline +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", status=" + status +
                ", obsolete=" + obsolete +
                '}';
    }
}
