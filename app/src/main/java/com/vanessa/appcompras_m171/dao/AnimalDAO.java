package com.vanessa.appcompras_m171.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.vanessa.appcompras_m171.model.Especie;
import com.vanessa.appcompras_m171.model.Animal;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by android on 28/08/2018.
 */

public class AnimalDAO {

    public static void inserir(Context contexto, Animal animal){
        Conexao conn = new Conexao(contexto);
        SQLiteDatabase banco = conn.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put("nome", animal.getNome());
        valores.put("idade", animal.getIdade());
        valores.put("codEspecie", animal.getEspecie().getId());

        banco.insert("animais", null, valores);
        //nullColumnHack para garantir que os valores nao vao ser null
    }

    public static List<Animal> getAnimais(Context contexto){
        List<Animal> listaDeAnimais = new ArrayList<>();
        Conexao conn = new Conexao(contexto);
        SQLiteDatabase banco = conn.getWritableDatabase();

        Cursor tabela = banco.rawQuery("SELECT * FROM animais", null);
        //primeiro consultas(SELECT) e depois clausulas where(NULL).

        if(tabela.getCount() > 0){
            tabela.moveToFirst();

            do{

                Especie esp = new Especie();
                esp.setId(tabela.getInt(3));


                Animal ani = new Animal();
                ani.setId(tabela.getInt(0));
                ani.setNome(tabela.getString(1));
                ani.setIdade(tabela.getDouble(2));
                ani.setEspecie(esp);


                listaDeAnimais.add(ani);


            }while (tabela.moveToNext());
        }
        return listaDeAnimais;
    }
}
