package fr.lernejo.navy_battle;

public class GeneratingBoats {
    private final int numberPreset;
    private final int[][] array = new int[10][10];
    public GeneratingBoats(int numberPreset)
    {
        this.numberPreset=numberPreset;
        for (int i = 0; i< 10;i++)
        {
            for (int y = 0; y <10;y++)
            {
                this.array[i][y] = 0;
            }
        }
    }
    public int[][] getBoats()
    {
        switch (this.numberPreset)
        {
            case 0: return firstPreset();
            case 1:return secondPreset();
            case 2: return thirdPreset();
            default : return this.array;
        }
    }
    public int [][]firstPreset()
    {
        generateBoats(2,"horizontal",0,0,2);
        generateBoats(31,"vertical",0,9,3);
        generateBoats(32,"horizontal",5,4,3);
        generateBoats(4,"vertical",1,3,4);
        generateBoats(5,"horizontal",9,0,5);
        return this.array;
    }
    public int [][]secondPreset()
    {
        generateBoats(2,"horizontal",1,0,2);
        generateBoats(31,"vertical",2,9,3);
        generateBoats(32,"horizontal",3,2,3);
        generateBoats(4,"vertical",4,3,4);
        generateBoats(5,"horizontal",5,4,5);
        return this.array;
    }
    public int [][]thirdPreset()
    {
        generateBoats(2,"horizontal",9,1,2);
        generateBoats(31,"vertical",6,7,3);
        generateBoats(32,"horizontal",4,3,3);
        generateBoats(4,"vertical",1,5,4);
        generateBoats(5,"horizontal",0,3,5);
        return this.array;
    }
    private void generateBoats(int id,String sens,int positionX,int positionY,int taille)
    {
        if (sens.equals("horizontal"))
        {
            for (int i = 0; i< taille; i++)
                this.array[positionX][positionY+i]=id;
        }
        else {
            for (int i = 0; i< taille; i++)
                this.array[positionX+i][positionY]=id;
        }
    }
}
