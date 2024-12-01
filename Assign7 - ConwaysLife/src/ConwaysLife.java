import edu.usu.graphics.Color;
import edu.usu.graphics.Graphics2D;
import edu.usu.graphics.Rectangle;
import edu.usu.graphics.Texture;

import static org.lwjgl.glfw.GLFW.*;

public class ConwaysLife {
    private static final int SIZE_X = 75;
    private static final int SIZE_Y = 75;

    public static void main(String[] args) {
        try (Graphics2D graphics = new Graphics2D(1000, 1000, "Conway's Game of Life")) {
            graphics.initialize(Color.BLACK);
            Texture texSquare = new Texture("resources/images/square-outline.png");

            LifeSimulator simulation = new LifeSimulator(SIZE_X, SIZE_Y);
            simulation.insertPattern(new PatternBlock(), 0, 0);
            simulation.insertPattern(new PatternAcorn(), 20, 40);
            simulation.insertPattern(new PatternBlinker(), 40, 40);
            simulation.insertPattern(new PatternGlider(), 10, 10);

            boolean done = false;
            while (!done) {
                render(graphics, texSquare, simulation);
                Thread.sleep(50);
                simulation.update();

                // Poll for window events: required in order for window, keyboard, etc. events are captured.
                glfwPollEvents();

                if (glfwGetKey(graphics.getWindow(), GLFW_KEY_ESCAPE) == GLFW_PRESS) {
                    done = true;
                }
            }
        } catch (Exception ex) {
            System.out.println("Something bad happened: " + ex.getMessage());
        }
    }

    public static void render(Graphics2D graphics, Texture texSquare, LifeSimulator simulation) {
        final float AREA_SIZE = 1.5f;
        final float CELL_SIZE = AREA_SIZE / SIZE_X;
        final float BOUNDARY_LEFT = -0.75f;
        final float BOUNDARY_TOP = -0.75f;

        graphics.begin();
        Rectangle rBackground = new Rectangle(BOUNDARY_LEFT, BOUNDARY_TOP, AREA_SIZE, AREA_SIZE);
        graphics.draw(rBackground, new Color(0, 0, 0.25f));

        for (int i = 0; i < simulation.getSizeX(); i++) {
            for (int j = 0; j < simulation.getSizeY(); j++) {
                // Only Draw the White Rectangle if the cell returns true
                if (simulation.getCell(i, j)) {
                    Rectangle r = new Rectangle(
                            BOUNDARY_LEFT + CELL_SIZE * i,
                            BOUNDARY_TOP + CELL_SIZE * j,
                            CELL_SIZE, CELL_SIZE);
                    graphics.draw(texSquare, r, Color.WHITE);

                }
            }
        }
        graphics.end();

    }

}
