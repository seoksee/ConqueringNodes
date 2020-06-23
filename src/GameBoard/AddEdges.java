package GameBoard;

import java.awt.*;
import java.util.List;
import java.util.ArrayList;

public class AddEdges implements Runnable{
    private DrawGraph graph;
    private String name;
    private String id;
    private Color[] colors;
    static boolean exit = false;
    List<String> threadList = new ArrayList<String>();

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
            if(!exit){
                threadList.add(Thread.currentThread().getName());
            }
        }
    }


    public void callToExit(){
        exit = true;
    }
}
