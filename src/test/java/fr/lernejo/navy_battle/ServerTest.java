package fr.lernejo.navy_battle;

import org.junit.Ignore;
import org.junit.Rule;
import org.junit.jupiter.api.Test;

import org.assertj.core.api.Assertions;


class ServerTest {
    @Test
    void test_start_game() {
       Server serv1 = new Server(89);
       serv1.start_game("212sdqfsqfstrezt");
    }

    @Test
    void send_fire() {
    }

    @Test
    void test_endGame() throws Exception {
        
    }


    @Test
    void addURL() {
    }

    @Test
    void addPlayerEnnemy() {
    }

    @Test
    void test_getPort() {
        Assertions.assertThat(new Server(9860).getPort()).isEqualTo(9860);
    }

}
