package modelo;

import modelo.Pregunta;
import java.util.ArrayList;
import java.util.Collections;

public class SistemaJuego {

    private ArrayList<Pregunta> preguntas;
    private int preguntaActual;
    private int puntaje;

    public SistemaJuego(ArrayList<Pregunta> preguntas) {
        this.preguntas = new ArrayList<>(preguntas);
        Collections.shuffle(this.preguntas);

        this.preguntaActual = 0;
        this.puntaje = 0;
    }

    public SistemaJuego() {
        this.preguntas = new ArrayList<>();
        this.preguntaActual = 0;
        this.puntaje = 0;
    }

    public Pregunta obtenerPreguntaActual() {
        if (preguntaActual < preguntas.size()) {
            return preguntas.get(preguntaActual);
        }
        return null;
    }

    public boolean comprobarRespuesta(String respuesta) {
        Pregunta pregunta = obtenerPreguntaActual();

        if (pregunta == null) {
            return false;
        }

        boolean correcta = pregunta.getRespuesta_correcta()
                .equalsIgnoreCase(respuesta);

        if (correcta) {
            puntaje++;
        }

        preguntaActual++;

        return correcta;
    }

    public boolean juegoTerminado() {
        return preguntaActual >= preguntas.size();
    }

    public int getPuntaje() {
        return puntaje;
    }

    public int getTotalPreguntas() {
        return preguntas.size();
    }
}