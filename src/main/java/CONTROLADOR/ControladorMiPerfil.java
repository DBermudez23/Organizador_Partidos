package CONTROLADOR;

import MODELO.Jugador;
import MODELO.Singleton;
import VISTA.VistaOpcionesJugador.MiPerfilVista;
import VISTA.VistaOpcionesJugador.MenuJugador;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controlador para la vista de MiPerfilVista.
 * Obtiene el Jugador actualmente logueado desde Singleton
 * y carga sus datos en la vista. 
 * Además, permite volver al menú de jugador.
 */
public class ControladorMiPerfil implements ActionListener {

    private final MiPerfilVista vista;
    private final Jugador jugadorActual;

    public ControladorMiPerfil(MiPerfilVista vista) {
        this.vista = vista;
        this.jugadorActual = (Jugador) Singleton.getInstance().getUsuario();

        inicializarVista();
        registrarListeners();
    }

    /**
     * Carga los datos del jugador en los componentes de la vista.
     */
    private void inicializarVista() {
        // Suponiendo que MiPerfilVista expone estos JLabel y este JButton:
        //   nombreLabel, frecuenteLabel, modoLabel, infraccionesLabel,
        //   promedioLabel, ultimoPartidoLabel, regresarButton
        vista.nombreLabel.setText(jugadorActual.getNombre());
        vista.frecuenteLabel.setText(jugadorActual.isFrecuente() ? "Sí" : "No");
        vista.modoLabel.setText(jugadorActual.getModo().name());
        vista.infraccionesLabel.setText(String.valueOf(jugadorActual.getInfracciones()));
        vista.promedioLabel.setText(String.format("%.2f", jugadorActual.getCalificacionPromedio()));
        vista.ultimoPartidoLabel.setText(String.format("%.2f", jugadorActual.getCalificacionUltimoPartido()));

        vista.setTitle("Mi Perfil - " + jugadorActual.getNombre());
        vista.setLocationRelativeTo(null);
        vista.setResizable(false);
        vista.setVisible(true);
    }

    private void registrarListeners() {
        // Botón “Regresar” lleva de vuelta al menú de jugador
        vista.regresarButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.regresarButton) {
            vista.dispose();
            MenuJugador menu = new MenuJugador();
            new ControladorOpcionesJugador(menu);
        }
    }
}
