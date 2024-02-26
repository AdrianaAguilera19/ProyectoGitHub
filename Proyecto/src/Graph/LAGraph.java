/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Graph;

/**
 ** Clase que representa un grafo no dirigido ponderado mediante listas de
 * adyacencia. Proporciona m&eacutetodos para la inserci&oacuten y
 * eliminaci&oacuten de aristas y v&eacutertices, as&iacute como para la
 * actualizaci&oacuten y consulta de pesos de aristas.
 *
 *
 *
 * @author Giselle Esclasans
 *
 */
public class LAGraph {

    private int maxNodes;
    private int vertexNum;
    private List[] adjList;

    /**
     * Constructor de la clase LAGraph. Inicializa el grafo con un número máximo
     * de nodos predeterminado (20).
     */
    public LAGraph() {
        maxNodes = 20;
        vertexNum = 0;
        adjList = new List[20];
    }

    /**
     * Inserta una arista entre dos vr&eacutetices con un peso dado.
     *
     * @param i El primer v&eacitertice de la arista.
     * @param j El segundo v&eacutertice de la arista.
     * @param weight El peso de la arista.
     */
    public void insertEdge(int i, int j, double weight) {
        if (i >= getVertexNum()) {
            System.out.println("Error no existe el vertice en el grafo");
        } else {
            if (adjList[i] == null) {
                adjList[i] = new List();
            }
            if (adjList[j] == null) {
                adjList[j] = new List();
            }

            getAdjList()[i].insertEnd(j, weight);
            getAdjList()[j].insertEnd(i, weight);

        }
    }

    /**
     * Elimina una arista entre dos v&eacutertices.
     *
     * @param i El primer v&eacutertice de la arista.
     * @param j El segundo v&eacutertice de la arista.
     */
    public void removeEdge(int i, int j) {
        if (j >= getVertexNum()) {
            System.out.println("Error no existe el vertice en el grafo");
        } else {
            getAdjList()[i].removeValue(j);
            getAdjList()[j].removeValue(i);
        }
    }

