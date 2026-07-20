package sn.jo.gestion.dto;

import lombok.Data;
import sn.jo.gestion.entity.Sexe;

import java.time.LocalDate;

@Data
public class AthleteRequestDTO {

    private String nom;
    private String prenom;
    private Sexe sexe;
    private LocalDate dateNaissance;
    private Long paysId;
    private Long disciplineId;
    private Double taille;
    private Double poids;

}
