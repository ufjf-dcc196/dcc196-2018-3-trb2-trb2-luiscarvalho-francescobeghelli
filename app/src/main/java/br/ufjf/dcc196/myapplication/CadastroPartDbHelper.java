package br.ufjf.dcc196.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CadastroPartDbHelper extends SQLiteOpenHelper{

    public final static int DATABASE_VERSION = 1;
    public final static String DATABASE_NAME = "CadastroParticipante.db";

    public CadastroPartDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CadastroPartContract.CadastroParticipante.CREATE_CADPARTICIPANTE);
        db.execSQL(CadastroEventoContract.CadastroEvento.CREATE_CADEVENTO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(CadastroPartContract.CadastroParticipante.DROP_CADPARTICIPANTE);
        db.execSQL(CadastroEventoContract.CadastroEvento.DROP_CADEVENTO);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion){
        onUpgrade(db,oldVersion,newVersion);
    }
}
