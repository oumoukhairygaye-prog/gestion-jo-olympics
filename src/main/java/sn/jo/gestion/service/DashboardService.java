package sn.jo.gestion.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sn.jo.gestion.dto.ClassementPointsDTO;
import sn.jo.gestion.dto.MedailleTableauDTO;
import sn.jo.gestion.dto.TotalMedaillesDTO;
import sn.jo.gestion.repository.AthleteRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final AthleteRepository athleteRepository;
    private final MedailleService medailleService;

    public long totalAthletes() {
        return athleteRepository.count();
    }

    // Nombre de pays distincts ayant au moins un athlete inscrit
    public long totalPaysParticipants() {
        return athleteRepository.findAll().stream()
                .filter(a -> a.getNationalite() != null)
                .map(a -> a.getNationalite().getId())
                .distinct()
                .count();
    }

    public TotalMedaillesDTO totalMedailles() {
        List<MedailleTableauDTO> tableau = medailleService.getTableauMedailles();
        long or = tableau.stream().mapToLong(MedailleTableauDTO::getOr).sum();
        long argent = tableau.stream().mapToLong(MedailleTableauDTO::getArgent).sum();
        long bronze = tableau.stream().mapToLong(MedailleTableauDTO::getBronze).sum();
        return new TotalMedaillesDTO(or, argent, bronze, or + argent + bronze);
    }

    // Classement par points : Or = 7 pts, Argent = 4 pts, Bronze = 1 pt
    public List<ClassementPointsDTO> classementParPoints() {
        List<ClassementPointsDTO> classement = medailleService.getTableauMedailles().stream()
                .map(m -> new ClassementPointsDTO(
                        m.getPaysId(), m.getPays(), m.getOr(), m.getArgent(), m.getBronze(),
                        m.getOr() * 7 + m.getArgent() * 4 + m.getBronze()))
                .collect(Collectors.toList());

        classement.sort(Comparator.comparingLong(ClassementPointsDTO::getPoints).reversed());
        return classement;
    }

    // Nombre de medailles par pays (reutilise le meme calcul que le tableau des medailles)
    public List<MedailleTableauDTO> medaillesParPays() {
        return medailleService.getTableauMedailles();
    }

}
