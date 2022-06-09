package com.example.recyclerview;

import android.content.Context;

import com.example.recyclerview.domain.Political;
import com.example.recyclerview.domain.PoliticalParty;
import com.example.recyclerview.domain.Project;
import com.example.recyclerview.domain.Vote;
import com.example.recyclerview.domain.enumeration.ElectivePositionType;
import com.example.recyclerview.domain.enumeration.TypeOfVote;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Data {
    private static Data instance = new Data();
    private Data(){ }
    public static Data getInstance(){
        return instance;
    }

    //Arrays
    private ArrayList<Political> politicals;
    private ArrayList<Political> senators;
    private ArrayList<Political> deputies;
    private ArrayList<Vote> votes;

    //Itens
    private Political political;
    private PoliticalParty politicalParty;
    private Project project;

    //
    private PoliticalDatabase database;

    public void createDatabase(Context context){
        database = new PoliticalDatabase(context);
        politicals = database.getPoliticalFromDB();
        senators = database.getPoliticalByTypeFromDB(ElectivePositionType.SENADOR);
        deputies = database.getPoliticalByTypeFromDB(ElectivePositionType.DEPUTADO_FEDERAL);
        votes = database.getVoteFromDB();

    }

    public ArrayList<Political> getPoliticals(){
        return politicals;
    }

    public ArrayList<Political> getSenators(){
        return senators;
    }
    public ArrayList<Political> getDeputies(){
        return deputies;
    }

    public ArrayList<Vote> getVotes(){
        return votes;
    }

    public PoliticalParty getPoliticalPartyFromId(long id){
        return database.getPoliticalPartyFromID(id);
    }

    public ArrayList<Vote> getVotesByPolitical(Long id){
        return database.getVotesFromPoliticalID(id);
    }

    public Political getPoliticalFromID(long id){
        return database.getPoliticalByIDFromDB(id);
    }

    public Project getProjectFromID(long id){
        return database.getProjectFromID(id);
    }

    public Political getDeputy(int pos){
        return deputies.get(pos);
    }

    public Vote getVote(int pos){
        return votes.get(pos);
    }

    public Political getSenator(int pos){
        return senators.get(pos);
    }

    public Political getPolitical(int pos){
        return politicals.get(pos);
    }

    public ArrayList<Political> getPoliticalByTypeAndName(ElectivePositionType type, String name){
        return database.getPoliticalByTypeAndNameFromDB(type, name);
    }

    public ArrayList<Political> getPoliticalByType(ElectivePositionType type){
        return database.getPoliticalByTypeFromDB(type);
    }

}
