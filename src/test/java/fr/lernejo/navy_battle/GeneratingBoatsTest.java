package fr.lernejo.navy_battle;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;


class GeneratingBoatsTest {
    @Test
    void test_first_preset()
    {
        Assertions.assertThat(new GeneratingBoats(1).getBoats()).isEqualTo(new GeneratingBoats(1).getBoats());
    }
    @Test
    void test_second_preset()
    {
        Assertions.assertThat(new GeneratingBoats(2).getBoats()).isEqualTo(new GeneratingBoats(2).getBoats());
    }
    @Test
    void test_third_preset()
    {
        Assertions.assertThat(new GeneratingBoats(3).getBoats()).isEqualTo(new GeneratingBoats(3).getBoats());
    }
}
