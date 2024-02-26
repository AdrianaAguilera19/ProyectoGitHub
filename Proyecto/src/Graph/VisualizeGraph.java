/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Graph;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.Edge;
import org.graphstream.ui.view.Viewer;

import org.graphstream.ui.view.Viewer;

/**
 * Clase que proporciona métodos para visualizar un grafo utilizando la biblioteca GraphStream.
 * Permite la conversión de un grafo ponderado representado por LAGraph a un objeto GraphStream y
 * proporciona funciones para resaltar caminos específicos en el grafo visualizado.
 * 
 * @author Isabella Leizaola
 */
public class VisualizeGraph {

    private LAGraph gArray;

    /**
     * @return the g
     */
    public LAGraph getG() {
        return gArray;
    }

    /**
     * @param g the g to set
     */
    public void setG(LAGraph g) {
        this.gArray = g;
    }
    
    /**
     * Convierte el grafo representado por LAGraph a un objeto GraphStream.
     * 
     * @return El grafo convertido a un objeto GraphStream.
     */
    public Graph Convert() {
        Graph g = new SingleGraph("hp");
        for (int i = 0; i < gArray.getAdjList().length; i++) {
            if (gArray.getAdjList()[i] != null) {
                g.addNode(Integer.toString(i + 1));
            }
        }
        for (int i = 0; i < gArray.getAdjList().length; i++) {
            if (gArray.getAdjList()[i] != null) { // Comprueba si el nodo actual no es nulo
                Nodo aux = gArray.getAdjList()[i].getpFirst();
                for (int j = 0; j < gArray.getAdjList()[i].getSize(); j++) {
                    try {
                        if (aux != null ) { // Comprueba si el nodo auxiliar no es nulo && ((i+1) != aux.getKey())
                            String edgeId = Integer.toString(i + 1) + "-" + Integer.toString(aux.getKey()+1); // Modifica el formato del identificador para incluir el peso
                            Edge edge = g.addEdge(edgeId, Integer.toString(i+1), Integer.toString(aux.getKey()+1));
                            edge.setAttribute("ui.label", Double.toString(aux.getWeigth())); // Utiliza el peso como etiqueta de la arista
                            edge.setAttribute("ui.style", "text-offset: 10px, -10px; text-alignment: along;"); // Ajusta la posición de la etiqueta del peso
                        }
                    } catch (Exception e) {
                        // Maneja la excepción si es necesario
                    }
                    if (aux != null) { // Avanza al siguiente nodo si no es nulo
                        aux = aux.getpNext();
                    }
                }
            }
        }
        return g;
    }
    
    /**
     * Imprime el grafo utilizando la biblioteca GraphStream.
     * 
     * @param g El grafo a imprimir.
     */
    public void PrintG(Graph g) {
        String styleSheet = "node { text-alignment: under; }"
                + "edge.path { fill-color: magenta; size: 3px; }"; // Define el estilo para las aristas del camino

        g.setAttribute("ui.stylesheet", "node { text-alignment: under; }");

        for (Node node1 : g) {
            node1.setAttribute("ui.label", node1.getId());
        }

        for (Node node : g) {
            node.setAttribute("ui.style", "text-size: 20;");
        }

        Viewer viewer = g.display();
        viewer.setCloseFramePolicy(Viewer.CloseFramePolicy.HIDE_ONLY);
    }
    
    /**
     * Destaca un camino espec&iacutefico en el grafo visualizado.
     * 
     * @param path El camino a destacar.
     * @return El grafo con el camino resaltado.
     */
    public Graph highlightPath(List path) {
        Graph g = Convert();
        PrintG(g);
        // Highlight nodes and edges in the path
        Nodo aux = path.getpFirst();
        for (int i = 0; i < path.getSize() - 1 ; i++) {
            String node = Integer.toString(aux.getKey());
            String nextNode = Integer.toString(aux.getpNext().getKey());

            Node n = g.getNode(node);
            if (n != null) {
                n.setAttribute("ui.style", "fill-color: magenta;");
            }

            Edge e = g.getEdge(node + "-" + nextNode);
            
            
            if (e != null) {
                // e.setAttribute("ui.style", "fill-color: magenta;"); // Cambia el color de la arista
                // Añade la siguiente línea para cambiar el grosor de la arista si lo deseas
                e.removeAttribute("ui.style");
                e.setAttribute("ui.style", "fill-color: magenta; size: 3px;");
            }
            Edge e1 = g.getEdge(nextNode + "-" + node );
            
            if (e1 != null) {
                // e.setAttribute("ui.style", "fill-color: magenta;"); // Cambia el color de la arista
                // Añade la siguiente línea para cambiar el grosor de la arista si lo deseas
                e1.removeAttribute("ui.style");
                e1.setAttribute("ui.style", "fill-color: magenta; size: 3px;");
            }
            aux = aux.getpNext();
            
        }

        // Highlight the last node in the path
        String lastNode = Integer.toString(path.getpLast().getKey());
        Node lastN = g.getNode(lastNode);
        if (lastN != null) {
            lastN.setAttribute("ui.style", "fill-color: magenta;");
        }
         if (path.getpFirst() != null) {
        String firstNode = Integer.toString(path.getpFirst().getKey());
        Node firstN = g.getNode(firstNode);
        if (firstN != null) {
            firstN.setAttribute("ui.label", Integer.toString(path.getpFirst().getKey()) +" (Inicio)" );
        }

        Node lastNodeN = g.getNode(lastNode);
        if (lastNodeN != null) {
            lastNodeN.setAttribute("ui.label", Integer.toString(path.getpLast().getKey()) + "( Final)" );
        }
        }

        return g;
    }

}
