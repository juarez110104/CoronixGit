package Analizador;

public class Token {

    public String token;
    public String lexema;
    public String patron;
    public String reservada;

    public Token(String t, String l, String p, String r) {
        token = t;
        lexema = l;
        patron = p;
        reservada = r;
    }
}