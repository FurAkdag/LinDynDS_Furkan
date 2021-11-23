package my_project.model;

import KAGO_framework.control.ViewController;
import KAGO_framework.model.GraphicalObject;
import KAGO_framework.view.DrawTool;

import java.awt.*;

public class ListBall extends GraphicalObject {

    private ViewController viewController;
    private int number;
    private double y;
    private boolean current;
    private double l;
    private boolean transform;

    public ListBall(int number, ViewController viewController){
        this.viewController = viewController;
        this.number = number;
        current = false;
        viewController.draw(this);
        y = 500 - 20*number;
        l = 10;
        transform = false;
    }

    @Override
    public void draw(DrawTool drawTool) {
        if(current){
            drawTool.setCurrentColor(Color.YELLOW);
            drawTool.drawFilledCircle(300,y,7);
        }else{
            drawTool.setCurrentColor(Color.BLACK);
            if(transform){
                double r = l/2;
                drawTool.drawFilledRectangle(300-r,y-10,l,20);
            }
            drawTool.drawFilledCircle(300,y,10);
        }
    }

    /**
     * Wird mit jeder Frame vom Framework aufgerufen und dient zur Manipulation des Objekts im Verlauf
     * der Zeit.
     * @param dt die Sekunden, die seit dem letzten Aufruf von update vergangen sind
     */
    @Override
    public void update(double dt){


    }

    public void delete(){
        viewController.removeDrawable(this);
    }

    public void setCurrent(boolean current) {
        this.current = current;
    }

    public void changeBall(){
        transform = true;
        l += 5;
    }

    public void getBack(){
        l = 10;
        transform = false;
    }

    public boolean isTransform() {
        return transform;
    }

    public void addNumber() {
        number++;
        y = 500 - 20*number;
    }

    public void decreaseNumber(){
        number--;
        y = 500 - 20*number;
    }

    public int getNumber(){
        return number;
    }
}
