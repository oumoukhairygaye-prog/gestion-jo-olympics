package sn.jo.gestion.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EpreuveResponseDTO {

    private Long id;
    private String nom;
    private LocalDateTime date;
    private String discipline; // nom de la discipline

}