    /**
     * Inserta un v&eacutertice en el grafo.
     *
     * @param n El n&uacutemero de v&eacutertices a insertar.
     */
    public void insertVertex(int n) {
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

    /**
     * Inserta un v&eacutertice en el grafo.
     *
     * @see #getVertexNum()
     * @see #getMaxNodes()
     */
    public void insertVertex() {
        if (getVertexNum() >= getMaxNodes()) {
            System.out.println("Error, se supera el numero de nodos maximo del grafo");
        } else {
            adjList[getVertexNum() - 1] = new List();
        }
        vertexNum += 1;
    }

    /**
     * Actualiza el peso de una arista entre dos v&eacutertices dados. Si uno o
     * ambos v&eacutertices no existen en el grafo, se muestra un mensaje de
     * error.
     *
     * @param i El primer v&eacutertice de la arista.
     * @param j El segundo v&eacutertice de la arista.
     * @param weight El nuevo peso de la arista.
     * @see #getVertexNum()
     * @see #getAdjList()
     */
    public void updateWeightEdge(int i, int j, double weight) {
        if (i >= getVertexNum() || j >= getVertexNum()) {
            System.out.println("Error: uno o ambos vértices no existen en el grafo.");
            return;
        }

        Nodo current = getAdjList()[i].getpFirst();
        while (current != null) {
            if (current.getKey() == j) {
                current.setWeigth(weight);
                break;
            }
            current = current.getpNext();
        }

        current = getAdjList()[j].getpFirst();
        while (current != null) {
            if (current.getKey() == i) {
                current.setWeigth(weight);
                break;
            }
            current = current.getpNext();
        }
    }

    /**
     * Elimina un v&eacutertice del grafo, así como todas las aristas incidentes
     * a ese v&eacutertice.
     *
     * @param vertex El v&eacutertice a eliminar.
     * @see #getVertexNum()
     * @see #getAdjList()
     * @see List#removeValue(int)
     */
    public void removeVertex(int vertex) {
        // Verificar si el vértice existe en el grafo
        if (vertex < 0 || vertex >= vertexNum) {
            System.out.println("El vértice especificado no existe en el grafo.");
            return;
        }

        // Eliminar todas las aristas incidentes al vértice
        List adjVertices = adjList[vertex];
        for (int i = 0; i < vertexNum; i++) {
            if (i != vertex) {
                List adjacentList = adjList[i];
                adjacentList.removeValue(vertex);
            }
        }

        // Eliminar la lista de adyacencia del vértice
        adjVertices = null;

        // Actualizar el número de vértices en el grafo
        vertexNum--;

        // Actualizar los índices de los vértices posteriores al eliminado
        for (int i = vertex; i < vertexNum; i++) {
            adjList[i] = adjList[i + 1];
        }

        // Liberar el último índice
        adjList[vertexNum] = null;
    }

    /**
     * Calcula el grado de entrada de un v&eacutertice dado en el grafo. El
     * grado de entrada de un v&eacutertice es el n&uacutemero de aristas
     * incidentes en &eacutel.
     *
     * @param v El &iacutendice del v&eacutertice del que se quiere calcular el
     * grado de entrada.
     * @return El grado de entrada del v&eacutertice especificado.
     * @see #getAdjList()
     * @see List#findValue(int)
     * @see #getVertexNum()
     */
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

    /**
     * Calcula el grado de salida de un v&eacutertice dado en el grafo. El grado
     * de salida de un v&eacutertice es el n&uacutemero de aristas que parten de
     * &eacutel.
     *
     * @param i El &iacutendice del v&eacutertice del que se quiere calcular el
     * grado de salida.
     * @return El grado de salida del vértice especificado.
     * @see #getAdjList()
     */
    public int outGrade(int i) {
        int gOut = 0;
        Nodo aux = getAdjList()[i].getpFirst();

        while (aux != null) {
            gOut++;
            aux = aux.getpNext();
        }
        return gOut;
    }

    /**
     * Calcula la incidencia de un v&eacutertice dado en el grafo. La incidencia
     * de un v&eacutertice es la suma de su grado de entrada y su grado de
     * salida.
     *
     * @param i El &iacutendice del v&eacutertice del que se quiere calcular la
     * incidencia.
     * @return La incidencia del vértice especificado.
     * @see #inGrade(int)
     * @see #outGrade(int)
     */
    public int incidence(int i) {
        return inGrade(i) + outGrade(i);
    }

    /**
     * Calcula el tamaño del grafo, es decir, la suma de los elementos de todas
     * las listas de adyacencia.
     *
     * @return El tamaño del grafo.
     * @see #getAdjList()
     * @see #getVertexNum()
     * @see #numElements(List)
     */
    public int size() {
        int sz = 0;

        for (int i = 0; i < getVertexNum(); i++) {
            sz += numElements(getAdjList()[i]);
        }

        return sz;
    }

    /**
     * Calcula el n&uacutemero de elementos en una lista dada.
     *
     * @param list La lista de la que se quiere contar los elementos.
     * @return El n&uacutemero de elementos en la lista especificada.
     * @see List#getpFirst()
     */
    static int numElements(List list) {
        Nodo aux = list.getpFirst();
        int result = 0;
        while (aux != null) {
            result += 1;
            aux = aux.getpNext();
        }
        return result;
    }

    /**
     * Imprime la representaci&oacuten del grafo en la consola. La
     * representaci&oacuten incluye el tamaño m&aacuteximo del grafo, el
     * n&uacutemero de v&eacutertices y las listas de adyacencia con sus
     * respectivos v&eacutertices y pesos de aristas.
     *
     * @see #getMaxNodes()
     * @see #getVertexNum()
     * @see #getAdjList()
     * @see #write(List)
     */
    public void printGraph() {
        System.out.println("Tamaño máximo del grafo: " + getMaxNodes() + "\n");
        System.out.println("El grafo contiene " + getVertexNum() + " vértices: \n");
        for (int i = 0; i < getVertexNum(); i++) {
            System.out.print("vértice " + (i + 1) + ": ");
            write(getAdjList()[i]);
        }
    }

    /**
     * M&eacutetodo de utilidad para imprimir una lista de adyacencia.
     *
     * @param list La lista de adyacencia que se desea imprimir.
     * @see List#getpFirst()
     */
    static void write(List list) {
        Nodo aux;
        aux = list.getpFirst();
        while (aux != null) {
            System.out.print(aux.getKey() + 1 + "(" + aux.getWeigth() + ")" + ",");
            System.out.println("hola");
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
        Nodo current = adjList[i].getpFirst();
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

    public void setWeight(int i, int j, double w) {
        Nodo current = adjList[i].getpFirst();
        while (current != null) {
            if (current.getKey() == j) {
                current.setWeigth(w);
            }
            current = current.getpNext();
        }
    }
}
