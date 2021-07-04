package fr.lernejo.navy_battle;

import com.github.stefanbirkner.systemlambda.SystemLambda;
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

        int status = SystemLambda.catchSystemExit(() -> {
            Server s1 = new Server(986);
            Server s2  =new Server(985);
            s1.addPlayerEnnemy("toto");
            s2.addPlayerEnnemy("titi");
            s1.endGame(true);
            s2.endGame(false);
            //the code under test, which calls System.exit(0);
        });
        Assertions.assertThat(status).isEqualTo(0);
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
