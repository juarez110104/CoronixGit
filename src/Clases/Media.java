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
public class Media {

    private String valor;
    private static Map<String, Media> variables = new HashMap<>();

    public Media(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }

    public Media concatenar(Media otro) {
        return new Media(this.valor + otro.valor);
    }

    @Override
    public String toString() {
        return valor;
    }

    // 🔥 SIMULACIÓN COMPLETA (tipo JFlex + CUP)
    public static void procesarLinea(String linea) {

        linea = linea.trim();

        // 🔹 DETECTAR palabra clave (léxico)
        if (linea.startsWith("media")) {

            // 🔴 ERROR SINTÁCTICO
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
            String valor = partes[3];

            // 🔴 ERROR SEMÁNTICO (re-declaración)
            if (variables.containsKey(nombre)) {
                System.out.println("❌ Error semántico: variable ya declarada -> " + nombre);
                return;
            }

            // 🔴 ERROR LÉXICO (string mal formado)
            if (!valor.startsWith("\"") || !valor.endsWith("\"")) {
                System.out.println("❌ Error léxico: string inválido -> " + valor);
                return;
            }

            valor = valor.substring(1, valor.length() - 1);

            variables.put(nombre, new Media(valor));

            System.out.println("✔ media creada: " + nombre + " = " + valor);
        } // 🔹 OPERACIÓN (concatenación)
        else if (linea.contains("+")) {

            String[] partes = linea.split("\\+");

            if (partes.length != 2) {
                System.out.println("❌ Error sintáctico: operación inválida");
                return;
            }

            Media a = obtener(partes[0].trim());
            Media b = obtener(partes[1].trim());

            if (a == null || b == null) {
                return;
            }

            Media resultado = a.concatenar(b);

            System.out.println("✔ Resultado: " + resultado);
        } // 🔴 ERROR LÉXICO
        else {
            System.out.println("❌ Error léxico: instrucción no reconocida");
        }
    }

    // 🔹 VALIDACIÓN SEMÁNTICA
    private static Media obtener(String nombre) {

        if (!variables.containsKey(nombre)) {
            System.out.println("❌ Error semántico: variable no definida -> " + nombre);
            return null;
        }

        return variables.get(nombre);
    }
    
}
