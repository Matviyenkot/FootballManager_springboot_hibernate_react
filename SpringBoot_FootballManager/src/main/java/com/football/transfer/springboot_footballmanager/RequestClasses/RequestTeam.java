package com.football.transfer.springboot_footballmanager.RequestClasses;

public class RequestTeam {

    private int id;

    private String name;

    private double commission;

    private double finances;

    public RequestTeam() {
    }

    public RequestTeam(String name, double commission, double finances) {
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

    public double getFinances() {
        return finances;
    }

    public void setFinances(double finances) {
        this.finances = finances;
    }
}
