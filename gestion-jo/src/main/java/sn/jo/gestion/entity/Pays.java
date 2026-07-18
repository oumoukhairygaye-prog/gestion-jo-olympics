package sn.jo.gestion.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pays")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pays {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Le nom du pays est obligatoire")
    @Column(nullable = false, unique = true)
    private String nom;

    // Code ISO3, ex: SEN, FRA, USA
    @NotBlank(message = "Le code du pays est obligatoire")
    @Column(nullable = false, unique = true, length = 3)
    private String code;

}
