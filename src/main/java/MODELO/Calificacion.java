/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELO;

/**
 *
 * @author danie
 */
public class Calificacion {
    private int puntuacion;
    private String comentario;
    private Jugador autor;

    public Calificacion() { }

    public Calificacion(int puntuacion, String comentario, Jugador autor) {
        this.puntuacion = puntuacion;
        this.comentario = comentario;
        this.autor = autor;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Jugador getAutor() {
        return autor;
    }

    public void setAutor(Jugador autor) {
        this.autor = autor;
    }
}
