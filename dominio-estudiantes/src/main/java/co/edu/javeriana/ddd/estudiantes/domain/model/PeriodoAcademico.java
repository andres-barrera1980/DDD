package co.edu.javeriana.ddd.estudiantes.domain.model;

import co.edu.javeriana.ddd.asignaturas.domain.valueobjects.AsignaturaId;
import co.edu.javeriana.ddd.asignaturas.domain.valueobjects.Creditos;
import co.edu.javeriana.ddd.estudiantes.domain.exceptions.EstudianteException;
import co.edu.javeriana.ddd.estudiantes.domain.valueobjects.EstadoMateria;
import co.edu.javeriana.ddd.estudiantes.domain.valueobjects.PeriodoId;
import co.edu.javeriana.ddd.estudiantes.domain.valueobjects.PromedioPonderado;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class PeriodoAcademico {
    private final PeriodoId periodoId;
    private final List<Materia> materias;

    public PeriodoAcademico(PeriodoId periodoId) {
        this.periodoId = Objects.requireNonNull(periodoId);
        this.materias = new ArrayList<>();
    }

    // Constructor para persistencia/builder
    public PeriodoAcademico(PeriodoId periodoId, List<Materia> materias) {
        this.periodoId = Objects.requireNonNull(periodoId);
        this.materias = new ArrayList<>(materias);
    }

    public void inscribir(AsignaturaId asignaturaId, Creditos creditos) {
        boolean yaInscrita = materias.stream()
                .anyMatch(m -> m.getAsignaturaId().equals(asignaturaId));
        
        if (yaInscrita) {
            throw new EstudianteException("La asignatura " + asignaturaId.value() + " ya está en este periodo");
        }

        this.materias.add(new Materia(asignaturaId, creditos));
    }

    public PromedioPonderado calcularPromedioPeriodo() {
        float sumaNotasPorCreditos = 0;
        int totalCreditos = 0;

        for (Materia m : materias) {
            if (m.getEstado() == EstadoMateria.APROBADA || m.getEstado() == EstadoMateria.REPROBADA) {
                sumaNotasPorCreditos += (m.getCalificacion().value() * m.getCreditos().value());
                totalCreditos += m.getCreditos().value();
            }
        }

        return totalCreditos == 0 ? PromedioPonderado.sinCalcular() : new PromedioPonderado(sumaNotasPorCreditos / totalCreditos);
    }

    public PeriodoId getPeriodoId() { return periodoId; }
    public List<Materia> getMaterias() { return Collections.unmodifiableList(materias); }
}
