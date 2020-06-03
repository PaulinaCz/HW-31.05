package com.czerniecka.model;

public class Owner {

    public static final int ID_OF_NOT_PERSISTENT_PERSON = -1;

    private int id;
    private String name;
    private String sex;
    private String city;
    private String street;
    private String postCode;
    private Dog dog;

    public Owner(String name, String sex, String city,
                 String street, String postCode, Dog dog) {
        this.id = ID_OF_NOT_PERSISTENT_PERSON;
        this.name = name;
        this.sex = sex;
        this.city = city;
        this.street = street;
        this.postCode = postCode;
        this.dog = dog;
    }

    public Owner(int id, String name, String sex, String city,
                 String street, String postCode, Dog dog) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.city = city;
        this.street = street;
        this.postCode = postCode;
        this.dog = dog;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public Dog getDog() {
        return dog;
    }

    public void setDog(Dog dog) {
        this.dog = dog;
    }

    @Override
    public String toString() {
        return "Owner{" +
                "name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", postCode='" + postCode + '\'' +
                ", dog=" + dog +
                '}';
    }
}
