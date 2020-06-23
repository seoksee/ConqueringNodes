package GameBoard;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Random;

public final class DrawGraph extends JPanel {
    private ArrayList<Node> nodes = new ArrayList<>();
    private ArrayList<Edge> edges = new ArrayList<>();
    private Random rand = new Random();

    public DrawGraph(int num){
        CreateNodes(num);
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

    public synchronized boolean AddEdges(Thread t, Color color){
        int attempt = 0;
        while(attempt<20){
            int i = rand.nextInt(nodes.size());
            if(!nodes.get(i).used){
                int j = rand.nextInt(nodes.size());
                if(!nodes.get(j).used && i!=j){
                    edges.add(new Edge(i, j, color));
                    nodes.get(i).used = true;
                    nodes.get(j).used = true;
                    break;
                }
            }
            attempt++;
        }
        if(attempt>=20){
            System.out.println(t.getName() + " has more than 20 attempts.");
         return true;
        }
    return false;
    }

    public boolean uniqueCoordinates (float x, float y, ArrayList<Node> nodes){
        return nodes.stream().noneMatch((n) -> (x==n.x && y==n.y));
    }

    @Override
    public void paint(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setStroke(new BasicStroke(2));
        super.paint(g2d);
        nodes.forEach((n) -> {
            Ellipse2D.Float circle = new Ellipse2D.Float((n.x-5),(n.y-5),10f,10f);
            g2d.fill(circle);
            System.out.println(n.x + ", " + n.y);
        });

        edges.forEach((e) -> {
            Shape line = new Line2D.Float(nodes.get(e.i).x, nodes.get(e.i).y, nodes.get(e.j).x, nodes.get(e.j).y);
            g2d.setColor(e.color.brighter());
            g2d.draw(line);
            
        });
    }
    
}
