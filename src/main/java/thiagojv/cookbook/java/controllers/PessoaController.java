package thiagojv.cookbook.java.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import thiagojv.cookbook.java.entities.Pessoa;
import thiagojv.cookbook.java.repositories.PessoaRepository;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/pessoa")
public class PessoaController {

    @Autowired
    private PessoaRepository pessoaRepository;

    @GetMapping
    public List<Pessoa> recuperarTodos(){
        return pessoaRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> recuperar(@PathVariable UUID id){
        var pessoa = pessoaRepository.findById(id);
        if (pessoa.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pessoa.get());
    }

    @PostMapping
    public ResponseEntity<Void> salvar(@RequestBody  Pessoa model){
        if (model.getNome().isBlank()){
            return ResponseEntity.badRequest().build();
        }
        try {
            pessoaRepository.save(model);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> alterar(@PathVariable UUID id,@RequestBody Pessoa model){
        if (id == null) {
            return ResponseEntity.badRequest().build();
        }else if (model.getNome().isBlank()){
            return ResponseEntity.badRequest().build();
        }
        var optional =pessoaRepository.findById(id);
        if (optional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        var pessoa = optional.get();
        pessoa.setNome(model.getNome());

        try {
            pessoaRepository.save(pessoa);
            return ResponseEntity.accepted().build();

        } catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> apagar(@PathVariable UUID id){
        if (id == null) {
            return ResponseEntity.badRequest().build();
        }else if (!pessoaRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }

        try {
            pessoaRepository.deleteById(id);
            return ResponseEntity.accepted().build();
        }catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }
}
