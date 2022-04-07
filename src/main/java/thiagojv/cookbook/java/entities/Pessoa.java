package thiagojv.cookbook.java.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.context.annotation.Bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Data
@Entity
public class Pessoa {

    @Id
    @JsonProperty
    @GeneratedValue
    private UUID id;

    @JsonProperty
    private String nome;
}
