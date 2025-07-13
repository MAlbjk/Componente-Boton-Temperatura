/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BotonDeTemperaturas;
// Importamos la clase base para trabajar con introspección de Beans
import java.beans.*;

/**
 *
 * @author socta
 */
/**
 * Esta clase es parte de JavaBeans.
 * Sirve para describir las propiedades de un componente visual (en este caso el botón).
 * Se usa sobre todo cuando estamos trabajando en entornos como NetBeans
 * donde podemos arrastrar y soltar componentes y editar sus propiedades desde un panel.
 */

public class BotonTemperaturaInfo  extends SimpleBeanInfo {
    // Este método se encarga de devolver un array con todas las propiedades
    // que queremos que se puedan ver y editar en el editor visual
     @Override
    public PropertyDescriptor[] getPropertyDescriptors() {
        try {
            // Creamos un descriptor para la propiedad "usarColor"
            // Esta propiedad permite activar o desactivar el cambio de color según el modo
            PropertyDescriptor usarColor = new PropertyDescriptor("usarColor", BotonTemperatura.class);
            // Creamos un descriptor para la propiedad "modoConversion"
            // Esta propiedad define desde qué unidad se realiza la conversión 
            PropertyDescriptor modoConversion = new PropertyDescriptor("modoConversion", BotonTemperatura.class);
            // Aquí indicamos que esta propiedad tiene un editor personalizado
            // En este caso, ModoEditor mostrará una lista desplegable con las opciones válidas
            modoConversion.setPropertyEditorClass(ModoEditor.class);  // Asocia el editor personalizado
            // Descriptores para cambiar los colores desde el editor visual
            
            PropertyDescriptor colorNormal = new PropertyDescriptor("colorNormal", BotonTemperatura.class);
            PropertyDescriptor colorError = new PropertyDescriptor("colorError", BotonTemperatura.class);
            PropertyDescriptor colorSuccess = new PropertyDescriptor("colorSuccess", BotonTemperatura.class);

            // Devolvemos todas las propiedades en un array
            // Esto es lo que el editor visual usará para mostrar las opciones editables
            return new PropertyDescriptor[] {
                usarColor,
                modoConversion,
                colorNormal,
                colorError,
                colorSuccess
            };

        } catch (IntrospectionException e) {
            // Si ocurre algún error al crear los descriptores, lo imprimimos
            e.printStackTrace();
            return null;// Si no se puede generar, devolvemos null
        }
    }
    
}
