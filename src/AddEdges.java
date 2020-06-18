import java.util.Date;

public class AddEdges implements Runnable{
    private DrawGraph graph;

    public AddEdges (DrawGraph graph){
        this.graph = graph;
    }

    @Override
    public void run() {
        graph.AddEdges();
        System.out.println(Thread.currentThread() + "is adding an edge");
    }
}
