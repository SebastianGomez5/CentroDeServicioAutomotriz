import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class Adicionar extends JPanel implements ActionListener{
    static JLabel validar;
    JTextField T_codigo,T_name,T_precio,T_codigoTitulo,T_nameTitulo,T_precioTitulo;
    JButton listo;
    JTextArea tituloVentana;
    JRadioButton RB_producto;
    JRadioButton RB_servicio;
    ButtonGroup bg; //Boton de grupo para que se seleccione solo uno radio buton
    Producto producto;
    ProductosArray productos;
    ArrayList<Backed> ArrayProductos_clon = new ArrayList<Backed>();
    ArrayList<Backed> ArrayServicios_clon = new ArrayList<Backed>();
    public Adicionar(){
        GuiAdicionar();
    }

    public void GuiAdicionar(){
        setLayout(null);
        setBounds(5,5,370,450);
        //setBackground(new Color(220, 220, 220)); //Activelo cuando quiera ver el panel
        setBackground(new Color(0, 191, 255));
        setVisible(false);

        tituloVentana = new JTextArea();
        tituloVentana.setText("     Adicionar" + 
                             "\n   un producto");
        tituloVentana.setFocusable(false);
        tituloVentana.setFont(new Font("arial", 3, 17));
        tituloVentana.setEditable(false);
        tituloVentana.setBorder(new LineBorder(Color.gray));
        tituloVentana.setBounds(205,25,130,45);
        add(tituloVentana);

        T_codigoTitulo = new JTextField();
        T_codigoTitulo.setText(" Código");
        T_codigoTitulo.setFont(new Font("arial", 3, 15));
        T_codigoTitulo.setEditable(false);
        T_codigoTitulo.setBorder(new LineBorder(Color.gray));
        T_codigoTitulo.setBounds(60,110,70,25);
        add(T_codigoTitulo);

        T_codigo = new JTextField();
        T_codigo.setBounds(60,140,250,30);
        T_codigo.setBorder(new LineBorder(Color.gray));
        add(T_codigo);

        T_nameTitulo = new JTextField();
        T_nameTitulo.setText(" Nombre");
        T_nameTitulo.setFont(new Font("arial", 3, 15));
        T_nameTitulo.setEditable(false);
        T_nameTitulo.setBorder(new LineBorder(Color.gray));
        T_nameTitulo.setBounds(60,180,70,25);
        add(T_nameTitulo);

        T_name = new JTextField();
        T_name.setBounds(60,210,250,30);
        T_name.setBorder(new LineBorder(Color.gray));
        add(T_name);

        T_precioTitulo = new JTextField();
        T_precioTitulo.setText(" Valor");
        T_precioTitulo.setFont(new Font("arial", 3, 15));
        T_precioTitulo.setBorder(new LineBorder(Color.gray));
        T_precioTitulo.setEditable(false);
        T_precioTitulo.setBounds(60,250,70,25);
        add(T_precioTitulo);

        T_precio = new JTextField();
        T_precio.setBounds(60,280,250,30);
        T_precio.setBorder(new LineBorder(Color.gray));
        add(T_precio);

        validar = new JLabel();
        validar.setText("");
        validar.setBounds(90, 370, 250, 30);
        add(validar);

        listo = new JButton("Listo ");
        listo.setBounds(100,410,150,30);
        listo.addActionListener(this);
        listo.setBorder(new LineBorder(Color.gray));
        add(listo);

        RB_producto = new JRadioButton("Producto");
        RB_producto.setBounds(60, 330, 120, 30);
        RB_producto.setFont(new Font("arial",1,15));
        RB_producto.setContentAreaFilled(true); //Le quito el fondo
        RB_producto.setFocusPainted(false); //Que no quede seleccionada
        RB_producto.setBorder(new LineBorder(Color.gray));
        add(RB_producto);

        RB_servicio = new JRadioButton("Servicio");
        RB_servicio.setBounds(190, 330, 120, 30);
        RB_servicio.setFont(new Font("arial",1,15));
        RB_servicio.setBorderPainted(false);
        RB_servicio.setFocusPainted(false); //Que no quede seleccionada
        RB_servicio.setContentAreaFilled(true); //Le quito el fondo
        RB_servicio.setBorder(new LineBorder(Color.gray));
        add(RB_servicio);

        bg = new ButtonGroup();
        bg.add(RB_producto);
        bg.add(RB_servicio);

    }

    public void actionPerformed(ActionEvent e){
        validar.setText(""); //Vaciar el texto
        if (e.getSource() == listo){
            //Evaluar si está vacio
            if(!(T_codigo.getText().isEmpty()) && !(T_name.getText().isEmpty()) && !(T_precio.getText().isEmpty()) && ((RB_producto.isSelected()) || (RB_servicio.isSelected()))){
                //Significa que todos lo campos fueron rellenados
                if(RB_producto.isSelected()){ //Si es un producto
                    if(validarDatos(T_codigo.getText(), T_name.getText(),true) == false){
                    /*Si es falso es porque no existe un producto
                    con esas caracteristicas por ende se puede añadir
                    */
                        ProductosArray.agregarP(T_codigo.getText(),T_name.getText(), T_precio.getText()); //Se supone que se debe guardar
                        //Serializo array con productos
                        Serializar.Funcion_serializar(ProductosArray.ArregloProductos,"ArchivoProductos.txt");
                        
                        validar.setForeground(Color.black);
                        validar.setText("        Creado Exitosamente!");
                        T_codigo.setText("");
                        T_name.setText("");
                        T_precio.setText("");
                    }else{
                    //Caso contrario 
                        validar.setForeground(new Color(0, 100, 0));  
                        validar.setText("        Hay Datos Existentes");
                    }
                }
                else{ //Si no se seleccionó productos entonces fué servicios
                    if(validarDatos(T_codigo.getText(), T_name.getText(),false) == false){
                    /*Si es falso es porque no existe un servicio
                    con esas caracteristicas, por ende se puede añadir
                    */
                    ServiciosArray.agregarS(T_codigo.getText(),T_name.getText(), T_precio.getText()); //Se supone que se debe guardar
                    //Serializo array con servicios
                    Serializar.Funcion_serializar(ServiciosArray.ArregloServicios,"ArchivoServicios.txt");

                    validar.setForeground(Color.black);
                    validar.setText("        Creado Exitosamente!");
                    T_codigo.setText("");
                    T_name.setText("");
                    T_precio.setText("");
                    }else{
                    //Caso contrario 
                    validar.setForeground(new Color(0, 100, 0));  
                    validar.setText("        Hay Datos Existentes");
                    }
                }
            }
            else{
                validar.setForeground(Color.red);  
                validar.setText("Debes Llenar Todos los Campos");
            }

        } 
    }

    public boolean validarDatos(String cod, String nam,boolean tipo){
        boolean existente = false;
        // true = producto, false = servicio
        if(tipo == true){
            //Deserializar y asigno a un arraylist propio de la clase
            ArrayList<Backed> ArrayProductos_clon = Serializar.Funcion_deserializar("ArchivoProductos.txt");
            for(int i = 0; i <  ArrayProductos_clon.size(); i++){
                if((ArrayProductos_clon.get(i).getCod().contains(cod)) || (ArrayProductos_clon.get(i).getDescripcion().contains(nam))){
                    existente = true;
                }
            }
        }
        else{ //tipo es falso osea que es servicio
            ArrayList<Backed> ArrayServicios_clon = Serializar.Funcion_deserializar("ArchivoServicios.txt");
            for(int i = 0; i <  ArrayServicios_clon.size(); i++){
                if((ArrayServicios_clon.get(i).getCod().contains(cod)) || (ArrayServicios_clon.get(i).getDescripcion().contains(nam))){
                    existente = true;
                }
            }
        }
        return existente;
    }

    public static void reiniciar(){
        /*Funcion que reinicia el mensaje de validacion
        Solo se usa desde el principal
        */
        validar.setText("");
    }
}