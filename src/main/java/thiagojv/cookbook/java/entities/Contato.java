package thiagojv.cookbook.java.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Contato {

    @Id
    @GeneratedValue
    private int id;

    @JsonProperty
    private String nome;

    @JsonProperty
    private String telefone;

    @JsonProperty
    private String email;

    @JsonProperty
    private Integer principal;

    @JsonProperty
    private Integer removido;

    @ManyToOne(fetch = FetchType.EAGER,  cascade=CascadeType.ALL)
    @JoinColumn(name="ID_OFICINA")
    private Oficina oficina;
}
