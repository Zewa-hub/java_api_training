package fr.lernejo.navy_battle;
import com.google.gson.JsonParser;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.concurrent.Executors;

public class Server {
    private final int port;
    private final Player player = new Player();
    private final ArrayList<String> url = new ArrayList<String>(1);
    public Server(int port) {
        this.port = port;
        try {
            HttpServer currentserver = HttpServer.create(new InetSocketAddress("localhost",this.port),0);
            currentserver.setExecutor(Executors.newSingleThreadExecutor());
            currentserver.createContext("/ping",new PingHandler());
            StartHandler game = new StartHandler(this);
            currentserver.createContext("/api/game/start",game);
            currentserver.createContext("/api/game/fire",new FireHandler(this));
            currentserver.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void start_game(String url) {
        HttpClient currentclient = HttpClient.newHttpClient();
        HttpRequest requetePost = HttpRequest.newBuilder().uri(URI.create(url + "/api/game/start")).setHeader("Accept", "application/json").setHeader("Content-Type", "application/json").POST(HttpRequest.BodyPublishers.ofString("{\"id\":\"1\", \"url\":\"http://localhost:" + port + "\", \"message\":\"hello\"}")).build();
        HttpResponse<String> response = null;
        try {
            response = currentclient.send(requetePost, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 202)
            {
                this.addURL(url);
                this.addPlayerEnnemy(JsonParser.parseString(response.body()).getAsJsonObject().get("id").toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void addURL(String url) {
        this.url.add(url);
    }
    public void addPlayerEnnemy(String id) {
        this.player.addEnnemy(id);
    }
    public int getPort() {
        return this.port;
    }
    public String getId() {
        return this.player.getId();
    }
    public Player getPlayer() {
        return this.player;
    }
}
