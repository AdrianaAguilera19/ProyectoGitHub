/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Graph;

/**
 *
 * @author gigie
 */
public class Node {
    private int key;
    private double weigth;
    private Node pNext;
    
    
    public Node (int data, double w){
        key = data;
        weigth = w;
        pNext = null;
    }

    public Node(int key, double weigth, Node pNext) {
        this.key = key;
        this.weigth = weigth;
        this.pNext = pNext;
    }
    
    

    /**
     * @return the key
     */
    public int getKey() {
        return key;
    }

    /**
     * @param key the key to set
     */
    public void setKey(int key) {
        this.key = key;
    }

    /**
     * @return the pNext
     */
    public Node getpNext() {
        return pNext;
    }

    /**
     * @param pNext the pNext to set
     */
    public void setpNext(Node pNext) {
        this.pNext = pNext;
    }

    /**
     * @return the weigth
     */
    public double getWeigth() {
        return weigth;
    }

    /**
     * @param weigth the weigth to set
     */
    public void setWeigth(int weigth) {
        this.weigth = weigth;
    }

    @Override
    public String toString() {
        return  key + 1 + ", " + getWeigth();
    }
    
    
}
