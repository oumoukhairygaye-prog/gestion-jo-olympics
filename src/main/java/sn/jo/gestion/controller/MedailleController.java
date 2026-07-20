package sn.jo.gestion.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sn.jo.gestion.dto.MedailleTableauDTO;
import sn.jo.gestion.service.MedailleService;

import java.util.List;

@RestController
@RequestMapping("/api/medailles")
@RequiredArgsConstructor
public class MedailleController {

    private final MedailleService medailleService;

    // Tableau des medailles par pays, trie or > argent > bronze
    @GetMapping("/tableau")
    public ResponseEntity<List<MedailleTableauDTO>> tableau() {
        return ResponseEntity.ok(medailleService.getTableauMedailles());
    }

}
