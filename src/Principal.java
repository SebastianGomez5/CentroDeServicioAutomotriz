import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
public class Principal extends JFrame implements ActionListener{
    
    JButton productos,servicios,add_orden,menu,addProductos,B_Eliminar;
    JLabel label;
    JTextArea tituloVentana;
    static JPanel panel_principal; //Static para activarlo desde el main
    Servicio servicio;
    Producto producto;
    Adicionar adicionar;
    Eliminar eliminarAlgo;
    OrdenTrabajo orden_trabajo;

    //Constructor
    public Principal(){
        //Configuracion general
        super("Centro De Servicio Automotriz");
        setSize(400, 500);
        getContentPane().setBackground(new Color(0, 191, 255));
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        crearGui();
        
        producto = new Producto();
        add(producto);

        servicio = new Servicio();
        add(servicio);

        adicionar = new Adicionar();
        add(adicionar);

        eliminarAlgo = new Eliminar();
        add(eliminarAlgo);

        orden_trabajo = new OrdenTrabajo();
        add(orden_trabajo);

    }

    //Funcion que crea la gui
    public void crearGui(){

        ImageIcon icono = new ImageIcon(getClass().getResource("imagenes/IconoVentana.png"));
        //ImageIcon iconoScalada =  new ImageIcon(iconoOriginal.getImage().getScaledInstance(500,500,Image.SCALE_SMOOTH));
        setIconImage(icono.getImage());

        panel_principal = new JPanel();
        panel_principal.setBackground(new Color(0, 191, 255));
        //panel_principal.setBackground(new Color(220, 220, 220)); //Activelo cuando quiera ver el panel
        panel_principal.setBounds(5,5,370,450);
        panel_principal.setLayout(null);
        panel_principal.setVisible(false);
        add(panel_principal);

        tituloVentana = new JTextArea();
        tituloVentana.setText(" Menú principal");
        tituloVentana.setFont(new Font("arial", 3, 17));
        tituloVentana.setEditable(false);
        tituloVentana.setBorder(new LineBorder(Color.gray));
        tituloVentana.setBounds(205,30,130,25);
        panel_principal.add(tituloVentana);
  
        productos = new JButton("Catalogo Productos");
        productos.setBounds(60, 100,250,30);
        productos.addActionListener(this);
        productos.setBorder(new LineBorder(Color.gray));
        panel_principal.add(productos);

        servicios = new JButton("Catalogo Servicios");
        servicios.setBounds(60, 150,250,30);
        servicios.addActionListener(this);
        servicios.setBorder(new LineBorder(Color.gray));
        panel_principal.add(servicios);

        addProductos = new JButton("Adicionar Productos / Servicios");
        addProductos.setBounds(60, 200,250,30);
        addProductos.addActionListener(this);
        addProductos.setBorder(new LineBorder(Color.gray));
        panel_principal.add(addProductos);

        add_orden = new JButton("Añadir Orden De Trabajo");
        add_orden.setBounds(60,250,250,30);
        add_orden.addActionListener(this);
        add_orden.setBorder(new LineBorder(Color.gray));
        panel_principal.add(add_orden);

        B_Eliminar = new JButton("Eliminar Articulo / Producto");
        B_Eliminar.setBounds(60, 300,250,30);
        B_Eliminar.addActionListener(this);
        B_Eliminar.setBorder(new LineBorder(Color.gray));
        panel_principal.add(B_Eliminar);

        ImageIcon foto1 = new ImageIcon(getClass().getResource("imagenes/Dcar.png"));
        ImageIcon foto2 = new ImageIcon(foto1.getImage().getScaledInstance(150,70,Image.SCALE_SMOOTH));
        menu = new JButton(foto2);
        menu.addActionListener(this);
        menu.setBounds(15, 15,150,70);
        menu.setToolTipText("Click para volver al menu principal");
        menu.setBorder(new LineBorder(Color.gray));
        menu.setBorderPainted(true);
        panel_principal.add(menu);
        add(menu);

        ImageIcon imgOriginal = new ImageIcon(getClass().getResource("imagenes/StarMas.jpg"));
        ImageIcon imgScalada = new ImageIcon(imgOriginal.getImage().getScaledInstance(250,120,Image.SCALE_SMOOTH));
        label = new JLabel(imgScalada);
        label.setBounds(60,340,250,120);
        panel_principal.add(label);

    }

    //Detecta eventos
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == menu){
            panel_principal.setVisible(true);
            producto.setVisible(false);
            adicionar.setVisible(false);
            eliminarAlgo.setVisible(false);
            servicio.setVisible(false);
            orden_trabajo.setVisible(false);
            Adicionar.reiniciar(); //Para quitar el mensaje
            Eliminar.vaciar(); //Para vaciar el mensaje
        }

        if(e.getSource() == productos){
            producto.setVisible(true);
            servicio.setVisible(false);
            panel_principal.setVisible(false);
            adicionar.setVisible(false);
            eliminarAlgo.setVisible(false);
            orden_trabajo.setVisible(false);
        }

        if(e.getSource() == servicios){
            servicio.setVisible(true);
            producto.setVisible(false);
            panel_principal.setVisible(false);
            adicionar.setVisible(false);
            eliminarAlgo.setVisible(false);
            orden_trabajo.setVisible(false);
        }

        if(e.getSource() == addProductos){
            adicionar.setVisible(true);
            panel_principal.setVisible(false);
            producto.setVisible(false);
            servicio.setVisible(false);
            eliminarAlgo.setVisible(false);
            orden_trabajo.setVisible(false);
        }

        if(e.getSource() == add_orden){
            orden_trabajo.setVisible(true);
            producto.setVisible(false);
            panel_principal.setVisible(false);
            adicionar.setVisible(false);
            servicio.setVisible(false);
            eliminarAlgo.setVisible(false);
        }

        if(e.getSource() == B_Eliminar){
            eliminarAlgo.setVisible(true);
            producto.setVisible(false);
            servicio.setVisible(false);
            panel_principal.setVisible(false);
            adicionar.setVisible(false);
            orden_trabajo.setVisible(false);
        }
    }
    
    public static void main(String[] args) {
        new Principal();
        panel_principal.setVisible(true);
    }
}