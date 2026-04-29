Proyecto de práctica: Gestión de Matrículas Académicas

Descripción breve
 - Proyecto Maven Java (JDK 17) que modela matrícula académica y reglas de negocio básicas.

Contenido principal
 - Código fuente: `src/main/java/com/example/matricula/`
 - Pruebas: `src/test/java/com/example/matricula/MatriculaTest.java` (JUnit 5 + Mockito)

Requisitos
 - JDK 17 instalado y `JAVA_HOME` configurado
 - Maven 3.x

Compilación y ejecución de pruebas
 - Desde la raíz del proyecto ejecutar:

```
mvn clean test
```

Qué verifican las pruebas
 - Cálculo de costo base (créditos × costoPorCredito)
 - Descuento del 5% cuando el curso tiene 5 o más créditos
 - Retiro dentro de los primeros 7 días con devolución del 70%
 - Flujo integral ( creación -> retiro )

Uso de frameworks y patrones
 - JUnit 5 para pruebas.
 - Mockito para simular `ServicioValidacion`.
 - Las pruebas siguen el patrón AAA (Arrange, Act, Assert).

Autor 
 - Nombre: Andreow Santiago Peña
 - Código: u202317362

