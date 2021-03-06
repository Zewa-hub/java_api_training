package fr.lernejo.navy_battle;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.*;

class StartHandlerTest {

    @Test
    void test_handle_start_bad_json() {
        Server s1 = new Server(0002);
        HttpClient newclient = HttpClient.newHttpClient();
        HttpRequest requetePost = HttpRequest.newBuilder().uri(URI.create("http://localhost:9876/api/game/start")).setHeader("Accept", "application/json").setHeader("Content-Type", "application/json").POST(HttpRequest.BodyPublishers.ofString("{ }")).build();
        try {
            HttpResponse<String> response = newclient.send(requetePost, HttpResponse.BodyHandlers.ofString());
            Assertions.assertThat(response.statusCode()).isEqualTo(400);
        } catch (Exception e) { e.printStackTrace();}
    }
    @Test
    void test_handle_start_bad_methods() {
        Server s1 = new Server(9876);
        HttpClient newclient = HttpClient.newHttpClient();
        HttpRequest requetePost = HttpRequest.newBuilder().uri(URI.create("http://localhost:9876/api/game/start")).setHeader("Accept", "application/json").setHeader("Content-Type", "application/json").GET().build();
        try {
            HttpResponse<String> response = newclient.send(requetePost, HttpResponse.BodyHandlers.ofString());
            Assertions.assertThat(response.statusCode()).isEqualTo(404);
        } catch (Exception e) { e.printStackTrace();}
    }
}
