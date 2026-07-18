package sn.jo.gestion.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sn.jo.gestion.dto.EpreuveRequestDTO;
import sn.jo.gestion.dto.EpreuveResponseDTO;
import sn.jo.gestion.entity.Discipline;
import sn.jo.gestion.entity.Epreuve;
import sn.jo.gestion.exception.ResourceNotFoundException;
import sn.jo.gestion.repository.DisciplineRepository;
import sn.jo.gestion.repository.EpreuveRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EpreuveService {

    private final EpreuveRepository epreuveRepository;
    private final DisciplineRepository disciplineRepository;

    public EpreuveResponseDTO create(EpreuveRequestDTO dto) {
        Epreuve epreuve = new Epreuve();
        applyDto(epreuve, dto);
        return toResponseDTO(epreuveRepository.save(epreuve));
    }

    public EpreuveResponseDTO update(Long id, EpreuveRequestDTO dto) {
        Epreuve epreuve = findEntity(id);
        applyDto(epreuve, dto);
        return toResponseDTO(epreuveRepository.save(epreuve));
    }

    public void delete(Long id) {
        epreuveRepository.delete(findEntity(id));
    }

    public EpreuveResponseDTO findById(Long id) {
        return toResponseDTO(findEntity(id));
    }

    public List<EpreuveResponseDTO> findAll() {
        return epreuveRepository.findAll().stream().map(this::toResponseDTO).toList();
    }

    public List<EpreuveResponseDTO> findByDiscipline(Long disciplineId) {
        return epreuveRepository.findByDisciplineId(disciplineId).stream().map(this::toResponseDTO).toList();
    }

    // Recherche par date : toutes les epreuves du jour donne (00:00 a 23:59)
    public List<EpreuveResponseDTO> findByDate(LocalDate date) {
        LocalDateTime debut = date.atStartOfDay();
        LocalDateTime fin = date.plusDays(1).atStartOfDay().minusSeconds(1);
        return epreuveRepository.findByDateBetween(debut, fin).stream().map(this::toResponseDTO).toList();
    }

    // --- Helpers ---

    private Epreuve findEntity(Long id) {
        return epreuveRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Epreuve introuvable avec id=" + id));
    }

    private void applyDto(Epreuve epreuve, EpreuveRequestDTO dto) {
        epreuve.setNom(dto.getNom());
        epreuve.setDate(dto.getDate());

        Discipline discipline = disciplineRepository.findById(dto.getDisciplineId())
                .orElseThrow(() -> new ResourceNotFoundException("Discipline introuvable avec id=" + dto.getDisciplineId()));
        epreuve.setDiscipline(discipline);
    }

    private EpreuveResponseDTO toResponseDTO(Epreuve e) {
        return new EpreuveResponseDTO(
                e.getId(),
                e.getNom(),
                e.getDate(),
                e.getDiscipline() != null ? e.getDiscipline().getNom() : null
        );
    }

}
