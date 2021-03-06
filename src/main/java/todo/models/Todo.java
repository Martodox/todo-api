package todo.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="todos")
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="owner", referencedColumnName="id")
    @JsonBackReference
    private User user;

    @NotNull
    private String text;

    @NotNull
    @ColumnDefault("false")
    private boolean resolved;

    public Todo() { }

    public Todo(User user, String text) {
        this.user = user;
        this.text = text;
        this.resolved = false;
    }

    public long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isResolved() {
        return resolved;
    }

    public void setResolved(boolean resolved) {
        this.resolved = resolved;
    }
}
