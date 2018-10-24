package br.ufjf.dcc196.myapplication;

import android.provider.BaseColumns;

public class CadastroEventoContract {

    public final class CadastroEvento implements BaseColumns
    {
        public static final String TABLE_NAME = "CadastroEvento";
        public static final String COLUMN_NAME_TITULO = "titulo";
        public static final String COLUMN_NAME_DIA = "dia";
        public static final String COLUMN_NAME_HORARIO = "horario";
        public static final String COLUMN_NAME_FACILIT = "facilitador";
        public static final String COLUMN_NAME_DESC = "descricao";
        public static final String CREATE_CADEVENTO = "CREATE TABLE " + CadastroEventoContract.CadastroEvento.TABLE_NAME + " ("
                + CadastroEventoContract.CadastroEvento._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + CadastroEventoContract.CadastroEvento.COLUMN_NAME_TITULO + " TEXT, "
                + CadastroEventoContract.CadastroEvento.COLUMN_NAME_DIA + " DATE, "
                + CadastroEventoContract.CadastroEvento.COLUMN_NAME_HORARIO + " TEXT, "
                + CadastroEventoContract.CadastroEvento.COLUMN_NAME_FACILIT + " TEXT, "
                + CadastroEventoContract.CadastroEvento.COLUMN_NAME_DESC + " TEXT, "
                +")";
        public static final String DROP_CADEVENTO = "DROP TABLE IF EXISTS " + CadastroEventoContract.CadastroEvento.TABLE_NAME;
    }
}
