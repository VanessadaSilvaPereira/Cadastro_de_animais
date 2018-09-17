package com.vanessa.appcompras_m171.model;

/**
 * Created by android on 27/08/2018.
 */

public class Especie {

    private int id;
    private String nome;

    public Especie(){

    }
    public Especie(String nome){
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    @Override
    public String toString() {
        return nome;
    }
}
