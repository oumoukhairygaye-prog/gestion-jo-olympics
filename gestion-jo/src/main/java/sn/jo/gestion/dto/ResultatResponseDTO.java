package sn.jo.gestion.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sn.jo.gestion.entity.TypeMedaille;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultatResponseDTO {

    private Long id;
    private String epreuve;       // nom de l'epreuve
    private String athleteNom;
    private String athletePrenom;
    private String athletePays;
    private Integer classement;
    private String performance;
    private TypeMedaille medaille;

}
