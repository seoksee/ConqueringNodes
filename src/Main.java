import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter n: ");
        int n = input.nextInt();
        DrawGraph frame = new DrawGraph();
        frame.setTitle("Game Board");
        frame.setSize(1200,1100);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setBackground(Color.BLUE);
        frame.CreateNodes(n);
        frame.AddEdges();
    }
}
