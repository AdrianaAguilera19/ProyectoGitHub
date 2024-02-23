/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AntSystem;

import Graph.LAGraph;
import Graph.Node;

/**
 *
 * @author gigie
 */
public class SystemAnt {
   double iPheromone;           //Feromona
   int iVertex;                 //Ciudad Inicial
   int targetVertex;            //Ciudad destino
   LAGraph dGraph;              //Grafo de la distancias
   int cycles;                  //Ciclos especificada al inicio
   int ants;
   
   
   // Probability of ant to choose a Path:
   int alpha = 1;
   int beta = 2;
   int q = 1;
   double n;                    //factor de visibilidad del camino

    public SystemAnt(int iVertex, int targetVertex, LAGraph dGraph,int ants, int cycles) {
        this.iPheromone = iPheromone;
        this.targetVertex = targetVertex;
        this.dGraph =  dGraph;
        this.cycles = cycles;
        this.ants = ants;
        
    }
   
    public void calculateIPheromones(double pheromone){  //Crea el grafo de las feromonas con la feromona inicial
        LAGraph pGraph = new LAGraph();
        pGraph.insertVertex(dGraph.getVertexNum());
        
        for (int i = 0; i < dGraph.getVertexNum(); i++) {    
            Node aux;
            aux = dGraph.getAdjList()[i].getpFirst();
            
            while (aux != null) {
                pGraph.insertEdge(i, aux.getKey(), pheromone/aux.getWeigth(), true);
                aux = aux.getpNext();            
        }
        }
        pGraph.printGraph();
        
    }
    
    
    public void calculateBestTrack(){
        int i = 0;
        while (i <= cycles){
            goToDestiny();
            
            
            pheromoneUpdate();
            i++;
        }
        
    }
   
    public void goToDestiny(){
    
    
    }
    
    public void pheromoneUpdate(){
    
    
    }
    
    
}
