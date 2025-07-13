/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BotonDeTemperaturas;
// Importamos una clase especial que nos permite crear editores personalizados para propiedades
import java.beans.PropertyEditorSupport;

/**
 *
 * @author socta
 */

/**
 * Esta clase es un editor personalizado para la propiedad "modoConversion"
 * del botón de temperatura. Sirve para limitar las opciones disponibles
 * a una lista fija de cadenas (tipo combo box o desplegable).
 * 
 * Es útil cuando usamos el botón en un entorno gráfico como NetBeans, 
 * donde queremos que el usuario elija entre ciertas opciones válidas
 * en vez de escribirlas a mano (para evitar errores).
 */

public class ModoEditor  extends PropertyEditorSupport {
    
    // Creamos un array con las opciones permitidas
    // Estas son las cadenas que el usuario podrá elegir desde el editor visual
    
    private final String[] opciones = {
        "Auto",  // El programa detecta desde qué unidad convertir
        "Celsius a otros", // Convierte desde Celsius a Fahrenheit y Kelvin
        "Fahrenheit a otros", // Convierte desde Fahrenheit a Celsius y Kelvin
        "Kelvin a otros"  // Convierte desde Kelvin a Celsius y Fahrenheit
    };
    // Este método devuelve las etiquetas que se mostrarán en el editor gráfico como opciones disponibles
    @Override
    public String[] getTags() {
        return opciones;
    }
    
    // Este método devuelve el valor actual de la propiedad en forma de texto
    // Es útil cuando el editor visual necesita mostrar cuál opción está seleccionada

    @Override
    public String getAsText() {
        return (String) getValue();
    }
    
    // Este método es llamado cuando el usuario selecciona una opción del combo box
    // Asigna el valor correspondiente a la propiedad

    @Override
    public void setAsText(String text) {
        setValue(text); // Guardamos la opción seleccionada como valor actual
    }
    
    // Este método genera el código Java necesario para inicializar la propiedad con el valor seleccionado
    // Por ejemplo, si elegimos "Kelvin a otros", este método devuelve: "Kelvin a otros"

    @Override
    public String getJavaInitializationString() {
        return "\"" + getAsText() + "\"";  // Devuelve el valor como una cadena en formato Java
    }
    
}
