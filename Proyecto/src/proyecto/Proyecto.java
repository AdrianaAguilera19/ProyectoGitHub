/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyecto;


import AntSystem.SystemAnt;

import Graph.LAGraph;
import Graph.List;
import Graph.VisualizeGraph;
import Interfaz.MenuPrincipal;
import Txt.Functions;
import javax.swing.JOptionPane;

/**
 *
 * @author gigie
 */
public class Proyecto {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.setProperty("org.graphstream.ui", "swing");
        JOptionPane.showMessageDialog(null, "Bienvenido a continuacion debe seleccionar de sus archivos uno del tipo txt para hacer el codigo en base a el");
        
        var graph = Functions.loadGraph();
        MenuPrincipal principalMenu = new MenuPrincipal(graph);
        principalMenu.setVisible(true);
        VisualizeGraph ver = new VisualizeGraph();
        ver.setG(graph);
 //       ver.PrintG(ver.Convert());
 
//        List shortestPath = new List();
//        shortestPath.insertEnd(1, 5.0);
//        shortestPath.insertEnd(2, 4.9);
//        shortestPath.insertEnd(3, 3.0);
//        shortestPath.insertEnd(7, 5.2);
//       


        SystemAnt currentSystemAnt =  new SystemAnt(100,graph,5,4 );
        List bestPath = currentSystemAnt.runAS(5);
        ver.highlightPath(bestPath);
    }

}
