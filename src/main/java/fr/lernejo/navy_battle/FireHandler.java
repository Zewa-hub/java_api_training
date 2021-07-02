package fr.lernejo.navy_battle;

import com.google.gson.JsonObject;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;


public class FireHandler implements HttpHandler {
    private final Server server;
    public FireHandler(Server server) {
        this.server = server;
    }
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if (exchange.getRequestMethod().equals("GET")) {
            String query = exchange.getRequestURI().getQuery().split("=")[1];
            if (checkQuery(query.toLowerCase())) {
                Object result_object[] = this.server.getPlayer().strike(convertQuery(query));
                JsonObject js = new JsonObject();
                js.addProperty("consequence",(String)result_object[0]);
                js.addProperty("shipLeft",(boolean)result_object[1]);
                send_message(exchange,js.toString(),202);
            }
            else
                send_message(exchange, "Bad Request", 400);
        }
        send_message(exchange,"Not Found",404);
    }
    private void send_message(HttpExchange exchange,String message,int server_code) throws IOException {
        exchange.sendResponseHeaders(server_code, message.length());
        try (OutputStream os = exchange.getResponseBody()) { // (1)
            os.write(message.getBytes());
        }
    }
    private boolean checkQuery(String query) {
        if (query.length() == 2 | query.length() == 3) {
            char check[] = query.toCharArray();
            if (check[0] >= 'a' && check[0] <= 'j') {
                if (query.length()==2)
                    return check[1] >= '1' && check[1] <= '9';
                else
                    return check[1] == '1' && check[2] == '0';
            }
        }
        return false;
    }
    private int[] convertQuery(String ch) {
        int tab[] = new int[2];
        char check[] = ch.toLowerCase().toCharArray();
        tab[0] = table_converter(check[0]);
        if (ch.length()==2)
            tab[1] = Integer.parseInt(String.valueOf(check[1])) - 1;
        else
            tab[1] = 9;
        return tab;
    }
    private int table_converter(char c) {
        switch(c) {
            case 'a': return 0;
            case 'b': return 1;
            case 'c': return 2;
            case 'd': return 3;
            case 'e': return 4;
            case 'f': return 5;
            case 'g': return 6;
            case 'h': return 7;
            case 'i': return 8;
            case 'j': return 9;
            default : return -1;
        }
    }
}