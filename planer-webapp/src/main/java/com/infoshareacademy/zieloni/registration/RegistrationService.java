package com.infoshareacademy.zieloni.registration;

import javax.ejb.Local;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Local
public interface RegistrationService {

    void init(HttpServletRequest req);

    void addNewUser(HttpServletRequest req, String login);

}
