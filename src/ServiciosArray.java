import java.util.ArrayList;

public class ServiciosArray {
    static ArrayList<Backed> ArregloServicios = new ArrayList<Backed>();

    public ServiciosArray(){
        ArregloServicios = Serializar.Funcion_deserializar("ArchivoServicios.txt");
        if(ArregloServicios.isEmpty()){ //Si está vacio creo valores por defecto
            ArregloServicios.add(new Backed("188","Lavado","15000"));
            ArregloServicios.add(new Backed("422","Ajuste de clutch","290000"));
            ArregloServicios.add(new Backed("330","Cambio de aceite","120000"));
            ArregloServicios.add(new Backed("109","Reemplazo de Bujias","150000"));
            ArregloServicios.add(new Backed("270","Ajuste de frenos","230000"));
        }
    }

    public static void agregarS(String cod,String nom,String valor){
        ArregloServicios.add(new Backed(cod,nom,valor));
    } 

    public static void eliminaS(String name){
        for(int i = 0; i < ArregloServicios.size();i++){
            Backed serivio = ArregloServicios.get(i);
            if(serivio.getDescripcion().equals(name)){
                ArregloServicios.remove(i);
            }
        }
    }  

}
