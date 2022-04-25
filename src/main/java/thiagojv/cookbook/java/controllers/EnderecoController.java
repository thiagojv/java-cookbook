package thiagojv.cookbook.java.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import thiagojv.cookbook.java.entities.Endereco;
import thiagojv.cookbook.java.entities.Oficina;
import thiagojv.cookbook.java.repositories.EnderecoRepository;
import thiagojv.cookbook.java.repositories.OficinaRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping(path = "/oficina/{idOficina}/endereco")
public class EnderecoController {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private OficinaRepository oficinaRepository;

    @GetMapping()
    public ResponseEntity<Iterable<Endereco>> listAll(@PathVariable UUID idOficina) {
        try{
            Oficina oficina = oficinaRepository.findById(idOficina).get();

            Iterable<Endereco> endereco = enderecoRepository.findByOficina(oficina);
            return ResponseEntity.ok(endereco);
        }catch (Exception e){
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Endereco>> listOne(@PathVariable UUID idOficina, @PathVariable Long id) {
        try{
            Oficina oficina = oficinaRepository.findById(idOficina).get();

            List<Endereco> enderecos = enderecoRepository.findByOficina(oficina).stream().filter(e -> e.getId() == id).collect(Collectors.toList());

            if (enderecos.size() == 0) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(enderecos);
        }catch (Exception e){
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping()
    public ResponseEntity<Void> salvar(@PathVariable UUID idOficina, @RequestBody Endereco endereco){
        try{
            Oficina oficina = oficinaRepository.findById(idOficina).get();
            endereco.setOficina(oficina);
            enderecoRepository.save(endereco);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }catch (Exception e){
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }
}
