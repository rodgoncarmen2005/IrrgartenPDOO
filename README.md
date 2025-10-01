# CLASE 1 PRÁCTICAS PDOO
24 - Septiembre - 2025

# Nuevo proyecto en Java

New proyect > Java with Ant > Seleccionar carpeta, nombre…

paquete.Clase → irrgarten.Irrgarten

Main debe tener la forma → public static void main(String[] args)

El nombre del archivo y el nombre de la clase deben llamarse igual.

# Nueva clase en Java

Añadimos al paquete una nueva clase para crear plantilla.

Las clases deben llevar la primera letra en mayúsculas.

Al menos un constructor. No se le indica ningún tipo de devolución, ni siquiera un void. P.e public Weapon(…). Debe inicializar todos los atributos de la instancia que estamos haciendo, aunque tengamos que decidir cuál es el más apropiado y no venga dado (puede ser null, 0 o false).

Para referenciar el propio objeto que está construyendo: this.atributo

Si queremos asignar un número a un float es necesario añadir un f: 0.0f. Si no, se considera double.

# Clase object Java

Como vamos a heredar un método y hay que sobre escribirlo, hay que poner @Override

Documentación de la clase: https://docs.oracle.com/javase/8/docs/api/java/lang/Object.html

# Nueva clase en Ruby

Cada clase en su archivo. Archivos de estensión archivo.rb.

Para que no nos de errores de codificación por el Español: #encoding:utf-8

No implementa paquetes. En vez de eso usa módulos, que no es exactamente lo mismo. Se escriben con la inicial en mayúscula. Se declaran como module Irrgarten … (código) .. end

Para crear una clase: class Weapon … (código) … end.

Los atributos se crean cuando se le asigna un valor la primera vez. Como no se le indica el tipo, no dará error si no asignamos bien. Consejo: poner un comentario de qué tipo sería. A los parámetros tampoco se les indica tipo.

Los constructores siempre se llaman initialize(…) … (código) …  end. Para inicializar los atributos @atributo = x. Si no le ponemos el @, Ruby piensa que es una variable local.

El método devolverá el resultado de la última instrucción. Es lo mismo poner al final return salida y salida. Tener cuidado si puede ser que no entremos en la última instrucción, por ejemplo cuando está en un if.

Los comentarios de una línea es con #.

Permite introducir en de un cadena código dentro de #{}. Por ejemplo "W[*#{@power}*]"

Los métodos de clase se escriben como self.metodo.

Cuando hay que aludir a algo que se ha definido dentro de un módulo pero estamos fuera Modulo::Clase.metodo

### Errores

- *uninitialized constant*. Somos responsables de comunicarle a Ruby que el proyecto tiene varios archivos. Siempre que pongamos el nombre de una clase que se ha definido en otro archivo hay que poner *require_relative Weapon.*
    
    ### Consejos
    
- Intentar no usar el mismo nombre para varias variables.
- ¡¡No olvidar el end en todo!!

# Versiones de Ruby y Java

Ruby: 3.2.3

Java: 21.0.4

# Grupo 13


# CLASE 2 PRÁCTICAS PDOO

### Ruby -> atributos
altr_reader: nombre
