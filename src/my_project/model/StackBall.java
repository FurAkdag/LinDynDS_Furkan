package my_project.model;

import KAGO_framework.control.ViewController;
import KAGO_framework.model.GraphicalObject;
import KAGO_framework.view.DrawTool;

public class StackBall extends GraphicalObject {

    private ViewController viewController;
    private StackBall previousStackBall; // Vorgänger des QueueBalls
    private boolean head;
    private boolean arrived;// hat der QueueBall den Anfang der Schlange erreicht?
    private boolean deleted; // wurde der QueueBall aus der Schlange gelöscht?
    private double x;
    private double y;

    public StackBall(int x, int y,boolean head, StackBall previousStackBall, ViewController viewController){
        this.x = x;
        this.y = y;
        this.previousStackBall = previousStackBall;
        this.viewController = viewController;
        this.head = head;
        deleted = false;
        arrived = false;
        viewController.draw(this);
    }

    @Override
    public void draw(DrawTool drawTool) {
        if(head && arrived){
            drawTool.drawFilledCircle(x,y,20);
        }else {
            drawTool.drawCircle(x, y, 20);
        }
    }
    @Override
    public void update(double dt){
        if(!arrived){
            if(previousStackBall == null || y > previousStackBall.getY() + 50) y -= dt*100;
            if(previousStackBall == null) {
                if(y < 50) arrived = true;
            }else if(y >= previousStackBall.getY() - 50) arrived = true;
        }
        if(deleted){
            x += 300*dt;
            if(x > 600) viewController.removeDrawable(this);
        }
    }

    public boolean tryToDelete(){
        if(head && arrived) {
            deleted = true;
            return true;
        }
        return false;

    }

    public void setHead(boolean head){
        this.head = head;
    }

    public boolean isHead() {
        return head;
    }

    public boolean isArrived() {
        return arrived;
    }

    public void setPreviousStackBall(StackBall previousStackBall) {
        this.previousStackBall = previousStackBall;
    }
}
