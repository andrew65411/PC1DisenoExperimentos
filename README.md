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

Archivos de evidencia generados
 - Reportes de Surefire en `target/surefire-reports/` (texto y XML).
 - Registro de salida de las pruebas en `target/surefire-reports/com.example.matricula.MatriculaTest.txt`.

Autor / Identificación del entregable
 - Nombre: Andreow Santiago Peña
 - Código: u202317362

Estado de cumplimiento (verificado en este repositorio)
 - C01 (Building y ejecución): ✅ `mvn test` pasa y genera reportes.
 - C02 (Unit test): ✅ 3 pruebas unitarias implementadas + 1 integral.
 - C03 (Patrón AAA): ✅ Evidente en todos los tests.
 - C04 (Mocking framework): ✅ Mockito usado correctamente.
 - C05 (Evidences): ⚠️ Reportes técnicos presentes; falta adjuntar capturas con fecha/hora en el Word de entrega para cumplir requisito manual del examen.

¿Quieres que genere un ZIP listo para entregar con reportes y un checklist adicional? Responde y lo preparo.
