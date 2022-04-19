package thiagojv.cookbook.java.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import thiagojv.cookbook.java.entities.Endereco;
import thiagojv.cookbook.java.repositories.EnderecoRepository;

import java.util.List;

@RestController
@RequestMapping(path = "/oficina/{idOficina}/endereco")
public class EnderecoController {

    private final EnderecoRepository repository;
    @Autowired
    private EnderecoRepository enderecoRepository;

    EnderecoController(EnderecoRepository enderecoRepository) {
        this.repository = enderecoRepository;
    }

    @GetMapping
    public List findAll (){
        return repository.findAll();
    }

    @GetMapping(path = {"/{id}"})
   public ResponseEntity findById (@PathVariable long id){
        return repository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
        }

    @PostMapping
    public Endereco salvar(@RequestBody Endereco endereco){
        return repository.save(endereco);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity alterar(@PathVariable Long id,@RequestBody Endereco endereco){
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

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> apagar(@PathVariable Long id){
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
