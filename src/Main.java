import java.awt.*;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Random rand = new Random();
        System.out.print("Enter n: ");
        int n = input.nextInt();
        System.out.print("Enter m (in seconds): ");
        int m = input.nextInt();
        System.out.print("Enter t: ");
        int t = input.nextInt();
//        ExecutorService executorService = Executors.newFixedThreadPool(t);


        Color[] arrayOfColor = new Color[t];
        for (int i = 0; i < arrayOfColor.length; i++) {
            float r = rand.nextFloat();
            float g = rand.nextFloat();
            float b = rand.nextFloat();
            arrayOfColor[i] = new Color(r,g,b);
        }

        DrawGraph frame = new DrawGraph(m);
        frame.setTitle("Game Board");
        frame.setSize(1200,1100);
        frame.getContentPane().setBackground(Color.WHITE);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.CreateNodes(n);

        AddEdges addEdges = new AddEdges(frame, arrayOfColor);

        long startTime = System.currentTimeMillis();
        long elapsedTime = 0L;
        while(elapsedTime< m*1000) {
            Thread[] arrayOfThreads = new Thread[t];
            for (int i = 0; i < arrayOfThreads.length; i++) {
                arrayOfThreads[i] = new Thread (addEdges);
                arrayOfThreads[i].setName("Thread" + i);
                arrayOfThreads[i].start();
            }
//            executorService.execute(addEdges);
            elapsedTime = System.currentTimeMillis() - startTime;
        }
        System.out.println("Times Up!");
//        executorService.shutdown();
    }
}
