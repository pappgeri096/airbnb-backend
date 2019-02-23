package com.codecool.airbnbmanager.model;

import com.codecool.airbnbmanager.util.enums.LodgingsType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Lodgings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank
    private String name;
    @Enumerated(value = EnumType.STRING)
    @JsonProperty()
    @NotBlank
    private LodgingsType lodgingsType;

    @NotBlank
    private long pricePerDay;
    @NotBlank
    private long electricityBill;
    @NotBlank
    private long gasBill;
    @NotBlank
    private long telecommunicationBill;
    @NotBlank
    private long cleaningCost;

    @OneToMany(mappedBy = "lodgings", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<ToDo> todos = new HashSet<>();

    @JsonIgnore
    @ManyToOne
    private User landlord;

    @JsonIgnore
    @ManyToOne
    private User propertyManager;


    @OneToOne(cascade = CascadeType.ALL)
    private Address fullAddress;

    @JsonIgnore
    @ManyToOne
    private User tenants;

    public Lodgings(
            String name, LodgingsType lodgingsType,
            long pricePerDay, long electricityBill, long gasBill, long telecommunicationBill, long cleaningCost,
            User landlord, Address fullAddress
    ) {
        this.name = name;
        this.lodgingsType = lodgingsType;
        this.pricePerDay = pricePerDay;
        this.electricityBill = electricityBill;
        this.gasBill = gasBill;
        this.telecommunicationBill = telecommunicationBill;
        this.cleaningCost = cleaningCost;
        this.landlord = landlord;
        this.fullAddress = fullAddress;
    }

    public Lodgings(
            String name, LodgingsType lodgingsType,
            long pricePerDay, long electricityBill, long gasBill, long telecommunicationBill, long cleaningCost,
            Address fullAddress
    ) {
        this.name = name;
        this.lodgingsType = lodgingsType;
        this.pricePerDay = pricePerDay;
        this.electricityBill = electricityBill;
        this.gasBill = gasBill;
        this.telecommunicationBill = telecommunicationBill;
        this.cleaningCost = cleaningCost;
        this.fullAddress = fullAddress;
    }




    public Lodgings(
            String name, LodgingsType lodgingsType,
            long pricePerDay, long electricityBill, long gasBill, long telecommunicationBill, long cleaningCost,
            User landlord, User propertyManager, Address fullAddress
    ) {
        this.name = name;
        this.lodgingsType = lodgingsType;
        this.pricePerDay = pricePerDay;
        this.electricityBill = electricityBill;
        this.gasBill = gasBill;
        this.telecommunicationBill = telecommunicationBill;
        this.cleaningCost = cleaningCost;
        this.landlord = landlord;
        this.propertyManager = propertyManager;
        this.fullAddress = fullAddress;
    }

    public Lodgings() {
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

    public LodgingsType getLodgingsType() {
        return lodgingsType;
    }

    public void setLodgingsType(LodgingsType lodgingsType) {
        this.lodgingsType = lodgingsType;
    }

    public long getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(long pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public long getElectricityBill() {
        return electricityBill;
    }

    public void setElectricityBill(long electricityBill) {
        this.electricityBill = electricityBill;
    }

    public long getGasBill() {
        return gasBill;
    }

    public void setGasBill(long gasBill) {
        this.gasBill = gasBill;
    }

    public long getTelecommunicationBill() {
        return telecommunicationBill;
    }

    public void setTelecommunicationBill(long telecommunicationBill) {
        this.telecommunicationBill = telecommunicationBill;
    }

    public long getCleaningCost() {
        return cleaningCost;
    }

    public void setCleaningCost(long cleaningCost) {
        this.cleaningCost = cleaningCost;
    }

    public Set<ToDo> getTodos() {
        return todos;
    }

    public void setTodos(Set<ToDo> todos) {
        this.todos = todos;
    }

    public User getLandlord() {
        return landlord;
    }

    public void setLandlord(User landlord) {
        this.landlord = landlord;
    }

    public User getPropertyManager() {
        return propertyManager;
    }

    public void setPropertyManager(User propertyManager) {
        this.propertyManager = propertyManager;
    }

    public Address getFullAddress() {
        return fullAddress;
    }

    public void setFullAddress(Address fullAddress) {
        this.fullAddress = fullAddress;
    }

    public User getTenants() {
        return tenants;
    }

    public void setTenants(User tenants) {
        this.tenants = tenants;
    }
}


