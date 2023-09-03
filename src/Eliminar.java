import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class Eliminar extends JPanel implements ActionListener{
    JButton B_elimina;
    JTextField JT_cambioEstado;
    JButton B_cambiaTablero;
    JTextArea tituloVentana;
    static JTextArea mensajeBorrado;
    JScrollPane barraDesplazamiento;
    JScrollPane js;
    JList<String> Jlist_eliminar;
    boolean estado; //True = servicios,  false = productos
    JLabel label;
    ArrayList<String> DatosJlistProducto = new ArrayList<String>();
    ArrayList<Backed> ArrayProductos_clon = new ArrayList<Backed>();
    public Eliminar(){
        GuiEliminar();
    }

    private void GuiEliminar() {
        setLayout(null);
        setBounds(5,5,370,450);
        setBackground(new Color(0, 191, 255));
        //setBackground(new Color(220, 220, 220)); //Activelo cuando quiera ver el panel
        setVisible(false);

        tituloVentana = new JTextArea();
        tituloVentana.setText("          Eliminar" + 
                              "\n  producto / servicio");
        tituloVentana.setFont(new Font("arial", 3, 17));
        tituloVentana.setEditable(false);
        tituloVentana.setBounds(180,20,170,50);
        tituloVentana.setBorder(new LineBorder(Color.gray));
        add(tituloVentana);

        mensajeBorrado = new JTextArea();
        mensajeBorrado.setFont(new Font("arial", 3, 15));
        mensajeBorrado.setBounds(220,212,140, 40);
        mensajeBorrado.setEditable(false);
        mensajeBorrado.setBorder(new LineBorder(Color.gray));
        add(mensajeBorrado);

        B_elimina = new JButton("Eliminar");
        B_elimina.setBounds(230,160,120,30);
        B_elimina.setFont(new Font("arial", 3, 15));
        B_elimina.addActionListener(this);
        B_elimina.setBorder(new LineBorder(Color.gray));
        B_elimina.setFocusPainted(false);
        add(B_elimina);

        B_cambiaTablero = new JButton();
        B_cambiaTablero.setBounds(230,108,120,30);
        B_cambiaTablero.setFont(new Font("arial", 3, 15));
        B_cambiaTablero.addActionListener(this);
        B_cambiaTablero.setBorder(new LineBorder(Color.gray));
        B_cambiaTablero.setFocusPainted(false);
        //B_cambiaTablero.setContentAreaFilled(false);
        cambioEstado(); //Asigna nombre al boton
        add(B_cambiaTablero);

        crearJlist(estado); //Crea los JList dependiendo el estado

        JT_cambioEstado = new JTextField("Presiona para cambiar ->");
        JT_cambioEstado.setFont(new Font("arial", 3, 15));
        JT_cambioEstado.setEditable(false);
        JT_cambioEstado.setBounds(20,110,190,25);
        JT_cambioEstado.setBorder(new LineBorder(Color.gray));
        add(JT_cambioEstado);

        ImageIcon imgOriginal = new ImageIcon(getClass().getResource("imagenes/HBOMax.jpg"));
        ImageIcon imgScalada = new ImageIcon(imgOriginal.getImage().getScaledInstance(140,170,Image.SCALE_SMOOTH));
        label = new JLabel(imgScalada);
        label.setBounds(220,270,140,170);
        add(label);
    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource() == B_elimina){
            //.getSelectedValue() me da el dato que se seleccionó
            if(Jlist_eliminar.getSelectedValue() != null){
                if(estado == true){
                    //Si estado es true es porque es productos
                    mensajeBorrado.setText("Se ha eliminado: \n" + Jlist_eliminar.getSelectedValue());
                    ProductosArray.eliminaP(Jlist_eliminar.getSelectedValue());
                    //Serializar para guardar datos
                    Serializar.Funcion_serializar(ProductosArray.ArregloProductos,"ArchivoProductos.txt");
                    agregarElementoProductos(estado);
                }
                else{
                    //Estado es false osea que es servicios
                    mensajeBorrado.setText("Se ha eliminado: \n" + Jlist_eliminar.getSelectedValue());
                    ServiciosArray.eliminaS(Jlist_eliminar.getSelectedValue());
                    //Serializo apara guardar los datos
                    Serializar.Funcion_serializar(ServiciosArray.ArregloServicios,"ArchivoServicios.txt");
                    agregarElementoProductos(estado);
                }
            }else{
                mensajeBorrado.setText("Selecciona Algo");
            }
        }
        if(e.getSource() == B_cambiaTablero){
            cambioEstado();
            agregarElementoProductos(estado);
        }
    }

    //Funcion que crea las listas rellenandolas con sus valores por defecto
    public void crearJlist(boolean estado){
        if(estado == true){
            //Deserializar y asigno a un arraylist propio de la clase
            ArrayList<Backed> ArrayProductos_clon = Serializar.Funcion_deserializar("ArchivoProductos.txt");
            for(int i = 0; i < ArrayProductos_clon.size();i++){
                DatosJlistProducto.add(ArrayProductos_clon.get(i).getDescripcion());
            }
            // Convertimos el ArrayList en una matriz utilizando el método toArray() y lo pasamos directamente al constructor de JList
            Jlist_eliminar = new JList<>(DatosJlistProducto.toArray(new String[0]));
            js = new JScrollPane(Jlist_eliminar);
            js.setBounds(20,160,190,250);
            js.setBorder(new LineBorder(Color.gray));
            add(js);
        }
        else{
            //Deserializo en un array propio de la clase
            ArrayList<Backed> ArrayServicios_clon = Serializar.Funcion_deserializar("ArchivoServicios.txt");
            for(int i = 0; i < ArrayServicios_clon.size();i++){
                DatosJlistProducto.add(ArrayServicios_clon.get(i).getDescripcion());
            }
            // Convertimos el ArrayList en una matriz utilizando el método toArray() y lo pasamos directamente al constructor de JList
            Jlist_eliminar = new JList<>(DatosJlistProducto.toArray(new String[0]));
            js = new JScrollPane(Jlist_eliminar);
            js.setBounds(20,160,190,250);
            js.setBorder(new LineBorder(Color.gray));
            add(js);
        }

    }

    /* funcion que agrega 
    elementos al jlist creo
     */
    public void agregarElementoProductos(boolean estado){
        
        if(estado == true){
            //Deserializar y asigno a un arraylist propio de la clase
            ArrayList<Backed> ArrayProductos_clon = Serializar.Funcion_deserializar("ArchivoProductos.txt");
            //Vacio el array para que no se clonen los datos
            DatosJlistProducto.clear();
            for(int i = 0; i < ArrayProductos_clon.size();i++){
                //Añado datos
                DatosJlistProducto.add(ArrayProductos_clon.get(i).getDescripcion());
            }
            //Con esto actualizo los datos 
            Jlist_eliminar.setListData(DatosJlistProducto.toArray(new String[0]));
        }
        else{
            //Deserializo en un array propio de la clase
            ArrayList<Backed> ArrayServicios_clon = Serializar.Funcion_deserializar("ArchivoServicios.txt");
            //Vacio el array para que no se clonen los datos
            DatosJlistProducto.clear();
            for(int i = 0; i < ArrayServicios_clon.size();i++){
                //Añado datos
                DatosJlistProducto.add(ArrayServicios_clon.get(i).getDescripcion());
            }
            //Con esto actualizo los datos 
            Jlist_eliminar.setListData(DatosJlistProducto.toArray(new String[0]));
        }

    }

    public void cambioEstado(){
        /*Funcion que cambia la palabra del boton con un booleano
        y cambia la variable estado que me dirá de que cosa hablamos
        */
        if(estado == false){
            B_cambiaTablero.setText("Productos");
            estado = true;
        }else{
            B_cambiaTablero.setText("Servicios");
            estado = false;
        }
    }
    public static void vaciar(){
        mensajeBorrado.setText("");
    }
}