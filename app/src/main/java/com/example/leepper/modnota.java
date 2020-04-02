package com.example.leepper;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.method.DigitsKeyListener;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class modnota extends AppCompatActivity {

    String [] array_noms;
    int [] array_nota;
    int [] array_nota2;
    int [] array_nota3;
    int contador,seleval,notasave;
    int contnom = 0;
    boolean existe = false;
    int posicionExiste = -1;

    Button cambiarnota;
    EditText nombre,nota;
    int maxLength = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modnota);

        cambiarnota = findViewById(R.id.save);

        nombre = findViewById(R.id.nombre);
        nota = findViewById(R.id.nota);

        Intent recogerDatos = getIntent();

        array_noms = recogerDatos.getStringArrayExtra("array_noms");
        array_nota = recogerDatos.getIntArrayExtra("array_nota");
        array_nota2 = recogerDatos.getIntArrayExtra("array_nota2");
        array_nota3 = recogerDatos.getIntArrayExtra("array_nota3");
        contador = recogerDatos.getIntExtra("contador", 0);

        cambiarnota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText guardarnombre = findViewById(R.id.nombre);
                EditText guardarnota = findViewById(R.id.nota);

                String nomGuardar = guardarnombre.getText().toString();


                for (int i = 0; i < array_noms.length; i++) {
                    String valorarray = array_noms[i];
                    if (nomGuardar.equals(valorarray)) {
                        existe = true;
                        posicionExiste = i;
                        break;
                    }
                }if ((vacionulo(nomGuardar))) {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(modnota.this);
                    alertDialogBuilder.setTitle("Información");
                    alertDialogBuilder
                            .setMessage("El nombre no puede estar vacío!")
                            .setCancelable(false)
                            .setNegativeButton("Aceptar",new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int id) {
                                    dialog.cancel();
                                }
                            }).create().show();
                } else if (!existe){
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(modnota.this);
                    alertDialogBuilder.setTitle("Información");
                    alertDialogBuilder
                            .setMessage("El alumno " + nomGuardar + " no existe!")
                            .setCancelable(false)
                            .setNegativeButton("Cerrar", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            }).create().show();
                }else if (guardarnota.getText().toString().length() == 0 && contnom==0) {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(modnota.this);
                    alertDialogBuilder.setTitle("Información");
                    alertDialogBuilder
                            .setMessage("La evaluacion no puede estar vacía!")
                            .setCancelable(false)
                            .setNegativeButton("Aceptar", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            }).create().show();
                } else if (guardarnota.getText().toString().length() == 0 && contnom>1) {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(modnota.this);
                    alertDialogBuilder.setTitle("Información");
                    alertDialogBuilder
                            .setMessage("La nota no puede estar vacía!")
                            .setCancelable(false)
                            .setNegativeButton("Aceptar", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            }).create().show();
                } else {
                    if (contnom == 0) {
                        for (int i = 0; i < array_noms.length; i++) {
                            String valorarray = array_noms[i];
                            if (guardarnombre.getText().toString().equals(valorarray)) {
                                existe = true;
                                posicionExiste = i;
                                break;
                            }
                        }
                        seleval = Integer.parseInt(nota.getText().toString());
                        if (existe) {
                            nombre.setEnabled(false);
                            nota.setText("");
                            nota.setKeyListener(DigitsKeyListener.getInstance("0123456789"));
                            nota.setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxLength)});
                            if (seleval == 1) {
                                nota.setHint("Nota - evaluacion 1");
                            }
                            if (seleval == 2) {
                                nota.setHint("Nota - evaluacion 2");
                            }
                            if (seleval == 3) {
                                nota.setHint("Nota - evaluacion 3");
                            }
                            contnom++;
                        } else {
                            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(modnota.this);
                            alertDialogBuilder.setTitle("Información");

                            alertDialogBuilder
                                    .setMessage("El alumno " + nomGuardar + " no existe!")
                                    .setCancelable(false)
                                    .setNegativeButton("Cerrar", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            dialog.cancel();
                                        }
                                    }).create().show();
                        }
                    }
                    else{
                        notasave = Integer.parseInt(nota.getText().toString());
                        if (notasave<1 || notasave>10){
                            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(modnota.this);
                            alertDialogBuilder.setTitle("Información");

                            alertDialogBuilder
                                    .setMessage("La nota debe ser mayor que 1 y menor que 10!")
                                    .setCancelable(false)
                                    .setNegativeButton("Aceptar",new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog,int id) {
                                            dialog.cancel();
                                        }
                                    }).create().show();
                        }
                        else {
                            if (seleval == 1 && contnom > 1) {
                                array_nota[posicionExiste] = Integer.parseInt(nota.getText().toString());
                                nombre.setEnabled(true);
                                nombre.setText("");
                                nota.setText("");
                                nota.setHint("Evaluacion a cambiar ");
                                nota.setKeyListener(DigitsKeyListener.getInstance("123"));
                                contnom = -1;

                                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(modnota.this);
                                alertDialogBuilder.setTitle("Información");

                                alertDialogBuilder
                                        .setMessage("Nota cambiada a: " + array_nota[posicionExiste] + "\nDel alumno: " + nomGuardar)
                                        .setCancelable(false)
                                        .setNegativeButton("Cerrar", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                dialog.cancel();
                                            }
                                        }).create().show();

                            } else if (seleval == 2 && contnom > 0) {
                                array_nota2[posicionExiste] = Integer.parseInt(nota.getText().toString());
                                nombre.setEnabled(true);
                                nombre.setText("");
                                nota.setText("");
                                nota.setHint("Evaluacion a cambiar ");
                                nota.setKeyListener(DigitsKeyListener.getInstance("123"));
                                contnom = -1;

                                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(modnota.this);
                                alertDialogBuilder.setTitle("Información");

                                alertDialogBuilder
                                        .setMessage("Nota cambiada a: " + array_nota2[posicionExiste] + "\nDel alumno: " + nomGuardar)
                                        .setCancelable(false)
                                        .setNegativeButton("Cerrar", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                dialog.cancel();
                                            }
                                        }).create().show();

                            } else if (seleval == 3 && contnom > 1) {
                                array_nota3[posicionExiste] = Integer.parseInt(nota.getText().toString());
                                nombre.setEnabled(true);
                                nombre.setText("");
                                nota.setText("");
                                nota.setHint("Evaluacion a cambiar ");
                                nota.setKeyListener(DigitsKeyListener.getInstance("123"));
                                contnom = -1;

                                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(modnota.this);
                                alertDialogBuilder.setTitle("Información");

                                alertDialogBuilder
                                        .setMessage("Nota cambiada a: " + array_nota3[posicionExiste] + "\nDel alumno: " + nomGuardar)
                                        .setCancelable(false)
                                        .setNegativeButton("Cerrar", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                dialog.cancel();
                                            }
                                        }).create().show();
                            }
                        }
                    }
                    contnom++;
                }
            }
        });
    }

    public static boolean vacionulo(String str) {
        if(str != null && !str.isEmpty())
            return false;
        return true;
    }

    public void onBackPressed(){
        Intent returnIntent = new Intent();
        returnIntent.putExtra("array_nota", array_nota);
        returnIntent.putExtra("array_nota2", array_nota2);
        returnIntent.putExtra("array_nota3", array_nota3);
        setResult(RESULT_OK, returnIntent);
        finish();
    }
}
