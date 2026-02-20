package org.iesalandalus.programacion.cuatroenraya.modelo;

import java.util.Objects;

public class Casilla {
    private Ficha ficha;
    public Casilla() {
        ficha = null;
    }

    public Ficha getFicha() {
        return ficha;
    }

    public void setFicha(Ficha ficha) throws CuatroEnRayaExcepcion{
        Objects.requireNonNull(ficha, "No se puede poner una ficha nula.");
        if (!estaOcupada()){
            this.ficha = ficha;
        } else {
            throw new CuatroEnRayaExcepcion("La casilla ya contiene una ficha.");
        }

    }
    public boolean estaOcupada(){
        return ficha != null;
    }

    @Override
    public String toString() {
        return (estaOcupada()) ? String.format("%s", ficha) : " ";

    }
}
