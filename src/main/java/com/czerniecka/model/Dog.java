package com.czerniecka.model;

public class Dog {

    public static final int ID_OF_NOT_PERSISTENT_DOG = -1;

    private int id;
    private String dogName;
    private String breed;

    public Dog() {
    }

    public Dog(String dogName, String breed) {
        this.id = ID_OF_NOT_PERSISTENT_DOG;
        this.dogName = dogName;
        this.breed = breed;
    }

    public Dog(int id, String dogName, String breed) {
        this.id = id;
        this.dogName = dogName;
        this.breed = breed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDogName() {
        return dogName;
    }

    public void setDogName(String dogName) {
        this.dogName = dogName;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "dogName='" + dogName + '\'' +
                ", breed='" + breed + '\'' +
                '}';
    }
}
