package CONTROLADOR;

import VISTA.VistaOpcionesJugador.MenuJugador;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controlador para el menú de opciones de Jugador.
 * Registra listeners en cada botón y redirige a la vista/controlador correspondiente.
 */
public class ControladorMenuJugador implements ActionListener {

    private final MenuJugador vistaMenuJugador;

    public ControladorMenuJugador(MenuJugador vistaMenuJugador) {
        this.vistaMenuJugador = vistaMenuJugador;

        // Configuración inicial de la ventana
        this.vistaMenuJugador.setVisible(true);
        this.vistaMenuJugador.setLocationRelativeTo(null);
        this.vistaMenuJugador.setResizable(false);
        this.vistaMenuJugador.setTitle("Menú Jugador");

        // Registrar este controlador como listener para cada botón
        this.vistaMenuJugador.proximoPartidoButton.addActionListener(this);
        this.vistaMenuJugador.calificacionUltimoButton.addActionListener(this);
        this.vistaMenuJugador.miPerfilButton.addActionListener(this);
        this.vistaMenuJugador.mailButton.addActionListener(this);
        this.vistaMenuJugador.reglasButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == vistaMenuJugador.proximoPartidoButton) {
            abrirProximoPartido();
        }
        else if (source == vistaMenuJugador.calificacionUltimoButton) {
            abrirCalificacionUltimoPartido();
        }
        else if (source == vistaMenuJugador.miPerfilButton) {
            abrirMiPerfil();
        }
        else if (source == vistaMenuJugador.mailButton) {
            abrirMail();
        }
        else if (source == vistaMenuJugador.reglasButton) {
            abrirReglas();
        }
    }

    // ===========================
    // Métodos auxiliares para cada acción
    // ===========================

    /**
     * Abre la vista/controlador que muestra el próximo partido del jugador.
     */
    private void abrirProximoPartido() {
        // TODO: Instanciar ProximoPartidoVista y su controlador
        // Ejemplo:
        // ProximoPartidoVista vista = new ProximoPartidoVista();
        // new ControladorProximoPartido(vista);
        System.out.println("Usuario seleccionó: Próximo Partido");
    }

    /**
     * Abre la vista/controlador que permite calificar el último partido.
     */
    private void abrirCalificacionUltimoPartido() {
        // TODO: Instanciar CalificacionUltimoPartidoVista y su controlador
        // Ejemplo:
        // CalificacionUltimoPartidoVista vista = new CalificacionUltimoPartidoVista();
        // new ControladorCalificacionUltimoPartido(vista);
        System.out.println("Usuario seleccionó: Calificación Último Partido");
    }

    /**
     * Abre la vista/controlador del perfil del jugador.
     */
    private void abrirMiPerfil() {
        // TODO: Instanciar MiPerfilVista y su controlador
        // Ejemplo:
        // MiPerfilVista vista = new MiPerfilVista();
        // new ControladorMiPerfil(vista);
        System.out.println("Usuario seleccionó: Mi Perfil");
    }

    /**
     * Abre la funcionalidad para enviar mail (o ver bandeja de entrada).
     */
    private void abrirMail() {
        // TODO: Instanciar MailVista y su controlador
        // Ejemplo:
        // MailVista vista = new MailVista();
        // new ControladorMail(vista);
        System.out.println("Usuario seleccionó: Mail");
    }

    /**
     * Abre la vista que muestra las reglas del juego.
     */
    private void abrirReglas() {
        // TODO: Instanciar ReglasVista y su controlador
        // Ejemplo:
        // ReglasVista vista = new ReglasVista();
        // new ControladorReglas(vista);
        System.out.println("Usuario seleccionó: Reglas");
    }
}
