package GameBoard;

import java.awt.*;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

public class Main {
    
    public static final Color[] COLORS = {
        Color.GREEN,Color.RED,Color.MAGENTA, Color.YELLOW, 
        Color.BLACK, Color.BLUE, Color.DARK_GRAY, Color.PINK, Color.CYAN
    };
    
    public static void main(String[] args) {
        
        /* Get input from user*/
        Scanner input = new Scanner(System.in);
        System.out.print("Enter n: ");
        int n = input.nextInt();
        System.out.print("Enter m (in seconds): ");
        int m = input.nextInt();
        System.out.print("Enter t: ");
        int t = input.nextInt();
        /* Create JFrame to display DrawGraph */
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
        gameBoard.setVisible(true);
        
        /* Create threadpool to execute Edge(Runnable Thread)*/
        ExecutorService pool = Executors.newFixedThreadPool(t);
        long startTime = System.currentTimeMillis();
        long elapsedTime = 0L;
        while(elapsedTime < m*1000) {
            pool.execute(addEdges);
            elapsedTime = System.currentTimeMillis() - startTime;
        }
        pool.shutdown();
        System.out.println("Times Up!");
        
        /* Create a ThreadCountTable to display each successful thread created how many lines*/
        ThreadCountTable table = new ThreadCountTable(addEdges.threadList, t, COLORS);
        JScrollPane sp = new JScrollPane(table.getTable());  
        JFrame boardTable = new JFrame();
        boardTable.add(sp);
        boardTable.setSize(300,400); 
        boardTable.setVisible(true);
    }

}
