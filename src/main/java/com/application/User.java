package com.application;

import com.univocity.parsers.annotations.Parsed;

/**
 * This class represents a User object contained in each rows of the csv data set.
 */
public class User {

  @Parsed(index = 0)
  private String id;

  @Parsed
  private String first_name;

  @Parsed
  private String last_name;

  @Parsed
  private String address1;

  @Parsed
  private String address2;

  @Parsed
  private String zip;

  @Parsed
  private String city;

  @Parsed
  private String state_long;

  @Parsed
  private String state;

  @Parsed
  private String phone;

  @Parsed
  private String company;

  @Parsed
  private String email;

  public User(String id, String fname, String lname, String company, String email, String address,
              String apartmentNumber, String zip, String city, String state_long, String state,
              String number) {
    this.id = id;
    this.first_name = fname;
    this.last_name = lname;
    this.company = company;
    this.email = email;
    this.address1 = address;
    this.address2 = apartmentNumber;
    this.zip = zip;
    this.city = city;
    this.state_long = state_long;
    this.state = state;
    this.phone = number;
  }

  public User() {
    //do nothing
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getAddress2() {
    return address2;
  }

  public void setAddress2(String address2) {
    this.address2 = address2;
  }

  public String getZip() {
    return zip;
  }

  public void setZip(String zip) {
    this.zip = zip;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getState_long() {
    return state_long;
  }

  public void setState_long(String state_long) {
    this.state_long = state_long;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getFirst_name() {
    return first_name;
  }

  public void setFirst_name(String first_name) {
    this.first_name = first_name;
  }

  public String getLast_name() {
    return last_name;
  }

  public void setLast_name(String last_name) {
    this.last_name = last_name;
  }

  public String getAddress() {
    return address1;
  }

  public void setAddress(String address) {
    this.address1 = address;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getCompany() {
    return company;
  }

  public void setCompany(String company) {
    this.company = company;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Override
  public String toString() {
    return this.getId() + " " + this.getFirst_name() + " " + this.getLast_name() + " " +
            this.getCompany() + " " + this.getAddress() + " " + this.getAddress2() + " " +
            this.getZip() + " " + this.getCity() + " " + this.getState_long() + " " +
            this.getState() + " " + this.getPhone();
  }
}
