package Clases;

import java.util.HashMap;
import java.util.Map;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Luisf
 */
public class Cuarto {

    private int valor;

    public Cuarto(int valor) {
        this.valor = valor;
    }

    // Getter
    public int getValor() {
        return valor;
    }

    // Suma
    public Cuarto sumar(Cuarto otro) {
        return new Cuarto(this.valor + otro.valor);
    }

    // Resta
    public Cuarto restar(Cuarto otro) {
        return new Cuarto(this.valor - otro.valor);
    }

    // Multiplicación
    public Cuarto multiplicar(Cuarto otro) {
        return new Cuarto(this.valor * otro.valor);
    }

    // División
    public Cuarto dividir(Cuarto otro) {
        if (otro.valor == 0) {
            throw new ArithmeticException("División entre cero");
        }
        return new Cuarto(this.valor / otro.valor);
    }

    @Override
    public String toString() {
        return String.valueOf(valor);
    }
}
