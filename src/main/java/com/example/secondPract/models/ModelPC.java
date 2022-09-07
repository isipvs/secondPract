package com.example.secondPract.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
public class ModelPC {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id_pc;
    private String name, cpu, ram;
    private Boolean there_is;
    private BigDecimal price;

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

    public Boolean getThere_is() {
        return there_is;
    }

    public void setThere_is(Boolean there_is) {
        this.there_is = there_is;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    
    public ModelPC(String name, String cpu, String ram, Boolean there_is, BigDecimal price) {
        this.name = name;
        this.cpu = cpu;
        this.ram = ram;
        this.there_is = there_is;
        this.price = price;
    }

    public ModelPC() {
    }
}
