import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter n: ");
        int n = input.nextInt();
        System.out.print("Enter t (in seconds): ");
        int t = input.nextInt();
        System.out.print("Enter m: ");
        int m = input.nextInt();
        DrawGraph frame = new DrawGraph(t);
        frame.setTitle("Game Board");
        frame.setSize(1200,1100);
        frame.getContentPane().setBackground(Color.WHITE);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.CreateNodes(n);
        frame.AddEdges();
    }
}
