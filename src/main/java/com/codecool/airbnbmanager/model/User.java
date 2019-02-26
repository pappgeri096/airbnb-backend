package com.codecool.airbnbmanager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", unique = true)
    private long id;

    @NotBlank
    @Size(min = 4,max = 20)
    private String username;

    @NotBlank
    @Size(min = 4,max = 20)
    private String firstName;

    @NotBlank
    @Size(min = 4,max = 20)
    private String surname;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank
    @Size(min = 12)
    private String phoneNumber;

    @NotBlank
    private String password;


    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    private Address fullAddress;

    @OneToMany(mappedBy = "propertyManager", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Lodgings> propertyManagerLodgings = new HashSet<>();

    @OneToMany(mappedBy = "landlord", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Lodgings> landlordLodgings = new HashSet<>();

    @OneToMany(mappedBy = "tenants", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Lodgings> tenantLodgings = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Pending> pendings = new HashSet<>();

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
            Address fullAddress,
            String password
    ) {
        this.username = username;
        this.firstName = firstName;
        this.surname = surname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.fullAddress = fullAddress;
        this.password = password;
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

    public void setId(long id) {
        this.id = id;
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
    public Address getFullAddress() {
        return fullAddress;
    }

    public void setFullAddress(Address fullAddress) {
        this.fullAddress = fullAddress;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Lodgings> getTenantLodgings() {
        return tenantLodgings;
    }

    public void setTenantLodgings(Set<Lodgings> tenantLodgings) {
        this.tenantLodgings = tenantLodgings;
    }

    public Set<Pending> getPendings() {
        return pendings;
    }

    public void setPendings(Set<Pending> pendings) {
        this.pendings = pendings;
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
                '}';
    }

    public String getName() {
        return firstName+" "+surname;
    }
}
