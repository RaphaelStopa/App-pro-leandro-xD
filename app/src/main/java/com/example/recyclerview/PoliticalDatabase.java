package com.example.recyclerview;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.recyclerview.domain.Political;
import com.example.recyclerview.domain.PoliticalParty;
import com.example.recyclerview.domain.Project;
import com.example.recyclerview.domain.Vote;
import com.example.recyclerview.domain.enumeration.ElectivePositionType;
import com.example.recyclerview.domain.enumeration.TypeOfVote;

import java.util.ArrayList;

public class PoliticalDatabase extends SQLiteOpenHelper {
    private static final String DB_NAME = "political.sqlite";
    private static final int DB_VERSION = 1;
    private Context context;

    public PoliticalDatabase(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    // TODO tive dificuldade com a chave estrangeira no sqlite. Então improvisei.
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String queryProject = "CREATE TABLE if not exists Project(" +
                "    id INTEGER PRIMARY KEY autoincrement," +
                "    numberOfProject TEXT," +
                "    name TEXT," +
                "    brief TEXT," +
                "    wasApproved BIT" +
                ");";
        sqLiteDatabase.execSQL(queryProject);


        String queryPoliticalParty = "CREATE TABLE if not exists PoliticalParty(" +
                "    id INTEGER PRIMARY KEY autoincrement," +
                "    name TEXT," +
                "    acronym VARCHAR(10)," +
                "    deleted BIT" +
                ");";
        sqLiteDatabase.execSQL(queryPoliticalParty);



       String queryPolitical = "CREATE TABLE if not exists Political(" +
                "    id INTEGER PRIMARY KEY autoincrement," +
                "    name TEXT," +
                "    electivePositionType TEXT CHECK(electivePositionType IN ('SENADOR','DEPUTADO_FEDERAL')), " +
                "    politicalParty INTEGER," +
                "    deleted BIT" +
                ");" ;

        sqLiteDatabase.execSQL(queryPolitical);


        String queryVote = "CREATE TABLE if not exists Vote(" +
                "    id INTEGER PRIMARY KEY autoincrement," +
                "    typeOfVote TEXT CHECK( typeOfVote IN ('YES','NO','ABSTAINED'))," +
                "    project INTEGER," +
                "    political  INTEGER" +
                ");";

        sqLiteDatabase.execSQL(queryVote);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public long createProjectInDB(Project project){
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("numberOfProject", project.getNumberOfProject());
        values.put("name", project.getName());
        values.put("brief", project.getBrief());
        values.put("wasApproved", project.isWasApproved());
        long id = database.insert("project", null, values);
        database.close();
        return id;
    }



    public ArrayList<Project> getProjectFromDB(){
        ArrayList<Project> projects = new ArrayList<>();

        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.query("project", null, null, null,
                null, null, null);

        if(cursor.moveToFirst()){
            do {
                long id = cursor.getLong(cursor.getColumnIndexOrThrow("id"));
                String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                String brief = cursor.getString(cursor.getColumnIndexOrThrow("brief"));
                String numberOfProject = cursor.getString(cursor.getColumnIndexOrThrow("numberOfProject"));
                Boolean wasApproved = Boolean.parseBoolean(cursor.getString(cursor.getColumnIndexOrThrow("wasApproved")));
                projects.add(
                        new Project(id, numberOfProject, name, brief, wasApproved)
                );
            }while (cursor.moveToNext());
        }
        database.close();

        return projects;
    }

    public long createPoliticalPartyInDB(PoliticalParty politicalParty){
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", politicalParty.getName());
        values.put("acronym", politicalParty.getAcronym());
        values.put("deleted", politicalParty.isDeleted());
        long id = database.insert("politicalParty", null, values);
        database.close();
        return id;
    }

    public ArrayList<PoliticalParty> getPoliticalPartyFromDB(){
        ArrayList<PoliticalParty> politicalParties = new ArrayList<>();

        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.query("politicalParty", null, null, null,
                null, null, null);

        if(cursor.moveToFirst()){
            do {
                long id = cursor.getLong(cursor.getColumnIndexOrThrow("id"));
                String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                String acronym = cursor.getString(cursor.getColumnIndexOrThrow("acronym"));
                Boolean deleted = Boolean.parseBoolean(cursor.getString(cursor.getColumnIndexOrThrow("deleted")));
                politicalParties.add(
                        new PoliticalParty(id, name, acronym, deleted)
                );
            }while (cursor.moveToNext());
        }
        database.close();

        return politicalParties;
    }


    public long createPoliticalInDB(Political political){
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", political.getName());
        values.put("electivePositionType", political.getElectivePositionType().toString());
        values.put("politicalParty", political.getPoliticalParty());
        values.put("deleted", political.isDeleted());
        long id = database.insert("political", null, values);
        database.close();
        return id;
    }

    public ArrayList<Political> getPoliticalFromDB(){
        ArrayList<Political> politicals = new ArrayList<>();

        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.query("political", null, null, null,
                null, null, null);


        if(cursor.moveToFirst()){
            do {
                long id = cursor.getLong(cursor.getColumnIndexOrThrow("id"));
                String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                ElectivePositionType electivePositionType = ElectivePositionType.valueOf(cursor.getString(cursor.getColumnIndexOrThrow("electivePositionType")));
                long politicalParty = cursor.getLong(cursor.getColumnIndexOrThrow("politicalParty"));
                Boolean deleted = Boolean.parseBoolean(cursor.getString(cursor.getColumnIndexOrThrow("deleted")));
                politicals.add(
                         new Political(id, name, electivePositionType,  politicalParty, deleted)

                );
            }while (cursor.moveToNext());
        }
        database.close();

        return politicals;
    }

    public long createVoteInDB(Vote vote){
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("typeOfVote", vote.getTypeOfVote().toString());
        values.put("project", vote.getProject());
        values.put("political", vote.getPolitical());
        long id = database.insert("vote", null, values);
        database.close();
        return id;
    }


    public ArrayList<Vote> getVoteFromDB(){
        ArrayList<Vote> votes = new ArrayList<>();

        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.query("vote", null, null, null,
                null, null, null);

        if(cursor.moveToFirst()){
            do {
                long id = cursor.getLong(cursor.getColumnIndexOrThrow("id"));
                TypeOfVote typeOfVote = TypeOfVote.valueOf(cursor.getString(cursor.getColumnIndexOrThrow("typeOfVote")));
                long project = cursor.getLong(cursor.getColumnIndexOrThrow("project"));
                long political = cursor.getLong(cursor.getColumnIndexOrThrow("political"));
                votes.add(
                        new Vote(id, typeOfVote, project, political)

                );
            }while (cursor.moveToNext());
        }
        database.close();

        return votes;
    }

    public Political getPoliticalByIDFromDB(Long id){

        SQLiteDatabase database = getReadableDatabase();

        String selection = "id = ? and deleted = 0";
        String[] selectionArgs = new String[] { id.toString() };

        Cursor cursor = database.query("political", null, selection, selectionArgs,
                null, null, null);

        if (cursor.getCount()>0){
            cursor.moveToFirst();
            long idUser = cursor.getLong(cursor.getColumnIndexOrThrow("id"));
            String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
            ElectivePositionType electivePositionType = ElectivePositionType.valueOf(cursor.getString(cursor.getColumnIndexOrThrow("electivePositionType")));
            long politicalParty = cursor.getLong(cursor.getColumnIndexOrThrow("politicalParty"));
            Boolean deleted = Boolean.parseBoolean(cursor.getString(cursor.getColumnIndexOrThrow("deleted")));
            return new Political(idUser, name, electivePositionType,  politicalParty, deleted);
        } else {return null;}
    }



    public ArrayList<Vote> getVotesFromPoliticalID(Long id){
        ArrayList<Vote> votes = new ArrayList<>();

        String selection = "political = ?";
        String[] selectionArgs = new String[] { id.toString() };

        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.query("vote", null, selection, selectionArgs,
                null, null, null);

        if(cursor.moveToFirst()){
            do {
                long idVote = cursor.getLong(cursor.getColumnIndexOrThrow("id"));
                TypeOfVote typeOfVote = TypeOfVote.valueOf(cursor.getString(cursor.getColumnIndexOrThrow("typeOfVote")));
                long project = cursor.getLong(cursor.getColumnIndexOrThrow("project"));
                long political = cursor.getLong(cursor.getColumnIndexOrThrow("political"));

                votes.add(
                        new Vote(idVote, typeOfVote, project, political)

                );
            }while (cursor.moveToNext());
        }
        database.close();

        return votes;
    }


    public ArrayList<Political> getPoliticalByTypeFromDB(ElectivePositionType type){
        ArrayList<Political> politicals = new ArrayList<>();

        String selection = "electivePositionType = ? and deleted = 0";
        String[] selectionArgs = new String[] { type.toString() };

        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.query("political", null, selection, selectionArgs,
                null, null, null);

        if(cursor.moveToFirst()){
            do {
                long id = cursor.getLong(cursor.getColumnIndexOrThrow("id"));
                String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                ElectivePositionType electivePositionType = ElectivePositionType.valueOf(cursor.getString(cursor.getColumnIndexOrThrow("electivePositionType")));
                long politicalParty = cursor.getLong(cursor.getColumnIndexOrThrow("politicalParty"));
                Boolean deleted = Boolean.parseBoolean(cursor.getString(cursor.getColumnIndexOrThrow("deleted")));
                politicals.add(
                        new Political(id, name, electivePositionType,  politicalParty, deleted)

                );
            }while (cursor.moveToNext());
        }
        database.close();

        return politicals;
    }

    public ArrayList<Political> getPoliticalByTypeAndNameFromDB(ElectivePositionType type, String searchName){
        ArrayList<Political> politicals = new ArrayList<>();

//        String result = searchName == null? "" : searchName;

        String selection = "electivePositionType = ? and deleted = 0 and name LIKE '" +searchName+ "%'";



        String[] selectionArgs = new String[] { type.toString()};


        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.query("political", null, selection, selectionArgs,
                null, null, null);

        if(cursor.moveToFirst()){
            do {
                long id = cursor.getLong(cursor.getColumnIndexOrThrow("id"));
                String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                ElectivePositionType electivePositionType = ElectivePositionType.valueOf(cursor.getString(cursor.getColumnIndexOrThrow("electivePositionType")));
                long politicalParty = cursor.getLong(cursor.getColumnIndexOrThrow("politicalParty"));
                Boolean deleted = Boolean.parseBoolean(cursor.getString(cursor.getColumnIndexOrThrow("deleted")));
                politicals.add(
                        new Political(id, name, electivePositionType,  politicalParty, deleted)

                );
            }while (cursor.moveToNext());
        }
        database.close();

        return politicals;
    }

    public Project getProjectFromID(Long id){

        SQLiteDatabase database = getReadableDatabase();

        String selection = "id = ?";
        String[] selectionArgs = new String[] { id.toString() };

        Cursor cursor = database.query("project", null, selection, selectionArgs,
                null, null, null);

        if (cursor.getCount()>0){
            cursor.moveToFirst();
            long idProject = cursor.getLong(cursor.getColumnIndexOrThrow("id"));
            String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
            String brief = cursor.getString(cursor.getColumnIndexOrThrow("brief"));
            String numberOfProject = cursor.getString(cursor.getColumnIndexOrThrow("numberOfProject"));
            Integer wasApproved = cursor.getInt(cursor.getColumnIndexOrThrow("wasApproved"));
            Boolean value = wasApproved == 0 ? false : true;
            return new Project(idProject, numberOfProject, name,  brief, value);
        } else {return null;}
    }


    public PoliticalParty getPoliticalPartyFromID(Long id){

        SQLiteDatabase database = getReadableDatabase();

        String selection = "id = ? and deleted = 0";
        String[] selectionArgs = new String[] { id.toString() };

        Cursor cursor = database.query("politicalParty", null, selection, selectionArgs,
                null, null, null);

        if (cursor.getCount()>0){
            cursor.moveToFirst();
            long idPoliticalParty = cursor.getLong(cursor.getColumnIndexOrThrow("id"));
            String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
            String acronym = cursor.getString(cursor.getColumnIndexOrThrow("acronym"));
            Boolean deleted = Boolean.parseBoolean(cursor.getString(cursor.getColumnIndexOrThrow("deleted")));
            return new PoliticalParty(idPoliticalParty, name, acronym, deleted);
        } else {return null;}
    }
}
