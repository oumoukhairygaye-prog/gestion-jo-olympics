package sn.jo.gestion.specification;

import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import sn.jo.gestion.entity.Athlete;
import sn.jo.gestion.entity.Sexe;

public class AthleteSpecification {

    public static Specification<Athlete> withCriteria(String nom, String prenom, Sexe sexe,
                                                        Long paysId, Long disciplineId) {
        return (root, query, cb) -> {
            Predicate predicate = cb.conjunction();

            if (nom != null && !nom.isBlank()) {
                predicate = cb.and(predicate, cb.like(cb.lower(root.get("nom")), "%" + nom.toLowerCase() + "%"));
            }
            if (prenom != null && !prenom.isBlank()) {
                predicate = cb.and(predicate, cb.like(cb.lower(root.get("prenom")), "%" + prenom.toLowerCase() + "%"));
            }
            if (sexe != null) {
                predicate = cb.and(predicate, cb.equal(root.get("sexe"), sexe));
            }
            if (paysId != null) {
                predicate = cb.and(predicate, cb.equal(root.get("nationalite").get("id"), paysId));
            }
            if (disciplineId != null) {
                predicate = cb.and(predicate, cb.equal(root.get("discipline").get("id"), disciplineId));
            }
            return predicate;
        };
    }

}
