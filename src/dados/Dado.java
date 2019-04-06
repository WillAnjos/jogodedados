package dados;

import java.util.Random;

public class Dado {

    private static final int NUMBER_OF_SIDES = 6;

    Random r = new Random();

    int roll(){
         return r.nextInt(NUMBER_OF_SIDES) + 1;
    }
}
