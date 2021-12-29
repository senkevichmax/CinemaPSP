package by.bsuir.cinema.controller.logic;


import by.bsuir.cinema.controller.database.dao.DaoManager;
import by.bsuir.cinema.controller.database.dao.FilmDao;
import by.bsuir.cinema.controller.database.dao.SessionDao;
import by.bsuir.cinema.controller.exception.ProjectException;

public class SessionLogic {
    public static String findAllSessions() throws ProjectException {
        DaoManager daoManager = new DaoManager();
        SessionDao sessionDao = new SessionDao();
        try{
            daoManager.startDAO(sessionDao);
            return sessionDao.findAllSessions();
        } finally {
            daoManager.endDAO();
        }
    }
    public static int[] find_price() throws ProjectException {
        DaoManager daoManager = new DaoManager();
        SessionDao sessionDao = new SessionDao();
        try{
            daoManager.startDAO(sessionDao);
            return sessionDao.find_price();
        } finally {
            daoManager.endDAO();
        }
    }

    public static boolean addNewSession(String filmName, String dateAndTime, String cost) throws ProjectException {
        DaoManager daoManager = new DaoManager();
        FilmDao filmDao = new FilmDao();
        SessionDao sessionDao = new SessionDao();
        boolean flag = false;
        try{
            daoManager.startDAO(filmDao, sessionDao);
            int filmId = filmDao.findFilmIdByName(filmName);
            if (filmId != 0){
                flag = sessionDao.insertSession(filmId, dateAndTime, cost);
            }
            daoManager.commit();
        } catch (ProjectException e) {
            daoManager.rollback();
            throw e;
        } finally {
            daoManager.endDAO();
        }
        return flag;
    }

    public static boolean deleteFromSession(int sessionId) throws ProjectException {
        DaoManager daoManager = new DaoManager();
        SessionDao sessionDao = new SessionDao();
        try{
            daoManager.startDAO(sessionDao);
            boolean flag = sessionDao.deleteById(sessionId);
            daoManager.commit();
            return flag;
        } catch (ProjectException e) {
            daoManager.rollback();
            throw e;
        } finally {
            daoManager.endDAO();
        }
    }
}
