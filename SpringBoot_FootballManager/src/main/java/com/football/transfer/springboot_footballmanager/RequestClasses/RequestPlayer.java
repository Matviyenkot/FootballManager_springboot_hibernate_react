package com.football.transfer.springboot_footballmanager.RequestClasses;

import java.time.LocalDate;

public class RequestPlayer {

    private String name;

    private LocalDate birthDate;

    public RequestPlayer() {
    }

    public RequestPlayer(String name, LocalDate birthDate) {
        this.name = name;
        this.birthDate = birthDate;
    }

    public int getPlayerAge(){

        int age = LocalDate.now().getYear() - birthDate.getYear();

        return age;
    }

    public int getPlayerMonthsOfExperience(){

        LocalDate startPlaying = birthDate.plusYears(17);

        int monthsOfExperience = (LocalDate.now().getYear() - startPlaying.getYear()) * 12
                + ( Math.abs (LocalDate.now().getMonthValue() -startPlaying.getMonthValue() ) );

        return monthsOfExperience;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
}
