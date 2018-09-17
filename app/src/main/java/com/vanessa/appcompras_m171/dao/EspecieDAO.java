package com.vanessa.appcompras_m171.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.vanessa.appcompras_m171.model.Especie;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by android on 28/08/2018.
 */

public class EspecieDAO {
public static void inserir(Context contexto, Especie especie){
    Conexao conn = new Conexao(contexto);
    SQLiteDatabase banco = conn.getWritableDatabase();

    banco.execSQL("INSERT INTO especies (nome) VALUES "+
    " ('"+ especie.getNome()+"') ");


}

public static List<Especie> getCategorias(Context contexto){
    List<Especie> listaDeEspecies = new ArrayList<>();
    Conexao conn = new Conexao(contexto);
    SQLiteDatabase banco = conn.getWritableDatabase();

    Cursor tabela = banco.rawQuery("SELECT * FROM especies", null);
    //primeiro consultas(SELECT) e depois clausulas where(NULL).

    if(tabela.getCount() > 0){
        tabela.moveToFirst();

        do{
            Especie cat = new Especie();
            cat.setId(tabela.getInt(0));
            cat.setNome(tabela.getString(1));

            listaDeEspecies.add(cat);


        }while (tabela.moveToNext());
    }
    return listaDeEspecies;
}

}
