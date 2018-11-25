package br.ufjf.dcc196.myapplication;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

public class ParticipanteContract {

    public final class Participante implements BaseColumns
    {
        public static final String TABLE_NAME = "Participante";
        public static final String COLUMN_NAME_NOME = "nome";
        public static final String COLUMN_NAME_EMAIL = "email";
        public static final String COLUMN_NAME_CPF = "cpf";
        public static final String CREATE_CADPARTICIPANTE = "CREATE TABLE " + Participante.TABLE_NAME + " ("
                + Participante._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Participante.COLUMN_NAME_NOME + " TEXT, "
                + Participante.COLUMN_NAME_EMAIL + " TEXT, "
                + Participante.COLUMN_NAME_CPF + " TEXT "
                +")";
        public static final String DROP_CADPARTICIPANTE = "DROP TABLE IF EXISTS " + Participante.TABLE_NAME;
    }

    public static void saveParticipantes(SQLiteDatabase db, String cpf, String email, String nome) {
        ContentValues cv = new ContentValues();
        cv.put(ParticipanteContract.Participante.COLUMN_NAME_CPF, cpf);
        cv.put(ParticipanteContract.Participante.COLUMN_NAME_EMAIL, email);
        cv.put(ParticipanteContract.Participante.COLUMN_NAME_NOME, nome);

        db.insert(ParticipanteContract.Participante.TABLE_NAME,null, cv);
    }

    public static Cursor getParticipanteCursor(SQLiteDatabase db, String selection)
    {
        return db.query(ParticipanteContract.Participante.TABLE_NAME, new String[] {ParticipanteContract.Participante._ID ,ParticipanteContract.Participante.COLUMN_NAME_NOME, ParticipanteContract.Participante.COLUMN_NAME_CPF,
                ParticipanteContract.Participante.COLUMN_NAME_EMAIL }, null,null,null,null,null,null);
    }
}
