package br.ufjf.dcc196.myapplication;

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
}
