package thiagojv.cookbook.java.controllers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import thiagojv.cookbook.java.entities.Oficina;
import thiagojv.cookbook.java.repositories.OficinaRepository;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping(path = "/oficina")

public class OficinaController {

    @Autowired
    private OficinaRepository oficinaRepository;

    @GetMapping
    public List<Oficina> recuperarTodos() {
        return oficinaRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Oficina> recuperar(@PathVariable UUID id ) {
        var oficina = oficinaRepository.findById(id);
        if (oficina.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(oficina.get());
    }
    @PostMapping
    public ResponseEntity<Void> salvar(@RequestBody Oficina model){
        if (model.getNome().isBlank()){
            return ResponseEntity.badRequest().build();
        }
        try {
            oficinaRepository.save(model);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }catch (Exception e){
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<Void> alterar(@PathVariable UUID id,@RequestBody Oficina model){
        if (id == null) {
            return ResponseEntity.badRequest().build();
        }else if (model.getNome().isBlank()){
            return ResponseEntity.badRequest().build();
        }
        var optional =oficinaRepository.findById(id);
        if (optional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        var oficina = optional.get();
        oficina.setNome(model.getNome());

        try {
            oficinaRepository.save(oficina);
            return ResponseEntity.accepted().build();

        } catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> apagar(@PathVariable UUID id){
        if (id == null) {
            return ResponseEntity.badRequest().build();
        }else if (!oficinaRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }

        try {
            oficinaRepository.deleteById(id);
            return ResponseEntity.accepted().build();
        }catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }
}
