package my_project.model;

import KAGO_framework.control.ViewController;
import KAGO_framework.model.GraphicalObject;
import KAGO_framework.view.DrawTool;

import java.awt.*;

public class NullPointer extends GraphicalObject {

    private boolean current;
    private ViewController viewController;

    public NullPointer(boolean current, ViewController viewController){
        this.current = current;
        this.viewController = viewController;
        viewController.draw(this);
    }

    @Override
    public void draw(DrawTool drawTool) {
        if(current){
            drawTool.setCurrentColor(Color.black);
            drawTool.drawText(100,100,"null");
        }

    }

    public void setCurrent(boolean current) {
        this.current = current;
    }


}
