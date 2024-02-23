/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyecto;

import AntSystem.Ant;
import AntSystem.SystemAnt;

import Graph.LAGraph;
import Txt.Functions;

/**
 *
 * @author gigie
 */
public class Proyecto {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        var graph = Functions.loadGraph();
        graph.printGraph();
        Functions.writeGraph(graph);
    }

}
