/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Graph;

/**
 * Clase que representa un nodo en una lista enlazada.
 * Cada nodo contiene una clave, un peso y una referencia al siguiente nodo.
 * La clase proporciona m&eacute;todos para acceder y modificar estos atributos.
 * Adem&aacute;s, incluye un m&eacute;todo toString para representar el nodo como una cadena de texto.
 * 
 * @author Giselle Esclasans
 */
public class Nodo {
    private int key;
    private double weigth;
    private Nodo pNext;
    
    /**
     * Constructor de la clase Nodo.
     * Crea un nodo con la clave y el peso especificados.
     * 
     * @param key La clave del nodo.
     * @param w El peso del nodo.
     */
    public Nodo (int data, double w){
        key = data;
        weigth = w;
        pNext = null;
    }
    
     /**
     * Constructor de la clase Nodo.
     * Crea un nodo con la clave especificada.
     * 
     * @param key La clave del nodo.
     */
    public Nodo (int data){
        key = data;
        pNext = null;
    }

    /**
     * Constructor de la clase Nodo.
     * Crea un nodo con la clave, el peso y la referencia al siguiente nodo especificados.
     * 
     * @param key La clave del nodo.
     * @param weigth El peso del nodo.
     * @param pNext La referencia al siguiente nodo.
     */
    public Nodo(int key, double weigth, Nodo pNext) {
        this.key = key;
        this.weigth = weigth;
        this.pNext = pNext;
    }
    
    

    /**
     * Obtiene la clave del nodo.
     * @return the key
     */
    public int getKey() {
        return key;
    }

    /**
     * Establece la clave del nodo.
     * @param key the key to set
     */
    public void setKey(int key) {
        this.key = key;
    }

    /**
     * Obtiene la referencia al siguiente nodo.
     * @return the pNext
     */
    public Nodo getpNext() {
        return pNext;
    }

    /**
     * Establece la referencia al siguiente nodo.
     * @param pNext the pNext to set
     */
    public void setpNext(Nodo pNext) {
        this.pNext = pNext;
    }

    /**
     * Obtiene el peso del nodo.
     * @return the weigth
     */
    public double getWeigth() {
        return weigth;
    }

    /**
     * Establece el peso del nodo.
     * @param weigth the weigth to set
     */
    public void setWeigth(double weigth) {
        this.weigth = weigth;
    }
    
    /**
     * Representacion en forma de cadena del nodo.
     * Devuelve la clave y el peso del nodo como una cadena de texto.
     * 
     * @return La representacion en forma de cadena del nodo.
     */
    @Override
    public String toString() {
        return  key + 1 + ", " + getWeigth();
    }
    
     /**
     * Agrega un peso adicional al nodo.
     * 
     * @param w El peso adicional a agregar.
     */
    public void addWeight(double w) {
        this.weigth = this.weigth + w;
    }
    
}
