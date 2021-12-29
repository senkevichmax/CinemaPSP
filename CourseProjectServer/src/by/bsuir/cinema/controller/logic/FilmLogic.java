package by.bsuir.cinema.controller.logic;


import by.bsuir.cinema.controller.database.dao.DaoManager;
import by.bsuir.cinema.controller.database.dao.FilmDao;
import by.bsuir.cinema.controller.exception.ProjectException;

public class FilmLogic {
    public static boolean addNewFilm (String name, String filmGenre, String producers, String mainRoles)
            throws ProjectException {
        DaoManager daoManager = new DaoManager();
        FilmDao filmDao = new FilmDao();
        boolean flag;
        try{
            daoManager.startDAO(filmDao);
            flag = filmDao.insertFilm(name, filmGenre, producers, mainRoles);
            daoManager.commit();
        } catch (ProjectException e) {
            daoManager.rollback();
            throw e;
        } finally {
            daoManager.endDAO();
        }
        return flag;
    }

    public static String findAllFilms() throws ProjectException {
        DaoManager daoManager = new DaoManager();
        FilmDao filmDao = new FilmDao();
        try{
            daoManager.startDAO(filmDao);
            return filmDao.findAll();
        } finally {
            daoManager.endDAO();
        }
    }
    public static int Find_comedy() throws ProjectException {
        DaoManager daoManager = new DaoManager();
        FilmDao filmDao = new FilmDao();
        try{
            daoManager.startDAO(filmDao);
            return filmDao.find_comedy();
        } finally {
            daoManager.endDAO();
        }
    }
    public static int Find_fantasy() throws ProjectException {
        DaoManager daoManager = new DaoManager();
        FilmDao filmDao = new FilmDao();
        try{
            daoManager.startDAO(filmDao);
            return filmDao.find_fantasy();
        } finally {
            daoManager.endDAO();
        }
    }
    public static int Find_thiller() throws ProjectException {
        DaoManager daoManager = new DaoManager();
        FilmDao filmDao = new FilmDao();
        try{
            daoManager.startDAO(filmDao);
            return filmDao.find_thriller();
        } finally {
            daoManager.endDAO();
        }
    }
    public static int Find_action() throws ProjectException {
        DaoManager daoManager = new DaoManager();
        FilmDao filmDao = new FilmDao();
        try{
            daoManager.startDAO(filmDao);
            return filmDao.find_action();
        } finally {
            daoManager.endDAO();
        }
    }
    public static boolean deleteFilmById(int filmId) throws ProjectException {
        DaoManager daoManager = new DaoManager();
        FilmDao filmDao = new FilmDao();
        boolean flag;
        try{
            daoManager.startDAO(filmDao);
            flag = filmDao.deleteFilmById(filmId);
            daoManager.commit();
        } catch (ProjectException e) {
            daoManager.rollback();
            throw e;
        } finally {
            daoManager.endDAO();
        }
        return flag;
    }
    public static boolean Edit_genre(String genre,int id) throws ProjectException {
        DaoManager daoManager = new DaoManager();
        FilmDao filmDao = new FilmDao();
        boolean flag;
        try{
            daoManager.startDAO(filmDao);
            flag = filmDao.edit_film(genre, id);
            daoManager.commit();
        } catch (ProjectException e) {
            daoManager.rollback();
            throw e;
        } finally {
            daoManager.endDAO();
        }
        return flag;
    }

    public static boolean Edit_name(String name,int id) throws ProjectException {
        DaoManager daoManager = new DaoManager();
        FilmDao filmDao = new FilmDao();
        boolean flag;
        try{
            daoManager.startDAO(filmDao);
            flag = filmDao.edit_name(name, id);
            daoManager.commit();
        } catch (ProjectException e) {
            daoManager.rollback();
            throw e;
        } finally {
            daoManager.endDAO();
        }
        return flag;
    }
}
