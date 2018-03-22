package com.infoshareacademy.zieloni.raport;

import com.infoshareacademy.zieloni.raport.dto.UserActivity;
import com.infoshareacademy.zieloni.raport.exception.CountryNotFoundException;
import com.infoshareacademy.zieloni.registration.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response.Status;

import org.json.simple.JSONObject;

public class RestClient {

    //private final static String API_URL = "https://restcountries.eu/rest/v2/";
    private final Logger logger = LoggerFactory.getLogger(RestClient.class.getName());
    private final static String API_URL = "http://localhost:8081/planer-inwigilator/";

    private Response getResponse(String sb) {
        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target(sb);
        final Response response = webTarget.request().get();
        return response;
    }

    private List<UserActivity> checkStatusIsOK(Response response) {
        if (response.getStatus() == Status.OK.getStatusCode()) {
            List<UserActivity> restCountryResponse = response.readEntity(new GenericType<List<UserActivity>>() {
            });
            response.close();
            return restCountryResponse;
        } else {
            response.close();
            throw new CountryNotFoundException("Not found country for given parameter.");
        }
    }


    public List<UserActivity> getUserList() {

        final Response response = getResponse(API_URL + "users/");
        List<UserActivity> restResponse = checkStatusIsOK(response);
        return restResponse;
    }

    public void infoAboutUserActivity(User user, String activity) {

        String address = API_URL + "user";

        JSONObject obj = new JSONObject();
        obj.put("name", user.getName());
        obj.put("surname",user.getSurname());
        obj.put("login",user.getLogin());
        obj.put("activity",  activity);

        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target(address);


        final Response response = webTarget.request().post(Entity.entity(obj, MediaType.APPLICATION_JSON_TYPE));
    }
}