package thiagojv.cookbook.java.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
public class Endereco {

    @Id
    @GeneratedValue
    private UUID id;

    @JsonProperty
    private String endereco;

    @JsonProperty
    private String complemento;

    @JsonProperty
    private String bairro;

    @JsonProperty
    private String cidade;

    @JsonProperty
    private String cep;

    @JsonProperty
    private String estado;

    @JsonProperty
    private Integer principal;

    @JsonProperty
    private Integer removido;

    @ManyToOne(fetch = FetchType.EAGER,  cascade=CascadeType.ALL)
    @JoinColumn(name="ID_OFICINA")
    private Oficina oficina;
}
