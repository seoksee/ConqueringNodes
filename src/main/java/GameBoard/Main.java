package GameBoard;

import java.awt.*;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

public class Main {
    
    public static final Color[] COLORS = {
        Color.GREEN,Color.RED,Color.MAGENTA, Color.YELLOW, 
        Color.BLACK, Color.BLUE, Color.DARK_GRAY, Color.PINK, Color.CYAN
    };
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Random rand = new Random();
        System.out.print("Enter n: ");
        int n = input.nextInt();
        System.out.print("Enter m (in seconds): ");
        int m = input.nextInt();
        System.out.print("Enter t: ");
        int t = input.nextInt();
        ExecutorService pool = Executors.newFixedThreadPool(t);


//        Color[] arrayOfColor = new Color[t];
//        for (int i = 0; i < arrayOfColor.length; i++) {
//            float r = rand.nextFloat();
//            float g = rand.nextFloat();
//            float b = rand.nextFloat();
//            arrayOfColor[i] = new Color(r,g,b);
//        }
        
        DrawGraph panel = new DrawGraph(n);
        
        JScrollPane pane = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        AddEdges addEdges = new AddEdges(panel, COLORS);

        JFrame gameBoard = new JFrame();
        gameBoard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameBoard.add(pane);
        gameBoard.setTitle("Game Board");
        gameBoard.setSize(1200,1100);
        gameBoard.getContentPane().setBackground(Color.decode("#d3d3d3"));
        gameBoard.setResizable(true);
        gameBoard.setAlwaysOnTop(true);
        gameBoard.setVisible(true);
        
        long startTime = System.currentTimeMillis();
        long elapsedTime = 0L;
        while(elapsedTime < m*1000) {
//            Thread[] arrayOfThreads = new Thread[t];
//            ExecutorService pool = Executors.newFixedThreadPool(t);
//            for (int i = 0; i < arrayOfThreads.length; i++) {
//                arrayOfThreads[i] = new Thread (addEdges);
//                arrayOfThreads[i].setName("Thread" + i);
//                arrayOfThreads[i].start();
//            }
            pool.execute(addEdges);
            elapsedTime = System.currentTimeMillis() - startTime;
        }
//        addEdges.callToExit();
        System.out.println("Times Up!");
//        System.out.println(addEdges.threadList);

        new ThreadCountTable(addEdges.threadList, t, COLORS);

        
        
    }

}
