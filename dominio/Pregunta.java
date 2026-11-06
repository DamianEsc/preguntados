package dominio;

public class Pregunta {
    private int id;
    private String enunciado;
    private String opcion_a;
    private String opcion_b;
    private String opcion_c;
    private String opcion_d;
    private String respuesta_correcta;
    private int id_categoria;

    public Pregunta(int id, String enunciado, String opcion_a, String opcion_b, String opcion_c, String opcion_d, String respuesta_correcta, int id_categoria) {
        this.id = id;
        this.enunciado = enunciado;
        this.opcion_a = opcion_a;
        this.opcion_b = opcion_b;
        this.opcion_c = opcion_c;
        this.opcion_d = opcion_d;
        this.respuesta_correcta = respuesta_correcta;
        this.id_categoria = id_categoria;
    }

    public int getId() {
        return id;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public String getOpcion_a() {
        return opcion_a;
    }

    public String getOpcion_b() {
        return opcion_b;
    }

    public String getOpcion_c() {
        return opcion_c;
    }

    public String getOpcion_d() {
        return opcion_d;
    }

    public String getRespuesta_correcta() {
        return respuesta_correcta;
    }

    public int getId_categoria() {
        return id_categoria;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public void setOpcion_a(String opcion_a) {
        this.opcion_a = opcion_a;
    }

    public void setOpcion_b(String opcion_b) {
        this.opcion_b = opcion_b;
    }

    public void setOpcion_c(String opcion_c) {
        this.opcion_c = opcion_c;
    }

    public void setOpcion_d(String opcion_d) {
        this.opcion_d = opcion_d;
    }

    public void setRespuesta_correcta(String respuesta_correcta) {
        this.respuesta_correcta = respuesta_correcta;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }

    
}
