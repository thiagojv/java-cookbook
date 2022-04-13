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

    @JsonProperty
    @OneToMany(mappedBy = "oficina", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Oficina> endereco;

    @JsonProperty
    private Integer aprovacaoAutomaticaVistoria;

    @JsonProperty
    private Integer vistoriaPelaCilia;

    @JsonProperty
    private Integer vistoriaPorImagem;

    @JsonProperty
    private Integer descontoFranquia;

    @JsonProperty
    @OneToMany(mappedBy = "oficina", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Oficina> oficina;

    @JsonProperty
    private Integer aprovado;

    @JsonProperty
    private Integer removido;
}
