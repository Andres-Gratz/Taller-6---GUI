package uniandes.dpoo.swing.interfaz.agregar;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class PanelEditarRestaurante extends JPanel
{
    /**
     * El campo para que el usuario ingrese el nombre del restaurante
     */
    private JTextField txtNombre;

    /**
     * Un selector (JComboBox) para que el usuario seleccione la calificación (1 a 5) del restaurante
     */
    private JComboBox<String> cbbCalificacion;

    /**
     * Un selector (JComboBox) para que el usuario indique si ya visitó el restaurante o no
     */
    private JComboBox<String> cbbVisitado;

    public PanelEditarRestaurante( )
    {
    	setLayout(new GridLayout(3,1));
        // Crea el campo para el nombre con una etiqueta al frente
    	JPanel pNombre = new JPanel(new FlowLayout());
        pNombre.add(new JLabel("Nombre:"));
        txtNombre = new JTextField(15);
        pNombre.add(txtNombre);

        // Crea el selector para la calificación con una etiqueta al frente
        JPanel pCalif = new JPanel(new FlowLayout());
        pCalif.add(new JLabel("Calificación:"));
        cbbCalificacion = new JComboBox<>(new String[]{"1", "2", "3", "4", "5"});
        pCalif.add(cbbCalificacion);

        // Crea el selector para indicar si ya ha sido visitado, con una etiqueta al frente
        JPanel pVisitado = new JPanel(new FlowLayout());
        pVisitado.add(new JLabel("Visitado:"));
        cbbVisitado = new JComboBox<>(new String[]{"Sí", "No"});
        pVisitado.add(cbbVisitado);

        // Agregar todos los elementos al panel
        add(pNombre);
        add(pCalif);
        add(pVisitado);
    }

    /**
     * Indica si en el selector se seleccionó la opción que dice que el restaurante fue visitado
     * @return
     */
    public boolean getVisitado( )
    {
    	return cbbVisitado.getSelectedItem().equals("Sí");
    }

    /**
     * Indica la calificación marcada en el selector
     * @return
     */
    public int getCalificacion( )
    {
        String calif = ( String )cbbCalificacion.getSelectedItem( );
        return Integer.parseInt( calif );
    }

    /**
     * Indica el nombre digitado para el restaurante
     * @return
     */
    public String getNombre( )
    {
    	return txtNombre.getText();
    }
}
