package sn.jo.gestion.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EpreuveRequestDTO {

    private String nom;
    private LocalDateTime date;
    private Long disciplineId;

}
