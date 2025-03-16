import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.*;

public class Reloj extends JFrame {
    private final JLabel timeLabel;
    private final JLabel dateLabel;
    private final Color backgroundColor = new Color(0, 0, 0, 200);
    private final Color textColor = Color.WHITE;

    public Reloj() {
        setUndecorated(true);
        setBackground(new Color(0, 0, 0, 0));
        setSize(400, 150);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        setAlwaysOnTop(true);

        // Panel principal con fondo semitransparente
        JPanel mainPanel = new JPanel(new BorderLayout(5, 5)) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(backgroundColor);
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
            }
        };
        mainPanel.setOpaque(false);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Etiquetas para hora y fecha
        timeLabel = new JLabel();
        timeLabel.setFont(new Font("Arial", Font.BOLD, 48));
        timeLabel.setForeground(textColor);
        timeLabel.setHorizontalAlignment(SwingConstants.CENTER);

        dateLabel = new JLabel();
        dateLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        dateLabel.setForeground(textColor);
        dateLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Botones de control
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 0));
        controlPanel.setOpaque(false);

        JButton closeButton = new JButton("×");
        closeButton.setForeground(textColor);
        closeButton.setContentAreaFilled(false);
        closeButton.setBorderPainted(false);
        closeButton.setFocusPainted(false);
        closeButton.setFont(new Font("Arial", Font.BOLD, 20));
        closeButton.addActionListener(_ -> System.exit(0));

        controlPanel.add(closeButton);

        // Agregar componentes
        mainPanel.add(controlPanel, BorderLayout.NORTH);
        mainPanel.add(timeLabel, BorderLayout.CENTER);
        mainPanel.add(dateLabel, BorderLayout.SOUTH);
        add(mainPanel);

        // Hacer la ventana arrastrable
        DragListener dragListener = new DragListener();
        addMouseListener(dragListener);
        addMouseMotionListener(dragListener);

        // Iniciar el reloj
        new Timer(1000, _ -> actualizarReloj()).start();
        actualizarReloj();
    }

    private void actualizarReloj() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "EEEE, d 'de' MMMM 'de' yyyy");

        timeLabel.setText(timeFormat.format(cal.getTime()));
        dateLabel.setText(dateFormat.format(cal.getTime()));
    }

    // Hacer la ventana arrastrable
    class DragListener extends MouseAdapter {
        private Point initialClick;

        @Override
        public void mousePressed(MouseEvent e) {
            initialClick = e.getPoint();
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            int thisX = getLocation().x;
            int thisY = getLocation().y;

            setLocation(
                    thisX + e.getX() - initialClick.x,
                    thisY + e.getY() - initialClick.y);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Reloj reloj = new Reloj();
            reloj.setVisible(true);
        });
    }
}