import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DigitalClock extends JFrame {
    private JLabel displayLabel;
    private SimpleDateFormat timeFormat;
    private JPanel panel;
    private JButton switchButton, startTimerButton, resetTimerButton;
    private boolean isTimerMode = false;
    private Timer clockTimer, countdownTimer;
    private int countdownSeconds = 0; //count down time

    public DigitalClock() {
        setTitle("Digital Clock & Timer");
        setSize(350, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        panel = new JPanel();
        panel.setBackground(new Color(255, 182, 193)); // Light pink
        panel.setLayout(new BorderLayout());

        displayLabel = new JLabel();
        displayLabel.setFont(new Font("Arial", Font.BOLD, 40));
        displayLabel.setHorizontalAlignment(SwingConstants.CENTER);

        switchButton = new JButton("Switch to Timer");
        startTimerButton = new JButton("Start Timer");
        resetTimerButton = new JButton("Reset Timer");

  
        switchButton.addActionListener(this::switchMode);
        startTimerButton.addActionListener(this::startTimer);
        resetTimerButton.addActionListener(this::resetTimer);

        // button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(switchButton);
        buttonPanel.add(startTimerButton);
        buttonPanel.add(resetTimerButton);

        panel.add(displayLabel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        add(panel);

        // timer for real time (initial) clock
        timeFormat = new SimpleDateFormat("HH:mm:ss");
        clockTimer = new Timer(1000, e -> updateClock());
        clockTimer.start();

        // timer for countdown
        countdownTimer = new Timer(1000, e -> updateCountdown());

        updateClock(); 
    }

    private void switchMode(ActionEvent e) {
        isTimerMode = !isTimerMode;

        if (isTimerMode) {
            switchButton.setText("Switch to Clock");
            displayLabel.setText("Set Time: 10s"); 
            countdownSeconds = 10; 
            clockTimer.stop();
        } else {
            switchButton.setText("Switch to Timer");
            clockTimer.start();
            countdownTimer.stop();
            updateClock();
        }
    }

    private void startTimer(ActionEvent e) {
        if (isTimerMode && !countdownTimer.isRunning()) {
            countdownTimer.start();
        }
    }

    private void resetTimer(ActionEvent e) {
        if (isTimerMode) {
            countdownTimer.stop();
            countdownSeconds = 10; // reset to 10 seconds
            displayLabel.setText("Set Time: 10s");
        }
    }

    private void updateCountdown() {
        if (countdownSeconds > 0) {
            displayLabel.setText(countdownSeconds + "s");
            countdownSeconds--;
        } else {
            displayLabel.setText("Time's up!");
            countdownTimer.stop();
        }
    }

    private void updateClock() {
        displayLabel.setText(timeFormat.format(new Date()));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DigitalClock clock = new DigitalClock();
            clock.setVisible(true);
        });
    }
}
