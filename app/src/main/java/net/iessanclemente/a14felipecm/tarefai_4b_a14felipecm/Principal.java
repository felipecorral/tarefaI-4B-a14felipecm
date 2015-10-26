package net.iessanclemente.a14felipecm.tarefai_4b_a14felipecm;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Principal extends FragmentActivity {
    public static ArrayList<Elemento> arrayElementos = new ArrayList<Elemento>();
    private EditText editElemento;
    public static TextView txtElementos;
    private Button btnShow;
    // Variable para crear as ventás de diálogo
    AlertDialog.Builder venta;
    private DialogElementos dialog_fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        Log.v("ESTADO", "onCreate");
        //Inicializo los elementos del xml, los objetos Elemento y el arraylist
        editElemento = (EditText) findViewById(R.id.edit_insertar);
        txtElementos = (TextView) findViewById(R.id.textv_marcados);
        btnShow = (Button) findViewById(R.id.btn_showd);

        dialog_fragment = new DialogElementos();

        if (arrayElementos.size()<1){
            Elemento E1 = new Elemento("Hacer la compra",true);
            arrayElementos.add(E1);
            Elemento E2 = new Elemento("Ir al banco");
            arrayElementos.add(E2);
            Elemento E3 = new Elemento("Hacer la colada",true);
            arrayElementos.add(E3);
        }
        txtElementos.setText(mostrarElementos(arrayElementos));


        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(1);
            }
        });
    }//Fin Oncreate

    /*
    * Metodo para llamar al 2º activity, meto dos arrays en un bundle y se lo paso
     */
    public void lanzarSegunda(View v){
        Bundle datos = new Bundle();
        datos.putStringArray("elementos", sacarNomes(arrayElementos));
        datos.putBooleanArray("check", sacarcheck(arrayElementos));
        FragmentManager fm = getSupportFragmentManager();
        dialog_fragment.setArguments(datos);
        dialog_fragment.show(fm, "");
    }




    //metodo para el showdialog
    protected Dialog onCreateDialog(int op) {
        final String[] arrayNombres = sacarNomes(arrayElementos);
        final boolean[] arrayCheckeds = sacarcheck(arrayElementos);
        venta = new AlertDialog.Builder(this);
        venta.setIcon(android.R.drawable.ic_dialog_info);
        venta.setTitle("Selecciona las tareas importantes");
        venta.setCancelable(false);

        venta.setMultiChoiceItems(arrayNombres, arrayCheckeds, new DialogInterface.OnMultiChoiceClickListener() {
            public void onClick(DialogInterface dialog, int opcion, boolean isChecked) {
                // Evento que ocorre cando o usuario selecciona unha opción
                if (isChecked) {
                    arrayCheckeds[opcion] = true;
                } else {
                    arrayCheckeds[opcion] = false;
                }
            }
        });
        venta.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int boton) {
                txtElementos.setText(mostrarElementos(actualizarArrayl(arrayCheckeds)));
                removeDialog(1);
            }
        });
        venta.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int boton) {
                removeDialog(1);
            }
        });
        return venta.create();
    }

    //metodo del boton que añade un elemento al arraylist
    public  void anhadirElemento(View v){
        if (!editElemento.getText().toString().equals("")){
            Elemento element = new Elemento (editElemento.getText().toString());
            arrayElementos.add(element);
            editElemento.setText("");
        }else{
            Toast.makeText(Principal.this, "Introduce una tarea.", Toast.LENGTH_SHORT).show();
        }
    }//Fin anhadir

    //metodo que recibe un array de boolean, de elementos checkeados y devuelve el arraylist actualizado
    public static ArrayList<Elemento> actualizarArrayl(boolean[] checks){
        for (int i=0;i<arrayElementos.size();i++){
            arrayElementos.get(i).setMarcado(checks[i]);
        }
        return arrayElementos;
    }//Fin actualizarArray


    //Metodo que devuelve el array de string del arraylist de elementos
    public String[] sacarNomes(ArrayList<Elemento> ale){
        String[] nomesarr = new String[ale.size()];
        for (int i=0;i<ale.size();i++){
            nomesarr[i] = ale.get(i).getNombre();
        }
        return nomesarr;
    }//Fin sacarNombres

    //Metodo que devuelve el array de booleanos del arraylist de elementos
    public boolean[] sacarcheck(ArrayList<Elemento> ale){
        boolean[] checkeados = new boolean[ale.size()];
        for (int i=0;i<ale.size();i++){
            checkeados[i]=ale.get(i).isChecked();
        }
        return checkeados;
    }//Fin sacarChecks


    //metodo q recibe el arraylist de elementos y devuelve un string con los que estan marcados
    public static String mostrarElementos(ArrayList<Elemento> ale){
        String listaEle="";
        for(int i=0; i<ale.size();i++){
            if(ale.get(i).isChecked()){
                listaEle+=ale.get(i).getNombre().toString()+"\n";
            }
        }
        return listaEle;
    }//Fin mostrar

    //metodo que actualiza el textview al volver del fragment
    public static void actualizarTextview(boolean[] checks){
        arrayElementos = actualizarArrayl(checks);
        txtElementos.setText(mostrarElementos(arrayElementos));
    }


    @Override
    protected void onSaveInstanceState(Bundle estado) {
        Log.v("ESTADO","onSaveInstanceState");
        super.onSaveInstanceState(estado);
        //Destruyo el dialog
        this.removeDialog(1);

    }//Fin


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState){
        Log.v("ESTADO", "onRestoreInstanceState");
        super.onRestoreInstanceState(savedInstanceState);
        //txtElementos.setText(mostrarElementos(arrayElementos));

    }//Fin

    @Override
    protected void onResume() {
        super.onResume();
        Log.v("ESTADO", "onResume");
    }


}
