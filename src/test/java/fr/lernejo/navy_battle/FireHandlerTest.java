package fr.lernejo.navy_battle;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.*;

class FireHandlerTest {

    @Test
    void test_handle_fire_bad_methods() {
        Server s1 = new Server(9876);
        HttpClient newclient = HttpClient.newHttpClient();
        HttpRequest requetePost = HttpRequest.newBuilder().uri(URI.create("http://localhost:9876/api/game/fire?cell=B2")).setHeader("Accept", "application/json").setHeader("Content-Type", "application/json").POST(HttpRequest.BodyPublishers.ofString("{ url\":\"http://localhost:9876\" , \"message\":\"Hello\"}")).build();
        try {
            HttpResponse<String> response = newclient.send(requetePost, HttpResponse.BodyHandlers.ofString());
            Assertions.assertThat(response.statusCode()).isEqualTo(404);
        } catch (Exception e) { e.printStackTrace();}
    }
    @Test
    void test_handle_fire_bad_query()
    {
        Server s1 = new Server(9876);
        HttpClient newclient = HttpClient.newHttpClient();
        HttpRequest requetePost = HttpRequest.newBuilder().uri(URI.create("http://localhost:9876/api/game/fire?cell=B6121541032")).setHeader("Accept", "application/json").setHeader("Content-Type", "application/json").GET().build();
        try {
            HttpResponse<String> response = newclient.send(requetePost, HttpResponse.BodyHandlers.ofString());
            Assertions.assertThat(response.statusCode()).isEqualTo(400);
        } catch (Exception e) { e.printStackTrace();}
    }
}

