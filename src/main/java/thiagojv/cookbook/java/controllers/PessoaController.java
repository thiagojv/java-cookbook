package thiagojv.cookbook.java.controllers;

import org.springframework.web.bind.annotation.*;
import thiagojv.cookbook.java.entities.Pessoa;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/pessoa")
public class PessoaController {
  @GetMapping
  public List<Pessoa> recuperarTodos() {
    return new ArrayList<>();
  }

  @GetMapping("/{id}")
  public Pessoa recuperar(String id) {
    return new Pessoa();
  }

  @PostMapping
  public void salvar(Pessoa pessoa) {

  }

  @PutMapping("/{id}")
  public void alterar(String id, Pessoa pessoa) {

  }

  @DeleteMapping("/{id}")
  public void apagar(String id) {

  }
}
