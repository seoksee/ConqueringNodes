package GameBoard;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static final Color[] COLORS = {
            Color.DARK_GRAY,Color.RED,Color.BLUE, Color.YELLOW,
            Color.BLACK, Color.MAGENTA, Color.GREEN, Color.WHITE, Color.CYAN
    };

    public static final String[] namesOfColors = {
      "Dark Gray", "Red", "Blue", "Yellow", "Black", "Magenta", "Green", "White", "Cyan"
    };

    public static void main(String[] args) {
        int n = 0, m = 0,t = 0;

        /* Get input from user*/
        while (true) {
            String N = JOptionPane.showInputDialog(
                    null, "The number of NODES to create, n: ");

            String M = JOptionPane.showInputDialog(
                    null, "How many seconds to run, m: "
            );

            String[] options = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};
            ImageIcon icon = new ImageIcon("src/images/thread.png");
            String T = (String)JOptionPane.showInputDialog(null,
                    "The number of THREADS to create, t: \n (For better visualization, maximum number of thread is 9)",
                    "Threads number",JOptionPane.QUESTION_MESSAGE, icon, options, "2");

            try {
                n = Integer.parseInt(N);
                m = Integer.parseInt(M);
                t = Integer.parseInt(T);
                break;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null,
                        "Input should be an integer",
                        "Number Format Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        /* Create JFrame to display GameBoard.DrawGraph */
        DrawGraph panel = new DrawGraph(n);
//        JScrollPane pane = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        AddEdges addEdges = new AddEdges(panel, COLORS);
        JFrame gameBoard = new JFrame();
        gameBoard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameBoard.add(panel);
        gameBoard.setTitle("Game Board");
        gameBoard.setSize(1100,1060);
        gameBoard.getContentPane().setBackground(Color.decode("#d3d3d3"));
        gameBoard.setResizable(false);
        gameBoard.setVisible(true);

        /* Create threadpool to execute GameBoard.Edge(Runnable Thread)*/
        ExecutorService pool = Executors.newFixedThreadPool(t);
        long startTime = System.currentTimeMillis();
        long elapsedTime = 0L;
        while(elapsedTime < m*1000) {
            pool.execute(addEdges);
            elapsedTime = System.currentTimeMillis() - startTime;
        }
        pool.shutdown();
        System.out.println("Times Up!");

        /* Create a GameBoard.ThreadCountTable to display each successful thread created how many lines*/
        ThreadCountTable table = new ThreadCountTable(addEdges.threadList, t, namesOfColors);
        JScrollPane sp = new JScrollPane(table.getTable());
        JFrame boardTable = new JFrame();
        boardTable.add(sp);
        boardTable.setSize(400,300);
        boardTable.setVisible(true);
    }
}
