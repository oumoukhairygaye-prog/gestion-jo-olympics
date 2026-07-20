package sn.jo.gestion.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sn.jo.gestion.dto.ClassementPointsDTO;
import sn.jo.gestion.dto.MedailleTableauDTO;
import sn.jo.gestion.dto.TotalMedaillesDTO;
import sn.jo.gestion.service.DashboardService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping("/total-athletes")
    public ResponseEntity<Map<String, Long>> totalAthletes() {
        return ResponseEntity.ok(Map.of("totalAthletes", dashboardService.totalAthletes()));
    }

    @GetMapping("/total-pays")
    public ResponseEntity<Map<String, Long>> totalPays() {
        return ResponseEntity.ok(Map.of("totalPaysParticipants", dashboardService.totalPaysParticipants()));
    }

    @GetMapping("/total-medailles")
    public ResponseEntity<TotalMedaillesDTO> totalMedailles() {
        return ResponseEntity.ok(dashboardService.totalMedailles());
    }

    @GetMapping("/classement-points")
    public ResponseEntity<List<ClassementPointsDTO>> classementParPoints() {
        return ResponseEntity.ok(dashboardService.classementParPoints());
    }

    @GetMapping("/medailles-par-pays")
    public ResponseEntity<List<MedailleTableauDTO>> medaillesParPays() {
        return ResponseEntity.ok(dashboardService.medaillesParPays());
    }

}
