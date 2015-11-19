package Client.Windows;

import java.awt.Component;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TimerPanel extends JPanel implements Runnable {
	
	public static final long serialVersionUID = 241341724341L;

	private String playerOneName;
	private String playerTwoName;
	private JLabel vsLabel;
	private JLabel timerLabel;
	
	private final long start;

	public TimerPanel(String playerOne, String playerTwo) {
		playerOneName = playerOne;
		playerTwoName = playerTwo;
		
		// get initial time to later calculate elapsed time
		start = System.currentTimeMillis();
		
		initializeComponents();
		createGUI();
	}
	
	private void initializeComponents() {
		// initialize label
		String labelStr = playerOneName + " VS " + playerTwoName;
		vsLabel = new JLabel(labelStr);
		vsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		timerLabel = new JLabel("00:00");
		timerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
	}
	
	private void createGUI() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		add(vsLabel);
		add(timerLabel);
	}
	
	private String timeFromMillis(long millis) {
		int totalSeconds = Math.toIntExact(millis)/1000;
		
		int minutes = totalSeconds/60;
		int seconds = totalSeconds%60;
		
		String timeString = "";
		
		// format total seconds into mm:ss
		if (minutes < 10) {
			timeString += "0" + minutes + ":";
		} else {
			timeString += minutes + ":";
		}
		
		if (seconds < 10) {
			timeString += "0" + seconds;
		} else {
			timeString += seconds;
		}
		
		return timeString;
	}
	
	@Override
	public void run() {
		while (true) {
			long timeNow = System.currentTimeMillis();
			long elapsedTime = timeNow - start;
			timerLabel.setText(timeFromMillis(elapsedTime));
			
			// sleep for some fraction of seconds
			try {
				Thread.sleep(100);
			} catch (InterruptedException ie) {
				System.out.println("Interrupted Exception in TimerPanel's run method: " + ie.getMessage());
			}
		}
	}
}
