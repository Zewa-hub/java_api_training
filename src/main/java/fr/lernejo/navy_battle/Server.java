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

import static java.lang.System.exit;

public class Server {
    private final int port;
    private final Player player = new Player();
    private final ArrayList<String> url = new ArrayList<String>(1);
    public Server(int port) {
        this.port = port;
        try {
            HttpServer currentServer = HttpServer.create(new InetSocketAddress("localhost",this.port),0);
            currentServer.setExecutor(Executors.newSingleThreadExecutor());
            currentServer.createContext("/ping",new PingHandler());
            currentServer.createContext("/api/game/start",new StartHandler(this));
            currentServer.createContext("/api/game/fire",new FireHandler(this));
            currentServer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void start_game(String url) {
        HttpClient currentclient = HttpClient.newHttpClient();
        HttpRequest requetePost = HttpRequest.newBuilder().uri(URI.create(url + "/api/game/start")).setHeader("Accept", "application/json").setHeader("Content-Type", "application/json").POST(HttpRequest.BodyPublishers.ofString("{\"id\":\""+this.player.getId()+"\", \"url\":\"http://localhost:" + port + "\", \"message\":\"Hello\"}")).build();
        try {
            HttpResponse<String> response = currentclient.send(requetePost, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 202) {
                this.addURL(url);
                this.addPlayerEnnemy(JsonParser.parseString(response.body()).getAsJsonObject().get("id").toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void send_fire() {
        Object[] tir = this.player.chooseCase();
        HttpClient newclient = HttpClient.newHttpClient();
        HttpRequest requeteFire = HttpRequest.newBuilder().uri(URI.create(this.url.get(0).replace("\"","") + "/api/game/fire?cell="+tir[2])).header("Accept","application/json").build();
        try {
            HttpResponse<String> response = newclient.send(requeteFire, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 202) {
                if (JsonParser.parseString(response.body()).getAsJsonObject().get("consequence").toString().equals("hit") || JsonParser.parseString(response.body()).getAsJsonObject().get("consequence").toString().equals("sunk")) {
                    this.player.setTir((int)tir[0],(int)tir[1]);
                    if (JsonParser.parseString(response.body()).getAsJsonObject().get("shipLeft").toString().equals("false")) { endGame(true); }
                }
            }
        } catch (Exception e) { e.printStackTrace();}
    }
    public void endGame(boolean status) {
        if (status)
            System.out.println("I won against the player \""+this.player.getEnnemyId()+"\". Congratulation !");
        else
            System.out.println("I lost against the player \""+this.player.getEnnemyId()+"\". I tried my best :( ");
        exit(0);
    }
    public void addURL(String url) { this.url.add(url); }
    public void addPlayerEnnemy(String id) { this.player.addEnnemy(id); }
    public int getPort() { return this.port; }
    public String getId() { return this.player.getId(); }
    public Player getPlayer() { return this.player; }
}
