package com.example.leepper;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

public class addusers extends AppCompatActivity {

    String [] array_noms;
    int [] array_nota;
    int [] array_nota2;
    int [] array_nota3;
    int contador;
    int contnom = 0;

    Button bottonsend;
    EditText nombre,nota;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addusers);

        bottonsend = findViewById(R.id.btnAfegir);
        nombre = findViewById(R.id.nombre);
        nota = findViewById(R.id.nota);

        Intent recogerDatos = getIntent();

        array_noms = recogerDatos.getStringArrayExtra("array_noms");
        array_nota = recogerDatos.getIntArrayExtra("array_nota");
        array_nota2 = recogerDatos.getIntArrayExtra("array_nota2");
        array_nota3 = recogerDatos.getIntArrayExtra("array_nota3");
        contador = recogerDatos.getIntExtra("contador", 0);

        final Button btnAfegir = findViewById(R.id.btnAfegir);

        btnAfegir.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText nomAlumne = findViewById(R.id.nombre);
                EditText notaAlumne = findViewById(R.id.nota);

                String nomGuardar = nomAlumne.getText().toString().trim();

                if (notaAlumne.getText().toString().length() == 0){
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(addusers.this);

                    alertDialogBuilder.setTitle("Información");

                    alertDialogBuilder
                            .setMessage("La nota no puede estar vacía!")
                            .setCancelable(false)
                            .setNegativeButton("Aceptar",new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int id) {
                                    dialog.cancel();
                                }
                            }).create().show();
                } else {
                    int notaGuardar = Integer.parseInt(notaAlumne.getText().toString());

                    if ((notaGuardar < 1) || (notaGuardar > 10)) {
                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(addusers.this);

                        alertDialogBuilder.setTitle("Información");
                        alertDialogBuilder
                                .setMessage("LA NOTA DEBE SER MAYOR QUE 0 Y MENOR QUE 10!")
                                .setCancelable(false)
                                .setNegativeButton("Aceptar",new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        dialog.cancel();
                                    }
                                }).create().show();

                    } else if ((vacionulo(nomGuardar))) {
                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(addusers.this);

                        alertDialogBuilder.setTitle("Información");
                        alertDialogBuilder
                                .setMessage("EL NOMBRE NO PUEDE ESTAR VACIO")
                                .setCancelable(false)
                                .setNegativeButton("Aceptar",new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        dialog.cancel();
                                    }
                                }).create().show();

                    }else if (contnom==0){
                        array_noms[contador] = nombre.getText().toString();
                        array_nota[contador] = Integer.parseInt(nota.getText().toString());
                        nombre.setEnabled(false);
                        nota.setHint("Nota 2");
                        nota.setText("");
                        contnom++;
                    }else if (contnom==1){
                        array_nota2[contador] = Integer.parseInt(nota.getText().toString());
                        nota.setHint("Nota 3");
                        nota.setText("");
                        contnom++;
                    }else if (contnom==2){
                        array_nota3[contador] = Integer.parseInt(nota.getText().toString());
                        Toast.makeText(getApplicationContext(),"Alumno añadido correctamente!",Toast.LENGTH_SHORT).show();
                        nombre.setEnabled(true);
                        nota.setHint("Nota 1");
                        nota.setText("");
                        nombre.setText("");
                        contnom=0;
                        contador++;
                    }
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
        returnIntent.putExtra("array_noms", array_noms);
        returnIntent.putExtra("array_nota", array_nota);
        returnIntent.putExtra("array_nota2", array_nota2);
        returnIntent.putExtra("array_nota3", array_nota3);
        returnIntent.putExtra("contador", contador);
        setResult(RESULT_OK, returnIntent);
        finish();
    }

}
