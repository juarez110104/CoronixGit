package Interfaz;

import Clases.ClasePrincipal;
import Analizador.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class InterfazCoronix extends JFrame {

    JTextArea editor = new JTextArea();
    JTextArea consola = new JTextArea();

    DefaultTableModel t1 = new DefaultTableModel(
            new String[]{"Token","Lexema","Patrón","Reservada"},0);

    DefaultTableModel t2 = new DefaultTableModel(
            new String[]{"Elemento","Tipo"},0);

    public InterfazCoronix(){

        setTitle("Coronix IDE PRO");
        setSize(1100,650);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JTable tabla1 = new JTable(t1);
        JTable tabla2 = new JTable(t2);

        JButton run = new JButton("▶ Ejecutar");

        run.addActionListener(e -> ejecutar());

        JSplitPane split1 = new JSplitPane(
                JSplitPane.VERTICAL_SPLIT,
                new JScrollPane(tabla1),
                new JScrollPane(tabla2)
        );

        JSplitPane split2 = new JSplitPane(
                JSplitPane.VERTICAL_SPLIT,
                split1,
                new JScrollPane(consola)
        );

        JSplitPane main = new JSplitPane(
                JSplitPane.HORIZONTAL_SPLIT,
                new JScrollPane(editor),
                split2
        );

        add(run, BorderLayout.NORTH);
        add(main, BorderLayout.CENTER);

        setVisible(true);
    }

    void ejecutar(){

        t1.setRowCount(0);
        t2.setRowCount(0);
        consola.setText("");

        String[] lineas = editor.getText().split("\n");

        for(int i=0;i<lineas.length;i++){

            String l = lineas[i].trim();
            if(l.isEmpty()) continue;

            try{

                for(Token tk:ClasePrincipal.obtenerTokens(l))
                    t1.addRow(new Object[]{tk.token,tk.lexema,tk.patron,tk.reservada});

                for(String[] c:ClasePrincipal.clasificacion(l))
                    t2.addRow(c);

                String r = ClasePrincipal.procesarLinea(l,i+1);
                consola.append("✔ "+r+"\n");

            }catch(ErrorC e){
                consola.append("❌ "+e+"\n");
            }
        }
    }
}