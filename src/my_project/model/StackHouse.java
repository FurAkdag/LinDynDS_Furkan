package my_project.model;

import KAGO_framework.control.ViewController;
import KAGO_framework.model.GraphicalObject;
import KAGO_framework.view.DrawTool;

import java.awt.*;

public class StackHouse extends GraphicalObject {

    private ViewController viewController;
    private boolean top;
    private boolean deleted; // wurde der QueueBall aus der Schlange gelöscht?
    private double x;
    private double y;

    public StackHouse(int x, int y, ViewController viewController){
        this.x = x;
        this.y = y;
        this.viewController = viewController;
        top = true;
        deleted = false;
        viewController.draw(this);
    }

    @Override
    public void draw(DrawTool drawTool) {
        if(top){
            drawTool.setCurrentColor(Color.RED);
            //drawTool.drawFilledCircle(x+20,y+20,20);
            drawTool.drawFilledTriangle(x+20,y-20,x+40,y+40,x,y+40);
        }else {
            drawTool.setCurrentColor(Color.BLACK);
            drawTool.drawFilledRectangle(x,y,40,40);
        }
    }


    @Override
    public void update(double dt){
        if(deleted){
            viewController.removeDrawable(this);
        }
    }

    public boolean tryToDelete(){
        if(top) {
            deleted = true;
            return true;
        }
        return false;

    }

    public void setTop(boolean top){
        this.top = top;
    }


}
