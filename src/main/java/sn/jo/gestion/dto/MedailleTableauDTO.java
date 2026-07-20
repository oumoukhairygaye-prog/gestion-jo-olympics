package sn.jo.gestion.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedailleTableauDTO {

    private Long paysId;
    private String pays;
    private long or;
    private long argent;
    private long bronze;
    private long total;

}
