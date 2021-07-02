package fr.lernejo.navy_battle;
import com.sun.net.httpserver.HttpServer;

public class Launcher {
    public static void main (String[]args)
    {
        new Server(9876);
        new Server(8798,"http://localhost:9876");
    }
}
