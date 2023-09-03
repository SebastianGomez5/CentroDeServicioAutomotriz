import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Serializar {
       /*
     * Funcion para serializar productos: (ArchivoProductos.txt)
     * Funcion para serializar servicios: (ArchivoServicios.txt)
     */
    public static void Funcion_serializar(ArrayList<Backed> listaLibros,String archivo){
        try(FileOutputStream fileOut = new FileOutputStream(archivo);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)){
                objectOut.writeObject(listaLibros);
                System.out.println("Arraylist serializada en " + archivo);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    /* Funcion para deserializar productos: (ArchivoProductos.txt)
     * Funcion para deserializar servicios: (ArchivoServicios.txt)
     */
    public static ArrayList<Backed> Funcion_deserializar(String archivo){
        ArrayList<Backed> lista = new ArrayList<>();
        try(FileInputStream fileIn = new FileInputStream(archivo);
            ObjectInputStream ObjectIn = new ObjectInputStream(fileIn)){
                lista = (ArrayList<Backed>) ObjectIn.readObject();
                System.out.println("Arraylist deserializado " + archivo);
        }catch (FileNotFoundException e) {
            System.out.println("No se encontró el archivo " + archivo);
        }catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
