import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DigitalClock extends JFrame {
    private JLabel timeLabel;
    private SimpleDateFormat timeFormat;
    private JPanel panel;  // Declare panel

    public DigitalClock() {
        setTitle("Digital Clock");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Define the time format as HH:mm:ss
        timeFormat = new SimpleDateFormat("HH:mm:ss");

        // Initialize the panel
        panel = new JPanel(); 
        panel.setBackground(new Color(255, 182, 193)); // Light pink color
        panel.setLayout(new BorderLayout());

        // Create the time label
        timeLabel = new JLabel();
        timeLabel.setFont(new Font("Arial", Font.BOLD, 40));
        timeLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Add label to panel and panel to frame
        panel.add(timeLabel, BorderLayout.CENTER);
        add(panel);
        
        updateClock();

        // Timer to update the time every second
        Timer timer = new Timer(1000, e -> updateClock());
        timer.start();
    }

    private void updateClock() {
        timeLabel.setText(timeFormat.format(new Date()));
    }

    // Run the clock
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DigitalClock clock = new DigitalClock();
            clock.setVisible(true);
        });
    }
}
