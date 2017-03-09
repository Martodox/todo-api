package todo.models;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public class TodoDao {

    @Autowired
    private SessionFactory _sessionFactory;

    private Session getSession() {
        return _sessionFactory.getCurrentSession();
    }

    public Todo find(Long id) {
        return (Todo) getSession().createQuery(
                "from Todo where id = :id")
                .setParameter("id", id)
                .uniqueResult();
    }

    public Todo create(Todo todo) {
        getSession().save(todo);
        return todo;
    }

    public Todo update(Todo todo) {
        getSession().update(todo);
        return todo;
    }
}
