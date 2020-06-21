import java.awt.*;
import java.util.Date;

public class AddEdges implements Runnable{
    private DrawGraph graph;
    private String name;
    private String id;
    private Color[] colors;

    public AddEdges (DrawGraph graph, Color[] colors){
        this.graph = graph;
        this.colors = colors;
    }

    @Override
    public void run() {
        name = Thread.currentThread().getName();
        id = name.substring(name.length()-1);
        graph.AddEdges(colors[Integer.parseInt(id)]);
    }
}
