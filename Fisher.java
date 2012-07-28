import java.util.Random;

import org.powerbot.concurrent.Task;
import org.powerbot.concurrent.strategy.Condition;
import org.powerbot.concurrent.strategy.Strategy;
import org.powerbot.game.api.ActiveScript;
import org.powerbot.game.api.Manifest;
import org.powerbot.game.api.methods.Tabs;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Bank;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.util.Time;
import org.powerbot.game.api.wrappers.interactive.NPC;
import org.powerbot.game.api.wrappers.node.Item;
import org.powerbot.game.api.wrappers.widget.Widget;
import org.powerbot.game.api.wrappers.widget.WidgetChild;
import org.powerbot.game.client.RSItem;

@Manifest(authors = { "Preston3050" }, name = "Fisher", description = "This is my first and only testing script", version = 1.0)
public class Fisher extends ActiveScript {
	String status = "";
	Random rand = new Random();
	AntibanTask antiBan = new AntibanTask();

	public boolean inventoryCheck() {
		Item[] bob = Inventory.getItems();
		for (int c = 0; c <= bob.length; c++) {
			if (bob[c].getId() != -1) {
				return true;

			}

		}
		return false;

	}

	public void fish() {
		if (!Players.getLocal().isMoving()
				&& Players.getLocal().getAnimation() != 621) {
			Time.sleep(1000);
			if (!Players.getLocal().isMoving()
					&& Players.getLocal().getAnimation() != 621) {
				status = "Fishing";
				NPC bob = NPCs.getNearest(327);
				if (bob.isOnScreen()) {
					bob.interact("Net");
				} else {
					Camera.turnTo(bob);
				}
				Time.sleep(3000);
			}
		} else {
			if (rand.nextInt(300) == 1) {

				antiBan.run();
			}else{
				if(rand.nextInt(2)==2){
					Tabs.STATS.open();
					Widgets.get(320,34).hover();
					Time.sleep(3000+rand.nextInt(5000));
					
				}
				
			}

		}

	}

	@Override
	protected void setup() {
		final Sample sample = new Sample();
		final Strategy sampleAction = new Strategy(sample, sample);

		provide(sampleAction);

	}

	private class Sample implements Task, Condition {
		@Override
		public void run() {

			if (!Inventory.isFull()) {
				fish();
			} else {

				Item[] bob = Inventory.getItems();

				for (int c = 0; inventoryCheck(); c++) {
					bob[c].getWidgetChild().interact("Drop");
					Time.sleep(100);

					if (c == 29 && inventoryCheck()) {
						c = 0;
					}
				}

			}
		}

		@Override
		public boolean validate() {
			return true;
		}
	}

}