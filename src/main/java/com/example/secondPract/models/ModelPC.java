package com.example.secondPract.models;

import javax.persistence.*;

@Entity
@Table(name = "modelpc")
public class ModelPC {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id_pc;
    private String name, cpu, ram;

    @Column(name = "there_is")
    private Boolean has = Boolean.TRUE;
    private int price;// = int.ZERO;

    public long getId_pc() {
        return id_pc;
    }

    public void setId_pc(long id_pc) {
        this.id_pc = id_pc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public Boolean getHas() {
        return has;
    }

    public void setHas(Boolean has) {
        this.has = has;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    
    public ModelPC(String name, String cpu, String ram, Boolean has, int price) {
        this.name = name;
        this.cpu = cpu;
        this.ram = ram;
        this.has = has;
        this.price = price;
    }

    public ModelPC() {
    }
}
