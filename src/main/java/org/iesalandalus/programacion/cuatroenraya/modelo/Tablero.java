package org.iesalandalus.programacion.cuatroenraya.modelo;

import java.util.Arrays;
import java.util.Objects;

public class Tablero {

    public static final int FILAS = 6;
    public static final int COLUMNAS = 7;
    public static int FICHAS_IGUALES_CONSECUTIVAS_NECESARIAS = 4;

    private Casilla[][] Tablero;

    public Tablero() {
        Tablero = new Casilla[FILAS][COLUMNAS];
    }

    private boolean columnaVacia(int columna) {
        return (!Tablero[0][columna].estaOcupada());
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

    private boolean columnaLlena(int columna) {
        return (!(Tablero[FILAS][columna].estaOcupada()));
    }

    public boolean estaLleno() {
        int llena = 0;

        for (int i = 0; i < COLUMNAS; i++) {
            if (columnaLlena(i)) {
                llena++;
            }
        }
        return (llena == COLUMNAS);
    }

    private void comprobarFicha(Ficha ficha) throws CuatroEnRayaExcepcion {
        Objects.requireNonNull(ficha, " El valor de la ficha no puede ser nulo. ");
    }

    private void comprobarColumna(int columna) {
        if (columna < 0 || columna > COLUMNAS) {
            throw new IllegalArgumentException("");
        }
    }

    private int getPrimeraFilaVacia(int columna) {
        int filaVacia = 0;

        for (int i = 0; i < FILAS; i++) {
            if (Tablero[i][columna].estaOcupada()) {
                filaVacia = i;
                i = FILAS - 1;
            }
        }

        return filaVacia;
    }

    private boolean objetivoAlcanzado(int fichasIgualesConsecutivas) {
        return (fichasIgualesConsecutivas >= FICHAS_IGUALES_CONSECUTIVAS_NECESARIAS);
    }

    private int menor(int fila, int columna) {
        int menor;

        if (fila < columna) {
            menor = fila;
        } else {
            menor = columna;
        }
        return menor;
    }

    public boolean introducirFicha(int columna, Ficha ficha) throws CuatroEnRayaExcepcion {
        comprobarColumna(columna);
        comprobarFicha(ficha);

        if (columnaLlena(columna)) {
            throw new CuatroEnRayaExcepcion("La columna esta llena.");

        }
        int fila = getPrimeraFilaVacia(columna);
        Tablero[fila][columna].setFicha(ficha);
        return comprobarTirada(fila, columna);
    }

    private boolean comprobarHorizontal(int fila, Ficha ficha) {
        int iguales = 0;

        for(int i = 0; i < 4; i++ ){
            if(ficha.equals(ficha)){
                iguales++;
            }
        }
    }

    private boolean comprobarVertical(int columna, Ficha ficha){
        int racha = 0;
        int mejorRacha = 0;

        for (int i = 0; i < FILAS; i++) {
            if (Tablero[i][columna].getFicha() == ficha) {
                racha++;
            } else {
                if (racha > mejorRacha) {
                    mejorRacha = racha;
                    racha = 0;
                }
            }
        }
        if (racha > mejorRacha) {
            mejorRacha = racha;
        }
        return  objetivoAlcanzado(mejorRacha);
    }
    private boolean comprobarDiagonalNE(int filaActual, int columnaActual, Ficha ficha){



    }
    private boolean comprobarDiagonalNO(int filaActual, int columnaActual, Ficha ficha){
        int fichasIgualesConsecutivas = 0;
        int desplazamiento = menor(filaActual, COLUMNAS - 1 - columnaActual);
        int filaInicial = filaActual - desplazamiento;
        int columnaInicial = columnaActual + desplazamiento;
        for (int fila = filaInicial, columna = columnaInicial; !objetivoAlcanzado(fichasIgualesConsecutivas) && (fila < FILAS && columna >= 0); fila ++, columna--){
            if(Tablero[fila][columna].estaOcupada() && Tablero[fila][columna].getFicha().equals(ficha)){
                fichasIgualesConsecutivas++;
            }else{
                fichasIgualesConsecutivas = 0;
            }
        }
        return objetivoAlcanzado(fichasIgualesConsecutivas);

    }

    private boolean comprobarTirada(int fila, int columa){
        Ficha ficha = Tablero[fila][columa].getFicha();
        return comprobarHorizontal(fila, ficha) ||
                comprobarVertical(fila, ficha) ||
                comprobarDiagonalNE(fila, columa, ficha) ||
                comprobarDiagonalNO(fila, columa, ficha);

    }

    @Override
    public String toString() {
        return String.format("Tablero (Tablero=%s)", Arrays.toString(Tablero));
    }
}

