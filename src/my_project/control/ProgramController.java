package my_project.control;

import KAGO_framework.control.ViewController;
import KAGO_framework.model.abitur.datenstrukturen.Queue;
import KAGO_framework.model.abitur.datenstrukturen.Stack;
import KAGO_framework.model.abitur.datenstrukturen.List;
import my_project.model.ListBall;
import my_project.model.NullPointer;
import my_project.model.QueueBall;
import my_project.model.StackHouse;
import my_project.view.InputReceiver;

import java.awt.event.MouseEvent;

/**
 * Ein Objekt der Klasse ProgramController dient dazu das Programm zu steuern. Die updateProgram - Methode wird
 * mit jeder Frame im laufenden Programm aufgerufen.
 */
public class ProgramController {

    //Attribute


    // Referenzen
    private ViewController viewController;  // diese Referenz soll auf ein Objekt der Klasse viewController zeigen. Über dieses Objekt wird das Fenster gesteuert.
    private Queue<QueueBall> ballQueue;
    private Stack<StackHouse> houseStack;
    private List<ListBall> ballList;
    private QueueBall lastBallinQueue;
    private StackHouse previouseStack;
    private NullPointer nullPointer;
    private int y;
    private int number;
    /**
     * Konstruktor
     * Dieser legt das Objekt der Klasse ProgramController an, das den Programmfluss steuert.
     * Damit der ProgramController auf das Fenster zugreifen kann, benötigt er eine Referenz auf das Objekt
     * der Klasse viewController. Diese wird als Parameter übergeben.
     * @param viewController das viewController-Objekt des Programms
     */
    public ProgramController(ViewController viewController){
        this.viewController = viewController;
    }

    /**
     * Diese Methode wird genau ein mal nach Programmstart aufgerufen.
     * Sie erstellt die leeren Datenstrukturen, zu Beginn nur eine Queue
     */
    public void startProgram() {
        new InputReceiver(this,viewController);
        ballQueue = new Queue<>();
        houseStack = new Stack<>();
        ballList = new List<>();
        nullPointer = new NullPointer(true,viewController);
        previouseStack = null;
        lastBallinQueue = null;
        y = 350;
        number = 0;
    }

    public void moveCurrent(String location) {
        if (!ballList.isEmpty()){
            nullPointer.setCurrent(false);
            switch (location) {
                case "Next":
                        if (ballList.hasAccess()) {
                            ballList.getContent().setCurrent(false);
                            ballList.next();
                            if (ballList.hasAccess()) {
                                ballList.getContent().setCurrent(true);
                            }else{
                                nullPointer.setCurrent(true);
                            }
                        }else {
                            ballList.toFirst();
                            ballList.getContent().setCurrent(true);
                        }
                    break;
                case "First":
                    if(!ballList.isEmpty()){
                        if(ballList.hasAccess()) ballList.getContent().setCurrent(false);
                        ballList.toFirst();
                        ballList.getContent().setCurrent(true);
                    }
                    break;
                case "Last":
                    if(!ballList.isEmpty()){
                        if(ballList.hasAccess()) ballList.getContent().setCurrent(false);
                        ballList.toLast();
                        ballList.getContent().setCurrent(true);
                    }

                    break;
            }
        }
    }



    public void addBallToCurrent(){

        if(!ballList.isEmpty() && ballList.hasAccess()){
            ListBall newListBall = new ListBall(ballList.getContent().getNumber(),viewController);
            ListBall tmp = ballList.getContent();
            ballList.insert(newListBall);
            while(ballList.hasAccess()){
                ballList.getContent().addNumber();
                ballList.next();
            }
            ballList.toFirst();
            while(ballList.getContent() != tmp){
                ballList.next();
            }
            number++;
        }

    }

    public void addBallToLast() { //Done!

            ListBall newListBall = new ListBall(number, viewController);
            ballList.append(newListBall);
            number++;

    }

    public void removeBallFromCurrent(){//Done??
        if(ballList.hasAccess()) ballList.getContent().setCurrent(false);
        if(ballList.hasAccess()) {
            ballList.getContent().delete();
            ballList.remove();

            ListBall tmp = ballList.getContent();

            if (tmp != null) {
                while(ballList.hasAccess()){
                    ballList.getContent().decreaseNumber();
                    ballList.next();
                }
                ballList.toFirst();
                while(ballList.getContent() != tmp){
                    ballList.next();
                }
            }else{
                nullPointer.setCurrent(true);
            }
            number--;
        }

        if(ballList.hasAccess()) ballList.getContent().setCurrent(true);

    }

    public void changeCurrent(){
        if(ballList.hasAccess()) ballList.getContent().changeBall();
    }

    public void returnCurrent(){
        if(ballList.hasAccess()){
            if(ballList.getContent().isTransform()){
                ballList.getContent().getBack();
            }
        }
    }


    public void addBallToQueue(){
        QueueBall newQueueBall = new QueueBall(650,50,lastBallinQueue,viewController);
        ballQueue.enqueue(newQueueBall);
        lastBallinQueue = newQueueBall;
    }

    public void deleteBallFromQueue(){
        if(!ballQueue.isEmpty()){
            if(ballQueue.front().tryToDelete()) ballQueue.dequeue();
        }
    }

    public void addToStack(){
        StackHouse newStackHouse = new StackHouse(300,y, viewController);
        if(!houseStack.isEmpty()) {
            houseStack.top().setTop(false);
        }
        houseStack.push(newStackHouse);
        previouseStack = newStackHouse;
        y -= 40;
    }

    public void deleteFromStack(){
        if(!houseStack.isEmpty()){
            if(houseStack.top().tryToDelete()) {
                houseStack.pop();
                if(!houseStack.isEmpty()) {
                    houseStack.top().setTop(true);
                }
                y += 40;

            }
        }
    }

    public void changeTop(){
        if(!houseStack.isEmpty()) {
                if(!houseStack.top().isDidStuff()) {
                    y += 40;
                }
            houseStack.top().doStuff();
        }

    }

    /**
     * Aufruf bei Mausklick
     * @param e das Objekt enthält alle Informationen zum Klick
     */
    public void mouseClicked(MouseEvent e){

    }


    /**
     * Aufruf mit jeder Frame
     * @param dt Zeit seit letzter Frame
     */
    public void updateProgram(double dt){

    }
}
