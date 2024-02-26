package Txt;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import Graph.LAGraph;
import Graph.List;
import Graph.Nodo;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 * Clase que contiene diferenctes funciones para manipular los archivos de tipo txt.
 *
 * @author Isabella Leizaola
 */
public class Functions {

    /**
     * Almacena el contenido de un archivo de texto en forma de String.
     *
     * @param file El archivo de texto del que se desea cargar el contenido.
     * @return El contenido del archivo de texto convertido en un objeto String.
     * @throws Excepcion si no se puede leer el metodo
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
            
        } catch (Exception e) {
            text = "";
            JOptionPane.showMessageDialog(null, "Error al leer el archivo");
        }

        return text;
    }

    /**
     * Abre un archivo de texto mediante un diálogo de selección.
     *
     * @return El archivo de texto seleccionado por el usuario.
     * @throws Exception si no se puede leer el archivo.
     */
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
     * @return El grafo cargado desde el archivo.
     */

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
            
            for (int l = 0; l < nodeSplit.length; l++) {
                int nodeKey = Integer.parseInt(nodeSplit[0].trim()) - 1;
                int nodePnext = Integer.parseInt(nodeSplit[1].trim()) - 1;
                double nodeWeigth = Double.parseDouble(nodeSplit[2].trim());
                System.out.println("sirvio 1");
                graph.insertEdge(nodeKey, nodePnext, nodeWeigth);
            }
            

        }
        
        graph.printGraph();
        return graph;
    }

    /**
     * Guarda la lista de adyacencia de un grafo en un archivo de texto.
     *
     * @param graph El grafo cuya lista de adyacencia se desea guardar.
     * @throws Exception si no se pudo guardar la informaci&oacuten
     */
    public static void writeGraph(LAGraph graph) {
        System.out.println("write");
        String lines = "ciudad\n";
        for (int i = 0; i < graph.getVertexNum(); i++) {
            lines += i + 1 + "\n";
        }
        lines += "aristas\n";
        Boolean[][] printed = new Boolean[graph.size()][graph.size()];
        System.out.println("write");
        for (int i = 0; i < graph.getVertexNum(); i++) {
            List lista = graph.getAdjList()[i];
            Nodo aux = lista.getpFirst();
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
