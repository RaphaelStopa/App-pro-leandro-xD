package com.example.recyclerview.domain;

public class Project {

    private Long id;

    private String numberOfProject;

    private String name;

    private String brief;

    private boolean wasApproved;

    public Project() {
    }

    public Project(Long id, String numberOfProject, String name, String brief, boolean wasApproved) {
        this.id = id;
        this.numberOfProject = numberOfProject;
        this.name = name;
        this.brief = brief;
        this.wasApproved = wasApproved;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumberOfProject() {
        return numberOfProject;
    }

    public void setNumberOfProject(String numberOfProject) {
        this.numberOfProject = numberOfProject;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public boolean isWasApproved() {
        return wasApproved;
    }

    public void setWasApproved(boolean wasApproved) {
        this.wasApproved = wasApproved;
    }

//    public String approvedToPortugues(){
//
//        return this.isWasApproved() == true ? "Aprovado." : "Rejeitado.";
//    }


    @Override
    public String toString() {
        return  '\n' + "Número: " + numberOfProject + '\n' +
                '\n' +
                "Nome: " + name + '\n' +
                '\n' +
                "Resumo: " + brief + '\n' +
                '\n' +
                "Status do projeto:  " + isWasApproved()+
                '\n';
    }
}
