/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameBoard;

import java.awt.Color;
import java.util.List;
import javax.swing.*;
/**
 *
 * @author Jia Kai
 */
public class ThreadCountTable {
    JFrame frame;
    private String data[][];
    private Color[] colors;

    
    public ThreadCountTable(List<String> list, int t, Color[] colors){
        data = new String[t][3];
        frame =new JFrame();
        printEdgeTable(list);
        String column[]={"THREAD_ID","COLOR", "COUNT"};         
        JTable jt = new JTable(data,column);    
        jt.setBounds(30,40,200,300);          
        JScrollPane sp = new JScrollPane(jt);    
        frame.add(sp);        
        frame.setSize(300,400);    
        frame.setVisible(true);
    }
    
    private void printEdgeTable(List<String> list){
        int count = 1;
        int index = 0;
        if (list.isEmpty()) {
            System.out.println("There is no Thread.");
        }else {
            for (int i = 0; i < list.size(); i++) {
                for (int j = i + 1; j < list.size(); j++) {
                    if (list.get(i).equals(list.get(j))) {
                        count ++;
                        list.set(j, "0");
                    }
                }
                if (!list.get(i).equals("0")) {  
                    data[index][0] = list.get(i);
                    System.out.println(Integer.parseInt(list.get(i).substring(list.get(i).length()-1)));
                    data[index][1] = colors[Integer.parseInt(list.get(i).substring(list.get(i).length()-1))].toString();
                    data[index++][2] = Integer.toString(count);
                }
                count = 1;
            }
        }
        
    }
}