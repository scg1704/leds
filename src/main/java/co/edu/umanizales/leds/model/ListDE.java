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

    public void allLedsOn(){
        if  (head != null){
            NodeDE temp = this.head;
            while (temp != null){
                temp.getData().setStatus(true);
                temp = temp.getNext();
            }
        }
    }

    public void allLedsOff(){
        if (head != null){
            NodeDE temp = this.head;
            while (temp != null) {
                temp.getData().setStatus(false);
                temp = temp.getNext();
            }
        }
    }

    public void midLeds(){
        if (head != null){
            NodeDE temp = this.head;
            int quant = 0;
            while (temp.getNext() != null){
                quant++;
                temp = temp.getNext();
            }
            if (quant % 2 == 0){
                temp.getData().getId();
                temp.getNext().getData().getId();
            }
            else{
                temp.getNext().getData().getId();
            }
        }
    }
}