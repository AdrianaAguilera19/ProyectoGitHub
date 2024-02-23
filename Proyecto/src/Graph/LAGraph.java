/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Graph;

/**
 *
 * @author gigie
 */
public class LAGraph {

    private int maxNodes;
    private int vertexNum;
    private List[] adjList;

    public LAGraph() {
        maxNodes = 20;
        vertexNum = 0;
        adjList = new List[20];
    }

    public void insertEdge(int i, int j, double weight) {
        if (i >= getVertexNum()) {
            System.out.println("Error no existe el vertice en el grafo");
        } else {
            if (adjList[i] == null) adjList[i] = new List();
            if (adjList[j] == null) adjList[j] = new List();
            
            getAdjList()[i].insertEnd(j, weight);
            getAdjList()[j].insertEnd(i, weight);
            
        }
    }

    public void removeEdge(int i, int j) {
        if (j >= getVertexNum()) {
            System.out.println("Error no existe el vertice en el grafo");
        } else {
            getAdjList()[i].removeValue(j);
            getAdjList()[j].removeValue(i);
        }
    }

    public void insertVertexes(int n) {
        if (n > getMaxNodes() - getVertexNum()) {
            System.out.println("Error, se supera el numero de nodos maximo del grafo");
        } else if (n < 4) {
            System.out.println("Error, no supera el numero minimo de nodos en el grafo");
        } else {
            for (int i = getVertexNum(); i < getVertexNum() + n; i++) {
                adjList[i] = new List();
            }
        }
        vertexNum += n;
    }

    public void insertVertex() {
        if (getVertexNum() >= getMaxNodes()) {
            System.out.println("Error, se supera el numero de nodos maximo del grafo");
        } else {
            adjList[getVertexNum() - 1] = new List();
        }
        vertexNum += 1;
    }

    public int inGrade(int v) {
        int gIn = 0;
        for (int i = 0; i < getVertexNum(); i++) {
            if (i != v) {
                if (getAdjList()[i].findValue(v)) {
                    gIn++;
                }
            }
        }
        return gIn;
    }

    public int outGrade(int i) {
        int gOut = 0;
        Node aux = getAdjList()[i].getpFirst();

        while (aux != null) {
            gOut++;
            aux = aux.getpNext();
        }
        return gOut;
    }

    public int incidence(int i) {
        return inGrade(i) + outGrade(i);
    }

    public int size() {
        int sz = 0;

        for (int i = 0; i < getVertexNum(); i++) {
            sz += numElements(getAdjList()[i]);
        }

        return sz;
    }

    static int numElements(List list) {
        Node aux = list.getpFirst();
        int result = 0;
        while (aux != null) {
            result += 1;
            aux = aux.getpNext();
        }
        return result;
    }

    public boolean isNotDirected() {
        boolean dir = true;
        for (int i = 0; i < getVertexNum(); i++) {
            for (int j = 0; j < getVertexNum(); j++) {
                if (getAdjList()[i].findValue(j) != getAdjList()[j].findValue(i)) {
                    dir = false;
                }
            }
        }
        return dir;
    }

    public void printGraph() {
        System.out.println("Tamaño máximo del grafo: " + getMaxNodes() + "\n");
        System.out.println("El grafo contiene " + getVertexNum() + " vértices: \n");
        for (int i = 0; i < getVertexNum(); i++) {
            System.out.print("vértice " + (i+1) + ": ");
            write(getAdjList()[i]);
        }
    }

    static void write(List list) {
        Node aux;
        aux = list.getpFirst();
        while (aux != null) {
            System.out.print(aux.getKey() + 1 + "(" + aux.getWeigth() + ")" + ",");
            aux = aux.getpNext();
        }
        System.out.println("FIN");
    }

    /**
     * @return the maxNodes
     */
    public int getMaxNodes() {
        return maxNodes;
    }

    /**
     * @return the vertexNum
     */
    public int getVertexNum() {
        return vertexNum;
    }

    /**
     * @return the adjList
     */
    public List[] getAdjList() {
        return adjList;
    }

    public List getAdjVert(int vertex) {
        return adjList[vertex];
    }

    public double getWeight(int i, int j) {
        Node current = adjList[i].getpFirst();
        while (current != null) {
            if (current.getKey() == j) {
                return current.getWeigth();
            }
            current = current.getpNext();
        }
        return -1;
    }

    public void setVertexNum(int vertexNum) {
        this.vertexNum = vertexNum;
    }

//No se si esto tiene que ir
//    
//    public void setAdjList(List[] adjList) {
//        this.adjList = adjList;
//    }
//    
    
    
}
