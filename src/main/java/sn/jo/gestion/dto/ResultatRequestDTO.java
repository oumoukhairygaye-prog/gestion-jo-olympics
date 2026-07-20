package sn.jo.gestion.dto;

import lombok.Data;

@Data
public class ResultatRequestDTO {

    private Long epreuveId;
    private Long athleteId;
    private Integer classement;
    private String performance; // temps, score, distance...

}
