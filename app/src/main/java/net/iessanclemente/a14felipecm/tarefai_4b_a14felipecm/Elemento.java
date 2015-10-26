package net.iessanclemente.a14felipecm.tarefai_4b_a14felipecm;

import java.io.Serializable;

/**
 * Created by felipe on 25/10/15.
 */
public class Elemento implements Serializable{


    private String nombre;
    private boolean marcado;

    public Elemento(String nombre) {
        this.nombre = nombre;
        this.marcado=false;
    }

    public Elemento(String nombre, boolean marcado) {
        this.nombre = nombre;
        this.marcado = marcado;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean isChecked() {
        return marcado;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setMarcado(boolean marcado) {
        this.marcado = marcado;
    }
}
