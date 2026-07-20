package sn.jo.gestion.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sn.jo.gestion.dto.AthleteRequestDTO;
import sn.jo.gestion.dto.AthleteResponseDTO;
import sn.jo.gestion.entity.Athlete;
import sn.jo.gestion.entity.Discipline;
import sn.jo.gestion.entity.Pays;
import sn.jo.gestion.entity.Sexe;
import sn.jo.gestion.exception.ResourceNotFoundException;
import sn.jo.gestion.repository.AthleteRepository;
import sn.jo.gestion.repository.DisciplineRepository;
import sn.jo.gestion.repository.PaysRepository;
import sn.jo.gestion.specification.AthleteSpecification;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AthleteService {

    private final AthleteRepository athleteRepository;
    private final PaysRepository paysRepository;
    private final DisciplineRepository disciplineRepository;

    public AthleteResponseDTO create(AthleteRequestDTO dto) {
        Athlete athlete = new Athlete();
        applyDto(athlete, dto, true);
        return toResponseDTO(athleteRepository.save(athlete));
    }

    // PUT : remplace toutes les informations de l'athlete
    public AthleteResponseDTO update(Long id, AthleteRequestDTO dto) {
        Athlete athlete = findEntity(id);
        applyDto(athlete, dto, true);
        return toResponseDTO(athleteRepository.save(athlete));
    }

    // PATCH : ne modifie que les champs fournis (non-null) dans le DTO
    public AthleteResponseDTO patch(Long id, AthleteRequestDTO dto) {
        Athlete athlete = findEntity(id);
        applyDto(athlete, dto, false);
        return toResponseDTO(athleteRepository.save(athlete));
    }

    public void delete(Long id) {
        athleteRepository.delete(findEntity(id));
    }

    public AthleteResponseDTO findById(Long id) {
        return toResponseDTO(findEntity(id));
    }

    public List<AthleteResponseDTO> findAll() {
        return athleteRepository.findAll().stream().map(this::toResponseDTO).toList();
    }

    public List<AthleteResponseDTO> search(String nom, String prenom, Sexe sexe, Long paysId, Long disciplineId) {
        return athleteRepository
                .findAll(AthleteSpecification.withCriteria(nom, prenom, sexe, paysId, disciplineId))
                .stream().map(this::toResponseDTO).toList();
    }

    // --- Méthodes privées ---

    private Athlete findEntity(Long id) {
        return athleteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Athlete introuvable avec id=" + id));
    }

    // fullUpdate = true (PUT, tout est remplacé) / false (PATCH, seuls les champs non-null sont appliqués)
    private void applyDto(Athlete athlete, AthleteRequestDTO dto, boolean fullUpdate) {
        if (fullUpdate || dto.getNom() != null) athlete.setNom(dto.getNom());
        if (fullUpdate || dto.getPrenom() != null) athlete.setPrenom(dto.getPrenom());
        if (fullUpdate || dto.getSexe() != null) athlete.setSexe(dto.getSexe());
        if (fullUpdate || dto.getDateNaissance() != null) athlete.setDateNaissance(dto.getDateNaissance());
        if (fullUpdate || dto.getTaille() != null) athlete.setTaille(dto.getTaille());
        if (fullUpdate || dto.getPoids() != null) athlete.setPoids(dto.getPoids());

        if (fullUpdate || dto.getPaysId() != null) {
            Pays pays = paysRepository.findById(dto.getPaysId())
                    .orElseThrow(() -> new ResourceNotFoundException("Pays introuvable avec id=" + dto.getPaysId()));
            athlete.setNationalite(pays);
        }
        if (fullUpdate || dto.getDisciplineId() != null) {
            Discipline discipline = disciplineRepository.findById(dto.getDisciplineId())
                    .orElseThrow(() -> new ResourceNotFoundException("Discipline introuvable avec id=" + dto.getDisciplineId()));
            athlete.setDiscipline(discipline);
        }
    }

    private AthleteResponseDTO toResponseDTO(Athlete a) {
        return new AthleteResponseDTO(
                a.getId(),
                a.getNom(),
                a.getPrenom(),
                a.getSexe(),
                a.getDateNaissance(),
                a.getNationalite() != null ? a.getNationalite().getNom() : null,
                a.getDiscipline() != null ? a.getDiscipline().getNom() : null,
                a.getTaille(),
                a.getPoids()
        );
    }

}
