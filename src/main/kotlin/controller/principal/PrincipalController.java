package controller.principal;

import kotlin.jvm.internal.markers.KMutableList;
import model.analizador.lexico.AnalizadorAFD;
import model.analizador.lexico.token.Token;
import view.principal.PrincipalView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PrincipalController implements ActionListener {
    private PrincipalView principalView;
    private ArrayList tokens;

    public PrincipalController(PrincipalView principalView) {
        this.principalView = principalView;

        principalView.getjMINuevo().addActionListener(this);
        principalView.getjMIAbrir().addActionListener(this);
        principalView.getjMIGuardar().addActionListener(this);
        principalView.getjMIGuardarComo().addActionListener(this);
        principalView.getjMIAnalizar().addActionListener(this);
        principalView.getjMIReportes().addActionListener(this);
        principalView.getjMIGraficos().addActionListener(this);
    }

    public void iniciar() {
        this.principalView.pack();
        principalView.setResizable(false);
        principalView.setLocationRelativeTo(null);
        this.principalView.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.principalView.setTitle("Parser-py editor");
        this.principalView.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == principalView.getjMINuevo()) {}
        if (e.getSource() == principalView.getjMIAbrir()) {}
        if (e.getSource() == principalView.getjMIGuardar()) {}
        if (e.getSource() == principalView.getjMIGuardarComo()) {}
        if (e.getSource() == principalView.getjMIAnalizar()) {
            String texto = principalView.getjTAEditor().getText().toString();
            AnalizadorAFD analizadorAFD = new AnalizadorAFD();
            tokens = analizadorAFD.generarTokens(texto);
            for (Object tk :
                    tokens) {
                System.out.println(tk.toString());
            }
            System.out.println("A ver que sale");
        }
        if (e.getSource() == principalView.getjMIReportes()) {}
        if (e.getSource() == principalView.getjMIGraficos()) {}
    }
}
