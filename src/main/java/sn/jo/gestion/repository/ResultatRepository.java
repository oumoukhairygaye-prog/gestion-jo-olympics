package sn.jo.gestion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sn.jo.gestion.entity.Resultat;
import sn.jo.gestion.entity.TypeMedaille;

import java.util.List;

public interface ResultatRepository extends JpaRepository<Resultat, Long> {

    List<Resultat> findByEpreuveIdOrderByClassementAsc(Long epreuveId);

    // Utile pour construire le tableau des médailles / dashboard
    List<Resultat> findByMedailleNot(TypeMedaille medaille);

}
