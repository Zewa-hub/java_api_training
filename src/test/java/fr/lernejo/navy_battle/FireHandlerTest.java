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
        Server s1 = new Server(4678);
        HttpClient newclient = HttpClient.newHttpClient();
        HttpRequest requetePost = HttpRequest.newBuilder().uri(URI.create("http://localhost:4678/api/game/fire?cell=B2")).setHeader("Accept", "application/json").setHeader("Content-Type", "application/json").POST(HttpRequest.BodyPublishers.ofString("{ url\":\"http://localhost:9876\" , \"message\":\"Hello\"}")).build();
        try {
            HttpResponse<String> response = newclient.send(requetePost, HttpResponse.BodyHandlers.ofString());
            Assertions.assertThat(response.statusCode()).isEqualTo(404);
        } catch (Exception e) { e.printStackTrace();}
    }
    @Test
    void test_handle_fire_bad_query()
    {
        Server s1 = new Server(1234);
        HttpClient newclient = HttpClient.newHttpClient();
        HttpRequest requetePost = HttpRequest.newBuilder().uri(URI.create("http://localhost:1234/api/game/fire?cell=B6121541032")).setHeader("Accept", "application/json").setHeader("Content-Type", "application/json").GET().build();
        try {
            HttpResponse<String> response = newclient.send(requetePost, HttpResponse.BodyHandlers.ofString());
            Assertions.assertThat(response.statusCode()).isEqualTo(400);
        } catch (Exception e) { e.printStackTrace();}
    }
    @Test
    void test_handle_fire_good_first_part() {
        try {
            Server s1 = new Server(0001);
            HttpClient newclient = HttpClient.newHttpClient();
            HttpResponse<String> response = null;
            HttpRequest requetePost = HttpRequest.newBuilder().uri(URI.create("http://localhost:0001/api/game/fire?cell=A10")).setHeader("Accept", "application/json").setHeader("Content-Type", "application/json").build();
            newclient.send(requetePost, HttpResponse.BodyHandlers.ofString());
            requetePost = HttpRequest.newBuilder().uri(URI.create("http://localhost:0001/api/game/fire?cell=B2")).setHeader("Accept", "application/json").setHeader("Content-Type", "application/json").build();
            newclient.send(requetePost, HttpResponse.BodyHandlers.ofString());
            requetePost = HttpRequest.newBuilder().uri(URI.create("http://localhost:0001/api/game/fire?cell=C2")).setHeader("Accept", "application/json").setHeader("Content-Type", "application/json").build();
            newclient.send(requetePost, HttpResponse.BodyHandlers.ofString());
            requetePost = HttpRequest.newBuilder().uri(URI.create("http://localhost:0001/api/game/fire?cell=D2")).setHeader("Accept", "application/json").setHeader("Content-Type", "application/json").build();
            newclient.send(requetePost, HttpResponse.BodyHandlers.ofString());
            Assertions.assertThat(response.statusCode()).isEqualTo(202);
        } catch (Exception e) { e.printStackTrace();}
    }
    @Test
    void test_handle_fire_good_second_part() {
        try{
            HttpClient newclient = HttpClient.newHttpClient();
            HttpResponse<String> response = null;
            HttpRequest requetePost = HttpRequest.newBuilder().uri(URI.create("http://localhost:0001/api/game/fire?cell=E2")).setHeader("Accept", "application/json").setHeader("Content-Type", "application/json").build();
            newclient.send(requetePost, HttpResponse.BodyHandlers.ofString());
            requetePost = HttpRequest.newBuilder().uri(URI.create("http://localhost:0001/api/game/fire?cell=F2")).setHeader("Accept", "application/json").setHeader("Content-Type", "application/json").build();
            newclient.send(requetePost, HttpResponse.BodyHandlers.ofString());
            requetePost = HttpRequest.newBuilder().uri(URI.create("http://localhost:0001/api/game/fire?cell=G2")).setHeader("Accept", "application/json").setHeader("Content-Type", "application/json").build();
            newclient.send(requetePost, HttpResponse.BodyHandlers.ofString());
            requetePost = HttpRequest.newBuilder().uri(URI.create("http://localhost:0001/api/game/fire?cell=H2")).setHeader("Accept", "application/json").setHeader("Content-Type", "application/json").build();
            newclient.send(requetePost, HttpResponse.BodyHandlers.ofString());
            Assertions.assertThat(response.statusCode()).isEqualTo(202);
        }catch(Exception e){e.printStackTrace();}
    }
    @Test
    void test_handle_fire_good_third_part() {
        try{
            HttpClient newclient = HttpClient.newHttpClient();
            HttpResponse<String> response = null;
            HttpRequest requetePost = HttpRequest.newBuilder().uri(URI.create("http://localhost:0001/api/game/fire?cell=I2")).setHeader("Accept", "application/json").setHeader("Content-Type", "application/json").build();
            newclient.send(requetePost, HttpResponse.BodyHandlers.ofString());
            requetePost = HttpRequest.newBuilder().uri(URI.create("http://localhost:0001/api/game/fire?cell=J2")).setHeader("Accept", "application/json").setHeader("Content-Type", "application/json").build();
            newclient.send(requetePost, HttpResponse.BodyHandlers.ofString());
            Assertions.assertThat(response.statusCode()).isEqualTo(202);
        }catch(Exception e){e.printStackTrace();}
    }
}

