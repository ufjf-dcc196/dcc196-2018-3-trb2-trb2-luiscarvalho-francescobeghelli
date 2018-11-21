package br.ufjf.dcc196.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ParticipanteEventoDbHelper extends SQLiteOpenHelper{

    public final static int DATABASE_VERSION = 1;
    public final static String DATABASE_NAME = "Participante.db";

    public ParticipanteEventoDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ParticipanteContract.Participante.CREATE_CADPARTICIPANTE);
        db.execSQL(EventoContract.Evento.CREATE_CADEVENTO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(ParticipanteContract.Participante.DROP_CADPARTICIPANTE);
        db.execSQL(EventoContract.Evento.DROP_CADEVENTO);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion){
        onUpgrade(db,oldVersion,newVersion);
    }
}
