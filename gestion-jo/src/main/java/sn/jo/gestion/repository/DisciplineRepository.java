package sn.jo.gestion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sn.jo.gestion.entity.Discipline;

public interface DisciplineRepository extends JpaRepository<Discipline, Long> {
}
