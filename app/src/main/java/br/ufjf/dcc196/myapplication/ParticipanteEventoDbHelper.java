package br.ufjf.dcc196.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ParticipanteEventoDbHelper extends SQLiteOpenHelper{

    public final static int DATABASE_VERSION = 1;
    public final static String DATABASE_NAME = "Participante.db";

    public ParticipanteEventoDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private void inicializeParticipante(SQLiteDatabase db){
        db.getWritableDatabase();

        ContentValues cv1 = new ContentValues();
        cv1.put(ParticipanteContract.Participante.COLUMN_NAME_CPF, "66666666666");
        cv1.put(ParticipanteContract.Participante.COLUMN_NAME_EMAIL, "fulano@hotmail.com");
        cv1.put(ParticipanteContract.Participante.COLUMN_NAME_NOME, "Fulano da Silva");
        ContentValues cv2 = new ContentValues();
        cv2.put(ParticipanteContract.Participante.COLUMN_NAME_CPF, "77777777777");
        cv2.put(ParticipanteContract.Participante.COLUMN_NAME_EMAIL, "cicrano@hotmail.com");
        cv2.put(ParticipanteContract.Participante.COLUMN_NAME_NOME, "Cicrano da Silva");
        ContentValues cv3 = new ContentValues();
        cv3.put(ParticipanteContract.Participante.COLUMN_NAME_CPF, "11111111111");
        cv3.put(ParticipanteContract.Participante.COLUMN_NAME_EMAIL, "beltrano@hotmail.com");
        cv3.put(ParticipanteContract.Participante.COLUMN_NAME_NOME, "Beltrano da Silva");
        ContentValues cv4 = new ContentValues();
        cv4.put(ParticipanteContract.Participante.COLUMN_NAME_CPF, "3333333333");
        cv4.put(ParticipanteContract.Participante.COLUMN_NAME_EMAIL, "outrano@hotmail.com");
        cv4.put(ParticipanteContract.Participante.COLUMN_NAME_NOME, "Outrano da Silva");

        db.insert(ParticipanteContract.Participante.TABLE_NAME,null, cv1);
        db.insert(ParticipanteContract.Participante.TABLE_NAME,null, cv2);
        db.insert(ParticipanteContract.Participante.TABLE_NAME,null, cv3);
        db.insert(ParticipanteContract.Participante.TABLE_NAME,null, cv4);
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
