package com.football.transfer.springboot_footballmanager.RequestClasses;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;

public class RequestTeam {

    private int id;

    private String name;

    private double commission;

    private BigDecimal finances;

    public RequestTeam() {
    }

    public RequestTeam(String name, double commission, BigDecimal finances) {
        this.name = name;
        this.commission = commission;
        this.finances = finances;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCommission() {
        return commission;
    }

    public void setCommission(double commission) {
        this.commission = commission;
    }

    public BigDecimal getFinances() {
        return finances;
    }

    @JsonIgnore
    public double getDoubleFinances(){

        double doubleFinances = finances.doubleValue();
        return doubleFinances;
    }

    public void setFinances(double finances) {
        this.finances = BigDecimal.valueOf(finances);
    }
}
