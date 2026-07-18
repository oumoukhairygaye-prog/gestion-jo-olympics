package sn.jo.gestion.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.jo.gestion.dto.AthleteRequestDTO;
import sn.jo.gestion.dto.AthleteResponseDTO;
import sn.jo.gestion.entity.Sexe;
import sn.jo.gestion.service.AthleteService;

import java.util.List;

@RestController
@RequestMapping("/api/athletes")
@RequiredArgsConstructor
public class AthleteController {

    private final AthleteService athleteService;

    @PostMapping
    public ResponseEntity<AthleteResponseDTO> create(@Valid @RequestBody AthleteRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(athleteService.create(dto));
    }

    // Modification complète (toutes les infos doivent être fournies)
    @PutMapping("/{id}")
    public ResponseEntity<AthleteResponseDTO> update(@PathVariable Long id, @Valid @RequestBody AthleteRequestDTO dto) {
        return ResponseEntity.ok(athleteService.update(id, dto));
    }

    // Modification partielle (seuls les champs envoyés sont modifiés)
    @PatchMapping("/{id}")
    public ResponseEntity<AthleteResponseDTO> patch(@PathVariable Long id, @RequestBody AthleteRequestDTO dto) {
        return ResponseEntity.ok(athleteService.patch(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        athleteService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AthleteResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(athleteService.findById(id));
    }

    // Recherche multicritère : GET /api/athletes?nom=usain&sexe=HOMME&paysId=1&disciplineId=2
    // Si aucun critère n'est fourni, renvoie tous les athlètes
    @GetMapping
    public ResponseEntity<List<AthleteResponseDTO>> search(
            @RequestParam(required = false) String nom,
            @RequestParam(required = false) String prenom,
            @RequestParam(required = false) Sexe sexe,
            @RequestParam(required = false) Long paysId,
            @RequestParam(required = false) Long disciplineId) {

        boolean aucunCritere = nom == null && prenom == null && sexe == null && paysId == null && disciplineId == null;
        if (aucunCritere) {
            return ResponseEntity.ok(athleteService.findAll());
        }
        return ResponseEntity.ok(athleteService.search(nom, prenom, sexe, paysId, disciplineId));
    }

}
