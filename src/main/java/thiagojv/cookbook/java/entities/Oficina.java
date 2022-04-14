package thiagojv.cookbook.java.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;
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

    @OneToMany(mappedBy = "oficina", cascade = CascadeType.ALL, fetch = FetchType.EAGER)

    @JsonProperty
    private Boolean aprovacaoAutomaticaVistoria;

    @JsonProperty
    private Boolean vistoriaPelaCilia;

    @JsonProperty
    private Boolean vistoriaPorImagem;

    @JsonProperty
    private Boolean descontoFranquia;

    @OneToMany(mappedBy = "oficina", cascade = CascadeType.ALL, fetch = FetchType.EAGER)

    @JsonProperty
    private Boolean aprovado;

    @JsonProperty
    private Boolean removido;
}
