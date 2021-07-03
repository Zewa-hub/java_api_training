package fr.lernejo.navy_battle;

import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import org.assertj.core.api.Assertions;

import static org.junit.jupiter.api.Assertions.*;


class LauncherTest {
    @Test
    void test_main() {
        String tab1[] = new String[1];
        String tab2[] = new String[2];
        String tab3[] = new String[3];
        tab1[0] = "9876";
        tab2[0] = "8765";
        tab2[1] = "http://localhost:9876";
        new Launcher().main(tab1);
        new Launcher().main(tab2);
        Exception e = assertThrows(IllegalArgumentException.class, () -> new Launcher().main(tab3));
        assertEquals("Error with arguments",e.getMessage());
    }
}
