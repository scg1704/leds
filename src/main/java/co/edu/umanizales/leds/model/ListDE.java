package co.edu.umanizales.leds.model;

import lombok.Data;

@Data
public class ListDE {
    private NodeDE head;
    private int size;

    /*
    LÓGICA AÑADIR AL FINAL:
    Entrada:
    El led con los datos que le queremos añadir

    Tenemos datos?
    SI
        Llamamos al ayudante y le decimos que se posicione en la cabeza
        Recorre la lista hasta el final
            Llega al final
        Le decimos al led con los datos que se meta en un nuevo nodo.
        Se acomoda al final y toma al anterior
    NO
        Lo añadimos al inicio como cabeza
     */

    public void addToEnd(Led led){
        if (this.head != null){
            NodeDE temp = this.head;
            while (temp.getNext() != null){
                temp = temp.getNext();
            }
            NodeDE newLed = new NodeDE(led);
            temp.setNext(newLed);
            newLed.setPrev(temp);
        }
        else{
            this.head = new NodeDE(led);
        }
        size++;
    }

    /*
    LÓGICA AÑADIR AL INICIO:
    Entrada:
    El led con los datos que le queremos añadir

    Tenemos datos en la cabeza?
    SI
        Le decimos al led con los datos que se meta en un nuevo nodo
        Le decimos que tome a la cabeza
        Le decimos a la cabeza que lo tome
        Se convierte en la cabeza
    NO
        Lo añadimos al inicio como cabeza
     */

    public void addToStart(Led led){
        if (head != null){
            NodeDE newLed = new NodeDE(led);
            newLed.setNext(head);
            head.setPrev(newLed);
            head = newLed;
        }
        else{
            head = new NodeDE(led);
        }
        size++;
    }

    /*
    LÓGICA ENCENDER LED:
    Entrada:
    La id del led a prender

    Tenemos datos en la cabeza?
    SI
        Llamamos a un ayudante
        Recorremos todos los datos de la lista
        El dato coincide con la entrada?
        SI
            Decimos que está encendido
     */

    public void oneLedOn (int id){
        if (head != null){
            NodeDE temp = this.head;
            while (temp != null){
                temp = temp.getNext();
            }
            if (temp.getNext().getData().getId() == id){
                temp.getData().setStatus(true);
            }
        }
    }

    /*
    LÓGICA APAGAR LED:
    Entrada:
    La id del led a apagar

    Tenemos datos en la cabeza?
    SI
        Llamamos a un ayudante
        Recorremos todos los datos de la lista
        El dato coincide con la entrada?
        SI
            Decimos que está apagado
     */

    public void oneLedOff (int id){
        if (head != null){
            NodeDE temp = this.head;
            while (temp != null){
                temp = temp.getNext();
            }
            if (temp.getNext().getData().getId() == id){
                temp.getData().setStatus(false);
            }
        }
    }

    /*
    LÓGICA ENCENDER TODOS LOS LEDS
    Tenemos datos en la cabeza?
    SI
        Llamamos a un ayudante que se posiciona en la cabeza
        Le decimos que recorra toda la lista
            Le decimos que encienda todos los leds
     */

    public void allLedsOn(){
        if  (head != null){
            NodeDE temp = this.head;
            while (temp != null){
                temp.getData().setStatus(true);
                temp = temp.getNext();
            }
        }
    }

    /*
    LÓGICA ENCENDER TODOS LOS LEDS
    Tenemos datos en la cabeza?
    SI
        Llamamos a un ayudante que se posiciona en la cabeza
        Le decimos que recorra toda la lista
            Le decimos que apague todos los leds
     */

    public void allLedsOff(){
        if (head != null){
            NodeDE temp = this.head;
            while (temp != null) {
                temp.getData().setStatus(false);
                temp = temp.getNext();
            }
        }
    }

    /*
    LÓGICA LLEGAR A LA MITAD DE LA LISTA
    La cabeza tiene datos?
    SI
        Llamamos a un ayudante y le decimos que se posicione en la cabeza
        Llamamos a otro ayudante que se va a situar también en la cabeza
        Se recorre la lista. El primer ayudante avanza un nodo a la vez, y el segundo avanza dos nodos a la vez
            El primer ayudante se encuentra en la mitad de la lista
            El segundo ayudante se encuentra al final de la lista
        La lista es par?
        SI
            Le decimos que nos imprima el nodo donde se encuentra, y el siguiente
        NO
            Le decimos que imprima el nodo siguiente

     NOTA: Este método va a ser FUNDAMENTAL a futuro.
     */

    public void midLeds(){
        if (head != null){
            NodeDE temp = this.head;
            NodeDE temp2 = this.head;
            while (temp != null && temp2.getNext() != null) {
                temp = temp.getNext();
                temp2 = temp2.getNext().getNext();
            }
            if (size % 2 == 0){
                System.out.println((temp.getData().getId()) + (temp.getNext().getData().getId()));
            }
            else{
                System.out.println((temp.getNext().getData().getId()));
            }
        }
    }
}