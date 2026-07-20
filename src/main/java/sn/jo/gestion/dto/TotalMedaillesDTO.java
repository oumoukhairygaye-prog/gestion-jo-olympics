package sn.jo.gestion.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TotalMedaillesDTO {

    private long or;
    private long argent;
    private long bronze;
    private long total;

}
