package fr.lernejo.navy_battle;

import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

public class Player {
    private final String id = UUID.randomUUID().toString();
    private final ArrayList<String> ennemyId = new ArrayList<String>(1);
    private final int[][]map;
    private final int [][]ennemyMap = new int [10][10];
    public Player() {
        Random random = new Random();
        this.map = new GeneratingBoats(random.nextInt(3)).getBoats();
        for (int i = 0; i < 10; i++)
        {
            for (int y = 0;y < 10;y++)
                this.ennemyMap[i][y]=0;
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
        return "sunk";
    }
    public boolean isEmpty() {
        for (int i =0; i < 10;i++) {
            for (int y = 0; y<10;y++)
                if (this.map[i][y] != 0) {
                    return false;
                }
        }
        return true;
    }
    public Object[] chooseCase() {
        Random r = new Random();
        int ligne, colonne;
        do {
            ligne = r.nextInt(10);
            colonne = r.nextInt(10);
        } while(this.ennemyMap[ligne][colonne] != 0);
        this.ennemyMap[ligne][colonne] = 1;
        Object[] o = new Object[3];
        o[0]= ligne;
        o[1] = colonne;
        o[2] = traduction(ligne)+String.valueOf(colonne+1);
        return o;
    }
    private String traduction(int ligne) {
        switch(ligne) {
            case 0: return "A";
            case 1: return "B";
            case 2: return "C";
            case 3: return "D";
            case 4: return "E";
            case 5: return "F";
            case 6: return "G";
            case 7: return "H";
            case 8: return "I";
            case 9: return "J";
            default : return "None";
        }
    }
    public String getId() { return this.id; }
    public String getEnnemyId(){return this.ennemyId.get(0); }
    public void addEnnemy(String id) { this.ennemyId.add(id); }
    public void setTir(int l,int c){this.ennemyMap[l][c] = 2;}
}
