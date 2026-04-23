/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.util.*;

/**
 *
 * @author zvela
 */
public class ClasePrincipal {

    static Map<String, Integer> variables = new HashMap<>();

    public static void main(String[] args) {

        String[] codigo = {
            "cuarto a = 5;",
            "cuarto b = 3;",
            "a + b"
        };

        for (String linea : codigo) {
            procesarLinea(linea);
        }
    }

    public static void procesarLinea(String linea) {
        linea = linea.trim();

        // Simular declaración: cuarto a = 5;
        if (linea.startsWith("cuarto")) {
            String[] partes = linea.replace(";", "").split(" ");

            String nombre = partes[1];
            int valor = Integer.parseInt(partes[3]);

            variables.put(nombre, valor);

            System.out.println("Variable creada: " + nombre + " = " + valor);
        } // Simular operación: a + b
        else if (linea.contains("+")) {
            String[] partes = linea.split("\\+");

            int val1 = obtenerValor(partes[0].trim());
            int val2 = obtenerValor(partes[1].trim());

            System.out.println("Resultado: " + (val1 + val2));
        }
    }

    public static int obtenerValor(String token) {
        if (variables.containsKey(token)) {
            return variables.get(token);
        }
        return Integer.parseInt(token);
    }
}
