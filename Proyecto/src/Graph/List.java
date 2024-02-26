/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Graph;

/**
 * Clase que representa una lista simplemente enlazada.
 * @author Giselle Esclasans
 */
public class List {
    private Nodo pFirst;
    private Nodo pLast;
    private int size;
    
     /**
     * Constructor de la clase List.
     * Inicializa la lista con valores predeterminados.
     */
    public List() {
        pFirst = null;
        pLast = null;
        size = 0;
    }

    
    /**
     * Inserta un nuevo nodo al final de la lista con un valor y peso dados.
     * 
     * @param value El valor del nodo a insertar.
     * @param weight El peso del nodo a insertar.
     */
    public void insertEnd(int value, double weigth) {
        Nodo pNode = new Nodo(value, weigth);
        
        if (size == 0){
            pFirst = pLast = pNode;
        } else{
            pLast.setpNext(pNode);
            pLast = pNode;
        }
        size++;
    }
    
    /**
     * Inserta un nuevo nodo al final de la lista con un valor y peso dados.
     * 
     * @param value El valor del nodo a insertar.
     * @param weight El peso del nodo a insertar.
     */
    public void insertEndG(int value, int weigth) {
        Nodo pNode = new Nodo(value, weigth);
        
        if (size == 0){
            pFirst = pLast = pNode;
        } else{
            pLast.setpNext(pNode);
            pLast = pNode;
        }
        size++;
    }
    
     /**
     * Inserta un nuevo nodo al final de la lista con un valor dado y peso predeterminado.
     * 
     * @param value El valor del nodo a insertar.
     */
    public void insertEndN(int value) {
        Nodo pNode = new Nodo(value);
        
        if (size == 0){
            pFirst = pLast = pNode;
        } else{
            pLast.setpNext(pNode);
            pLast = pNode;
        }
        size++;
    }
    
    /**
     * Elimina un nodo de la lista que contiene el valor especificado.
     * 
     * @param value El valor del nodo a eliminar.
     * @return El valor del nodo eliminado, o 0 si el valor no se encuentra en la lista.
     */
    public int removeValue(int value) {
        if(size == 0) {
            return 0;
        } 
        for(Nodo pANode = pFirst, pPrev = null; pANode != null; pPrev = pANode, pANode = pANode.getpNext()){
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
    
    /**
     * Busca un valor en la lista.
     * 
     * @param value El valor a buscar.
     * @return true si el valor se encuentra en la lista, false en caso contrario.
     */
    public boolean findValue(int value){
        if(pFirst.getKey() == value){
            return true;
        }
        
        for(Nodo pANode = pFirst; pANode != null; pANode = pANode.getpNext()){
            if (java.util.Objects.equals(pANode.getKey(), value)) {
                return true;
            }  
        }
        return false;
    }
    
    /**
     * Obtiene el valor de un nodo en la posici&oacuten especificada en la lista.
     * 
     * @param index La posici&oacuten del nodo en la lista.
     * @return El valor del nodo en la posici&oacuten especificada.
     * @throws IndexOutOfBoundsException Si el &iacutendice está fuera de rango.
     */
    public int getValue(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index");
        }

        Nodo currentNode = pFirst;
        int currentIndex = 0;

        while (currentIndex < index) {
            currentNode = currentNode.getpNext();
            currentIndex++;
        }

        return currentNode.getKey();
    }
    
    /**
     * Imprime los valores de todos los nodos en la lista.
     */
    public void printList() {
    Nodo currentNode = pFirst;

    while (currentNode != null) {
        System.out.print(currentNode.getKey() + " ");
        currentNode = currentNode.getpNext();
    }

    System.out.println();
    }
    
     /**
     * Elimina todos los nodos de la lista, dej&aactendola vac&iacutea.
     */
    public void clear() {
        pFirst = null;
        pLast = null;
        size = 0;
    }
    
    
    
    
    
    
    /**
     * @return the pFirst
     */
    public Nodo getpFirst() {
        return pFirst;
    }

    /**
     * @param pFirst the pFirst to set
     */
    public void setpFirst(Nodo pFirst) {
        this.pFirst = pFirst;
    }

    /**
     * @return the pLast
     */
    public Nodo getpLast() {
        return pLast;
    }

    /**
     * @param pLast the pLast to set
     */
    public void setpLast(Nodo pLast) {
        this.pLast = pLast;
    }

    /**
     * @return the size
     */
    public int getSize() {
        return size;
    }
    
    
}
