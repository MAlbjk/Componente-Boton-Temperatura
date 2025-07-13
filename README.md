# 🔘 Componente Visual Reutilizable: `BotonTemperatura`

## 🎓 Proyecto de Tópicos Avanzados de Programación (TAP)

Este proyecto presenta un **componente gráfico reutilizable y visualmente configurable**, desarrollado en Java utilizando **NetBeans 25** y el enfoque de **JavaBeans**. El componente se integra fácilmente en la paleta de diseño de NetBeans, permitiendo a los desarrolladores arrastrarlo, configurarlo y utilizarlo visualmente en cualquier formulario Swing.

---

## 🧠 Propósito del Componente

`BotonTemperatura` encapsula toda la lógica y diseño necesarios para realizar conversiones de temperatura entre **Celsius, Fahrenheit y Kelvin**, cumpliendo con los principios de la programación orientada a objetos:

- **Encapsulamiento**: todos los elementos están aislados dentro del botón.
- **Reutilización**: puede ser utilizado en cualquier proyecto sin reescribir lógica.
- **Modularidad**: admite personalización mediante propiedades públicas.
- **Abstracción**: oculta la lógica de validación, cálculo y presentación de errores.

---

## ✨ Características clave

- 🔁 Conversión automática al ingresar un solo valor.
- ✅ Validación de entrada y control de errores.
- 🎨 Estilos visuales configurables desde NetBeans.
- 🛠 Propiedad `modoConversion` con editor desplegable (combo box).
- 📦 Distribuido como `.jar` reutilizable, listo para importar en otros proyectos.

---

## 📚 Tecnologías y herramientas

| Herramienta       | Uso principal                                        |
|-------------------|------------------------------------------------------|
| Java SE 17        | Lógica del componente y programación OOP             |
| Swing (JavaBeans) | Diseño de interfaces gráficas                        |
| NetBeans 25       | Desarrollo y construcción del `.jar`                 |
| PropertyEditor    | Configuración visual desde el editor                 |
| Git & GitHub      | Control de versiones y documentación                 |

---

## ⚙️ Propiedades configurables

Estas propiedades pueden configurarse desde el código **o** desde el editor visual al seleccionar el componente:

| Propiedad         | Tipo     | Descripción                                            |
|-------------------|----------|--------------------------------------------------------|
| `usarColor`       | boolean  | Activa estilos visuales dependiendo del `modoConversion`. |
| `modoConversion`  | String   | Define el estilo visual según el tipo preferido: `"Auto"`, `"Celsius a otros"`, etc. |
| `colorNormal`     | Color    | Color base del botón.                                  |
| `colorError`      | Color    | Color usado para campos con errores.                   |
| `colorSuccess`    | Color    | Color usado para campos válidos.                       |

---

## 🧑‍💻 Métodos destacados

```java
##public void convertirTemperatura()
##public void resetCampos()
##public void setModoConversion(String modo)
##public void setUsarColor(boolean activo)
