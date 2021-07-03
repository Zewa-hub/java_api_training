package fr.lernejo.navy_battle;

import org.junit.jupiter.api.Test;

import org.assertj.core.api.Assertions;


class ServerTest {

    @Test
    void test_start_game() {
       // Server serv1 = new Server();
    }

    @Test
    void send_fire() {
    }

    @Test
    void endGame() {
    }

    @Test
    void addURL() {
    }

    @Test
    void addPlayerEnnemy() {
    }

    @Test
    void test_getPort() {
        Assertions.assertThat(new Server(9876).getPort()).isEqualTo(9876);
    }

    @Test
    void test_getId() {
        //Assertions.assertThat(new Server(9876).getId()).isEqualTo(9876);
    }

    @Test
    void getPlayer() {
    }
}
