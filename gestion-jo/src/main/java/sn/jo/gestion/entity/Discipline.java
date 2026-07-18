package sn.jo.gestion.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "discipline")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Discipline {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Le nom de la discipline est obligatoire")
    @Column(nullable = false, unique = true)
    private String nom;

    private String description;

    @JsonIgnore
    @OneToMany(mappedBy = "discipline")
    private List<Athlete> athletes = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "discipline")
    private List<Epreuve> epreuves = new ArrayList<>();

}
