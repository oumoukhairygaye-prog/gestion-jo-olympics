package sn.jo.gestion.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.jo.gestion.dto.ResultatRequestDTO;
import sn.jo.gestion.dto.ResultatResponseDTO;
import sn.jo.gestion.service.ResultatService;

import java.util.List;

@RestController
@RequestMapping("/api/resultats")
@RequiredArgsConstructor
public class ResultatController {

    private final ResultatService resultatService;

    // Enregistre le resultat d'un athlete a une epreuve. La medaille est attribuee automatiquement.
    @PostMapping
    public ResponseEntity<ResultatResponseDTO> create(@RequestBody ResultatRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(resultatService.create(dto));
    }

    // Tous les resultats d'une epreuve, tries par classement
    @GetMapping("/epreuve/{epreuveId}")
    public ResponseEntity<List<ResultatResponseDTO>> findByEpreuve(@PathVariable Long epreuveId) {
        return ResponseEntity.ok(resultatService.findByEpreuve(epreuveId));
    }

    // Le podium (top 3) d'une epreuve
    @GetMapping("/epreuve/{epreuveId}/podium")
    public ResponseEntity<List<ResultatResponseDTO>> podium(@PathVariable Long epreuveId) {
        return ResponseEntity.ok(resultatService.podium(epreuveId));
    }

}
