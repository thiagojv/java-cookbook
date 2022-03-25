package thiagojv.cookbook.java.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import thiagojv.cookbook.java.entities.Pessoa;
import thiagojv.cookbook.java.repositories.PessoaRepository;

@RestController
@RequestMapping(path = "/pessoa")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PessoaController {
  private PessoaRepository repository;

  @GetMapping
  public Iterable<Pessoa> recuperarTodos() {
    return repository.findAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Pessoa> recuperar(@PathVariable Integer id) {
    var pessoa = repository.findById(id);

    if (pessoa.isEmpty()) {
      return ResponseEntity
              .notFound()
              .build();
    }

    return ResponseEntity
            .ok(pessoa.get());
  }

  @PostMapping
  public ResponseEntity<Void> salvar(@RequestBody Pessoa model) {
    if (model.getNome().isBlank()) {
      return ResponseEntity
              .badRequest()
              .build();
    }

    try {
      repository.save(model);

      return ResponseEntity
              .status(HttpStatus.CREATED)
              .build();
    } catch (Exception e) {
      return ResponseEntity
              .internalServerError()
              .build();
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<Void> alterar(@PathVariable Integer id, @RequestBody Pessoa model) {
    if (id == null || model.getNome().isBlank()) {
      return ResponseEntity
              .badRequest()
              .build();
    }

    var optional = repository.findById(id);

    if (optional.isEmpty()) {
      return ResponseEntity
              .notFound()
              .build();
    }

    var pessoa = optional.get();
    pessoa.setNome(model.getNome());

    try {
      repository.save(pessoa);

      return ResponseEntity
              .accepted()
              .build();
    } catch (Exception e) {
      return ResponseEntity
              .internalServerError()
              .build();
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> apagar(@PathVariable Integer id) {
    if (id == null) {
      return ResponseEntity
              .badRequest()
              .build();
    } else if (!repository.existsById(id)) {
      return ResponseEntity
              .notFound()
              .build();
    }

    try {
      repository.deleteById(id);

      return ResponseEntity
              .accepted()
              .build();
    } catch (Exception e) {
      return ResponseEntity
              .internalServerError()
              .build();
    }
  }
}
