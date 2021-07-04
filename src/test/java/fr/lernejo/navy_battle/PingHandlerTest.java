package fr.lernejo.navy_battle;

import com.google.gson.JsonParser;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

class PingHandlerTest {

    @Test
    void Test_handle_ping_good_request() {
        Server s1 = new Server(9876);
        HttpClient newclient = HttpClient.newHttpClient();
        HttpRequest requetePing = HttpRequest.newBuilder().uri(URI.create("http://localhost:9876/ping")).header("Accept","application/json").build();
        try {
            HttpResponse<String> response = newclient.send(requetePing, HttpResponse.BodyHandlers.ofString());
            Assertions.assertThat(response.statusCode()).isEqualTo(200);
        } catch (Exception e) { e.printStackTrace();}
    }
}
