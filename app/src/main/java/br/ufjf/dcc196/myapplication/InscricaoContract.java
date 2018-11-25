package br.ufjf.dcc196.myapplication;

import android.provider.BaseColumns;

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
}
