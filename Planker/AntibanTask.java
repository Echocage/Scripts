package Planker;

import java.awt.Point;

import org.powerbot.concurrent.Task;
import org.powerbot.concurrent.strategy.Strategy;
import org.powerbot.game.api.methods.Tabs;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.input.Mouse;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Skills;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.util.Random;
import org.powerbot.game.api.util.Time;
import org.powerbot.game.api.util.Timer;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.widget.WidgetChild;

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
	private int minTime = (20 * 1000); // 20 seconds
	private int maxTime = (120 * 1000); // 2 minutes, or 120 seconds

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
			// Open the skills tab and check a random skill
			int randomSkill = Random.nextInt(0, 24);
			Tabs.STATS.open();
			WidgetChild randStat = Skills.getWidgetChild(randomSkill);
			Point randStatPoint = randStat.getAbsoluteLocation();
			randStatPoint.x += Random.nextInt(-27, 22); // Don't have the mouse
														// go to the EXACT same
														// spot every time! :)
			randStatPoint.y += Random.nextInt(-17, 32); // Also, you can change
														// all of these values.
														// >_>
			Mouse.move(randStatPoint);
			Time.sleep(3000);
			break;
		case 1:
			Time.sleep(Random.nextInt(100, 300));
			int bob = Random.nextInt(1000, -70);
			int joe = Random.nextInt(1000, -70);
			Mouse.move(bob, joe);
			break;

		case 2:
		case 3:
		case 5:
		case 4:
		case 7:
		case 8:
			Camera.setAngle(Random.nextInt(0, 360));
			if (Camera.getPitch() != 92) {
				Camera.setPitch(92);
			}

			break;

		default:
			if (Players.getLocal().getInteracting() != null) {
				// Turn to the object we are interacting with, then move it
				// around a bit
				Camera.turnTo(Players.getLocal().getInteracting());
				int currentPitch = Camera.getPitch();
				int currentYaw = Camera.getYaw();
				Camera.setPitch(currentPitch + Random.nextInt(-17, 13));
				Camera.setAngle(currentYaw + Random.nextInt(-19, 25));
			} else {
				// Move the camera a bit
				int currentPitch = Camera.getPitch();
				int currentYaw = Camera.getYaw();
				Camera.setPitch(currentPitch + Random.nextInt(-50, 50));
				Camera.setAngle(currentYaw + Random.nextInt(-70, 70));
			}
		}

		resetAntiBanTime();
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