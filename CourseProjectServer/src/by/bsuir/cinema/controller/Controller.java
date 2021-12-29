package by.bsuir.cinema.controller;

import by.bsuir.cinema.controller.entity.user.Client;
import by.bsuir.cinema.controller.entity.user.User;
import by.bsuir.cinema.controller.exception.ProjectException;
import by.bsuir.cinema.controller.service.Service;

import java.io.*;
import java.math.BigDecimal;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.time.LocalDateTime;

public class Controller implements Runnable{
    private static ServerSocket server;
    private static Socket connection;
    private static InputStream input;
    private final Object lock = new Object();

    public static void main(String[] args) throws IOException {
        server = new ServerSocket(8888, 10);
        for (int i = 0; i < 10; i++){
            new Thread(new Controller()).start();
        }
    }

    @Override
    public void run() {
        try {
            FileWriter fileWriter = new FileWriter("out.txt", true);
            System.out.println("Ожидание клиента!");
            connection = server.accept();
            while (true) {
                System.out.println("Клиент подключился!");
                input = connection.getInputStream();
                DataInputStream dataInputStream = new DataInputStream(input);
                String string = dataInputStream.readUTF();

                String[] getData = string.split("&");
                switch(getData[0]){
                    case "signUp": {
                        Service.signUpUser(getData[1], getData[2] );
                        break;
                    }
                    case "addNewFilm": {
                        Service.addNewFilm(getData[1], getData[2], getData[3], getData[4] );
                        break;
                    }
                    case "addNewSession": {
                        Service.addNewSession(getData[1], getData[2], getData[3]);
                        break;
                    }
                    case "addToBasket": {
                        Service.addToBasket(getData[1], getData[2]);
                        break;
                    }
                    case "deleteFromSession": {
                        Service.deleteFromSession(getData[1]);
                        break;
                    }
                    case "logIn": {
                        Service.logIn(getData[1], getData[2] );
                        break;
                    }
                    case "deleteFromFilm": {
                        Service.deleteFilmById(getData[1]);
                        break;
                    }
                    case "EditGenre": {
                        Service.EditGenre(getData[1], getData[2] );
                        break;
                    }
                    case "EditName": {
                        Service.editName(getData[1], getData[2] );
                        break;
                    }
                    case"deleteFromBasket": {
                        Service.deleteFromBasket(getData[1], getData[2]);
                        break;
                    }

                }
                synchronized (lock) {
                    fileWriter.write(string + " " + LocalDateTime.now() + "\n");
                }
                fileWriter.flush();
            }
        } catch (UnknownHostException e) {
           // e.printStackTrace();
            System.out.println("Клиент отключился!");
        } catch (IOException e) {
           // e.printStackTrace();
            System.out.println("Клиент отключился!");
        } catch (ProjectException e) {
            e.printStackTrace();
        }
    }
}
