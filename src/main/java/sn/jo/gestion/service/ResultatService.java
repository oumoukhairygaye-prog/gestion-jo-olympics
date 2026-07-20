package sn.jo.gestion.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sn.jo.gestion.dto.ResultatRequestDTO;
import sn.jo.gestion.dto.ResultatResponseDTO;
import sn.jo.gestion.entity.Athlete;
import sn.jo.gestion.entity.Epreuve;
import sn.jo.gestion.entity.Resultat;
import sn.jo.gestion.exception.ResourceNotFoundException;
import sn.jo.gestion.repository.AthleteRepository;
import sn.jo.gestion.repository.EpreuveRepository;
import sn.jo.gestion.repository.ResultatRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResultatService {

    private final ResultatRepository resultatRepository;
    private final EpreuveRepository epreuveRepository;
    private final AthleteRepository athleteRepository;

    // Enregistre un resultat et attribue automatiquement la medaille selon le classement
    public ResultatResponseDTO create(ResultatRequestDTO dto) {
        Epreuve epreuve = epreuveRepository.findById(dto.getEpreuveId())
                .orElseThrow(() -> new ResourceNotFoundException("Epreuve introuvable avec id=" + dto.getEpreuveId()));
        Athlete athlete = athleteRepository.findById(dto.getAthleteId())
                .orElseThrow(() -> new ResourceNotFoundException("Athlete introuvable avec id=" + dto.getAthleteId()));

        Resultat resultat = new Resultat();
        resultat.setEpreuve(epreuve);
        resultat.setAthlete(athlete);
        resultat.setClassement(dto.getClassement());
        resultat.setPerformance(dto.getPerformance());
        resultat.attribuerMedaille(); // OR / ARGENT / BRONZE / AUCUNE selon le classement

        return toResponseDTO(resultatRepository.save(resultat));
    }

    // Tous les resultats d'une epreuve, tries par classement
    public List<ResultatResponseDTO> findByEpreuve(Long epreuveId) {
        return resultatRepository.findByEpreuveIdOrderByClassementAsc(epreuveId)
                .stream().map(this::toResponseDTO).toList();
    }

    // Le podium = les 3 premiers d'une epreuve
    public List<ResultatResponseDTO> podium(Long epreuveId) {
        return resultatRepository.findByEpreuveIdOrderByClassementAsc(epreuveId)
                .stream()
                .filter(r -> r.getClassement() != null && r.getClassement() <= 3)
                .map(this::toResponseDTO)
                .toList();
    }

    private ResultatResponseDTO toResponseDTO(Resultat r) {
        return new ResultatResponseDTO(
                r.getId(),
                r.getEpreuve() != null ? r.getEpreuve().getNom() : null,
                r.getAthlete() != null ? r.getAthlete().getNom() : null,
                r.getAthlete() != null ? r.getAthlete().getPrenom() : null,
                r.getAthlete() != null && r.getAthlete().getNationalite() != null
                        ? r.getAthlete().getNationalite().getNom() : null,
                r.getClassement(),
                r.getPerformance(),
                r.getMedaille()
        );
    }

}
