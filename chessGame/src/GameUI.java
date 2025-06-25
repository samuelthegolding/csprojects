import javax.swing.*;
import java.awt.event.MouseEvent;

public class GameUI extends JFrame {
    public GameUI() {
        setTitle("Chess Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setVisible(true);
    }

    public void mouseClickHandler(MouseEvent e) {
        int startX = e.getX();
        int startY = e.getY();
        int endX = startX + getWidth();
        int endY = startY + getHeight();

    }
}
