import java.io.Serializable;

public class Backed implements Serializable{
        String valor;
    String name, codigo;
    
    public Backed(String codigo, String name, String valor){
        this.codigo = codigo;
        this.name = name;
        this.valor = valor;
    }

    public String getCod(){ return codigo; }

    public String getDescripcion(){ return name; }

    public String getValor(){return valor;}
}
