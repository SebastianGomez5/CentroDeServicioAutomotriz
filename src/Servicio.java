import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class Servicio extends JPanel implements ActionListener{
    JTextArea T_cod,T_name,T_precio;
    JTextField T_codigoTitulo,T_nameTitulo,T_precioTitulo;
    JButton actualizar;
    JScrollPane barraDesplazamiento;
    ArrayList<Backed> ArrayServicios_clon = new ArrayList<Backed>();
    public Servicio(){
        super();
        new ServiciosArray();
        crearGui();
    }

    public void crearGui(){
        setBounds(5,5,370,450);
        setBackground(new Color(0, 191, 255));
        //setBackground(new Color(220, 220, 220)); //Activelo cuando quiera ver el panel
        setVisible(false);
        setLayout(null);

        T_codigoTitulo = new JTextField();
        T_codigoTitulo.setText("Código");
        T_codigoTitulo.setFont(new Font("arial", 3, 15));
        T_codigoTitulo.setEditable(false);
        T_codigoTitulo.setBounds(20,110,60,25);
        T_codigoTitulo.setBorder(new LineBorder(Color.gray));
        add(T_codigoTitulo);

        T_nameTitulo = new JTextField();
        T_nameTitulo.setText("          Nombre");
        T_nameTitulo.setFont(new Font("arial", 3, 15));
        T_nameTitulo.setBounds(100,110,150,20);
        T_nameTitulo.setEditable(false);
        T_nameTitulo.setBounds(100,110,150,25);
        T_nameTitulo.setBorder(new LineBorder(Color.gray));
        add(T_nameTitulo);

        T_precioTitulo = new JTextField();
        T_precioTitulo.setText("    Valor");
        T_precioTitulo.setFont(new Font("arial", 3, 15));
        T_precioTitulo.setBounds(270,110,80,25);
        T_precioTitulo.setEditable(false);
        T_precioTitulo.setBorder(new LineBorder(Color.gray));
        add(T_precioTitulo);

        T_cod = new JTextArea();
        T_cod.setFont(new Font("arial", 2, 15));
        T_cod.setEditable(false);
        barraDesplazamiento = new JScrollPane(T_cod);
        barraDesplazamiento.setBounds(20,140,60,300);
        barraDesplazamiento.setBorder(new LineBorder(Color.gray));
        add(barraDesplazamiento);

        T_name = new JTextArea();
        T_name.setFont(new Font("arial", 2, 15)); //pongo nombre en negrilla
        T_name.setEditable(false);  
        barraDesplazamiento = new JScrollPane(T_name);
        barraDesplazamiento.setBounds(100,140,150,300);
        barraDesplazamiento.setBorder(new LineBorder(Color.gray));
        add(barraDesplazamiento);

        T_precio = new JTextArea();
        T_precio.setFont(new Font("arial", 2, 15));
        T_precio.setEditable(false);
        barraDesplazamiento = new JScrollPane(T_precio);
        barraDesplazamiento.setBounds(270,140,80,300);
        barraDesplazamiento.setBorder(new LineBorder(Color.gray));
        add(barraDesplazamiento);

        actualizar = new JButton("SERVICIOS");
        actualizar.setBounds(190,30,170,30);
        actualizar.setFont(new Font("cooper black",0,20));
        actualizar.setBorderPainted(true); //Establece si el borde del botón debe ser dibujado o no.
        actualizar.setContentAreaFilled(true); //Establece si el área de contenido del botón debe ser dibujada o no.
        actualizar.setFocusPainted(false); //desactivar el efecto de resaltado del texto cuando se hace clic en el botón
        actualizar.addActionListener(this);
        actualizar.setToolTipText("Click para actualizar pagina");
        actualizar.setBorder(new LineBorder(Color.gray));
        add(actualizar);

        llenarTablero();
    }

    public void llenarTablero(){
        //Serializo array con servicios
        Serializar.Funcion_serializar(ServiciosArray.ArregloServicios,"ArchivoServicios.txt");
        //Deserializo en un array propio de la clase
        ArrayList<Backed> ArrayServicios_clon = Serializar.Funcion_deserializar("ArchivoServicios.txt");
        //-------------------------Codigo -------------------------------
        String salida = ""; 
        for (int i = 0;i < ArrayServicios_clon.size();i++){
            salida = salida + "   " + ArrayServicios_clon.get(i).getCod() + "\n";
        }
        T_cod.append(salida);
         
        salida = ""; //Se reinicia
        //-------------------------Nombre --------------------------------
        for (int i = 0;i < ArrayServicios_clon.size();i++){
            salida = salida + " " + ArrayServicios_clon.get(i).getDescripcion() + "\n";
        }
        T_name.append(salida); //Agrego al tablero

        salida = ""; //Se reinicia
        //-------------------------Precio -------------------------------
        for (int i = 0;i < ArrayServicios_clon.size();i++){
            salida = salida + "  $" + ArrayServicios_clon.get(i).getValor() + "\n";
        }
        T_precio.append(salida); 
       
    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource() == actualizar){
            T_cod.setText("");
            T_name.setText("");
            T_precio.setText("");
            llenarTablero();
        }
    }
}

/* funcion de rellena tablero antes de serializar:
    public void llenarTablero(){
        //-------------------------Codigo -------------------------------
        String salida = ""; 
        for (int i = 0;i < ServiciosArray.ArregloServicios.size();i++){
            salida = salida + "   " + ServiciosArray.ArregloServicios.get(i).getCod() + "\n";
        }
        T_cod.append(salida);
         
        salida = ""; //Se reinicia
        //-------------------------Nombre --------------------------------
        for (int i = 0;i < ServiciosArray.ArregloServicios.size();i++){
            salida = salida + " " + ServiciosArray.ArregloServicios.get(i).getDescripcion() + "\n";
        }
        T_name.append(salida); //Agrego al tablero

        salida = ""; //Se reinicia
        //-------------------------Precio -------------------------------
        for (int i = 0;i < ServiciosArray.ArregloServicios.size();i++){
            salida = salida + "  $" + ServiciosArray.ArregloServicios.get(i).getValor() + "\n";
        }
        T_precio.append(salida); 
       
    }

 */