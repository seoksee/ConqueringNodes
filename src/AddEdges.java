import java.awt.*;
import java.util.Date;

public class AddEdges implements Runnable{
    private DrawGraph graph;
    private String name;
    private String id;
    private Color[] colors;
    boolean exit = false;

    public AddEdges (DrawGraph graph, Color[] colors){
        this.graph = graph;
        this.colors = colors;
    }

    @Override
    public void run() {
        while(!exit){
            name = Thread.currentThread().getName();
            id = name.substring(name.length()-1);
            exit = graph.AddEdges(Thread.currentThread(), colors[Integer.parseInt(id)]);
        }
        if(exit){
//            System.out.println(Thread.currentThread().getName() + " has 20 failed attempts, exiting the program...");
        }
    }


    public void callToExit(){
        exit = true;
    }
}
