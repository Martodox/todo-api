package todo.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
  
  @NotNull
  private String token;
  
  @NotNull
  @Size(min = 2, max = 80)
  private String name;

  public User() { }

  public User(String name) {
    this.name = name;
    this.token = "randomToken";
  }

  public long getId() {
    return id;
  }

  public void setId(long value) {
    this.id = value;
  }
  
  public String getName() {
    return name;
  }

  public void setName(String value) {
    this.name = value;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

}
