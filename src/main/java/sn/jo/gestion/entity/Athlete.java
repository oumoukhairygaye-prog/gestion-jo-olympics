package sn.jo.gestion.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "athlete")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Athlete {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nom;

    @NotBlank
    private String prenom;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Sexe sexe;

    private LocalDate dateNaissance;

    @ManyToOne
    @JoinColumn(name = "pays_id")
    @NotNull
    private Pays nationalite;

    @ManyToOne
    @JoinColumn(name = "discipline_id")
    @NotNull
    private Discipline discipline;

    // en cm
    private Double taille;

    // en kg
    private Double poids;

}
