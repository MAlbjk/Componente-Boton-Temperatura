/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
// Importamos varias clases de Java necesarias para que el botón funcione correctamente
package BotonDeTemperaturas;
import java.awt.Color; 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.io.Serializable;
import java.text.DecimalFormat;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author socta
 */

/**
 * Esta clase define un botón personalizado para convertir temperaturas.
 * Al hacer clic, detecta de qué unidad parte (Celsius, Fahrenheit o Kelvin)
 * y muestra el resultado en las otras dos.
 */

public class BotonTemperatura  extends JButton implements ActionListener, Serializable {
    // Propiedades configurables
    // Estas variables permiten configurar el botón, como si queremos que use colores personalizados o no
    
    private boolean usarColor = false;
    private String modoConversion = "Auto";

    // Estilos de color
    // Aquí definimos colores para mostrar si todo va bien, si hay error o el color normal
    private Color colorNormal = new Color(0, 102, 204);
    private Color colorError = new Color(204, 0, 0);
    private Color colorSuccess = new Color(0, 153, 51);

    // Componentes asociados
    // Estas son referencias a campos de texto y etiquetas que están fuera de esta clase
    private JTextField txtCelsius;
    private JTextField txtFahrenheit;
    private JTextField txtKelvin;
    private JLabel lblResultadoCelsius;
    private JLabel lblResultadoFahrenheit;
    private JLabel lblResultadoKelvin;

    // Constructor
    // Este es el constructor de la clase. Se ejecuta cuando se crea un nuevo botón de temperatura
    public BotonTemperatura() {
        setText("Convertir Temperatura"); // Texto del botón
        setBackground(Color.LIGHT_GRAY); // Color de fondo por defecto
        setForeground(Color.WHITE); // Color del texto 
        addActionListener(this); // Le decimos al botón que escuche cuando se hace clic 
        actualizarEstilo(); // Aplicamos el color según configuración 
    }
    // Este método cambia el color del botón según el modo de conversión y si se usan colores
    private void actualizarEstilo() {
        if (usarColor) {
             // Dependiendo del modo, cambia el color
            switch (modoConversion) {
                case "Celsius a otros":
                    setBackground(new Color(0, 150, 255));
                    break;
                case "Fahrenheit a otros":
                    setBackground(new Color(255, 100, 100));
                    break;
                case "Kelvin a otros":
                    setBackground(new Color(100, 255, 100));
                    break;
                default:
                    setBackground(colorNormal); // Si no hay modo definido, usamos el color normal
            }
        } else {
            // Si usarColor es falso, dejamos el color normal
            setBackground(colorNormal); 
        }
    }
    // Este método se ejecuta cuando se hace clic en el botón (porque implementamos ActionListener)
    @Override
    public void actionPerformed(ActionEvent e) {
        convertirTemperatura(); // Llamamos a la función principal que hace la conversión
    }
    // Esta es la función más importante del botón: convierte de una temperatura a las otras dos
    public void convertirTemperatura() {
        // Primero comprobamos que los componentes están bien conectados
        if (txtCelsius == null || txtFahrenheit == null || txtKelvin == null ||
            lblResultadoCelsius == null || lblResultadoFahrenheit == null || lblResultadoKelvin == null) {
            JOptionPane.showMessageDialog(this,
                "Componentes no configurados correctamente",
                "Error de configuración",
                JOptionPane.ERROR_MESSAGE);
            return;// Terminamos si algo está mal conectado
        }
        // Reiniciamos los estilos (colores, etc.) antes de comenzar la conversión
        resetEstilos();
        // Limpiamos las etiquetas de resultado por si había valores anteriores
        lblResultadoCelsius.setText("");
        lblResultadoFahrenheit.setText("");
        lblResultadoKelvin.setText("");
        
         // Leemos los valores que el usuario introdujo en los campos
        String celsiusStr = txtCelsius.getText().trim();
        String fahrenheitStr = txtFahrenheit.getText().trim();
        String kelvinStr = txtKelvin.getText().trim();
        // Contamos cuántos campos están llenos
        int llenos = 0;
        if (!celsiusStr.isEmpty()) llenos++;
        if (!fahrenheitStr.isEmpty()) llenos++;
        if (!kelvinStr.isEmpty()) llenos++;
        // Si no hay ningún campo lleno, mostramos error
        if (llenos == 0) {
            mostrarError("Ingresa un valor en uno de los campos");
            marcarError();// Pintamos los campos en rojo
            return;
        }
        // Si hay más de un campo lleno, también es un error
        if (llenos > 1) {
            mostrarError("Solo un campo debe estar lleno");
            marcarError();
            return;
        }

        try {
            // Variable para guardar el valor y saber qué campo usamos
            double valor;
            JTextField campo = null;
            // Dependiendo de qué campo esté lleno, convertimos desde esa unidad
            if (!celsiusStr.isEmpty()) {
                valor = Double.parseDouble(celsiusStr);
                campo = txtCelsius;
                mostrarResultadosDesdeCelsius(valor);
            } else if (!fahrenheitStr.isEmpty()) {
                valor = Double.parseDouble(fahrenheitStr);
                campo = txtFahrenheit;
                mostrarResultadosDesdeFahrenheit(valor);
            } else {
                valor = Double.parseDouble(kelvinStr);
                campo = txtKelvin;
                mostrarResultadosDesdeKelvin(valor);
            }
            // Si todo fue bien, cambiamos el color del campo a verde
            if (campo != null) {
                campo.setForeground(colorSuccess);
                campo.setBackground(new Color(230, 255, 230)); // verde clarito
            }

        } catch (NumberFormatException ex) {
            // Si el valor no es numérico, mostramos error
            mostrarError("Solo se permiten valores numéricos");
            marcarError();
        }
    }
    // Estos métodos hacen los cálculos dependiendo desde qué unidad empezamos
    private void mostrarResultadosDesdeCelsius(double c) {
        DecimalFormat df = new DecimalFormat("#0.00"); // Usamos este formato para que los resultados salgan bonitos
        lblResultadoFahrenheit.setText(df.format((c * 9 / 5) + 32) + " °F");
        lblResultadoKelvin.setText(df.format(c + 273.15) + " K");
    }

