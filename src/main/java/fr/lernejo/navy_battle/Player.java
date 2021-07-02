package fr.lernejo.navy_battle;

import java.util.ArrayList;
import java.util.UUID;

public class Player {
    private final String id = UUID.randomUUID().toString();
    private final ArrayList<String> ennemyId = new ArrayList<String>(1);
    private final int[][]map;

    public Player() {
        map = new int[10][10];
        for (int i = 0; i < 10; i++ )
        {
            for (int y = 0; y < 10; y++)
            {
                this.map[i][y]=0;
            }
        }
    }
    public Object[] strike(int[] tab) {
        Object[] result = new Object[2];
        result[0] = "miss";
        result[1] = true;
        if (this.map[tab[0]][tab[1]] != 0) {
            int boat_id = this.map[tab[0]][tab[1]];
            this.map[tab[0]][tab[1]] = 0;
            if (this.isEmpty()) {
                result[0] = "sunk";
                result[1]= false;
            }
            result[0] = isSunked(boat_id);
            }
        return result;
    }
    public String  isSunked(int boat_id) {
        for (int i = 0; i < 10; i++) {
            for (int y = 0; y < 10; y++) {
                if (this.map[i][y] == boat_id) {
                    return "hit";
                }
            }
        }
        return "miss";
    }

    public boolean isEmpty()
    {
        for (int i =0; i < 10;i++) {
            for (int y = 0; y<10;y++)
                if (this.map[i][y] != 0) {
                    return false;
                }
        }
        return true;
    }

    public String getId() {
        return this.id;
    }
    public void addEnnemy(String id) {
        this.ennemyId.add(id);
    }
}
