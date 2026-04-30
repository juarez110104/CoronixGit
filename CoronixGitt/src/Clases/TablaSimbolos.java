package Clases;

import java.util.*;
import Analizador.ErrorC;

public class TablaSimbolos {

    public static class Simbolo {
        public String tipo;
        public Object valor;

        public Simbolo(String t, Object v) {
            tipo = t;
            valor = v;
        }
    }

    private Map<String, Simbolo> tabla = new HashMap<>();

    public void guardar(String n, String t, Object v) {
        tabla.put(n, new Simbolo(t, v));
    }

    // 🔥 AQUÍ ESTÁ LA CORRECCIÓN IMPORTANTE
    public Simbolo obtener(String n, int linea) throws ErrorC {
        if (!tabla.containsKey(n))
            throw new ErrorC(ErrorC.Tipo.SEMANTICO, linea,
                    "Variable no definida: " + n);
        return tabla.get(n);
    }
}