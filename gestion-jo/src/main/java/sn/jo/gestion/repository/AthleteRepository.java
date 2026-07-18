package sn.jo.gestion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import sn.jo.gestion.entity.Athlete;

import java.util.List;

// JpaSpecificationExecutor permet la recherche multicritère dynamique (voir AthleteSpecification à créer côté service)
public interface AthleteRepository extends JpaRepository<Athlete, Long>, JpaSpecificationExecutor<Athlete> {

    List<Athlete> findByDisciplineId(Long disciplineId);

}
