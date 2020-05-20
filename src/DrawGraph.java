import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Random;

public class DrawGraph extends JFrame {
    private ArrayList<Node> nodes = new ArrayList<Node>();

    public DrawGraph(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void CreateNodes (int num){
        Random rand = new Random();
        final float max_y = 1000.00f;
        final float max_x = 1000.00f;
        for (int i = 0; i < num; i++) {
            float x=rand.nextFloat()*max_x;
            float y=rand.nextFloat()*max_y;
            while(true){
                if(uniqueCoordinates(x,y,nodes)){
                    break;
                }else{
                    x=rand.nextFloat()*max_x;
                    y=rand.nextFloat()*max_y;
                    uniqueCoordinates(x,y,nodes);
                }
            }
            nodes.add(new Node(x, y));
            this.repaint();
        }
    }

    public boolean uniqueCoordinates (float x, float y, ArrayList<Node> nodes){
        for(Node n:nodes){
            if(x==n.x){
                return false;
            }
            if(y==n.y){
                return false;
            }
        }
        return true;
    }

    public void paint(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        FontMetrics f = g2d.getFontMetrics();
        g2d.setColor(Color.BLACK);
        for (Node n: nodes){
            g2d.setColor(Color.BLACK);
            Ellipse2D.Float circle = new Ellipse2D.Float(n.x,n.y,10.0f,10.0f);
            g2d.fill(circle);
            System.out.println(n.x + ", " + n.y);

        }
    }
}
