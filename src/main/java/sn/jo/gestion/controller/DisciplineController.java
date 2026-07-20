package sn.jo.gestion.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.jo.gestion.entity.Athlete;
import sn.jo.gestion.entity.Discipline;
import sn.jo.gestion.exception.ResourceNotFoundException;
import sn.jo.gestion.repository.AthleteRepository;
import sn.jo.gestion.repository.DisciplineRepository;

import java.util.List;

@RestController
@RequestMapping("/api/disciplines")
@RequiredArgsConstructor
public class DisciplineController {

    private final DisciplineRepository disciplineRepository;
    private final AthleteRepository athleteRepository;

    @PostMapping
    public ResponseEntity<Discipline> create(@Valid @RequestBody Discipline discipline) {
        return ResponseEntity.status(HttpStatus.CREATED).body(disciplineRepository.save(discipline));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Discipline> update(@PathVariable Long id, @Valid @RequestBody Discipline updated) {
        Discipline discipline = findEntity(id);
        discipline.setNom(updated.getNom());
        discipline.setDescription(updated.getDescription());
        return ResponseEntity.ok(disciplineRepository.save(discipline));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        disciplineRepository.delete(findEntity(id));
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Discipline>> findAll() {
        return ResponseEntity.ok(disciplineRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Discipline> findById(@PathVariable Long id) {
        return ResponseEntity.ok(findEntity(id));
    }

    // Consultation des athletes d'une discipline (exigence du sujet)
    @GetMapping("/{id}/athletes")
    public ResponseEntity<List<Athlete>> findAthletes(@PathVariable Long id) {
        findEntity(id); // verifie que la discipline existe
        return ResponseEntity.ok(athleteRepository.findByDisciplineId(id));
    }

    private Discipline findEntity(Long id) {
        return disciplineRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Discipline introuvable avec id=" + id));
    }

}
