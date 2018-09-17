package com.vanessa.appcompras_m171.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by android on 28/08/2018.
 */

public class Conexao extends SQLiteOpenHelper {
    //PRECISA TER UM METODO CONSTRUTOR UM OnCreate E OnUpgrade

    //NOME DO BANCO E VERSAO NO MÉTODO CONSTRUTOR

    private static final String NOME_BANCO = "appZoo";
    private static final int VERSAO_BANCO = 1;

    public Conexao(Context contexto){
        super(contexto, NOME_BANCO, null,VERSAO_BANCO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE IF NOT EXISTS especies (" +
                        " id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                        "nome TEXT NOT NULL)"
        );

        db.execSQL(
                "CREATE TABLE IF NOT EXISTS animais (" +
                        " id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                        "nome TEXT NOT NULL, "+
                        "idade DOUBLE," +
                        "codEspecie INTEGER)"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
