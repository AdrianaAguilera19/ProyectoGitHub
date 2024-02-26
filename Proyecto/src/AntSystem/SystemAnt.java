/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AntSystem;

import Graph.LAGraph;
import Graph.List;
import Graph.Nodo;


 /**
 * Clase que implementa un sistema de colonia de hormigas para encontrar la ruta m&acutes corta en un grafo ponderado.
 * Utiliza feromonas para representar la información de ruta y heurísticas para guiar la b&ucutesqueda.
 * 
 * @author gigie
 */
public class SystemAnt {
    private static final double p = 0.5; // Tasa de evaporación de feromonas
    private static final double alpha = 1.0; // Importancia relativa de la feromona
    private static final double beta = 2.0; // Importancia relativa de la heurística
    private static int ants; // Número de hormigas
    private LAGraph dGraph; //Grafo de las distancias
    private LAGraph pGraph; //Grafo de las feromonas
    private int iVertex;
    private int targetVertex;
    private int cycles;
    private double tourLength;
    private static final double q = 1.0;
    private List antPath;
    private List bestPath;
    private double bestPathLength;
    
    /**
     * Constructor de la clase SystemAnt.
     * 
     * @param ants El n&uacutemero de hormigas en el sistema.
     * @param dGraph El grafo de distancias sobre el cual operan las hormigas.
     * @param ivertex El &iacutendice del v&eacutertice de inicio.
     * @param targetvertex El &iacutendice del v&eacutertice de destino.
     */
    public SystemAnt(int ants, LAGraph dGraph, int ivertex, int targetvertex) {
        this.ants = ants;
        this.dGraph = dGraph;
        this.pGraph = initializePheromoneGraph(dGraph.getVertexNum());
        this.iVertex = ivertex;
        this.targetVertex = targetvertex;
        this.antPath = new List();
        this.bestPath = new List();
        this.bestPathLength = Double.MAX_VALUE;
    }
    
    /**
     * Inicializa el grafo de feromonas con un valor inicial uniforme.
     * 
     * @param numVertices El n&uacutemero de v&eacutertices en el grafo.
     * @return El grafo de feromonas inicializado.
     */
    private LAGraph initializePheromoneGraph(int numVertices) {
        LAGraph pheromoneGraph = new LAGraph();
        pheromoneGraph.insertVertex(numVertices);
        
       
  
        
        for (int i = 0; i < numVertices ; i++) {
            List aList = dGraph.getAdjList()[i]; 
            for (Nodo pANode = aList.getpFirst(); pANode != null; pANode = pANode.getpNext())
                pheromoneGraph.insertEdge(i, pANode.getKey(), 1.0/numVertices); // Agrega la cantidad inicial de feromonas al grafo de feromonas
            }
        

        return pheromoneGraph;
    }
    
    /**
     * Ejecuta el algoritmo de colonia de hormigas durante un n&uacutemero de ciclos dado.
     * 
     * @param cycles El n&uacutemero de ciclos de b&uacutesqueda.
     * @return El mejor camino encontrado durante la ejecución.
     */
    public List runAS(int cycles) { 
        for (int cycle = 0; cycle < cycles; cycle++) {
            pGraph.printGraph();
            bestPath.clear();
            bestPathLength = Double.MAX_VALUE;
            // Iteración de cada hormiga
            for (int ant = 0; ant < ants; ant++) {
                antPath.clear();
                tourLength = 0.0;
                int currentVertex = iVertex; // Ciudad actual de la hormiga
                antPath.insertEndN(iVertex);
          
                
                // Mover la hormiga hasta alcanzar la comida (targetVertex
                
                while (currentVertex != targetVertex ) {
                    int nextVertex = selectNextVertex(currentVertex);
                    if(nextVertex == -1){                       
                        break; 
                    }else{                   
                        if(!(antPath.findValue(nextVertex))){
                            tourLength += dGraph.getWeight(currentVertex, nextVertex);
                            antPath.insertEndN(nextVertex);
                            currentVertex = nextVertex;
                    } else {
                        // La hormiga no puede visitar más vértices, pasa a la siguiente hormiga
                        break;
                    }}
                }
                
                if (currentVertex == targetVertex) {
                        updatePheromone();
                        if(tourLength < getBestPathLength()){
                            bestPath.clear();
                            for(Nodo pANode = antPath.getpFirst(); pANode != null; pANode = pANode.getpNext()){
                                bestPath.insertEndN(pANode.getKey());
                            }                        
                            setBestPathLength(tourLength);   
                        }
                    }
                
             
                // Realizar la evaporación de feromonas en el grafo de feromonas
                        
            }
            System.out.println("---------------------");
            System.out.println("Camino más eficaz: ");
            bestPath.printList();
            System.out.println("Tamano del camino: "+getBestPathLength());
            System.out.println("----------------");
           
            pGraph = evaporatePheromones();
             
        }
        return bestPath;
    }
   
