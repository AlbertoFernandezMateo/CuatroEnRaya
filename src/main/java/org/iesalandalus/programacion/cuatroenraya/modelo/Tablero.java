package org.iesalandalus.programacion.cuatroenraya.modelo;

import java.util.Objects;

public class Tablero {

    public static final int FILAS = 6;
    public static final int COLUMNAS = 7;
    public static int FICHAS_IGUALES_CONSECUTIVAS_NECESARIAS = 4 ;
    private Casilla[][] Tablero;

    public Tablero() {
        Tablero = new Casilla[FILAS][COLUMNAS];
    }

    private boolean columnaVacia(int columna) {
        return (Tablero[0][columna].estaOcupada());
    }

    public boolean estaVacio() {
        int vacias = 0;
        for (int i = 0; i < COLUMNAS; i++) {
            if (columnaVacia(i)) {
                vacias++;
            }
        }
        return (vacias == COLUMNAS);
    }
    private boolean columnaLlena(int columna){
        return (!(Tablero[FILAS][columna].estaOcupada()));
    }
    public boolean estaLleno(){
        int llena = 0;

        for (int i = 0; i < COLUMNAS; i++) {
            if (columnaLlena(i)) {
                llena++;
            }
        }
        return (llena == COLUMNAS);
    }
    private void comprobarFicha(Ficha ficha) throws CuatroEnRayaExcepcion{
        Objects.requireNonNull(ficha, " El valor de la ficha no puede ser nulo. ");
    }
    private void comprobarColumna(int columna)  {
        if (columna < 0 || columna > COLUMNAS){
            throw new IllegalArgumentException("");
        }
    }

    private int getPrimeraFilaVacia(int columna){
        int filaVacia = 0;

        for (int i = 0; i < FILAS; i++){
            if (Tablero[i][columna].estaOcupada()){
                filaVacia = i;
                i = FILAS - 1;
            }
        }

        return filaVacia;
    }
    private boolean objetivoAlcanzado(int fichasIgualesConsecutivas){
        return (fichasIgualesConsecutivas >= FICHAS_IGUALES_CONSECUTIVAS_NECESARIAS);
    }
    private int menor(int fila, int columna){
        int menor;

        if (fila < columna){
            menor = fila;
        } else  {
           menor = columna;
        }
        return menor;
    }
    public boolean introducirFicha(int columna, Ficha ficha) throws CuatroEnRayaExcepcion{

    }


}

