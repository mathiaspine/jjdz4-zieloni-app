package com.infoshareacademy.zieloni.services;

import com.infoshareacademy.zieloni.model.Users;

import javax.ejb.Local;
import java.util.List;


@Local
public interface IUsersDao {

    boolean addUser(Users user);

    boolean editUser(Users user);

    boolean removeUser(Users user);

    Users getUserById(int id);

    Users getUserByLogin(String login);

    List<Users> getUsersList();

    List<String> getUsersNames();
}