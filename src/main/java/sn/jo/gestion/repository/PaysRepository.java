package sn.jo.gestion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sn.jo.gestion.entity.Pays;

public interface PaysRepository extends JpaRepository<Pays, Long> {
}
