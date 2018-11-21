package br.ufjf.dcc196.myapplication;

import android.provider.BaseColumns;

public class EventoContract {

    public final class Evento implements BaseColumns
    {
        public static final String TABLE_NAME = "Evento";
        public static final String COLUMN_NAME_TITULO = "titulo";
        public static final String COLUMN_NAME_DIA = "dia";
        public static final String COLUMN_NAME_HORARIO = "horario";
        public static final String COLUMN_NAME_FACILIT = "facilitador";
        public static final String COLUMN_NAME_DESC = "descricao";
        public static final String CREATE_CADEVENTO = "CREATE TABLE " + Evento.TABLE_NAME + " ("
                + Evento._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Evento.COLUMN_NAME_TITULO + " TEXT, "
                + Evento.COLUMN_NAME_DIA + " DATE, "
                + Evento.COLUMN_NAME_HORARIO + " TEXT, "
                + Evento.COLUMN_NAME_FACILIT + " TEXT, "
                + Evento.COLUMN_NAME_DESC + " TEXT "
                +")";
        public static final String DROP_CADEVENTO = "DROP TABLE IF EXISTS " + Evento.TABLE_NAME;
    }
}
