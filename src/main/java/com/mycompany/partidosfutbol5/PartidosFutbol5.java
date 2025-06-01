/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.partidosfutbol5;
import CONTROLADOR.ControladorLogIn;
import VISTA.InicioSesion.LogInVista;
import VISTA.InicioSesion.RegistroVista;

/**
 *
 * @author danie
 */
public class PartidosFutbol5 {

    public static void main(String[] args) {
        
        LogInVista vistaLogin = new LogInVista();
        RegistroVista vistaRegistro = new VISTA.InicioSesion.RegistroVista();
        ControladorLogIn controlador = new ControladorLogIn(vistaLogin, vistaRegistro);

        vistaLogin.setVisible(true);
    }
}
