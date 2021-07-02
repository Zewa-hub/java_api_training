package fr.lernejo.navy_battle;


import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;

public class StartHandler implements HttpHandler {
    private final Server server;
    public StartHandler(Server srv)
    {
        this.server=srv;
    }
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if (exchange.getRequestMethod().equals("POST")){
            JSONObject response = null;
            try {
                response = (JSONObject) new JSONParser().parse(new InputStreamReader(exchange.getRequestBody(),"UTF-8"));
                JsonObject js = JsonParser.parseString(response.toJSONString()).getAsJsonObject();
                if (verify(js)) {
                    this.server.addURL(js.get("url").toString());
                    this.server.addPlayerEnnemy(js.get("id").toString());
                    send_message(exchange,valid_message(),202); }
                else { send_message(exchange, "Bad Request", 400); }
            } catch (ParseException e) { send_message(exchange, "Bad Request", 400); } }
        else { send_message(exchange, "Not found", 404); }
    }

    private String valid_message()
    {
        JsonObject result = new JsonObject();
        result.addProperty("id",this.server.getId());
        result.addProperty("url","http://localhost:"+this.server.getPort());
        result.addProperty("message","May the best code win");
        return result.toString();
    }
    private void send_message(HttpExchange exchange,String message,int server_code) throws IOException {
        exchange.sendResponseHeaders(server_code, message.length());
        try (OutputStream os = exchange.getResponseBody()) { // (1)
            os.write(message.getBytes());
        }
    }
    private boolean verify(JsonObject json)
    {
        return (json.has("id") && json.has("url") && json.has("message"));
    }
}
