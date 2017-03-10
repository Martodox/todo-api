package todo.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import todo.utils.TokenGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

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

  @OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
  @JsonBackReference
  private List<Todo> todos = new ArrayList<>();

  public User() { }

  public User(String name) {
    this.name = name;
    this.token = TokenGenerator.generate(256);
  }

  public long getId() {
    return id;
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

  public List<Todo> getTodos() {
    return todos;
  }

  public List<Todo> addTodo(Todo todo) {
    todos.add(todo);
    return todos;
  }

}
