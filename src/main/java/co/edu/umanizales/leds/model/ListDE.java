package co.edu.umanizales.leds.model;

import lombok.Data;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class ListDE {
    private NodeDE head;
    private int size;
    private List<Led> leds = new ArrayList<>();

    /*
    LÓGICA MÉTODO AÑADIR:
    Hacemos lo mismo que en añadir al final
     */

    public void addLed(Led led) {
        if (head == null) {
            head = new NodeDE(led);
        } else {
            NodeDE newNode = new NodeDE(led);
            NodeDE current = head;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(newNode);
            newNode.setPrev(current);
        }
        size++;
    }

    /*
    LÓGICA MÉTODO PARA IMPRIMIR LA LISTA:
    Llamamos a la lista de leds
    Le decimos que si tiene elementos pase por toda la lista con ayuda de un temporal y los añada
     */

    public List <Led> print (){
        leds.clear();
        if (head != null){
            NodeDE temp = head;
            while (temp != null){
                leds.add(temp.getData());
                temp =temp.getNext();
            }
        }
        return leds;
    }

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
        Recorremos la lista hasta llegar al dato que coincide con la entrada
            Decimos que está encendido
     */

    public void oneLedOn (int id){
        if (head != null){
            NodeDE temp = this.head;
            while (temp != null){
                if (temp.getData().getId() == id){
                    temp.getData().setStatus(true);
                    break;
                }
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
        Recorremos la lista hasta llegar al dato que coincide con la entrada
            Decimos que está encendido
     */

    public void oneLedOff (int id){
        if (head != null){
            NodeDE temp = this.head;
            while (temp != null){
                if (temp.getData().getId() == id){
                    temp.getData().setStatus(false);
                    break;
                }
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
    LÓGICA APAGAR TODOS LOS LEDS
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
            El segundo ayudante se encuentra al final de la lista. Si salta 2, y no hay 2, nada más va a saltar 1
        La lista es par?
        SI
            Le decimos que los nodos de la mitad se enciendan
        NO
            Le decimos que el nodo de la mitad se encienda
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
                temp.getData().setStatus(true);
                temp.getNext().getData().setStatus(true);
            }
            else{
                temp.getData().setStatus(true);
            }
        }
    }

    /*
    LÓGICA MÉTODO REINICIAR NORMAL
    Tenemos datos?
    SI
        Le decimos a todos los leds que se apaguen
        Le decimos a todos los leds que se enciendan
     */

    public void simpleRestart(){
        if (head != null){
            allLedsOff();
            allLedsOn();
        }
    }

    /*
    LÓGICA MÉTODO SLEEP
    Tiene datos?
    SI
        Llamamos un ayudante
        Recorremos la lista
            Nos paramos en un nodo
            Le decimos que encienda el led, y guarde el tiempo
            Le decimos que apague el led, y guarde el tiempo
            Vamos al siguiente nodo y repetimos el mismo proceso en toda la lista
     */

    public void sleep(){
        if (head != null){
            NodeDE temp = head;
            while (temp != null){
                temp.getData().setStatus(true);
                temp.getData().setDateOn(LocalTime.from(LocalTime.now()));
                try{
                    Thread.sleep(1000);
                }
                catch (InterruptedException e){
                    e.printStackTrace();
                }
                temp.getData().setStatus(false);
                temp.getData().setDateOff(LocalTime.from(LocalTime.now()));
                try{
                    Thread.sleep(1000);
                }
                catch (InterruptedException e){
                    e.printStackTrace();
                }
                temp = temp.getNext();
            }
        }
    }

    /*
    Después de un análisis me di cuenta que para realizar el ejercicio propuesto me queda un poco complicado reutilizar algunos de los métodos
    que realicé con anterioridad ya que hay ciertas cosas que cambian y necesitan una mayor complejidad. Aún así los voy a dejar ya que nos pueden
    ayudar en futuras ocasiones.

    NOTA: El método sleep me sirve como una gran base para
     */

    /*
    LÓGICA MÉTODO LUCES ENCENDER Y APAGAR DESDE LA MITAD:
    Tenemos datos?
    SI
        Termina en número par la lista?
        SI
            Sacamos la mitad
            Creamos una variable que toma el número de pasos
            Llamamos a un ayudante
            Recorremos la lista
                Decimos que el número de pasos a dar sea igual a la mitad, y al siguiente
                    Establecemos que los leds se encendieron y tomamos el tiempo
                    Llamamos a otro ayudante que se va devolver
                    Recorremos la lista
                        Decimos que se encienda, y se apague despues de 1 segundo a cada nodo
                Aumentamos el número de pasos, y pasamos al siguiente nodo para saber si posee las condiciones
        NO
            Sacamos la mitad y le sumamos 1
            Creamos una variable que toma el número de pasos
            Llamamos a un ayudante
            Recorremos la lista
                Decimos que los pasos recorridos vayan hasta la mitad
                Encendemos el led y tomamos el tiempo
                Llamamos al otro ayudante que se va a devolver
                Recorremos la lista
                        Decimos que se encienda, y se apague despues de 1 segundo a cada nodo
                Aumentamos el número de pasos, y pasamos al siguiente nodo para saber si posee las condiciones
     */

    public  void turnLightsHalf(){
        if (head != null) {
            NodeDE temp = head;
            int pasos = 1;
            int medium;
            if ((size%2) != 0){
                medium = (size/2) + 1;
                while (temp!= null){

                    if (pasos == medium){
                        NodeDE tempNext = temp;
                        temp.getData().setStatus(true);
                        temp.getData().setDateOn(LocalTime.from(LocalDateTime.now()));

                        while (tempNext.getNext() != null){

                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                            temp.getData().setStatus(false);
                            temp.getData().setDateOff(LocalTime.from(LocalDateTime.now()));
                            tempNext.getData().setStatus(false);
                            tempNext.getData().setDateOff(LocalTime.from(LocalDateTime.now()));

                            temp = temp.getPrev();
                            tempNext= tempNext.getNext();

                            temp.getData().setStatus(true);
                            temp.getData().setDateOn(LocalTime.from(LocalDateTime.now()));
                            tempNext.getData().setStatus(true);
                            tempNext.getData().setDateOn(LocalTime.from(LocalDateTime.now()));
                        }
                    }
                    pasos++;
                    temp= temp.getNext();
                }
            } else
            {
                medium = size/2;
                while (temp!= null){
                    if (pasos == medium){
                        NodeDE tempNext = temp.getNext();
                        temp.getData().setStatus(true);
                        temp.getData().setDateOn(LocalTime.from(LocalDateTime.now()));
                        tempNext.getData().setStatus(true);
                        tempNext.getData().setDateOn(LocalTime.from(LocalDateTime.now()));

                        while (tempNext.getNext() != null) {

                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                            temp.getData().setStatus(false);
                            temp.getData().setDateOff(LocalTime.from(LocalDateTime.now()));
                            tempNext.getData().setStatus(false);
                            tempNext.getData().setDateOff(LocalTime.from(LocalDateTime.now()));

                            temp = temp.getPrev();
                            tempNext = tempNext.getNext();

                            temp.getData().setStatus(true);
                            temp.getData().setDateOn(LocalTime.from(LocalDateTime.now()));
                            tempNext.getData().setStatus(true);
                            tempNext.getData().setDateOn(LocalTime.from(LocalDateTime.now()));


                        }
                    }
                    pasos++;
                    temp= temp.getNext();

                }

            }

        }

    }
}
