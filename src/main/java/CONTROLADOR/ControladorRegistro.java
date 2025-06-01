/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CONTROLADOR;
import VISTA.InicioSesion.RegistroVista;
import VISTA.InicioSesion.LogInVista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author danie
 */
public class ControladorRegistro implements ActionListener{
    
    private LogInVista vistaLogin;
    private RegistroVista vistaRegistro;
    
    public ControladorRegistro(LogInVista vistaLogin, RegistroVista vistaRegistro){
        this.vistaLogin = vistaLogin;
        this.vistaRegistro = vistaRegistro;
        
        this.vistaRegistro.registrarseBoton.addActionListener(this);
        this.vistaRegistro.atrasBoton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vistaRegistro.registrarseBoton) {
            System.out.println("Botón registrar presionado");
            // TODO: validar credenciales
        } else if (e.getSource() == vistaRegistro.atrasBoton) {
            System.out.println("Botón Nuevo atras presionado");
            vistaLogin.setVisible(true);
            vistaRegistro.setVisible(false);
        }
    }
    
}
