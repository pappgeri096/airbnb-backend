package com.codecool.airbnbmanager.model;

import com.codecool.airbnbmanager.model.builder.AddressBuilder;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", unique = true)
    private long id;
    private String username;
    private String firstName;
    private String surname;
    private String email;
    private String phoneNumber;

    @JsonIgnore
    private String passwordHash;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    private AddressBuilder fullAddress;

    @OneToMany(mappedBy = "propertyManager", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Lodgings> propertyManagerLodgings = new HashSet<>();

    @OneToMany(mappedBy = "landlord", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Lodgings> landlordLodgings = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public User() {

    }

    public User(
            String username,
            String firstName,
            String surname,
            String email,
            String phoneNumber,
            String passwordHash,
            AddressBuilder fullAddress
    ) {
        this.username = username;
        this.firstName = firstName;
        this.surname = surname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.passwordHash = passwordHash;
        this.fullAddress = fullAddress;
    }

    public long getId() {
        return id;
    }

    @JsonIgnore
    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public Set<Lodgings> getPropertyManagerLodgings() {
        return propertyManagerLodgings;
    }

    public void setPropertyManagerLodgings(Set<Lodgings> propertyManagerLodgings) {
        this.propertyManagerLodgings = propertyManagerLodgings;
    }

    public Set<Lodgings> getLandlordLodgings() {
        return landlordLodgings;
    }

    public void setLandlordLodgings(Set<Lodgings> landlordLodgings) {
        this.landlordLodgings = landlordLodgings;
    }

    @JsonIgnore
    public String getFullName() {
        return getFirstName() + " " + getSurname();
    }

    @JsonIgnore
    public AddressBuilder getFullAddress() {
        return fullAddress;
    }

    public void setFullAddress(AddressBuilder fullAddress) {
        this.fullAddress = fullAddress;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", country='" + getCountry() + '\'' +
                ", city='" + getCity() + '\'' +
                ", zipCode='" + getZipCode() + '\'' +
                ", address='" + getAddress() + '\'' +
                ", passwordHash='" + passwordHash + '\'' +
                '}';
    }
}
