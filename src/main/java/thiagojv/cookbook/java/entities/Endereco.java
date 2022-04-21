package thiagojv.cookbook.java.entities;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {

    @Id
    @GeneratedValue
    private Long id;

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

    @ManyToOne()
    private Oficina oficina;
}
