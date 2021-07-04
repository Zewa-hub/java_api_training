package fr.lernejo.navy_battle;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
class PlayerTest {
    @Test
    void test_get_EnnemyId()
    {
        Player p = new Player();
        p.addEnnemy("toto");
        Assertions.assertThat(p.getEnnemyId()).isEqualTo("toto");
    }
    @Test
    void test_sunk()
    {
        int tab[] = new int[2];
        for (int i = 0;i < 10; i++)
        {
            for (int y = 0; y< 10;y++)
            {
                tab[0] = i;
                tab[1] = i;
                Player p = new Player();
                p.strike(tab);
            }
        }
    }
    @Test
    void test_set_tir()
    {
        new Player().setTir(0,0);
    }

}
