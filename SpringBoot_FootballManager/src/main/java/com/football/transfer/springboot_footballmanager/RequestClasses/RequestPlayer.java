package com.football.transfer.springboot_footballmanager.RequestClasses;

public class RequestPlayer {

    private String name;

    private int age;

    private int monthsOfExperience;

    public RequestPlayer() {
    }

    public RequestPlayer(String name, int age, int monthsOfExperience) {
        this.name = name;
        this.age = age;
        this.monthsOfExperience = monthsOfExperience;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getMonthsOfExperience() {
        return monthsOfExperience;
    }

    public void setMonthsOfExperience(int monthsOfExperience) {
        this.monthsOfExperience = monthsOfExperience;
    }
}
