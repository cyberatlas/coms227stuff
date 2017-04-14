package lab10;

import java.awt.*;

/**
 * Created by Ruski on 4/13/2017.
 */
public class Portrait8ADS extends Portrait {
    public Portrait8ADS(){
        super(.10);
        setSpreadEagleness(.50);

    }
    public void paintComponent(Graphics r){
        super.paintComponent(r);

        int midX = getWidth() / 2;

        // Draw a big eye right in the middle of the face.
        int eyeRadius = (int)(0.05 * SIZE);
        r.fillOval(midX - eyeRadius, headRadius - eyeRadius * 2, 2 * eyeRadius,
                2 * eyeRadius);

        // And give him a creepy smile.
        int smileRadius = (int)(0.5 * headRadius);
        r.drawArc(midX - smileRadius, (int)(0.8 * headRadius), smileRadius * 2,
                smileRadius * 2, 0, -180);

    }

}
