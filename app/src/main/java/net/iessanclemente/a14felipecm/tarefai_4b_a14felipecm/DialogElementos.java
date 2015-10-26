package net.iessanclemente.a14felipecm.tarefai_4b_a14felipecm;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.DialogFragment;

import android.os.Bundle;

public class DialogElementos extends DialogFragment {
    private Intent inten = new Intent();
    String[] arrayNombres;
    boolean[] arrayCheckeds;


    @Override
    public Dialog onCreateDialog(Bundle estado){

        AlertDialog.Builder dialogo;
        //Cojo los argumentos que les paso en el bundle
        dialogo = new AlertDialog.Builder(getActivity());
        arrayNombres = getArguments().getStringArray("elementos");
        arrayCheckeds = getArguments().getBooleanArray("check");
        dialogo = new AlertDialog.Builder(getActivity());
        dialogo.setIcon(android.R.drawable.ic_dialog_info);
        dialogo.setTitle("Selecciona las tareas pendientes");

        dialogo.setMultiChoiceItems(arrayNombres, arrayCheckeds, new DialogInterface.OnMultiChoiceClickListener() {
            public void onClick(DialogInterface dialog, int opcion, boolean isChecked) {
                // Evento que ocorre cando o usuario selecciona unha opción
                if (isChecked) {
                    arrayCheckeds[opcion] = true;
                }
                else{
                    arrayCheckeds[opcion] = false;
                }
            }
        });
        dialogo.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int boton) {
                Principal.actualizarTextview(arrayCheckeds);
                dismiss();
            }
        });
        dialogo.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int boton) {
                dismiss();
            }
        });
        return dialogo.create();
    }//FIN onCreateDialog


}
