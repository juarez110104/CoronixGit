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
public class Mega {

    private double valor;
    private static Map<String, Mega> variables = new HashMap<>();

    public Mega(double valor) {
        this.valor = valor;
    }

    public double getValor() {
        return valor;
    }

    public Mega sumar(Mega otro) {
        return new Mega(this.valor + otro.valor);
    }

    public Mega restar(Mega otro) {
        return new Mega(this.valor - otro.valor);
    }

    public Mega multiplicar(Mega otro) {
        return new Mega(this.valor * otro.valor);
    }

    public Mega dividir(Mega otro) {
        if (otro.valor == 0) {
            throw new ArithmeticException("❌ Error semántico: división entre cero");
        }
        return new Mega(this.valor / otro.valor);
    }

    @Override
    public String toString() {
        return String.valueOf(valor);
    }

    // 🔥 SIMULACIÓN COMPLETA
    public static void procesarLinea(String linea) {

        linea = linea.trim();

        // 🔹 DETECCIÓN LÉXICA
        if (linea.startsWith("Mega")) {

            if (!linea.contains("=") || !linea.endsWith(";")) {
                System.out.println("❌ Error sintáctico: estructura inválida");
                return;
            }

            String[] partes = linea.replace(";", "").split(" ", 4);

            if (partes.length < 4) {
                System.out.println("❌ Error sintáctico: declaración incompleta");
                return;
            }

            String nombre = partes[1];
            String valorStr = partes[3];

            // 🔴 ERROR SEMÁNTICO (re-declaración)
            if (variables.containsKey(nombre)) {
                System.out.println("❌ Error semántico: variable ya declarada -> " + nombre);
                return;
            }

            // 🔴 ERROR LÉXICO (no es número válido)
            double valor;
            try {
                valor = Double.parseDouble(valorStr);
            } catch (Exception e) {
                System.out.println("❌ Error léxico: número inválido -> " + valorStr);
                return;
            }

            variables.put(nombre, new Mega(valor));

            System.out.println("✔ Mega creada: " + nombre + " = " + valor);
        }

        // 🔹 OPERACIONES
        else if (linea.contains("+") || linea.contains("-") || linea.contains("*") || linea.contains("/")) {

            String operador = "";

            if (linea.contains("+")) operador = "+";
            else if (linea.contains("-")) operador = "-";
            else if (linea.contains("*")) operador = "*";
            else if (linea.contains("/")) operador = "/";

            String[] partes = linea.split("\\" + operador);

            if (partes.length != 2) {
                System.out.println("❌ Error sintáctico: operación inválida");
                return;
            }

            Mega a = obtener(partes[0].trim());
            Mega b = obtener(partes[1].trim());

            if (a == null || b == null) return;

            Mega resultado;

            try {
                switch (operador) {
                    case "+": resultado = a.sumar(b); break;
                    case "-": resultado = a.restar(b); break;
                    case "*": resultado = a.multiplicar(b); break;
                    case "/": resultado = a.dividir(b); break;
                    default:
                        System.out.println("❌ Error sintáctico: operador desconocido");
                        return;
                }

                System.out.println("✔ Resultado: " + resultado);

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        // 🔴 ERROR LÉXICO
        else {
            System.out.println("❌ Error léxico: instrucción no reconocida");
        }
    }

    // 🔹 VALIDACIÓN SEMÁNTICA
    private static Mega obtener(String nombre) {

        if (!variables.containsKey(nombre)) {
            System.out.println("❌ Error semántico: variable no definida -> " + nombre);
            return null;
        }

        return variables.get(nombre);
    }
}
