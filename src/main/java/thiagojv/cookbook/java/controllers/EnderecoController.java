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

import java.util.UUID;

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
    public ResponseEntity<Endereco> listOne(@PathVariable UUID idOficina, @PathVariable Long id) {
        try{
            Oficina oficina = oficinaRepository.findById(idOficina).get();

            Iterable<Endereco> endereco = enderecoRepository.findByOficina(oficina);
            Endereco enderecoReturn = new Endereco();
            for(Endereco end: endereco) {
                if(end.getId() == id){
                    enderecoReturn = end;
                }
            }
            if (enderecoReturn.getEndereco() == null){
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(enderecoReturn);
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
