package br.ufjf.dcc196.myapplication;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.util.EventLog;

public class InscricaoContract {
    public final class Inscricao implements BaseColumns
    {
        public static final String TABLE_NAME = "Inscricao";
        public static final String COLUMN_NAME_ID_EVENTO = "idEvento";
        public static final String COLUMN_NAME_ID_PARTICIPANTE = "idParticipante";
        public static final String CREATE_INSCRICAO = "CREATE TABLE " + InscricaoContract.Inscricao.TABLE_NAME + " ("
                + InscricaoContract.Inscricao._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + InscricaoContract.Inscricao.COLUMN_NAME_ID_EVENTO + " INTEGER REFERENCES " + EventoContract.Evento.TABLE_NAME + "(" + EventoContract.Evento._ID + "), "
                + InscricaoContract.Inscricao.COLUMN_NAME_ID_PARTICIPANTE + " INTEGER REFERENCES " + ParticipanteContract.Participante.TABLE_NAME + "(" + ParticipanteContract.Participante._ID + ") "
                + ")";
        public static final String DROP_INSCRICAO = "DROP TABLE IF EXISTS " + InscricaoContract.Inscricao.TABLE_NAME;
    }

    public static Cursor getEventosQueNaoParticipaCursor(SQLiteDatabase db, int id)
    {
        return db.rawQuery("SELECT DISTINCT e.* from " + EventoContract.Evento.TABLE_NAME + " e LEFT JOIN " + Inscricao.TABLE_NAME +
                " i ON e." + EventoContract.Evento._ID + " = i." + Inscricao.COLUMN_NAME_ID_EVENTO + " WHERE i." + Inscricao.COLUMN_NAME_ID_PARTICIPANTE
                + " != ? OR " + Inscricao.COLUMN_NAME_ID_PARTICIPANTE + " IS NULL", new String[] { Integer.toString(id) });
    }

    public static Cursor getEventosQueParticipaCursor(SQLiteDatabase db, int id)
    {
        return db.rawQuery("SELECT DISTINCT e.* from " + EventoContract.Evento.TABLE_NAME + " e INNER JOIN " + Inscricao.TABLE_NAME +
                " i ON e." + EventoContract.Evento._ID + " = i." + Inscricao.COLUMN_NAME_ID_EVENTO + " WHERE i." + Inscricao.COLUMN_NAME_ID_PARTICIPANTE
                + " = ?", new String[] { Integer.toString(id) });
    }

    public static Cursor getParticipantesNaoVaoEventoCursor(SQLiteDatabase db, int id)
    {
        return db.rawQuery("SELECT DISTINCT p.* from " + ParticipanteContract.Participante.TABLE_NAME + " p LEFT JOIN " + Inscricao.TABLE_NAME +
                " i ON p." + ParticipanteContract.Participante._ID + " = i." + Inscricao.COLUMN_NAME_ID_PARTICIPANTE + " WHERE i." + Inscricao.COLUMN_NAME_ID_EVENTO
                + " != ? OR " + Inscricao.COLUMN_NAME_ID_EVENTO + " IS NULL", new String[] { Integer.toString(id) });
    }

    public static Cursor getParticipantesEventoCursor(SQLiteDatabase db, int id)
    {
        return db.rawQuery("SELECT DISTINCT p.* from " + ParticipanteContract.Participante.TABLE_NAME + " p INNER JOIN " + Inscricao.TABLE_NAME +
                " i ON p." + ParticipanteContract.Participante._ID + " = i." + Inscricao.COLUMN_NAME_ID_PARTICIPANTE + " WHERE i." + Inscricao.COLUMN_NAME_ID_EVENTO
                + " = ?", new String[] { Integer.toString(id) });
    }

    public static void saveInscricao(SQLiteDatabase db, int idEvento, int idParticipante)
    {
        ContentValues cv = new ContentValues();
        cv.put(Inscricao.COLUMN_NAME_ID_EVENTO, idEvento);
        cv.put(Inscricao.COLUMN_NAME_ID_PARTICIPANTE, idParticipante);

        db.insert(Inscricao.TABLE_NAME,null,cv);
    }
}
