import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class DrawGraph extends JFrame {
    private ArrayList<Node> nodes = new ArrayList<Node>();
    private ArrayList<Edge> edges = new ArrayList<Edge>();
    Random rand = new Random();
//    int time;
//    long startTime = System.currentTimeMillis();
//    long elapsedTime = 0L;

    public DrawGraph(int time){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        this.time = time;
    }

    public void CreateNodes (int num){
        final float max_y = 1030f;
        final float max_x = 1000f;
        final float min_x = 0f;
        final float min_y = 30f;
        for (int i = 0; i < num; i++) {
            float x=rand.nextFloat()*(max_x-min_x)+min_x;
            float y=rand.nextFloat()*(max_y-min_y)+min_y;
            while(true){
                if(uniqueCoordinates(x,y,nodes)){
                    break;
                }else{
                    x=rand.nextFloat()*(max_x-min_x)+min_x;
                    y=rand.nextFloat()*(max_y-min_y)+min_y;
                }
            }
            nodes.add(new Node(x, y));
            this.repaint();
        }
    }

    public synchronized void AddEdges(Color color){
//        while(elapsedTime< time*1000){
        int attempt = 0;
            int i = rand.nextInt(nodes.size());
            if(!nodes.get(i).used){
                int j = rand.nextInt(nodes.size());
                if(!nodes.get(j).used && i!=j){
                    edges.add(new Edge(i, j, color));
                    nodes.get(i).used = true;
                    nodes.get(j).used = true;
                }
            }
//            elapsedTime = (new Date()).getTime() - startTime;
//        }
//        System.out.println("Times up!");

//        this.repaint();
    }

    public boolean uniqueCoordinates (float x, float y, ArrayList<Node> nodes){
        for(Node n:nodes){
            if(x==n.x && y==n.y){
                return false;
            }
        }
        return true;
    }

    public void paint(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setStroke(new BasicStroke(2));
        super.paint(g2d);
        for (Node n: nodes){
            Ellipse2D.Float circle = new Ellipse2D.Float((n.x-5),(n.y-5),10f,10f);
            g2d.fill(circle);
            System.out.println(n.x + ", " + n.y);
        }

        for (Edge e: edges){
            Shape line = new Line2D.Float(nodes.get(e.i).x, nodes.get(e.i).y, nodes.get(e.j).x, nodes.get(e.j).y);
//            g2d.setColor(new Color(e.r, e.g, e.b).brighter());
            g2d.setColor(e.color.brighter());
            g2d.draw(line);
//            System.out.println("line " + e.i + " and " + e.j);
        }
    }
}
