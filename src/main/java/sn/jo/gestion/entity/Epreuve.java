package sn.jo.gestion.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "epreuve")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Epreuve {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nom;

    @NotNull
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "discipline_id")
    @NotNull
    private Discipline discipline;

    @JsonIgnore
    @OneToMany(mappedBy = "epreuve")
    private List<Resultat> resultats = new ArrayList<>();

}
