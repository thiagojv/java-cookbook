package thiagojv.cookbook.java.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Data
@Entity
public class Oficina {

    @Id
    @JsonProperty
    @GeneratedValue
    private UUID id;

    @JsonProperty
    private String nome;

    @JsonProperty
    private String cpfCnpj;

    @JsonProperty
    private Boolean aprovacaoAutomaticaVistoria;

    @JsonProperty
    private Boolean vistoriaPelaCilia;

    @JsonProperty
    private Boolean vistoriaPorImagem;

    @JsonProperty
    private Boolean descontoFranquia;

    @JsonProperty
    private Boolean aprovado;

    @JsonProperty
    private Boolean removido;

    @OneToMany()
    private List<Endereco> enderecos;
}
