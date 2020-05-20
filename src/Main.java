import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter n: ");
        int n = input.nextInt();
        DrawGraph frame = new DrawGraph();
        frame.CreateNodes(n);
        frame.setSize(1500,2000);
        frame.setVisible(true);
    }


}
