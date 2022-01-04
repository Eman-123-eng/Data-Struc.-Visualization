/*
This class is for testing
*/


import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.MouseInputAdapter;


class LinesRectsOvals {
    // execute application
    public static void main(String[] args) {
new DrawingArea();
    }


    private static void createAndShowGUI() {
        DrawingArea drawingArea = new DrawingArea();
        ButtonPanel buttonPanel = new ButtonPanel(drawingArea);

        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("Draw On Component");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(drawingArea);
        frame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        frame.setSize(400, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

class ButtonPanel extends JPanel {
    private DrawingArea drawingArea;

    public ButtonPanel(DrawingArea drawingArea) {
        this.drawingArea = drawingArea;

        add(createButton("	", Color.BLACK));
        add(createButton("	", Color.RED));
        add(createButton("	", Color.GREEN));
        add(createButton("	", Color.BLUE));
        add(createButton("	", Color.ORANGE));
        add(createButton("	", Color.YELLOW));
        add(createButton("Clear Drawing", null));
    }

    private JButton createButton(String text, Color background) {
        JButton button = new JButton(text);
        button.setBackground(background);
        //button.addActionListener( this );

        return button;
    }
}

 class DrawingArea extends JPanel {
    private final static int AREA_SIZE = 400;
    //private ArrayList<ColoredRectangle> rectangles = new ArrayList<ColoredRectangle>();
    private ArrayList<Rectangle> rectangles = new ArrayList<Rectangle>();
    private Rectangle shape;

    public DrawingArea() {
        setBackground(Color.WHITE);
    }

    @Override
    public Dimension getPreferredSize() {
        return isPreferredSizeSet() ?
                super.getPreferredSize() : new Dimension(AREA_SIZE, AREA_SIZE);
    }

    public void draw() {
        //ArrayList<Rectangle> shapes = new ArrayList<Rectangle>();
        //rectangles.add(shape);

        /*protected void paintComponent (Graphics g)
        {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g.create();

            for (Shape info : shapes) {
                g2d.fill(info.getShape());
            }

            g2d.dispose();
        }*/
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Color foreground = g.getColor();

        g.setColor(Color.BLACK);
        for (Rectangle cr : rectangles) {
            g.drawRect(cr.x, cr.y, cr.width, cr.height);
        }

       if (shape != null) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(foreground);
            g2d.draw(shape);
        }

    }

    public void addRectangle(Rectangle rectangle) {
        //  Add the Rectangle to the List so it can be repainted
        rectangles.add(rectangle);
        repaint();
    }

    /*public void clear() {
        coloredRectangles.clear();
        repaint();
    }*/



    class ColoredRectangle {
        private Color foreground;
        private Rectangle rectangle;

        public ColoredRectangle(Color foreground, Rectangle rectangle) {
            this.foreground = foreground;
            this.rectangle = rectangle;
        }

        public Color getForeground() {
            return foreground;
        }

        public void setForeground(Color foreground) {
            this.foreground = foreground;
        }

        public Rectangle getRectangle() {
            return rectangle;
        }
    }
}

