import java.awt.Color;

import javax.swing.JPanel;

public class OrdenTrabajo extends JPanel{
    public OrdenTrabajo(){
        GuiOrdenTrabajo();
    }

    public void GuiOrdenTrabajo(){
        setBounds(5,5,370,450);
        //setBackground(new Color(0, 191, 255));
        setBackground(new Color(220, 220, 220)); //Activelo cuando quiera ver el panel
        setVisible(false);
        setLayout(null);
    }
}
