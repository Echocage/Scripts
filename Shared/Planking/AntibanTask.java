package Shared.Planking;



import org.powerbot.concurrent.Task;
import org.powerbot.concurrent.strategy.Strategy;
import org.powerbot.game.api.methods.input.Mouse;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.util.Random;
import org.powerbot.game.api.util.Timer;

/**
 * Simple anti ban class
 * 
 * @author RoadProphet
 * 
 */
public class AntibanTask extends Strategy implements Task {

	/** Our timer */
	private Timer antiBanTimer;

	/** Our min and max times */
	private int minTime = (1 * 1000); // 20 seconds
	private int maxTime = (2 * 1000); // 2 minutes, or 120 seconds

	/**
	 * Constructor. Initializes timer.
	 */
	public AntibanTask() {

		antiBanTimer = new Timer(Random.nextInt(minTime, maxTime)); // Do an
																	// anti ban
																	// action at
																	// least
																	// once
																	// between
																	// 20
																	// seconds
																	// and two
																	// minutes
	}

	@Override
	public void run() {
		int whatdo = Random.nextInt(0, 10);

		switch (whatdo) {

		case 0:
		case 1:
		case 2:
		case 3:
			int currentX = Mouse.getX();
			int currentY = Mouse.getY();
			Mouse.move(Random.nextInt(0, 20) + currentX, Random.nextInt(16, 39)
					+ currentY);

			break;

	
		case 5:
		case 4:
		case 7:
		case 8:
			// Move the camera a bit
			int currentPitch = Camera.getPitch();
			int currentYaw = Camera.getYaw();
			Camera.setPitch(currentPitch + Random.nextInt(-50, 50));
			Camera.setAngle(currentYaw + Random.nextInt(-70, 70));
			break;

		default:
			Camera.setAngle(Random.nextInt(0, 360));
			break;
		}

	}

	/**
	 * Returns true if the timer has ended
	 */
	@Override
	public boolean validate() {
		return !antiBanTimer.isRunning();
	}

	/**
	 * Resets the timer to a new random time between our min and max values
	 */
	private void resetAntiBanTime() {
		antiBanTimer.setEndIn(Random.nextInt(minTime, maxTime));
	}

}
