package com.example.foodstore;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;

public class Burger implements Serializable {
  private String country;

  private String img;

  private String dsc;

  private Integer rate;

  private Integer price;

  private String name;

  private String id;

  public String getCountry() {
    return this.country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getImg() {
    return this.img;
  }

  public void setImg(String img) {
    this.img = img;
  }

  public String getDsc() {
    return this.dsc;
  }

  public void setDsc(String dsc) {
    this.dsc = dsc;
  }

  public Integer getRate() {
    return this.rate;
  }

  public void setRate(Integer rate) {
    this.rate = rate;
  }

  public Integer getPrice() {
    return this.price;
  }

  public void setPrice(Integer price) {
    this.price = price;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getId() {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
  }
}