    private void mostrarResultadosDesdeFahrenheit(double f) {
        DecimalFormat df = new DecimalFormat("#0.00");
        lblResultadoCelsius.setText(df.format((f - 32) * 5 / 9) + " °C");
        lblResultadoKelvin.setText(df.format((f - 32) * 5 / 9 + 273.15) + " K");
    }

    private void mostrarResultadosDesdeKelvin(double k) {
        DecimalFormat df = new DecimalFormat("#0.00");
        lblResultadoCelsius.setText(df.format(k - 273.15) + " °C");
        lblResultadoFahrenheit.setText(df.format((k - 273.15) * 9 / 5 + 32) + " °F");
    }
    // Este método restaura los colores por defecto de los campos de texto
    private void resetEstilos() {
        if (txtCelsius != null) {
            txtCelsius.setForeground(Color.BLACK);
            txtCelsius.setBackground(Color.WHITE);
        }
        if (txtFahrenheit != null) {
            txtFahrenheit.setForeground(Color.BLACK);
            txtFahrenheit.setBackground(Color.WHITE);
        }
        if (txtKelvin != null) {
            txtKelvin.setForeground(Color.BLACK);
            txtKelvin.setBackground(Color.WHITE);
        }
    }
    // Muestra un mensaje emergente de error
    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }
    // Cambia el color de los campos a rojo para indicar error
    private void marcarError() {
        if (txtCelsius != null) {
            txtCelsius.setForeground(colorError);
            txtCelsius.setBackground(new Color(255, 230, 230));
        }
        if (txtFahrenheit != null) {
            txtFahrenheit.setForeground(colorError);
            txtFahrenheit.setBackground(new Color(255, 230, 230));
        }
        if (txtKelvin != null) {
            txtKelvin.setForeground(colorError);
            txtKelvin.setBackground(new Color(255, 230, 230));
        }
    }

    // Setters para los componentes
    // Estos son los métodos para conectar los campos y etiquetas desde fuera (como desde una ventana)
    public void setTxtCelsius(JTextField txtCelsius) { this.txtCelsius = txtCelsius; }
    public void setTxtFahrenheit(JTextField txtFahrenheit) { this.txtFahrenheit = txtFahrenheit; }
    public void setTxtKelvin(JTextField txtKelvin) { this.txtKelvin = txtKelvin; }
    public void setLblResultadoCelsius(JLabel lblResultadoCelsius) { this.lblResultadoCelsius = lblResultadoCelsius; }
    public void setLblResultadoFahrenheit(JLabel lblResultadoFahrenheit) { this.lblResultadoFahrenheit = lblResultadoFahrenheit; }
    public void setLblResultadoKelvin(JLabel lblResultadoKelvin) { this.lblResultadoKelvin = lblResultadoKelvin; }

    // Getters y Setters de propiedades para editor visual
    // Propiedades que pueden ser modificadas desde un editor visual
    public boolean isUsarColor() { return usarColor; }
    public void setUsarColor(boolean usarColor) {
        this.usarColor = usarColor;
        actualizarEstilo();// Cambiamos el color si se actualiza esta propiedad
    }

    public String getModoConversion() { return modoConversion; }
    public void setModoConversion(String modoConversion) {
        this.modoConversion = modoConversion;
        actualizarEstilo();// Cambiamos el color dependiendo del nuevo modo
        setToolTipText("Modo: " + modoConversion);//Texto que aparece cuando pasamos el mouse por encima
    }
    // Getters y setters para los colores
    public Color getColorNormal() { return colorNormal; }
    public void setColorNormal(Color colorNormal) {
        this.colorNormal = colorNormal;
        actualizarEstilo();
    }

    public Color getColorError() { return colorError; }
    public void setColorError(Color colorError) { this.colorError = colorError; }

    public Color getColorSuccess() { return colorSuccess; }
    public void setColorSuccess(Color colorSuccess) { this.colorSuccess = colorSuccess; }

    // Método adicional útil desde código o editor
    // Método para borrar los campos y reiniciar estilos (útil si queremos reiniciar todo)
    public void resetCampos() {
        if (txtCelsius != null) txtCelsius.setText("");
        if (txtFahrenheit != null) txtFahrenheit.setText("");
        if (txtKelvin != null) txtKelvin.setText("");
        resetEstilos();
        lblResultadoCelsius.setText("");
        lblResultadoFahrenheit.setText("");
        lblResultadoKelvin.setText("");
    }
    
}
