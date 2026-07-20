package sn.jo.gestion.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.jo.gestion.entity.Pays;
import sn.jo.gestion.exception.ResourceNotFoundException;
import sn.jo.gestion.repository.PaysRepository;

import java.util.List;

@RestController
@RequestMapping("/api/pays")
@RequiredArgsConstructor
public class PaysController {

    private final PaysRepository paysRepository;

    @PostMapping
    public ResponseEntity<Pays> create(@Valid @RequestBody Pays pays) {
        return ResponseEntity.status(HttpStatus.CREATED).body(paysRepository.save(pays));
    }

    @GetMapping
    public ResponseEntity<List<Pays>> findAll() {
        return ResponseEntity.ok(paysRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pays> findById(@PathVariable Long id) {
        return ResponseEntity.ok(paysRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pays introuvable avec id=" + id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        paysRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
