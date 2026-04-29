    package com.example.matricula;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MatriculaTest {

    @Mock
    ServicioValidacion servicio;

    @Test
    void calcularCostoMatriculaEstandar() {
        // Arrange
        Estudiante est = new Estudiante("Diego", "Soto", "D010", "diego@example.com", "Sistemas");
        Curso curso = new Curso("Cálculo", "CALC101", 3, 100.0);
        PeriodoAcademico periodo = new PeriodoAcademico("2026-1", LocalDate.now(), LocalDate.now().plusMonths(4));
        when(servicio.esValido(any(), any(), any())).thenReturn(true);

        // Act
        Matricula m = new Matricula(est, curso, periodo, servicio);

        // Assert
        assertEquals(300.0, m.getCostoTotal(), 0.001);
        assertEquals("Activa", m.getEstado());
        verify(servicio, times(1)).esValido(any(), any(), any());
        System.out.println("\n--- TEST 1: Costo Matricula Estandar ---");
        System.out.println("Estudiante: " + est.getNombre() + " | Curso: " + curso.getNombre());
        System.out.println("Creditos: " + curso.getCreditos() + " x S/" + curso.getCostoPorCredito() + " = S/" + (curso.getCreditos() * curso.getCostoPorCredito()));
        System.out.println("Descuento: NO");
        System.out.println("Costo final: S/" + m.getCostoTotal() + " [OK]");
        System.out.println("Estado: " + m.getEstado() + " [OK]\n");
    }

    @Test
    void descuentoPorCreditosMayoresOIgual5() {
        // Arrange
        Estudiante est = new Estudiante("Valeria", "Torres", "V021", "valeria@example.com", "Civil");
        Curso curso = new Curso("Ingeniería de Software", "IS500", 5, 200.0);
        PeriodoAcademico periodo = new PeriodoAcademico("2026-1", LocalDate.now(), LocalDate.now().plusMonths(4));
        when(servicio.esValido(any(), any(), any())).thenReturn(true);

        // Act
        Matricula m = new Matricula(est, curso, periodo, servicio);

        // Assert: base = 5*200 = 1000, discount 5% => 950
        assertEquals(950.0, m.getCostoTotal(), 0.001);
        System.out.println("\n--- TEST 2: Descuento por Creditos (>=5) ---");
        System.out.println("Estudiante: " + est.getNombre() + " | Curso: " + curso.getNombre());
        System.out.println("Base: " + curso.getCreditos() + " x S/" + curso.getCostoPorCredito() + " = S/" + (curso.getCreditos() * curso.getCostoPorCredito()));
        System.out.println("Descuento: SI (5% por >= 5 creditos)");
        System.out.println("Final: S/" + m.getCostoTotal() + " [OK]\n");
    }

    @Test
    void retiroDentroDe7DiasDevuelve70porciento() {
        // Arrange
        Estudiante est = new Estudiante("Martin", "Lopez", "M033", "martin@example.com", "Derecho");
        Curso curso = new Curso("Historia del Derecho", "HD100", 4, 150.0);
        PeriodoAcademico periodo = new PeriodoAcademico("2026-1", LocalDate.now(), LocalDate.now().plusMonths(4));
        when(servicio.esValido(any(), any(), any())).thenReturn(true);

        Matricula m = new Matricula(est, curso, periodo, servicio);

        // Act
        double refund = m.retirarCurso(LocalDate.now().plusDays(5));

        // Assert
        assertEquals("Retirada", m.getEstado());
        assertEquals(m.getCostoTotal() * 0.70, refund, 0.001);
        System.out.println("\n--- TEST 3: Retiro Dentro de 7 Dias ---");
        System.out.println("Estudiante: " + est.getNombre() + " | Curso: " + curso.getNombre());
        System.out.println("Retiro en dia 5: Valido (dentro de 7 dias)");
        System.out.println("Costo: S/" + m.getCostoTotal() + " -> Devolucion 70%: S/" + refund + " [OK]");
        System.out.println("Estado: " + m.getEstado() + " [OK]\n");
    }

    @Test
    void pruebaIntegralMatricula() {
        // Arrange
        Estudiante est = new Estudiante("Lucia", "Ramirez", "L044", "lucia@example.com", "Odontologia");
        Curso curso = new Curso("Fisiología", "FIS200", 3, 120.0);
        PeriodoAcademico periodo = new PeriodoAcademico("2026-1", LocalDate.now(), LocalDate.now().plusMonths(4));
        when(servicio.esValido(any(), any(), any())).thenReturn(true);

        // Act
        Matricula m = new Matricula(est, curso, periodo, servicio);
        assertEquals(360.0, m.getCostoTotal(), 0.001);

        double refund = m.retirarCurso(LocalDate.now().plusDays(3));

        // Assert
        assertEquals("Retirada", m.getEstado());
        assertEquals(360.0 * 0.70, refund, 0.001);
        verify(servicio, times(1)).esValido(any(), any(), any());
        System.out.println("\n--- TEST 4: Flujo Integral Matricula ---");
        System.out.println("Estudiante: " + est.getNombre() + " | Curso: " + curso.getNombre());
        System.out.println("Paso 1: Crear matricula -> Costo: S/" + m.getCostoTotal() + " Estado: " + m.getEstado() + " [OK]");
        System.out.println("Paso 2: Retiro dia 3 -> Devolucion 70%: S/" + refund + " [OK]");
        System.out.println("Flujo completo: APROBADO\n");
    }
}
