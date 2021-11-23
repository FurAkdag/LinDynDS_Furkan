package my_project.view;

import KAGO_framework.control.Interactable;
import KAGO_framework.control.ViewController;
import my_project.control.ProgramController;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
/**
 * Realisiert ein Objekt, dass alle Eingaben empfängt und dann danach passende Methoden
 * im ProgramController aufruft
 */
public class InputReceiver implements Interactable {

    private ProgramController programController;
    private ViewController viewController;

    /**
     * Objekterzeugung
     * @param programController Nötig als Objekt vom Controllerbereich, das informiert wird
     * @param viewController Nötig, um den Aufruf der Interface-Methoden sicherzustellen
     */
    public InputReceiver(ProgramController programController, ViewController viewController){
        this.programController = programController;
        this.viewController = viewController;
        viewController.register(this);
    }


    @Override
    public void keyPressed(int key) {

    }

    @Override
    public void keyReleased(int key) {
        if(key == KeyEvent.VK_N) {
            programController.moveCurrent("Next");
        }else if(key == KeyEvent.VK_F){
            programController.moveCurrent("First");
        }else if(key == KeyEvent.VK_L){
            programController.moveCurrent("Last");
        }else if(key == KeyEvent.VK_A){
            programController.addBallToCurrent();
        }else if(key == KeyEvent.VK_S){
            programController.addBallToLast();
        }else if(key == KeyEvent.VK_R){
            programController.removeBallFromCurrent();
        }else if(key == KeyEvent.VK_C){
            programController.changeCurrent();
        }else if(key == KeyEvent.VK_B){
            programController.returnCurrent();
        }

    }



    @Override
    public void mouseReleased(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1){ // falls die linke Maustaste geklickt wurde...
            programController.addToStack();
        }else if(e.getButton() == MouseEvent.BUTTON3){ // falls eine andere Maustaste geklickt wurde
            programController.deleteFromStack();
        }else{
            programController.changeTop();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }
}
