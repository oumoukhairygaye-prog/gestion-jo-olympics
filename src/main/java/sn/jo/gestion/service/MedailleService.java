package sn.jo.gestion.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sn.jo.gestion.dto.MedailleTableauDTO;
import sn.jo.gestion.entity.Pays;
import sn.jo.gestion.entity.Resultat;
import sn.jo.gestion.entity.TypeMedaille;
import sn.jo.gestion.repository.ResultatRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MedailleService {

    private final ResultatRepository resultatRepository;

    public List<MedailleTableauDTO> getTableauMedailles() {
        List<Resultat> resultatsAvecMedaille = resultatRepository.findByMedailleNot(TypeMedaille.AUCUNE);

        // Regroupe les resultats par pays de l'athlete
        Map<Pays, List<Resultat>> parPays = resultatsAvecMedaille.stream()
                .filter(r -> r.getAthlete() != null && r.getAthlete().getNationalite() != null)
                .collect(Collectors.groupingBy(r -> r.getAthlete().getNationalite()));

        List<MedailleTableauDTO> tableau = new ArrayList<>();
        for (Map.Entry<Pays, List<Resultat>> entry : parPays.entrySet()) {
            Pays pays = entry.getKey();
            List<Resultat> resultats = entry.getValue();

            long or = resultats.stream().filter(r -> r.getMedaille() == TypeMedaille.OR).count();
            long argent = resultats.stream().filter(r -> r.getMedaille() == TypeMedaille.ARGENT).count();
            long bronze = resultats.stream().filter(r -> r.getMedaille() == TypeMedaille.BRONZE).count();

            tableau.add(new MedailleTableauDTO(pays.getId(), pays.getNom(), or, argent, bronze, or + argent + bronze));
        }

        // Tri : or desc, puis argent desc en cas d'egalite, puis bronze desc
        tableau.sort(Comparator
                .comparingLong(MedailleTableauDTO::getOr).reversed()
                .thenComparing(Comparator.comparingLong(MedailleTableauDTO::getArgent).reversed())
                .thenComparing(Comparator.comparingLong(MedailleTableauDTO::getBronze).reversed()));

        return tableau;
    }

}
