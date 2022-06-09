package com.example.recyclerview.domain;



import android.content.Intent;

import com.example.recyclerview.domain.enumeration.ElectivePositionType;

import java.util.HashSet;
import java.util.Set;

public class Political {

    private Long id;

    private String name;

    // I did it by enum because at first, only these two make sense. However, in the future, if applicable, there may be other
    private ElectivePositionType electivePositionType;

    private Long politicalParty;

    //soft delete
    private boolean deleted = false;



    public Political(Long id, String name, ElectivePositionType electivePositionType, Long politicalParty, boolean deleted) {
        this.id = id;
        this.name = name;
        this.electivePositionType = electivePositionType;
        this.politicalParty = politicalParty;
        this.deleted = deleted;
    }

    public Political() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ElectivePositionType getElectivePositionType() {
        return electivePositionType;
    }

    public void setElectivePositionType(ElectivePositionType electivePositionType) {
        this.electivePositionType = electivePositionType;
    }

    public Long getPoliticalParty() {
        return politicalParty;
    }

    public void setPoliticalParty(Long politicalParty) {
        this.politicalParty = politicalParty;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return  '\n' + name + '\n' + '\n' + "Partido: " + politicalParty;
    }
}
