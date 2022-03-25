package thiagojv.cookbook.java.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Pessoa {
  @Id
  @GeneratedValue
  @JsonProperty
  private Integer id;

  @JsonProperty
  private String nome;
}
