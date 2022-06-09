package com.example.recyclerview.domain;


import com.example.recyclerview.domain.enumeration.TypeOfVote;

public class Vote {

    private Long id;

    private TypeOfVote typeOfVote;

    private Long project;

    private Long political;

    public Vote(Long id, TypeOfVote typeOfVote, Long project, Long political) {
        this.id = id;
        this.typeOfVote = typeOfVote;
        this.project = project;
        this.political = political;
    }

    public Vote() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TypeOfVote getTypeOfVote() {
        return typeOfVote;
    }

    public void setTypeOfVote(TypeOfVote typeOfVote) {
        this.typeOfVote = typeOfVote;
    }

    public Long getProject() {
        return project;
    }

    public void setProject(Long project) {
        this.project = project;
    }

    public Long getPolitical() {
        return political;
    }

    public void setPolitical(Long political) {
        this.political = political;
    }

    public String voteToPortuguese(){
        if (typeOfVote.equals(TypeOfVote.YES)) {
            return "Sim.";
        } else if(typeOfVote.equals(TypeOfVote.NO)){
            return "Não.";
        } else {
            return "Não votou.";
        }
    }

    @Override
    public String toString() {
        return  project + "\n"+
                "Voto: " +political+ " "+  voteToPortuguese() + "\n";
    }
}
