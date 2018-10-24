package br.ufjf.dcc196.myapplication;

import android.provider.BaseColumns;

public class CadastroPartContract{

    public final class CadastroParticipante implements BaseColumns
    {
        public static final String TABLE_NAME = "CadastroParticipante";
        public static final String COLUMN_NAME_NOME = "nome";
        public static final String COLUMN_NAME_EMAIL = "email";
        public static final String COLUMN_NAME_CPF = "cpf";
        public static final String CREATE_CADPARTICIPANTE = "CREATE TABLE " + CadastroParticipante.TABLE_NAME + " ("
                + CadastroParticipante._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + CadastroParticipante.COLUMN_NAME_NOME + " TEXT, "
                + CadastroParticipante.COLUMN_NAME_EMAIL + " TEXT, "
                + CadastroParticipante.COLUMN_NAME_CPF + " TEXT, "
                +")";
        public static final String DROP_CADPARTICIPANTE = "DROP TABLE IF EXISTS " + CadastroParticipante.TABLE_NAME;
    }
}
