package by.bsuir.cinema.controller.service;

import by.bsuir.cinema.controller.entity.user.Client;
import by.bsuir.cinema.controller.exception.ProjectException;
import by.bsuir.cinema.controller.logic.BasketLogic;
import by.bsuir.cinema.controller.logic.FilmLogic;
import by.bsuir.cinema.controller.logic.SessionLogic;
import by.bsuir.cinema.controller.logic.UserLogic;
import by.bsuir.cinema.controller.util.Encryption;

import javax.swing.*;
import java.io.IOException;
import java.math.BigDecimal;

public class Service {

    public static void signUpUser(String login, String password) throws ProjectException {

        UserLogic.registerNewClient(login,
                Encryption.encryptPassword(password));
           /* if (!UserLogic.isLoginExists(login)) {
                UserLogic.registerNewClient(login,
                        Encryption.encryptPassword(password));
            }*/

    }
    public static void addNewFilm(String filmName, String genreName, String producers, String mainRoles) throws ProjectException {
        FilmLogic.addNewFilm(filmName, genreName, producers, mainRoles);
    }

    public static void addNewSession(String filmName,String dateAndTime,String cost) throws ProjectException {
        SessionLogic.addNewSession(filmName, dateAndTime, cost);
    }

    public static void addToBasket(String userId, String sessionId) throws ProjectException {
        int userId1 = Integer.parseInt(userId);
        int sessionId1 = Integer.parseInt(sessionId);
        BasketLogic.addToBasket(userId1, sessionId1);
    }
    public static void deleteFromSession(String sessionId) throws ProjectException {
        int integerSessionId = Integer.parseInt(sessionId);
        SessionLogic.deleteFromSession(integerSessionId);
    }

    public static void logIn(String login, String password) throws ProjectException {

        UserLogic.findUser(login, password);
    }

    public static void deleteFilmById(String filmId) throws ProjectException {
       int integerFilmId = Integer.parseInt(filmId);
        FilmLogic.deleteFilmById(integerFilmId);
    }

    public static void EditGenre(String genre,String filmId) throws ProjectException {
        int integerFilmId = Integer.parseInt(filmId);
        FilmLogic.Edit_genre(genre, integerFilmId);
    }

    public static void editName(String name,String filmId) throws ProjectException {
        int integerFilmId = Integer.parseInt(filmId);
        FilmLogic.Edit_name(name, integerFilmId);
    }

    public static void deleteFromBasket(String userId, String sessionId) throws ProjectException {
        int integerUserId = Integer.parseInt(userId);
        int integerSessionId = Integer.parseInt(sessionId);
        BasketLogic.deleteFromBasket(integerUserId, integerSessionId);
    }
}
