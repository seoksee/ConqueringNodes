package GameBoard;

import java.util.ArrayList;
import java.util.Random;

public class Node {
    float x, y;
    boolean used = false;

    public Node(float myX, float myY){
        this.x = myX;
        this.y = myY;
    }

    public void setUsed (boolean set){
        this.used = set;
    }

    public boolean getUsed (){
        return used;
    }
}
