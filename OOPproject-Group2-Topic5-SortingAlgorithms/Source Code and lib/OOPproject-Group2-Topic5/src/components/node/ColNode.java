package components.node;

import javafx.animation.TranslateTransition;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class ColNode extends Rectangle{
    //Uhm...This class was created initially to contain the custom attribute 'Value'. But I changed my mind about it so...
    //This class is just here to chill
    public TranslateTransition moveX(double x) {
        TranslateTransition t = new TranslateTransition();
        t.setNode(this);
        t.setDuration(Duration.millis(100));
        t.setByX(x);
        return t;
    }
}
