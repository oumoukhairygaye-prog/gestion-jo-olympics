package sn.jo.gestion.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "resultat")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Resultat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "epreuve_id")
    @NotNull
    private Epreuve epreuve;

    @ManyToOne
    @JoinColumn(name = "athlete_id")
    @NotNull
    private Athlete athlete;

    // 1 = premier, 2 = deuxième, etc.
    @NotNull
    private Integer classement;

    // Temps, score, distance... selon la discipline
    private String performance;

    @Enumerated(EnumType.STRING)
    private TypeMedaille medaille = TypeMedaille.AUCUNE;

    // Attribue automatiquement la médaille selon le classement
    public void attribuerMedaille() {
        if (classement == null) {
            this.medaille = TypeMedaille.AUCUNE;
            return;
        }
        this.medaille = switch (classement) {
            case 1 -> TypeMedaille.OR;
            case 2 -> TypeMedaille.ARGENT;
            case 3 -> TypeMedaille.BRONZE;
            default -> TypeMedaille.AUCUNE;
        };
    }

}
