import java.util.*;

public class airports{
    public int id;
    public String sigla;
    public String nome;
    public String cidade;
    public String estado;
    public double latitude;
    public double longitude;

    public airports() {
    }

    // Constructor 2
    public airports(int id, String sigla, String nome, String cidade, String estado, double latitude, double longitude) {
        this.id = id;
        this.sigla = sigla;
        this.nome = nome;
        this.cidade = cidade;
        this.estado = estado;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}