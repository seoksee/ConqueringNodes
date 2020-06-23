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
    JTable threadCountTable;
    private String data[][];
    private Color[] colors;

    
    public ThreadCountTable(List<String> list, int t, Color[] colors){
        data = new String[t][3];
        this.colors = colors;
        printEdgeTable(list);
        String column[]={"THREAD_ID", "COLOR", "COUNT"};         
        threadCountTable = new JTable(data,column);
        threadCountTable.setBounds(30,40,200,300);       
    }
    
    public JTable getTable(){
        return this.threadCountTable;
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
                    
                    data[index][1] = colors[Integer.parseInt(list.get(i).substring(list.get(i).length()-1))].toString();
                    
                    data[index++][2] = Integer.toString(count);
                    
                }
                count = 1;
            }
        }
        
    }
}
