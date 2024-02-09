package edu.tienda.core.domain;

import lombok.*;

/**
 * -@Getter y @Setter
 * Lo más común y frecuente es que nuestras clases de dominio definen atributos con sus
 * respectivos métodos accesores “getters” y “setters”. Podemos obviarnos la rutina de escribir
 * estos métodos decorando a la clase con las anotaciones @Getter y @Setter de la siguiente
 * manera.
 */

/**
 * -@NoArgsConstructor
 * Es muy usual generar explícitamente el constructor “default”, que es aquel que no recibe
 * ningún argumento. Incluso, muchas veces es necesario disponer de este constructor ya que será
 * utilizado para instanciar objetos de la clase por librerías externas como por ejemplo Jackson.
 * También podemos obviarnos la codificación del constructor default introduciendo la siguiente
 * anotación.
 */

/**
 * -@AllArgsConstructor
 * Por supuesto que también podemos reemplazar el código fuente del constructor completo de
 * argumentos por la anotación @AllArgsConstructor. Nuestra clase va tomando un aspecto mucho
 * más minimalista.
 */

/**
 * -@ToString
 * Ahora agregaremos la anotación @ToString, para que Lombock sobreescriba este método y
 * lo haga como si lo hubiésemos generado con el asistente de IntelliJ.
 */

/**
 * -@EqualsAndHashCode
 * En caso de que usemos nuestros objetos de clase para listas de tipo “Set” o “Hash” donde
 * queremos asegurarnos que no se repitan dos ejemplares iguales, es necesario escribir los métodos
 * “equals” y “hashcode”, que juntos conformarán un contrato que garantice la unicidad de los
 * objetos. Lombok también puede hacer este trabajo, que les aseguro que no es trivial.
 */

/**
 * -@Data
 * Como se observa, la clase fue perdiendo en código Java pero ganando en anotaciones.
 * Lombok dispone de una anotación “mágica” llamada @Data que permite resumir y contener casi
 * todas estas anotaciones en una sola. Aquí está la nueva clase java.
 * -@Data incluye todas las anteriores anotaciones excepto la de constructores.
 * Por lo tanto, de ahora en adelante, lo más práctico es decorar estas clases con @Data.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Producto {

    private Integer id;
    private String nombre;
    private Double precio;
    private Integer stock;

}

