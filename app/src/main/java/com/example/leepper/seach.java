package com.example.leepper;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class seach extends AppCompatActivity {

    String [] array_noms;
    int [] array_nota;
    int [] array_nota2;
    int [] array_nota3;
    float [] calculos;
    int contador;

    Button buttone;
    EditText nom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seach);

        buttone = findViewById(R.id.seachnombre);
        //nom = findViewById(R.id.nom);

        Intent recogerDatos = getIntent();
        array_noms = recogerDatos.getStringArrayExtra("array_noms");
        array_nota = recogerDatos.getIntArrayExtra("array_nota");
        array_nota2 = recogerDatos.getIntArrayExtra("array_nota2");
        array_nota3 = recogerDatos.getIntArrayExtra("array_nota3");
        contador = recogerDatos.getIntExtra("contador", 0);

        calculos = calco(contador,array_nota,array_nota2,array_nota3);

        final Button button = findViewById(R.id.seachnombre);
        final TextView nom = findViewById(R.id.nombre);

        button.setOnClickListener(new View.OnClickListener() {
            //EditText nomAlumne = findViewById(R.id.nom);

            //String nomGuardar = nomAlumne.getText().toString();
            @Override
            public void onClick(View v) {
                String nomGuardar = nom.getText().toString().trim();
                Log.d("PRUEBA", "Esta es la prueba --->>>>> "+nomGuardar);
                String search = nomGuardar;
                boolean existe = false;

                int posicionExiste = -1;
                for (int i = 0; i < array_noms.length; i++) {
                    String valorarray = array_noms[i];
                    if (search.equals(valorarray)) {
                        existe = true;
                        posicionExiste = i;
                        break;

                    }
                }
                if (existe) {
                    float mid =  calculos[posicionExiste];
                    DecimalFormat df = new DecimalFormat("#.#");
                    df.setRoundingMode(RoundingMode.CEILING);

                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(seach.this);
                    alertDialogBuilder.setTitle("Alumno: "+search);

                    alertDialogBuilder
                            .setMessage("N1= "+array_nota[posicionExiste] + ", N2= " + array_nota2[posicionExiste] + ", N3= " + array_nota3[posicionExiste]+", Media= "+df.format(mid))
                            .setCancelable(false)
                            .setNegativeButton("Cerrar", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            }).create().show();
                } else {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(seach.this);
                    alertDialogBuilder.setTitle("Información");

                    alertDialogBuilder
                            .setMessage("EL ALUMNO "+search+" NO EXISTE!!!")
                            .setCancelable(false)
                            .setNegativeButton("Cerrar",new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int id) {
                                    dialog.cancel();
                                }
                            }).create().show();
                }
            }
        });
    }


    //hay que buscar una forma de no repetir tanto el bucle este y solicitar estos datos desde aqui a otra ventana
    public static float [] calco(int contador, int [] array_nota, int [] array_nota2, int [] array_nota3){
        float [] result = new float [contador];
        float suma;
        for(int i=0;i<contador;i++){
            suma=array_nota[i]+array_nota2[i]+array_nota3[i];
            result [i] = suma/3.0f;
            suma=0;
            Log.d("PRUEBA", "Esta es la prueba "+result[i]);
        }
        return result;

    }

}
