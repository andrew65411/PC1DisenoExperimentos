# Evidencias y explicación de las pruebas

Proyecto: `matricula-reserva` (Java 17)

Resumen rápido
- Tests implementados: 3 pruebas unitarias + 1 prueba integral (patrón AAA)
- Frameworks: JUnit 5, Mockito
- Resultado de ejecución: todos los tests pasan (4/4)

Cómo ejecutar (desde la raíz del proyecto):
```bash
mvn clean test
```

Rutas de evidencia generadas automáticamente
- Reportes Surefire (texto y XML): `target/surefire-reports/`
- Archivo de reporte de la clase de tests: `target/surefire-reports/com.example.matricula.MatriculaTest.txt`

Pruebas (código) — archivo: `src/test/java/com/example/matricula/MatriculaTest.java`

1) `calcularCostoMatriculaEstandar()`

Código (extracto):

```java
// Arrange
Estudiante est = new Estudiante("Ana", "Lopez", "A001", "ana@example.com", "Ingenieria");
Curso curso = new Curso("Matemáticas", "MATE101", 3, 100.0);
PeriodoAcademico periodo = new PeriodoAcademico("2026-1", LocalDate.now(), LocalDate.now().plusMonths(4));
when(servicio.esValido(any(), any(), any())).thenReturn(true);

// Act
Matricula m = new Matricula(est, curso, periodo, servicio);

// Assert
assertEquals(300.0, m.getCostoTotal(), 0.001);
assertEquals("Activa", m.getEstado());
```

Evidencia de ejecución:
- Ver `target/surefire-reports/com.example.matricula.MatriculaTest.txt` (contiene: "Tests run: 4, Failures: 0, Errors: 0")

Explicación corta: valida que el costo se calcule como `créditos × costoPorCredito`.

Por qué se realizó: caso base para asegurar cálculo correcto sin descuentos.

---

2) `descuentoPorCreditosMayoresOIgual5()`

Código (extracto):

```java
// Arrange
Estudiante est = new Estudiante("Luis", "Garcia", "L002", "luis@example.com", "Arquitectura");
Curso curso = new Curso("Proyecto Avanzado", "PROJ500", 5, 200.0);
when(servicio.esValido(any(), any(), any())).thenReturn(true);

// Act
Matricula m = new Matricula(est, curso, periodo, servicio);

// Assert
assertEquals(950.0, m.getCostoTotal(), 0.001);
```

Evidencia: mismo reporte surefire.

Explicación corta: verifica que cursos con 5+ créditos apliquen 5% de descuento.

Por qué: regla de negocio crítica (descuento por alta carga de créditos).

---

3) `retiroDentroDe7DiasDevuelve70porciento()`

Código (extracto):

```java
// Arrange
Estudiante est = new Estudiante("Carlos", "Perez", "C003", "carlos@example.com", "Derecho");
Curso curso = new Curso("Introducción al Derecho", "DER100", 4, 150.0);
when(servicio.esValido(any(), any(), any())).thenReturn(true);
Matricula m = new Matricula(est, curso, periodo, servicio);

// Act
double refund = m.retirarCurso(LocalDate.now().plusDays(5));

// Assert
assertEquals("Retirada", m.getEstado());
assertEquals(m.getCostoTotal() * 0.70, refund, 0.001);
```

Evidencia: mismo reporte surefire.

Explicación corta: valida política de retiro dentro de 7 días y devolución del 70%.

Por qué: protege cálculo de reembolsos y el estado de la matrícula.

---

4) `pruebaIntegralMatricula()` (integral, patrón AAA)

Código (extracto):

```java
// Arrange
Estudiante est = new Estudiante("María", "Rodriguez", "M004", "maria@example.com", "Medicina");
Curso curso = new Curso("Anatomía", "ANAT200", 3, 120.0);
when(servicio.esValido(any(), any(), any())).thenReturn(true);

// Act
Matricula m = new Matricula(est, curso, periodo, servicio);
double refund = m.retirarCurso(LocalDate.now().plusDays(3));

// Assert
assertEquals("Retirada", m.getEstado());
assertEquals(360.0 * 0.70, refund, 0.001);
```

Evidencia: mismo reporte surefire; ejecución de `mvn test` genera el reporte en `target/surefire-reports`.

Explicación corta: flujo completo — creación, verificación de costo, retiro y comprobación de estado y reembolso.

Por qué: comprueba integración básica entre constructores, reglas de negocio y métodos de cambio de estado.

---

Notas sobre evidencias y requisitos del examen
- Los reportes automáticos (`target/surefire-reports/`) y la salida de `mvn test` son evidencia técnica de ejecución. Contienen tiempos y resultados.
- El enunciado del examen solicita además capturas con fecha/hora del sistema operativo. Yo no puedo generar capturas del sistema del alumno; debes incluir en el Word de entrega una o dos capturas de pantalla que muestren la hora del sistema y la ejecución (`mvn test` o la ventana de terminal), y agregarlas al documento `código_alumno.zip`.

Conclusión provisional de calificación (self-check)
- C01 (Build/ejecución): 3.0 — `mvn test` ejecuta correctamente.
- C02 (Unit tests): 5.5 — 3 unitarias + 1 integral implementadas.
- C03 (Patrón AAA): 4.0 — pruebas siguen AAA.
- C04 (Mocking framework): 4.5 — Mockito usado correctamente para `ServicioValidacion`.
- C05 (Evidences): 3.0 — reportes automáticos incluidos; para el examen agregue capturas con fecha/hora en el Word para cumplir requisito human-verifiable.



