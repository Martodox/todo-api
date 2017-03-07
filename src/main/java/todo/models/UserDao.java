package todo.models;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class UserDao {
  
  @Autowired
  private SessionFactory _sessionFactory;
  
  private Session getSession() {
    return _sessionFactory.getCurrentSession();
  }


  public User getByToken(String token) {
    return (User) getSession().createQuery(
        "from User where token = :token")
        .setParameter("token", token)
        .uniqueResult();
  }

  public void create(User user) {
    getSession().save(user);
  }


}
