package med.voll.api.domain.medico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.ResponseEntity;

import jakarta.transaction.Transactional;

import java.time.LocalDateTime;

public interface MedicoRepository extends JpaRepository <Medico,Long> {
    
    Page<Medico> findByActivoTrue(Pageable paginacion);
@Query("""
        select m from Medico m
        where m.activo=1 and
        m.especialidad=:especialidad
        m.id not in(
        select c.medico.id from Consulta c 
        c.data=:fecha
        )
        order by rand()
        limit 1
        """)
    Medico seleccionarMedicoConEspecialidadEnFecha(Especialidad especialidad, LocalDateTime fecha);
        @Query("""
                select m.activo
                from Medico m
                where m.id=:idMedico
                """)
    Boolean findActivoById(Long idMedico);
        @DeleteMapping("/{id}")
    @Transactional
    default ResponseEntity<Void> eliminarMedico(@PathVariable Long id) {
        Medico medico = getReferenceById(id);
        medico.desactivaMedico();
        return ResponseEntity.noContent().build();
    }
}

