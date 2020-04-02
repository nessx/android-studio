package com.example.leepper;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class modalum extends AppCompatActivity {

    String [] array_noms;
    int [] array_nota;
    int [] array_nota2;
    int [] array_nota3;
    int [] calculos;
    int contador;

    Button cambiarnombre;
    Button cambiarnota;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modalum);

        cambiarnombre = findViewById(R.id.changename);
        cambiarnota = findViewById(R.id.changenota);

        Intent recogerDatos = getIntent();
        array_noms = recogerDatos.getStringArrayExtra("array_noms");
        array_nota = recogerDatos.getIntArrayExtra("array_nota");
        array_nota2 = recogerDatos.getIntArrayExtra("array_nota2");
        array_nota3 = recogerDatos.getIntArrayExtra("array_nota3");
        contador = recogerDatos.getIntExtra("contador", 0);

        cambiarnombre.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (contador<=0){
                    informacion();
                }
                else{
                    Intent intent = new Intent (modalum.this, modname.class);
                    intent.putExtra("array_noms", array_noms);
                    intent.putExtra("array_nota", array_nota);
                    intent.putExtra("array_nota2", array_nota2);
                    intent.putExtra("array_nota3", array_nota3);
                    intent.putExtra("contador", contador);
                    startActivityForResult(intent, 1);
                }
            }
        });

        cambiarnota.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (contador<=0){
                    informacion();
                }
                else{
                    Intent intent = new Intent (modalum.this, modnota.class);
                    intent.putExtra("array_noms", array_noms);
                    intent.putExtra("array_nota", array_nota);
                    intent.putExtra("array_nota2", array_nota2);
                    intent.putExtra("array_nota3", array_nota3);
                    intent.putExtra("contador", contador);
                    startActivityForResult(intent, 2);
                }
            }
        });

        cambiarnota.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (contador<=0){
                    informacion();
                }
                else{
                    Intent intent = new Intent (modalum.this, modnota.class);
                    intent.putExtra("array_noms", array_noms);
                    intent.putExtra("array_nota", array_nota);
                    intent.putExtra("array_nota2", array_nota2);
                    intent.putExtra("array_nota3", array_nota3);
                    intent.putExtra("contador", contador);
                    startActivityForResult(intent, 2);
                }
            }
        });
    }

    //se encarga de mostrar el primer dialogo de informacion de los alumnos, el else como es unico pues se hace en cada una
    public void informacion(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(modalum.this);

        alertDialogBuilder.setTitle("Informacion");

        alertDialogBuilder
                .setMessage("No hay alumnos añadidos!")
                .setCancelable(false)
                .setNegativeButton("Cerrar",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        dialog.cancel();
                    }
                }).create().show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // devolucion modname --> arraynames
        if(requestCode==1){
            if(resultCode == RESULT_OK){
                array_noms = data.getStringArrayExtra("array_noms");
            }
        }

        // devolucion modnota
        if(requestCode == 2){
            if(resultCode == RESULT_OK){
                array_nota = data.getIntArrayExtra("array_nota");
                array_nota2 = data.getIntArrayExtra("array_nota2");
                array_nota3 = data.getIntArrayExtra("array_nota3");

            }
        }
    }

    public void onBackPressed(){
        Intent returnIntent = new Intent();
        returnIntent.putExtra("array_noms", array_noms);
        returnIntent.putExtra("array_nota", array_nota);
        returnIntent.putExtra("array_nota2", array_nota2);
        returnIntent.putExtra("array_nota3", array_nota3);
        returnIntent.putExtra("contador", contador);
        setResult(RESULT_OK, returnIntent);
        finish();
    }

}
