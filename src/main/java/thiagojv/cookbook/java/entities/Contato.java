package thiagojv.cookbook.java.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor
public class Contato implements Serializable {

    private static final long serialVersionUID = 1L;

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
