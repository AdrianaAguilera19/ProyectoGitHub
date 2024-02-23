/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AntSystem;

/**
 *
 * @author gigie
 */

import Graph.LAGraph;
import Graph.List;
import Graph.Node;


import java.util.Random;


public class Ant {
    private int currentVertex;
    private int targetVertex;
    private LAGraph graph;
    private LAGraph pheromoneMatrix;
    private LAGraph visibilityMatrix;
    private LAGraph heuristicMatrix;
    private double alpha;
    private int[] visitedVertices;
    private int visitedVerticesCount;

    public Ant(int currentVertex, int targetVertex, LAGraph graph, LAGraph pheromoneMatrix,
               LAGraph visibilityMatrix, LAGraph heuristicMatrix, double alpha) {
        this.currentVertex = currentVertex;
        this.targetVertex = targetVertex;
        this.graph = graph;
        this.pheromoneMatrix = pheromoneMatrix;
        this.visibilityMatrix = visibilityMatrix;
        this.heuristicMatrix = heuristicMatrix;
        this.alpha = alpha;
        this.visitedVertices = new int[graph.getVertexNum()];
        this.visitedVerticesCount = 1;
        visitedVertices[currentVertex] = 1;
    }

    public void constructSolution() {
        while (currentVertex != targetVertex) {
            int nextVertex = selectNextVertex();
            visitedVertices[nextVertex] = 1;
            visitedVerticesCount++;
            currentVertex = nextVertex;
        }
    }

    private int selectNextVertex() {
        double[] probabilities = calculateProbabilities();
        double randomValue = Math.random();
        double cumulativeProbability = 0.0;

        for (int i = 0; i < probabilities.length; i++) {
            cumulativeProbability += probabilities[i];
            if (randomValue <= cumulativeProbability) {
                return i;
            }
        }

        // If no vertex is selected, return the last vertex as a fallback option
        return getLastVisitedVertex();
    }

    private double[] calculateProbabilities() {
        int numVertices = graph.getVertexNum();
        double[] probabilities = new double[numVertices];
        double total = 0.0;

        
       
        for (int i = 0; i < numVertices; i++) {
            if (visitedVertices[i] != 1) {
                double pheromone = Math.pow(pheromoneMatrix.getAdjList()[i].getpFirst().getKey(), alpha);
                double visibility = visibilityMatrix.getAdjList()[i].getpFirst().getKey();
                double heuristic = heuristicMatrix.getAdjList()[i].getpFirst().getKey();
                probabilities[i] = pheromone * visibility * heuristic;
                total += probabilities[i];
            }
        }

        // Normalize probabilities
        for (int i = 0; i < numVertices; i++) {
            if (visitedVertices[i] != 1) {
                probabilities[i] /= total;
            }
        }

        return probabilities;
    }

    public boolean hasTraversedEdge(int vertex1, int vertex2) {
        return visitedVertices[vertex1] == 1 && visitedVertices[vertex2] == 1;
    }

    public double getDeltaPheromone(int vertex1, int vertex2) {
        return 1.0 / graph.getWeight(vertex1, vertex2);
    }

    public int getLastVisitedVertex() {
        for (int i = visitedVerticesCount - 1; i >= 0; i--) {
            if (visitedVertices[i] == 1) {
                return i;
            }
        }
        return currentVertex;
    }

    public int[] getVisitedVertices() {
        int[] path = new int[visitedVerticesCount];
        int index = 0;
        for (int i = 0; i < visitedVertices.length; i++) {
            if (visitedVertices[i] == 1) {
                path[index++] = i;
            }
        }
        return path;
    }
}


    

