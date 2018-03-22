package com.infoshareacademy.zieloni.servlets;

import com.infoshareacademy.zieloni.servlets.model.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Stateless
public class UsersDaoImpl implements UsersDao {

    private static final String EDIT_USER = "edit";

    @PersistenceContext(unitName = "pUnit")
    private EntityManager entityManager;

    public boolean addUser(User user) {
        entityManager.persist(user);
        return true;
    }

    public boolean editUser(User user) {
        entityManager.createNamedQuery("updateUser")
                .setParameter("id", user.getId())
                .setParameter("name", user.getName())
                .setParameter("surname", user.getSurname())
                .executeUpdate();

        return true;
    }

    public boolean removeUser(User user) {
        User removedUser = entityManager.find(User.class, user.getId());
        entityManager.remove(removedUser);
        return true;
    }

    public Optional<User> getUserById(int id) {
        Optional<User> user = Optional.ofNullable(entityManager.find(User.class, id));
        return user;
    }

    public Optional<User> getUserByLogin(String login) {
        Optional<User> user = (Optional<User>) entityManager.createNamedQuery("getUserByLogin")
                .setParameter("login", login)
                .getSingleResult();
        return user;
    }

    public List<User> getUsersList() {
        return entityManager.createNamedQuery("getAll").getResultList();
    }
}