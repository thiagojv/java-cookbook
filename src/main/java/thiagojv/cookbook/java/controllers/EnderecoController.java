package thiagojv.cookbook.java.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import thiagojv.cookbook.java.entities.Endereco;
import thiagojv.cookbook.java.repositories.EnderecoRepository;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping(path = "/endereco")
public class EnderecoController {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @GetMapping
    public List<Endereco> recuperarTodos() {
        return enderecoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Endereco> recuperar(@PathVariable UUID id ) {
        var endereco = enderecoRepository.findById(id);
        if (endereco.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(endereco.get());
    }

    @PostMapping
    public ResponseEntity<Void> salvar(@RequestBody Endereco endereco) {
        if (endereco.getEndereco().isBlank()) {
            return ResponseEntity.badRequest().build();
        }
        try {
            enderecoRepository.save(endereco);
            return ResponseEntity.status(HttpStatus.CREATED).build();
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