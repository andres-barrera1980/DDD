package co.edu.javeriana.ddd.estudiantes.infrastructure.persistence.repositories;

import co.edu.javeriana.ddd.estudiantes.domain.model.Estudiante;
import co.edu.javeriana.ddd.estudiantes.domain.repository.EstudianteRepository;
import co.edu.javeriana.ddd.estudiantes.domain.valueobjects.EstudianteId;
import co.edu.javeriana.ddd.estudiantes.infrastructure.persistence.entities.EstudianteEntity;
import co.edu.javeriana.ddd.estudiantes.infrastructure.persistence.mappers.EstudianteMapper;
import co.edu.javeriana.ddd.shared.domain.valueobjects.Email;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class JpaEstudianteRepository implements EstudianteRepository {

    private final EntityManager entityManager;

    public JpaEstudianteRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void guardar(Estudiante estudiante) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            EstudianteEntity entity = EstudianteMapper.toEntity(estudiante);
            
            if (entityManager.find(EstudianteEntity.class, entity.getId()) == null) {
                entityManager.persist(entity);
            } else {
                entityManager.merge(entity);
            }
            
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Error al guardar el estudiante", e);
        }
    }

    @Override
    public Optional<Estudiante> obtenerPorId(EstudianteId id) {
        EstudianteEntity entity = entityManager.find(EstudianteEntity.class, id.value());
        return Optional.ofNullable(entity).map(EstudianteMapper::toDomain);
    }

    @Override
    public List<Estudiante> obtenerTodos() {
        return entityManager.createQuery("SELECT e FROM EstudianteEntity e", EstudianteEntity.class)
                .getResultList()
                .stream()
                .map(EstudianteMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void eliminar(EstudianteId id) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            EstudianteEntity entity = entityManager.find(EstudianteEntity.class, id.value());
            if (entity != null) {
                entityManager.remove(entity);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Error al eliminar el estudiante", e);
        }
    }

    @Override
    public Optional<Estudiante> buscarPorEmail(Email email) {
        try {
            EstudianteEntity entity = entityManager.createQuery(
                    "SELECT e FROM EstudianteEntity e WHERE e.email = :email", EstudianteEntity.class)
                    .setParameter("email", email.value())
                    .getSingleResult();
            return Optional.of(EstudianteMapper.toDomain(entity));
        } catch (jakarta.persistence.NoResultException e) {
            return Optional.empty();
        }
    }
}
