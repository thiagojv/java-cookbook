package thiagojv.cookbook.java.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import thiagojv.cookbook.java.entities.Endereco;
import thiagojv.cookbook.java.entities.Oficina;
import thiagojv.cookbook.java.repositories.EnderecoRepository;
import lombok.extern.slf4j.Slf4j;
import thiagojv.cookbook.java.repositories.OficinaRepository;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping(path = "/oficina/{idOficina}/endereco")
public class EnderecoController {

    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private OficinaRepository oficinaRepository;

    @GetMapping
    public ResponseEntity<Oficina> recuperarTodos(@PathVariable("idOficina") UUID idOficina) {
        var oficina = oficinaRepository.findById(idOficina).get();
        return ResponseEntity.ok(oficina);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Endereco> recuperar(@PathVariable("idOficina") UUID idOficina, @PathVariable("id") UUID id ) {
        var oficina = oficinaRepository.findById(idOficina).get();
        if (oficina.getEndereco().isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var end = enderecoRepository.getById(id);
        return ResponseEntity.ok(end);
    }

    @PostMapping
    public ResponseEntity<Oficina> salvar(@PathVariable("idOficina") UUID idOficina, @RequestBody Endereco endereco) {
        if (endereco.getEndereco().isBlank()) {
            return ResponseEntity.badRequest().build();
        }
        try {
            var newOficina = oficinaRepository.findById(idOficina).get();
            Set<Endereco> end = new HashSet<Endereco>();
            end.add(endereco);
            newOficina.setEndereco(end);
            oficinaRepository.save(newOficina);
            return ResponseEntity.ok(newOficina);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<Void> alterar(@PathVariable UUID id,@RequestBody Endereco endereco){
        if (id == null) {
            return ResponseEntity.badRequest().build();
        }else if (endereco.getEndereco().isBlank()){
            return ResponseEntity.badRequest().build();
        }
        var optional =enderecoRepository.findById(id);
        if (optional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        var novoEndereco = optional.get();
        novoEndereco.setEndereco(endereco.getEndereco());
        novoEndereco.setBairro(endereco.getBairro());
        novoEndereco.setCidade(endereco.getCidade());
        novoEndereco.setComplemento(endereco.getComplemento());
        novoEndereco.setCep(endereco.getCep());

        try {
            enderecoRepository.save(novoEndereco);
            return ResponseEntity.accepted().build();

        } catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> apagar(@PathVariable UUID id){
        if (id == null) {
            return ResponseEntity.badRequest().build();
        }else if (!enderecoRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        try {
            enderecoRepository.deleteById(id);
            return ResponseEntity.accepted().build();
        }catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }
}