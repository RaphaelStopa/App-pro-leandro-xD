package com.example.recyclerview.domain;

public class PoliticalParty {

    private Long id;

    private String name;

    private String acronym;

    private boolean deleted = false;

    public PoliticalParty(Long id, String name, String acronym, boolean deleted) {
        this.id = id;
        this.name = name;
        this.acronym = acronym;
        this.deleted = deleted;
    }

    public PoliticalParty() {
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

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return name + "     "+ acronym;
    }
}
