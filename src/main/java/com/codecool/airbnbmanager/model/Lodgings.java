package com.codecool.airbnbmanager.model;

import com.codecool.airbnbmanager.model.builder.AddressBuilder;
import com.codecool.airbnbmanager.util.LodgingsType;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Lodgings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @Enumerated(value = EnumType.STRING)
    private LodgingsType lodgingsType;

    private long pricePerDay;
    private long electricityBill;
    private long gasBill;
    private long telecommunicationBill;
    private long cleaningCost;

    @OneToMany(mappedBy = "lodgings", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<ToDo> todos = new HashSet<>();

    @ManyToOne
    private User landlord;

    @ManyToOne
    private User propertyManager;

    @OneToOne(cascade = CascadeType.ALL)
    private AddressBuilder fullAddress;


//    @OneToMany(mappedBy = "tenantLodgings", fetch = FetchType.LAZY)
//    private Set<User> tenants = new HashSet<>();
//

    public Lodgings(
            String name, LodgingsType lodgingsType,
            long pricePerDay, long electricityBill, long gasBill, long telecommunicationBill, long cleaningCost,
            User landlord, AddressBuilder fullAddress
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
            User landlord, User propertyManager, AddressBuilder fullAddress
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

    public String getCountry() {
        return fullAddress.getCountry();
    }

    public void setCountry(String country) {
        this.fullAddress.setCountry(country);
    }

    public String getCity() {
        return fullAddress.getCity();
    }

    public void setCity(String city) {
        this.fullAddress.setCity(city);
    }

    public String getZipCode() {
        return fullAddress.getZipCode();
    }

    public void setZipCode(String zipCode) {
        this.fullAddress.setZipCode(zipCode);
    }

    public String getAddress() {
        return fullAddress.getAddress();
    }

    public void setAddress(String address) {
        this.fullAddress.setAddress(address);
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

//    public Set<User> getTenants() {
//        return tenants;
//    }
//
//    public void addTenant(Tenant tenant) {
//        tenants.add(tenant);
//    }
//
//    public void removeTenant(Tenant tenant) {
//        tenants.remove(tenant);
//    }

    public void addTodo(ToDo toDo){
        this.todos.add(toDo);
    }

    public Set<ToDo> getTodos() {
        return todos;
    }

    public void setTodos(Set<ToDo> todos) {
        this.todos = todos;
    }

    @Override
    public String toString() {
        return "Lodgings{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lodgingsType=" + lodgingsType +
                ", country='" + getCountry() + '\'' +
                ", city='" + getCity() + '\'' +
                ", zipCode='" + getZipCode() + '\'' +
                ", address='" + getAddress() + '\'' +
                ", pricePerDay=" + pricePerDay +
                ", electricityBill=" + electricityBill +
                ", gasBill=" + gasBill +
                ", telecommunicationBill=" + telecommunicationBill +
                ", cleaningCost=" + cleaningCost +
                ", landlord=" + landlord.getFullName() +
                '}';
    }
}


