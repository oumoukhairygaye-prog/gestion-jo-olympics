package sn.jo.gestion.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sn.jo.gestion.entity.Sexe;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AthleteResponseDTO {

    private Long id;
    private String nom;
    private String prenom;
    private Sexe sexe;
    private LocalDate dateNaissance;
    private String nationalite; // nom du pays
    private String discipline;  // nom de la discipline
    private Double taille;
    private Double poids;

}
