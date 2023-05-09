package co.edu.umanizales.leds.model;

import lombok.Data;

@Data
public class ListDE {
    private NodeDE head;
    private int size;

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
}