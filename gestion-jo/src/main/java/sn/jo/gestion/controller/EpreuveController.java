package sn.jo.gestion.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.jo.gestion.dto.EpreuveRequestDTO;
import sn.jo.gestion.dto.EpreuveResponseDTO;
import sn.jo.gestion.service.EpreuveService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/epreuves")
@RequiredArgsConstructor
public class EpreuveController {

    private final EpreuveService epreuveService;

    @PostMapping
    public ResponseEntity<EpreuveResponseDTO> create(@RequestBody EpreuveRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(epreuveService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EpreuveResponseDTO> update(@PathVariable Long id, @RequestBody EpreuveRequestDTO dto) {
        return ResponseEntity.ok(epreuveService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        epreuveService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EpreuveResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(epreuveService.findById(id));
    }

    // GET /api/epreuves -> tout, ou filtre par date et/ou discipline
    // Exemple : /api/epreuves?date=2026-08-10
    // Exemple : /api/epreuves?disciplineId=1
    @GetMapping
    public ResponseEntity<List<EpreuveResponseDTO>> search(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestParam(required = false) Long disciplineId) {

        if (date != null) {
            return ResponseEntity.ok(epreuveService.findByDate(date));
        }
        if (disciplineId != null) {
            return ResponseEntity.ok(epreuveService.findByDiscipline(disciplineId));
        }
        return ResponseEntity.ok(epreuveService.findAll());
    }

}
