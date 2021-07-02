package fr.lernejo.navy_battle;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.Executors;

public class Server {
    private final int port;
    private final int id = 0;
    public Server(int port)
    {
        this.port = port;
        try {
            HttpServer currentserver = HttpServer.create(new InetSocketAddress("localhost",this.port),0);
            currentserver.setExecutor(Executors.newSingleThreadExecutor());
            currentserver.createContext("/ping",new PingHandler());
            StartHandler game = new StartHandler(this.id,this.port);
            currentserver.createContext("/api/game/start",game);
            FireHandler
            currentserver.createContext("/api/game/fire",)
            currentserver.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Server(int port, String url)
    {
        this.port = port;
        HttpClient currentclient = HttpClient.newHttpClient();
        HttpRequest requetePost = HttpRequest.newBuilder()
            .uri(URI.create(url + "/api/game/start"))
            .setHeader("Accept", "application/json")
            .setHeader("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString("{\"id\":\"1\", \"url\":\"http://localhost:" + port + "\", \"message\":\"hello\"}"))
            .build();
        HttpResponse<String> response = null;
        try {
            response = currentclient.send(requetePost, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.statusCode());
            System.out.println(response.body());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
