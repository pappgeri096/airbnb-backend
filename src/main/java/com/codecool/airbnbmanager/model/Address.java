package com.codecool.airbnbmanager.model;

import com.codecool.airbnbmanager.model.Lodgings;
import com.codecool.airbnbmanager.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 4)
    @JsonProperty("country")
    private String country;

    @NotBlank
    @JsonProperty("city")
    private String city;

    @NotBlank
    @JsonProperty("zipCode")
    private String zipCode;

    @NotBlank
    @JsonProperty("address")
    private String address;

    @JsonIgnore
    @OneToMany(mappedBy = "fullAddress", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<User> userSet = new HashSet<>();

    @JsonIgnore
    @OneToOne(mappedBy = "fullAddress", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    Lodgings lodgings;


    public Address() {
    }

    public Address(String country, String city, String zipCode, String address) {
        this.country = country;
        this.city = city;
        this.zipCode = zipCode;
        this.address = address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Set<User> getUserSet() {
        return userSet;
    }

    public void setUserSet(Set<User> ownerList) {
        this.userSet = ownerList;
    }

    public Lodgings getLodgings() {
        return lodgings;
    }

    public void setLodgings(Lodgings lodgings) {
        this.lodgings = lodgings;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
