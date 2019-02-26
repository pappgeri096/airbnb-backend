package com.codecool.airbnbmanager.model;

import javax.persistence.*;

@Entity
public class Pending {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    private User user;
    @ManyToOne
    private Lodgings lodgings;
    private boolean accepted = false;

    public Pending() {
    }

    public Pending(User user, Lodgings lodgings, boolean accepted) {
        this.user = user;
        this.lodgings = lodgings;
        this.accepted = accepted;
    }

    public Pending(User user, Lodgings lodgings) {
        this.user = user;
        this.lodgings = lodgings;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Lodgings getLodgings() {
        return lodgings;
    }

    public void setLodgings(Lodgings lodgings) {
        this.lodgings = lodgings;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
