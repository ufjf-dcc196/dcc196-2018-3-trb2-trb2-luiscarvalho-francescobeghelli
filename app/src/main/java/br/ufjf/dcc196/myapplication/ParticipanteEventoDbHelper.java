package br.ufjf.dcc196.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ParticipanteEventoDbHelper extends SQLiteOpenHelper{

    public final static int DATABASE_VERSION = 6;
    public final static String DATABASE_NAME = "Participante.db";

    public ParticipanteEventoDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ParticipanteContract.Participante.CREATE_CADPARTICIPANTE);
        db.execSQL(EventoContract.Evento.CREATE_CADEVENTO);
        db.execSQL(InscricaoContract.Inscricao.CREATE_INSCRICAO);

        inicializeParticipante(db);
        inicializeEvento(db);
    }

    private void inicializeEvento(SQLiteDatabase db){
        EventoContract.saveEvento(db,"Curso de Aprendizagem 1","04/02/2020","16:20","Teacher Love","Descrição do Curso de Aprendizagem 1");
        EventoContract.saveEvento(db,"Curso de Aprendizagem 2","04/02/2020","16:20","Teacher Happiness","Descrição do Curso de Aprendizagem 2");
        EventoContract.saveEvento(db,"Curso de Aprendizagem 3","04/02/2020","16:20","Teacher A","Descrição do Curso de Aprendizagem 3");
        EventoContract.saveEvento(db,"Curso de Aprendizagem 4","04/02/2020","16:20","Teacher Newbie","Descrição do Curso de Aprendizagem 4");
    }

    private void inicializeParticipante(SQLiteDatabase db){
        ParticipanteContract.saveParticipantes(db,"66666666666","fulano@hotmail.com","Fulano da Silva");
        ParticipanteContract.saveParticipantes(db,"77777777777","cicrano@hotmail.com","Cicrano da Silva");
        ParticipanteContract.saveParticipantes(db,"11111111111","beltrano@hotmail.com","Beltrano da Silva");
        ParticipanteContract.saveParticipantes(db,"3333333333","outrano@hotmail.com","Outrano da Silva");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(InscricaoContract.Inscricao.DROP_INSCRICAO);
        db.execSQL(ParticipanteContract.Participante.DROP_CADPARTICIPANTE);
        db.execSQL(EventoContract.Evento.DROP_CADEVENTO);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion){
        onUpgrade(db,oldVersion,newVersion);
    }
}
