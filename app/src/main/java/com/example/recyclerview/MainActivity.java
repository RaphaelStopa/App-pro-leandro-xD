package com.example.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.recyclerview.domain.Political;
import com.example.recyclerview.domain.PoliticalParty;
import com.example.recyclerview.domain.Project;
import com.example.recyclerview.domain.Vote;
import com.example.recyclerview.domain.enumeration.ElectivePositionType;
import com.example.recyclerview.domain.enumeration.TypeOfVote;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PoliticalDatabase database = new PoliticalDatabase(MainActivity.this);

        Project projectOne = new Project(null, "44/2021", "Aumento do Fundão Eleitoral.",
                "Aumento do fundo eleitoralde R$ 5,7 bilhões.", true);
        Project projectTwo = new Project(null, "PL 4199/2020", "BR do Mar.",
                "O BR do Mar busca incentivar a navegação de cabotagem.", true);
        Project projectTree = new Project(null, "PLS 261/2018", "Marco Legal das Ferrovias.",
                "Autoriza a exploração de serviços de transporte ferroviário pelo setor privado.", false);
        Project projectFour = new Project(null, "MPV 1085/2021", "Mudança nos Serviços de Cartórios.",
                "Cria o Sistema Eletrônico de Registros Públicos.", false);
        Project projectFive = new Project(null, "PL 442/1991", "Marco Legal dos Jogos.",
                "Pretende regulamentar a exploração dos seguintes \"jogos de fortuna\": jogos de cassino; jogo de bingo; jogos lotéricos federais e estaduais; apostas de quotas fixas; apostas eletrônicas.", true);


        Long projectOneId = database.createProjectInDB(projectOne);
        Long projectTwoId = database.createProjectInDB(projectTwo);
        Long projectTreeId = database.createProjectInDB(projectTree);
        Long projectFourId = database.createProjectInDB(projectFour);
        Long projectFiveId = database.createProjectInDB(projectFive);


        PoliticalParty politicalParty = new PoliticalParty(null, "Partido dos Enroladores Profissionais.",
                "PDRP", false );

        Long politicalPartyId = database.createPoliticalPartyInDB(politicalParty);
        politicalParty.setId(politicalPartyId);


        Political politicalOne = new Political(null, "Amarildo Santos",
                ElectivePositionType.SENADOR, politicalPartyId, false);

        Political politicalTwo = new Political(null, "Marilda do Povos",
                ElectivePositionType.DEPUTADO_FEDERAL, politicalPartyId, false);

        Political politicalTree = new Political(null, "Andrade Lero",
                ElectivePositionType.DEPUTADO_FEDERAL, politicalPartyId, false);

        Political politicalFour = new Political(null, "Elenice do Capelario",
                ElectivePositionType.DEPUTADO_FEDERAL, politicalPartyId, false);

        Political politicalFive = new Political(null, "Raphael Stopa",
                ElectivePositionType.SENADOR, politicalPartyId, false);


        Long politicalIdOne = database.createPoliticalInDB(politicalOne);
        Long politicalIdTwo = database.createPoliticalInDB(politicalTwo);
        Long politicalIdTree = database.createPoliticalInDB(politicalTree);
        Long politicalIdFour = database.createPoliticalInDB(politicalFour);
        Long politicalIdFive = database.createPoliticalInDB(politicalFive);



        Vote voteOne = new Vote(null, TypeOfVote.NO, projectOneId, politicalIdOne);
        Vote voteTwo = new Vote(null, TypeOfVote.NO, projectTwoId, politicalIdOne);
        Vote voteTree = new Vote(null, TypeOfVote.YES, projectTreeId, politicalIdOne);

        Vote voteFour = new Vote(null, TypeOfVote.YES, projectOneId, politicalIdTwo);
        Vote voteFive = new Vote(null, TypeOfVote.YES, projectTwoId, politicalIdTwo);
        Vote voteSix = new Vote(null, TypeOfVote.YES, projectTreeId, politicalIdTwo);

        Vote voteSeven = new Vote(null, TypeOfVote.YES, projectOneId, politicalIdTree);
        Vote voteEight = new Vote(null, TypeOfVote.NO, projectTwoId, politicalIdTree);
        Vote voteNine = new Vote(null, TypeOfVote.ABSTAINED, projectTreeId, politicalIdTree);

        Vote voteTen = new Vote(null, TypeOfVote.NO, projectTwoId, politicalIdFour);
        Vote voteEleven = new Vote(null, TypeOfVote.ABSTAINED, projectTreeId, politicalIdFour);
        Vote voteTwelve = new Vote(null, TypeOfVote.NO, projectOneId, politicalIdFour);

        Vote voteThirteen = new Vote(null, TypeOfVote.NO, projectTwoId, politicalIdFive);
        Vote voteFourteen = new Vote(null, TypeOfVote.ABSTAINED, projectTreeId, politicalIdFive);
        Vote voteFifteen = new Vote(null, TypeOfVote.NO, projectOneId, politicalIdFive);

        database.createVoteInDB(voteOne);
        database.createVoteInDB(voteTwo);
        database.createVoteInDB(voteTree);
        database.createVoteInDB(voteFour);
        database.createVoteInDB(voteFive);
        database.createVoteInDB(voteSix);
        database.createVoteInDB(voteSeven);
        database.createVoteInDB(voteEight);
        database.createVoteInDB(voteNine);
        database.createVoteInDB(voteTen);
        database.createVoteInDB(voteEleven);
        database.createVoteInDB(voteTwelve);
        database.createVoteInDB(voteThirteen);
        database.createVoteInDB(voteFourteen);
        database.createVoteInDB(voteFifteen);

        ArrayList<Project> projects = database.getProjectFromDB();
        for (Project c: projects){
            Log.v("SQLDatabase", "Projects === " + c.isWasApproved() + "]:");
        }
//
//
//        ArrayList<PoliticalParty> politicalParties = database.getPoliticalPartyFromDB();
//        for (PoliticalParty proj: politicalParties){
//            Log.v("SQLDatabase", "parties ==== " + proj );
//        }
//
//
//        ArrayList<Political> politicals = database.getPoliticalFromDB();
//        for (Political proj: politicals){
//            Log.v("SQLDatabase", "political === " + proj.getId() );
//        }
//
//        ArrayList<Vote> votes = database.getVoteFromDB();
//        for (Vote proj: votes){
//            Log.v("SQLDatabase", "vote === " + proj.getId() );
//        }
//
//        System.out.println("Political 01 ==== " + database.getPoliticalByIDFromDB(1l));
//        System.out.println("Votes 01 ==== " + database.getVotesFromPoliticalID(1l));
//        System.out.println("Type 01 ==== " + database.getPoliticalByTypeFromDB(ElectivePositionType.SENADOR));
//        System.out.println("Project 01 ==== " + database.getProjectFromID(3l));
//        System.out.println("Project 01 ==== " + database.getProjectFromID(1l));
//        System.out.println("Political Party 01 ==== " + database.getPoliticalPartyFromID(1l));
        System.out.println("Type 01 and name ==== " + database.getPoliticalByTypeAndNameFromDB(ElectivePositionType.SENADOR, "Ra"));



    }

    public void goToSenatorOnClick (View v) {
        Intent intent = new Intent(MainActivity.this, PoliticalActivity.class);
        intent.putExtra("political", "SENADOR");
        startActivity(intent);
    }


    public void goToDeputyOnClick (View v) {
        Intent intent = new Intent(MainActivity.this, PoliticalActivity.class);
        intent.putExtra("political", "DEPUTADO_FEDERAL");
        startActivity(intent);
    }
}