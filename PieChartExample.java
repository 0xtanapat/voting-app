package voting;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Arc2D;

public class PieChartExample extends JFrame {
    private JPanel chartPanel;

    public PieChartExample() {
        setTitle("Pie Chart Example");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        chartPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Define data
                int[] values = {30, 40, 20, 10};
                String[] labels = {"Label 1", "Label 2", "Label 3", "Label 4"};

                // Calculate total value
                int total = 0;
                for (int value : values) {
                    total += value;
                }

                // Draw pie chart segments
                int startAngle = 0;
                for (int i = 0; i < values.length; i++) {
                    int arcAngle = (int) Math.round(360.0 * values[i] / total);

                    g2d.setColor(getColor(i));
                    g2d.fill(new Arc2D.Double(50, 50, 200, 200, startAngle, arcAngle, Arc2D.PIE));

                    startAngle += arcAngle;
                }

                // Draw legend
                int x = 300;
                int y = 50;
                for (int i = 0; i < values.length; i++) {
                    g2d.setColor(getColor(i));
                    g2d.fillRect(x, y, 20, 20);
                    g2d.setColor(Color.BLACK);
                    g2d.drawString(labels[i], x + 30, y + 15);
                    y += 30;
                }
            }
        };

        add(chartPanel);

        setSize(400, 400);
        setVisible(true);
    }

    private Color getColor(int index) {
        Color[] colors = {Color.RED, Color.GREEN, Color.BLUE, Color.ORANGE};
        return colors[index % colors.length];
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(PieChartExample::new);
    }
}
