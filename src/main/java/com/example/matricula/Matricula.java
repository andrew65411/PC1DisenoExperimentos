package com.example.matricula;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Matricula {
    private Estudiante estudiante;
    private Curso curso;
    private PeriodoAcademico periodo;
    private LocalDate fechaMatricula;
    private String estado; // "Activa", "Retirada", "Finalizada"
    private LocalDate fechaRetiro;
    private double costoTotal;

    public Matricula(Estudiante estudiante, Curso curso, PeriodoAcademico periodo, ServicioValidacion servicioValidacion) {
        if (estudiante == null || curso == null || periodo == null) {
            throw new IllegalArgumentException("Datos inválidos para matrícula");
        }

        if (!servicioValidacion.esValido(estudiante, curso, periodo)) {
            throw new IllegalArgumentException("Estudiante o curso no válido para matricularse");
        }

        this.estudiante = estudiante;
        this.curso = curso;
        this.periodo = periodo;
        this.fechaMatricula = LocalDate.now();
        this.estado = "Activa";

        this.costoTotal = curso.getCreditos() * curso.getCostoPorCredito();

        if (curso.getCreditos() >= 5) {
            this.costoTotal = this.costoTotal * 0.95; // 5% discount
        }
    }

    public double retirarCurso(LocalDate fechaRetiro) {
        long diasDesdeMatricula = ChronoUnit.DAYS.between(fechaMatricula, fechaRetiro);

        if (diasDesdeMatricula <= 7 && diasDesdeMatricula >= 0) {
            this.estado = "Retirada";
            this.fechaRetiro = fechaRetiro;
            return this.costoTotal * 0.70; // refund 70%
        } else {
            throw new IllegalArgumentException("No se puede retirar el curso. El retiro solo está permitido dentro de los primeros 7 días.");
        }
    }

    public void finalizarCurso() {
        this.estado = "Finalizada";
    }

    public double getCostoTotal() { return costoTotal; }
    public String getEstado() { return estado; }
    public Estudiante getEstudiante() { return estudiante; }
    public Curso getCurso() { return curso; }
    public LocalDate getFechaMatricula() { return fechaMatricula; }
    public LocalDate getFechaRetiro() { return fechaRetiro; }
}
