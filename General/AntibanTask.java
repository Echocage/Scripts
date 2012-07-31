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
			Mouse.move(Random.nextInt(0, 750), Random.nextInt(16, 500));
			break;

		case 2:
		case 3:
		case 5:
		case 4:
		case 7:
		case 8:
			Camera.setAngle(Random.nextInt(0, 360));
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

}