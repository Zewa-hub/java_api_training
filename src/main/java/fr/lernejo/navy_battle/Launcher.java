package fr.lernejo.navy_battle;
import com.sun.net.httpserver.HttpServer;

public class Launcher {
    public static void main(String[] args) {
        /*
        if (args.length == 1)
        {
            Server server =  new Server(Integer.parseInt(args[0]));
        }
        else if (args.length == 2)
        {
            Server server = new Server(Integer.parseInt(args[0]));
            server.start_game(args[1]);
        }
        else throw new IllegalArgumentException("Please call Main method with a port number");
    }*/
        Server server = new Server(9876);
        Server server2 = new Server(8765);
        server2.start_game("http://localhost:9876");
    }
}
