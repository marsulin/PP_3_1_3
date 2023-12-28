package ru.kata.spring.boot_security.demo.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.Role;

@Repository
public class RoleDaolmpl implements RoleDao {
  @PersistenceContext
  private EntityManager entityManager;

  public List<Role> getRoles() {
    return entityManager.createQuery("from Role", Role.class).getResultList();
  }
}
