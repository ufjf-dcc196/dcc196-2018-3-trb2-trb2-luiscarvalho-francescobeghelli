package br.ufjf.dcc196.myapplication;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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

    public static void saveEvento(SQLiteDatabase db, String titulo, String dia,
                                  String horario, String facilitador, String descricao) {
        ContentValues cv = new ContentValues();
        cv.put(EventoContract.Evento.COLUMN_NAME_TITULO, titulo);
        cv.put(EventoContract.Evento.COLUMN_NAME_DIA, dia);
        cv.put(EventoContract.Evento.COLUMN_NAME_HORARIO, horario);
        cv.put(EventoContract.Evento.COLUMN_NAME_FACILIT, facilitador);
        cv.put(EventoContract.Evento.COLUMN_NAME_DESC, descricao);

        db.insert(EventoContract.Evento.TABLE_NAME,null, cv);
    }

    public static Cursor getEventoCursor(SQLiteDatabase db, String selection)
    {
        return db.query(EventoContract.Evento.TABLE_NAME, new String[] {EventoContract.Evento._ID, EventoContract.Evento.COLUMN_NAME_TITULO, EventoContract.Evento.COLUMN_NAME_HORARIO,
                EventoContract.Evento.COLUMN_NAME_DESC, EventoContract.Evento.COLUMN_NAME_FACILIT, EventoContract.Evento.COLUMN_NAME_DIA }, null,null,null,null,null,null);
    }
}
