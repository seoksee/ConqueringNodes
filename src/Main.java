import java.awt.*;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter n: ");
        int n = input.nextInt();
        System.out.print("Enter m (in seconds): ");
        int m = input.nextInt();
        System.out.print("Enter t: ");
        int t = input.nextInt();
        ExecutorService executorService = Executors.newFixedThreadPool(t);

        DrawGraph frame = new DrawGraph(m);
        frame.setTitle("Game Board");
        frame.setSize(1200,1100);
        frame.getContentPane().setBackground(Color.WHITE);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.CreateNodes(n);

        AddEdges addEdges = new AddEdges(frame);

        long startTime = System.currentTimeMillis();
        long elapsedTime = 0L;
        while(elapsedTime< m*1000) {
            executorService.execute(addEdges);
//            frame.AddEdges();
            elapsedTime = System.currentTimeMillis() - startTime;
            System.out.println(elapsedTime);
        }
        System.out.println("Times Up!");
        executorService.shutdown();
        frame.AddEdges();
    }
}
