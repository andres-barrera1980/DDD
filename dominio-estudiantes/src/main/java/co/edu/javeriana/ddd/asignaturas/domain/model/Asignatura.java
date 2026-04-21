package co.edu.javeriana.ddd.asignaturas.domain.model;

import co.edu.javeriana.ddd.asignaturas.domain.valueobjects.AsignaturaId;
import co.edu.javeriana.ddd.asignaturas.domain.valueobjects.Creditos;
import co.edu.javeriana.ddd.asignaturas.domain.valueobjects.NombreAsignatura;
import co.edu.javeriana.ddd.shared.domain.model.AggregateRoot;

import java.util.Objects;

public class Asignatura extends AggregateRoot {
    private final AsignaturaId id;
    private final NombreAsignatura nombre;
    private final Creditos creditos;
    private boolean activa;

    private Asignatura(AsignaturaId id, NombreAsignatura nombre, Creditos creditos, boolean activa) {
        this.id = Objects.requireNonNull(id);
        this.nombre = Objects.requireNonNull(nombre);
        this.creditos = Objects.requireNonNull(creditos);
        this.activa = activa;
    }

    public static Asignatura crear(AsignaturaId id, NombreAsignatura nombre, Creditos creditos) {
        return new Asignatura(id, nombre, creditos, true);
    }

    public void desactivar() {
        this.activa = false;
    }

    public void activar() {
        this.activa = true;
    }

    public AsignaturaId getId() { return id; }
    public NombreAsignatura getNombre() { return nombre; }
    public Creditos getCreditos() { return creditos; }
    public boolean isActiva() { return activa; }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private AsignaturaId id;
        private NombreAsignatura nombre;
        private Creditos creditos;
        private boolean activa;

        public Builder id(AsignaturaId id) { this.id = id; return this; }
        public Builder nombre(NombreAsignatura nombre) { this.nombre = nombre; return this; }
        public Builder creditos(Creditos creditos) { this.creditos = creditos; return this; }
        public Builder activa(boolean activa) { this.activa = activa; return this; }

        public Asignatura build() {
            return new Asignatura(id, nombre, creditos, activa);
        }
    }
}
