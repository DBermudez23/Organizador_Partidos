package MODELO;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa las formaciones disponibles (por ejemplo, 2-2-1, 1-2-2, etc.).
 */
public class Formaciones {

    private List<String> formacionesDisponibles;

    public Formaciones() {
        // Ejemplo simple: cada String podría ser el nombre de la formación
        formacionesDisponibles = new ArrayList<>();
        formacionesDisponibles.add("2-2-1");
        formacionesDisponibles.add("1-2-2");
        formacionesDisponibles.add("2-1-2");
        // … agrega las que necesites
    }

    public List<String> getFormaciones() {
        return formacionesDisponibles;
    }

    /**
     * @param nombreFormacion Algo como "2-2-1"
     * @return un objeto Formaciones “más completo” si tuvieras metadatos (en este ejemplo,
     * simplemente devolvemos un objeto con ese nombre, pero podrías mapear un id, etc.).
     */
    public Formaciones getFormacionPorNombre(String nombreFormacion) {
        if (formacionesDisponibles.contains(nombreFormacion)) {
            Formaciones copia = new Formaciones();
            copia.formacionesDisponibles.clear();
            copia.formacionesDisponibles.add(nombreFormacion);
            return copia;
        }
        return null;
    }
}
