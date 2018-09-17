package com.vanessa.appcompras_m171;

import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.vanessa.appcompras_m171.dao.EspecieDAO;
import com.vanessa.appcompras_m171.dao.AnimalDAO;
import com.vanessa.appcompras_m171.model.Especie;
import com.vanessa.appcompras_m171.model.Animal;

import java.util.List;

public class FormularioActivity extends AppCompatActivity {

    //ATRIBUTOS:
    private EditText etNome, etIdade;
    private Button btnSalvar;
    private Spinner spEspecie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        //LIGAR OS OBJETOS COM OS ELEMENTOS DA TELA
        etNome = (EditText) findViewById(R.id.etNomeAnimal);
        etIdade = (EditText) findViewById(R.id.etIdade);
        btnSalvar = (Button) findViewById(R.id.btnSalvar);
        spEspecie = (Spinner) findViewById(R.id.spEspecie);


        carregarCategorias();

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvar();
            }
        });

    }

    private void salvar(){
        String nome = etNome.getText().toString();
        if(nome.isEmpty() || spEspecie.getSelectedItemPosition() == 0){
            AlertDialog.Builder alerta = new AlertDialog.Builder(this);

            alerta.setTitle(getResources().getString(R.string.txtAtencao));
            alerta.setMessage(R.string.txtCamposObrigatorios);
            alerta.setIcon(android.R.drawable.ic_dialog_alert);
            alerta.setNeutralButton("OK", null);
            alerta.show();
        }else{
            String idade = etIdade.getText().toString();

            if(idade.isEmpty()){
                idade= "0";}


                idade = idade.replace(",", ".");
                double ida = Double.valueOf(idade);

            Animal prod = new Animal();
            prod.setNome(nome);
            prod.setIdade(ida);
            prod.setEspecie((Especie) spEspecie.getSelectedItem());


            AnimalDAO.inserir(this, prod);
            finish();
            }
        }





    //para carregar o menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_formulario, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.menu_nova_especie){
            cadastrarEspecie();

        }
        return super.onOptionsItemSelected(item);
    }
    private void cadastrarEspecie(){
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setTitle(  getResources().getString(R.string.txtNovaEspecie));
        alerta.setIcon(android.R.drawable.ic_menu_edit);

        final EditText etNomeCategoria = new EditText(this);
        etNomeCategoria.setHint(R.string.hintEspecies);
        etNomeCategoria.setTextColor(Color.rgb(255,0,255));

        alerta.setView(etNomeCategoria);

        alerta.setNeutralButton(getResources().getString(R.string.txtCancelar), null);
        alerta.setPositiveButton(getResources().getString(R.string.txtSalvar), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String nome = etNomeCategoria.getText().toString();
                if(!nome.isEmpty()){
                    Especie especie = new Especie();
                    especie.setNome(nome);
                    EspecieDAO.inserir(FormularioActivity.this, especie);
                    carregarCategorias();

                }
            }
        })
        ;
        alerta.show();

    }

    private void carregarCategorias(){

        List<Especie> lista = EspecieDAO.getCategorias(this);

        Especie fake = new Especie();
        fake.setId(0);
        fake.setNome(getResources().getString(R.string.txtSelecione));
        lista.add(0, fake);


        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, lista);
        spEspecie.setAdapter(adapter);

    }



}
