package Txt;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import Graph.LAGraph;
import Graph.List;
import Graph.Node;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 * Clase que contiene diferenctes funciones para usar en el codigo.
 *
 * @author Isabella
 */
public class Functions {

    /**
     * Almacena el contenido de un archivo de texto en forma de String
     *
     * @param direction Texto que contiene la direccion del archivo en la
     * computadora
     * @return informacion del txt convertida en String
     * @throws ExcepcionTipo Descripción de la excepción que puede lanzar el
     * método.
     */
    public static String loadTextFromFile(File file) {
        String text = "";

        try {
            FileReader fr = new FileReader(file);
            BufferedReader bf = new BufferedReader(fr);
            String currentLine;

            while ((currentLine = bf.readLine()) != null) {

                text += (currentLine + "\n");
            }
            System.out.println(text);
        } catch (Exception e) {
            text = "";
            JOptionPane.showMessageDialog(null, "Error al leer el archivo");
        }

        return text;
    }

    public static File openFile() {
        File file;
        try {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.showOpenDialog(fileChooser);
            file = fileChooser.getSelectedFile();
//                           System.out.println(file.());

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar el txt, se procedera a cargar un archivo por default");
            file = new File("./Prueba.txt");
            System.out.println(file.getAbsolutePath());

        }
        return file;
//        return loadGraph(direction);
    }

    /**
     * Carga un grafo desde un archivo de texto.
     *
     * @return Un array de nodos que representa el grafo cargado desde el
     * archivo.
     */
//ciudad
//1
//2
//3
//4
//5
//6
//7
//aristas
//1,2,5
//1,3,3.1
//1,6,5.2
//6,3,3.2
//6,5,4.7
//3,2,4.9
//2,7,5.2
//
    public static LAGraph loadGraph() { //antes retornaba lista
        File file = openFile();
        String data = loadTextFromFile(file);
        var graph = new LAGraph();

        String[] lines = data.split("\n");

        int vertexNum = 0;
        for (int i = 0; i < lines.length; i++) {
            String currentLine = lines[i];
            if (currentLine.trim().equalsIgnoreCase("aristas")) {
                vertexNum = i - 1;
                graph.setVertexNum(vertexNum);
                break;
            }
        }
        int k = 0;
        for (int j = vertexNum + 2; j < lines.length; j++) {
            String currentLine = lines[j];
            String[] nodeSplit = currentLine.split(",");
            int nodeKey = Integer.parseInt(nodeSplit[0]) - 1;
            int nodePnext = Integer.parseInt(nodeSplit[1]) - 1;
            double nodeWeigth = Double.parseDouble(nodeSplit[2]);
//            Node pNextCurrentNode = new Node(nodePnext, 0);
//            Node currentNode = new Node(nodeKey, nodeWeigth, pNextCurrentNode);
            graph.insertEdge(nodeKey, nodePnext, nodeWeigth);

        }
        graph.printGraph();
        return graph;
    }

    /**
     * Guarda la lista de adyacencia de un grafo en un archivo de texto.
     *
     * @param graph El grafo que se desea almacenar
     * @throws ExcepcionTipo Descripción de la excepción que puede lanzar el
     * método.
     */
    public static void writeGraph(LAGraph graph) {
        String lines = "ciudad\n";
        for (int i = 0; i < graph.getVertexNum(); i++){
            lines += i+1 + "\n" ;
        }
        lines += "aristas\n";
        Boolean[][] printed = new Boolean[graph.size()][graph.size()];

        for (int i = 0; i < graph.getVertexNum(); i++) {
            List lista = graph.getAdjList()[i];
            Node aux = lista.getpFirst();
            while (aux != null) {
                if (printed[i][aux.getKey()] == null) {
                    lines += i + 1 + ", " + aux.toString() + "\n";
                    printed[i][aux.getKey()] = true;
                    printed[aux.getKey()][i] = true;
                }

                aux = aux.getpNext();
            }
        }

        var file = openFile();
        try {
            PrintWriter pw = new PrintWriter(file);
            pw.print(lines);
            pw.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se pudo guardar la información");
        }

    }
    //    public static void saveAdyacentList(Node[] graph){
//        String toTxt = "";
//        for (int i = 0; i<graph.length; i++){
//            toTxt += graph[i].getKey() + ",";
//            for (int j = 0 ;  j < graph[i].adjList.length; j++){
//                toTxt += graph[i].adjList[j] + "\n";
//
//            } 
//        }
//        
//        String path = "";
//        try{
//            PrintWriter pw = new PrintWriter(path);
//            pw.print(toTxt);
//            pw.close();
//        }catch(Exception e){
//            JOptionPane.showMessageDialog(null, "No se pudo guardar la información");
//        }
//        
//    }
}
