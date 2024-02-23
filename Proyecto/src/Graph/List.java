/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Graph;

/**
 *
 * @author gigie
 */
public class List {
    private Node pFirst;
    private Node pLast;
    private int size;
    
    
    public List() {
        pFirst = null;
        pLast = null;
        size = 0;
    }

    public void insertEnd(int value, double weigth) {
        Node pNode = new Node(value, weigth);
        
        if (size == 0){
            pFirst = pLast = pNode;
        } else{
            pLast.setpNext(pNode);
            pLast = pNode;
        }
        size++;
    }
    
    public void insertEndG(int value, int weigth) {
        Node pNode = new Node(value, weigth);
        
        if (size == 0){
            pFirst = pLast = pNode;
        } else{
            pLast.setpNext(pNode);
            pLast = pNode;
        }
        size++;
    }
    
    public int removeValue(int value) {
        if(size == 0) {
            return 0;
        } 
        for(Node pANode = pFirst, pPrev = null; pANode != null; pPrev = pANode, pANode = pANode.getpNext()){
            if (java.util.Objects.equals(pANode.getKey(), value)) {
                if (pANode == pFirst){
                    pFirst = pANode.getpNext();
                }else {
                    pPrev.setpNext(pANode.getpNext());
                }
                if (pANode == getpLast()){
                    pLast = pPrev;
                }
                size--;
                return pANode.getKey();
            }
        }
        return 0; 
            
    }
    
    public boolean findValue(int value){
        for(Node pANode = pFirst; pANode != null; pANode = pANode.getpNext()){
            if (java.util.Objects.equals(pANode.getKey(), value)) {
                return true;
            }  
        }
        return false;
    }
    
    public int getValue(int value){
        for(Node pANode = pFirst; pANode != null; pANode = pANode.getpNext()){
            if (java.util.Objects.equals(pANode.getKey(), value)) {
                return pANode.getKey();
            }  
        }
        return 0;
    }
    
    
    
    
    
    
    
    
    /**
     * @return the pFirst
     */
    public Node getpFirst() {
        return pFirst;
    }

    /**
     * @param pFirst the pFirst to set
     */
    public void setpFirst(Node pFirst) {
        this.pFirst = pFirst;
    }

    /**
     * @return the pLast
     */
    public Node getpLast() {
        return pLast;
    }

    /**
     * @param pLast the pLast to set
     */
    public void setpLast(Node pLast) {
        this.pLast = pLast;
    }

    /**
     * @return the size
     */
    public int getSize() {
        return size;
    }
    
    
}
