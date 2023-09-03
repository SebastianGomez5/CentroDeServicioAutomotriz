import java.util.ArrayList;
public class ProductosArray {
    static ArrayList<Backed> ArregloProductos = new ArrayList<Backed>();
    /*El static me deja acceder a ellos directamente desde afuera
    */
    public ProductosArray(){
        //Al arraylist de le asigna la deserializacion
        ArregloProductos = Serializar.Funcion_deserializar("ArchivoProductos.txt");
        if(ArregloProductos.isEmpty()){ //si está vacio añade los valores por defecto
            
            ArregloProductos.add(new Backed("320","Llanta","150000"));
            ArregloProductos.add(new Backed("150","Espejo","15000"));
            ArregloProductos.add(new Backed("212","Volante","200000"));
            ArregloProductos.add(new Backed("304","Palanca","165000"));
            ArregloProductos.add(new Backed("505","Bombilla","35000"));
        }
    }

    public static void agregarP(String cod,String nom,String valor){
        ArregloProductos.add(new Backed(cod,nom,valor));
    }

    public static void eliminaP(String name){
        for(int i = 0; i < ArregloProductos.size();i++){
            Backed producto = ArregloProductos.get(i);
            if(producto.getDescripcion().equals(name)){
                ArregloProductos.remove(i);
            }
        }
    }
}
