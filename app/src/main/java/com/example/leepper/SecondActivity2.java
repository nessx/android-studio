package com.example.leepper;

import android.app.ActionBar;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import java.util.Arrays;

public class SecondActivity2 extends AppCompatActivity {
    CardView add,peligro,mostrar,aprall,help,seach,change;

    String [] array_noms = new String[50];
    int [] array_nota = new int[50];
    int [] array_nota2 = new int[50];
    int [] array_nota3 = new int[50];
    int contador = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        add = findViewById(R.id.add);
        mostrar = findViewById(R.id.mostrar);
        aprall = findViewById(R.id.aprall);
        //help = findViewById(R.id.help);
        seach = findViewById(R.id.seach);
        change = findViewById(R.id.change);
        peligro = findViewById(R.id.peligro);

        add.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent (SecondActivity2.this, addusers.class);
                intent.putExtra("array_noms", array_noms);
                intent.putExtra("array_nota", array_nota);
                intent.putExtra("array_nota2", array_nota2);
                intent.putExtra("array_nota3", array_nota3);
                intent.putExtra("contador", contador);
                startActivityForResult(intent, 1);
            }
        });

        mostrar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (contador<=0){
                    informacion();
                }
                else{
                    Intent intent = new Intent (SecondActivity2.this, mostrar.class);
                    intent.putExtra("array_noms", array_noms);
                    intent.putExtra("array_nota", array_nota);
                    intent.putExtra("array_nota2", array_nota2);
                    intent.putExtra("array_nota3", array_nota3);
                    intent.putExtra("contador", contador);
                    startActivityForResult(intent, 1);
                }
            }
        });

        /*
        help.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(SecondActivity2.this);

                alertDialogBuilder.setTitle("Ayuda");

                alertDialogBuilder
                        .setMessage("Hola, esta es la ventana de ayuda :), hay "+contador+" alumnos")
                        .setCancelable(false)
                        .setNegativeButton("Cerrar",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.cancel();
                            }
                        }).create().show();
            }
        });
         */

        aprall.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (contador<=0){
                    informacion();
                }
                else{
                    for(int i=0;i<contador;i++){
                        if (array_nota[i] <=4){
                            array_nota[i] = 5;
                        }
                        if (array_nota2[i] <=4){
                            array_nota2[i] = 5;
                        }
                        if (array_nota3[i] <=4){
                            array_nota3[i] = 5;
                        }
                    }
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(SecondActivity2.this);

                    alertDialogBuilder.setTitle("Informacion");

                    alertDialogBuilder
                            .setMessage("Alumnos aprobados correctamente!")
                            .setCancelable(false)
                            .setNegativeButton("Cerrar",new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int id) {
                                    dialog.cancel();
                                }
                            }).create().show();
                }
            }
        });

        seach.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (contador<=0) {
                    informacion();
                }
                else {
                    Intent intent = new Intent(SecondActivity2.this, seach.class);
                    intent.putExtra("array_noms", array_noms);
                    intent.putExtra("array_nota", array_nota);
                    intent.putExtra("array_nota2", array_nota2);
                    intent.putExtra("array_nota3", array_nota3);
                    intent.putExtra("contador", contador);
                    startActivityForResult(intent, 1);
                }
            }
        });

        change.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (contador<=0) {
                    informacion();
                }
                else {
                    Intent intent = new Intent(SecondActivity2.this, modalum.class);
                    intent.putExtra("array_noms", array_noms);
                    intent.putExtra("array_nota", array_nota);
                    intent.putExtra("array_nota2", array_nota2);
                    intent.putExtra("array_nota3", array_nota3);
                    intent.putExtra("contador", contador);
                    startActivityForResult(intent, 1);
                }
            }
        });
        peligro.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (contador<=0) {
                    informacion();
                }
                else {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(SecondActivity2.this);
                    dialog.setTitle("ADVERTENCIA!");
                    dialog.setMessage("Estas apunto de restaurar toda \nla información de esta app.\nEsta opción es irreversible.\nestás seguro de que quieres continuar? ");
                    dialog.setCancelable(true);
                    dialog.setPositiveButton(
                            "SI",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int
                                        id) {
                                    Arrays.fill(array_noms, null);
                                    Arrays.fill(array_nota, 0);
                                    Arrays.fill(array_nota2, 0);
                                    Arrays.fill(array_nota3, 0);
                                    contador=0;
                                    Toast.makeText(getApplicationContext(),"AJUSTES RESTABLECIDOS CORRECTAMENTE!",Toast.LENGTH_SHORT).show();

                                    Intent intent = new Intent(SecondActivity2.this, MainActivity.class);
                                    intent.putExtra("array_noms", array_noms);
                                    intent.putExtra("array_nota", array_nota);
                                    intent.putExtra("array_nota2", array_nota2);
                                    intent.putExtra("array_nota3", array_nota3);
                                    intent.putExtra("contador", contador);
                                    startActivityForResult(intent, 1);


                                }
                            });
                    dialog.setNegativeButton(
                            "NO",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                    Toast.makeText(getApplicationContext(),"OPERACIÓN CANCELADA POR EL USUARIO",Toast.LENGTH_SHORT).show();
                                }
                            });

                    AlertDialog alert = dialog.create();
                    alert.show();
                }
            }
        });
    }


    //se encarga de mostrar el primer dialogo de informacion de los alumnos, el else como es unico pues se hace en cada una
    public void informacion(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(SecondActivity2.this);

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
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==1){
            if(resultCode == RESULT_OK){
                array_noms = data.getStringArrayExtra("array_noms");
                array_nota = data.getIntArrayExtra("array_nota");
                array_nota2 = data.getIntArrayExtra("array_nota2");
                array_nota3 = data.getIntArrayExtra("array_nota3");
                contador = data.getIntExtra("contador", 0);
            }
        }

    }

}
