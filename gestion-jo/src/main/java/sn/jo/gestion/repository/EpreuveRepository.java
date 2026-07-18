package sn.jo.gestion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sn.jo.gestion.entity.Epreuve;

import java.time.LocalDateTime;
import java.util.List;

public interface EpreuveRepository extends JpaRepository<Epreuve, Long> {

    List<Epreuve> findByDisciplineId(Long disciplineId);

    List<Epreuve> findByDateBetween(LocalDateTime debut, LocalDateTime fin);

}