    /**
     * Selecciona el siguiente v&eacutertice a visitar por una hormiga en función de las feromonas y la heur&iacutestica.
     * 
     * @param currentVertex El &iacutendice del v&eacutertice actual de la hormiga.
     * @return El &iacutendice del siguiente v&eacutertice a visitar.
     */
    public int selectNextVertex(int currentVertex) {
        List adjList = dGraph.getAdjVert(currentVertex); // Get the adjacency list of the current vertex
        int numAdjacentVertices = adjList.getSize(); // Get the number of adjacent vertices
        
       
        // Calculate the total sum of heuristics and pheromones for the available paths
        double totalSum = 0.0;
        for (int i = 0; i < numAdjacentVertices; i++) {
            int adjacentVertex = adjList.getValue(i);
            double heuristic = pGraph.getWeight(currentVertex, adjacentVertex); // Use the correct heuristic calculation
            double pheromone = pGraph.getWeight(currentVertex, adjacentVertex);
            totalSum += Math.pow(pheromone, alpha) * Math.pow(heuristic, beta);
        }

        // Calculate the probability for each available path and select one randomly
        double[] probabilities = new double[numAdjacentVertices];
        double randomValue = Math.random(); // Generate a random number between 0 and 1

        int selectedVertex = -1;
        double cumulativeProbability = 0.0;
        for (int i = 0; i < numAdjacentVertices; i++) {
            int adjacentVertex = adjList.getValue(i);
            double heuristic = pGraph.getWeight(currentVertex, adjacentVertex); // Use the correct heuristic calculation
            double pheromone = pGraph.getWeight(currentVertex, adjacentVertex);
            double probability = (Math.pow(pheromone, alpha) * Math.pow(heuristic, beta)) / totalSum;
            probabilities[i] = probability;

            cumulativeProbability += probability;
            if (randomValue <= cumulativeProbability) {           
                selectedVertex = adjacentVertex;
                break;
            }
        }
        
       

        return selectedVertex;
    }
    
    /**
     * Actualiza las feromonas en funci&oacuten de la ruta de la hormiga.
     */
    public void updatePheromone() {
        double pheromone = q / tourLength;
        
        Nodo currentNode = antPath.getpFirst();
        Nodo nextNode = currentNode.getpNext();
        
        while (nextNode != null) {
            int currentVertex = currentNode.getKey();
            int nextVertex = nextNode.getKey();

            double currentPheromone = pGraph.getWeight(currentVertex, nextVertex);
            double updatedPheromone = currentPheromone + pheromone;

            pGraph.setWeight(currentVertex, nextVertex, updatedPheromone);
            pGraph.setWeight(nextVertex, currentVertex, updatedPheromone);
            currentNode = nextNode;
            nextNode = currentNode.getpNext();
        }
    }    
     
    /**
     * Realiza la evaporación de feromonas en el grafo de feromonas.
     * 
     * @return El grafo de feromonas despu&eacutes de la evaporaci&oacuSten.
     */
    public LAGraph evaporatePheromones() {
        int numVertices = pGraph.getVertexNum();
        double pheromone = 0;
         
        LAGraph pheromoneGraph = new LAGraph();
        pheromoneGraph.insertVertex(numVertices);
        
        for (int i = 0; i < numVertices ; i++) {
            List aList = dGraph.getAdjList()[i]; 
            for (Nodo pANode = aList.getpFirst(); pANode != null; pANode = pANode.getpNext()){
                pheromone = pGraph.getWeight(i, pANode.getKey());
                pheromone = (1 - p) * pheromone;
                pheromoneGraph.insertEdge(i, pANode.getKey(), pheromone); // Agrega la cantidad inicial de feromonas al grafo de feromonas
            }
        
        }
        return pheromoneGraph;
        }
    

    
    
    
    
    
    
    
    
    
  
    

   
    /**
     * @return the iVertex
     */
    public int getiVertex() {
        return iVertex;
    }

    /**
     * @param iVertex the iVertex to set
     */
    public void setiVertex(int iVertex) {
        this.iVertex = iVertex;
    }

    /**
     * @return the targetVertex
     */
    public int getTargetVertex() {
        return targetVertex;
    }

    /**
     * @param targetVertex the targetVertex to set
     */
    public void setTargetVertex(int targetVertex) {
        this.targetVertex = targetVertex;
    }

    /**
     * @return the dGraph
     */
    public LAGraph getdGraph() {
        return dGraph;
    }

    /**
     * @param dGraph the dGraph to set
     */
    public void setdGraph(LAGraph dGraph) {
        this.dGraph = dGraph;
    }
    
    public LAGraph getpGraph() {
        return pGraph;
    }

    /**
     * @param dGraph the dGraph to set
     */
    public void setpGraph(LAGraph pGraph) {
        this.pGraph = pGraph;
    }

    /**
     * @return the cycles
     */
    public int getCycles() {
        return cycles;
    }

    /**
     * @param cycles the cycles to set
     */
    public void setCycles(int cycles) {
        this.cycles = cycles;
    }

    /**
     * @return the ants
     */
    public int getAnts() {
        return ants;
    }

    /**
     * @param ants the ants to set
     */
    public void setAnts(int ants) {
        this.ants = ants;
    }

    /**
     * @return the bestPath
     */
    public List getBestPath() {
        return bestPath;
    }

    /**
     * @param bestPath the bestPath to set
     */
    public void setBestPath(List bestPath) {
        this.bestPath = bestPath;
    }

    /**
     * @return the bestPathLength
     */
    public double getBestPathLength() {
        return bestPathLength;
    }

    /**
     * @param bestPathLength the bestPathLength to set
     */
    public void setBestPathLength(double bestPathLength) {
        this.bestPathLength = bestPathLength;
    }
    
    

}