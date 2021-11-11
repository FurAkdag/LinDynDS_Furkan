package my_project.model;

import KAGO_framework.control.ViewController;
import KAGO_framework.model.GraphicalObject;
import KAGO_framework.view.DrawTool;

public class StackBall extends GraphicalObject {

    private ViewController viewController;
    private StackBall previousStackBall; // Vorgänger des QueueBalls
    private boolean arrived; // hat der QueueBall den Anfang der Schlange erreicht?
    private boolean deleted; // wurde der QueueBall aus der Schlange gelöscht?

    public StackBall(double x, double y, StackBall previousStackBall, ViewController viewController){
        this.x = x;
        this.y = y;
        this.previousStackBall = previousStackBall;
        this.viewController = viewController;
        arrived = false;
        deleted = false;
        viewController.draw(this);
    }

    @Override
    public void draw(DrawTool drawTool) {
        drawTool.drawCircle(x,y,20);
    }

    public void update(double dt){
        if(!arrived){
            if(previousStackBall == null || x > previousStackBall.getY()+50) y -= 100*dt;
            if (y < 100) arrived = true;
        }
        if(deleted){
            y -= 200*dt;
            if(y < -25) viewController.removeDrawable(this);
        }
    }

    public boolean tryToDelete(){
        if(arrived){
            deleted = true;
            return deleted;
        }
        return false;
    }
}
