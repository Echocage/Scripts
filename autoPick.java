import org.powerbot.concurrent.Task;
import org.powerbot.concurrent.strategy.Condition;
import org.powerbot.concurrent.strategy.Strategy;
import org.powerbot.game.api.ActiveScript;
import org.powerbot.game.api.Manifest;
import org.powerbot.game.api.methods.Tabs;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.input.Mouse;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Bank;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.util.Filter;
import org.powerbot.game.api.util.Time;
import org.powerbot.game.api.wrappers.Area;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.node.SceneObject;
import java.util.Random;

/*



 private class Pick implements Task, Condition {
 @Override
 public void run() { 


 }


 @Override
 public boolean validate() {

 }

 }

 * 
 * 
 */

@Manifest(authors = { "Preston3050" }, name = "Potato picker", description = "ting script", version = 1.0)
public class autoPick extends ActiveScript {
	public void goHome() {
		if (!Tabs.MAGIC.isOpen()) {
			Tabs.MAGIC.open();
			Time.sleep(500);
		}
		Mouse.click(573, 228, true);
		Time.sleep(1000 + rand.nextInt(1500));
		Mouse.click(324, 232, true);
		Time.sleep(10000 + rand.nextInt(15000));
	}

	int halfWay = 0;

	public SceneObject getStair(String str) {
		if (str.contains("top")) {
			SceneObject Stair = SceneEntities
					.getNearest(new Filter<SceneObject>() {
						public boolean accept(SceneObject entity) {
							return entity.getId() == 36775;

						}
					});

			return Stair;
		}
		if (str.contains("mid")) {
			SceneObject Stair = SceneEntities
					.getNearest(new Filter<SceneObject>() {
						public boolean accept(SceneObject entity) {
							return entity.getId() == 36774;

						}
					});

			return Stair;
		}
		if (str.contains("low")) {
			SceneObject Stair = SceneEntities
					.getNearest(new Filter<SceneObject>() {
						public boolean accept(SceneObject entity) {
							return entity.getId() == 36773;

						}
					});

			return Stair;
		}
		return null;

	}

	public Tile getLocation() {
		Tile Location = Players.getLocal().getLocation();
		return Location;

	}

	Area aField = new Area(new Tile(3253, 3315, 0), new Tile(3266, 3299, 0));

	Random rand = new Random();
	Tile SheepCow = new Tile(3241, 3274, 0);
	Tile bankerTile = new Tile(3208, 3218, 2);
	Tile bankTile = new Tile(3206, 3210, 0);
	Tile[] load2Bank = { new Tile(3233, 3221, 0), new Tile(3233, 3220, 0),
			new Tile(3233, 3219, 0), new Tile(3232, 3219, 0),
			new Tile(3231, 3219, 0), new Tile(3230, 3219, 0),
			new Tile(3229, 3219, 0), new Tile(3228, 3219, 0),
			new Tile(3227, 3219, 0), new Tile(3226, 3219, 0),
			new Tile(3225, 3219, 0), new Tile(3224, 3219, 0),
			new Tile(3223, 3219, 0), new Tile(3222, 3219, 0),
			new Tile(3221, 3219, 0), new Tile(3220, 3219, 0),
			new Tile(3219, 3219, 0), new Tile(3218, 3219, 0),
			new Tile(3217, 3219, 0), new Tile(3216, 3219, 0),
			new Tile(3215, 3219, 0), new Tile(3215, 3218, 0),
			new Tile(3215, 3217, 0), new Tile(3215, 3216, 0),
			new Tile(3215, 3215, 0), new Tile(3214, 3214, 0),
			new Tile(3214, 3213, 0), new Tile(3215, 3212, 0),
			new Tile(3215, 3211, 0), new Tile(3215, 3210, 0),
			new Tile(3214, 3210, 0), new Tile(3213, 3210, 0),
			new Tile(3212, 3210, 0), new Tile(3211, 3210, 0),
			new Tile(3210, 3210, 0), new Tile(3209, 3210, 0),
			new Tile(3208, 3210, 0), new Tile(3207, 3210, 0),
			new Tile(3206, 3209, 0) };
	Tile[] toField = { new Tile(3206, 3210, 0), new Tile(3207, 3210, 0),
			new Tile(3208, 3210, 0), new Tile(3209, 3210, 0),
			new Tile(3210, 3210, 0), new Tile(3211, 3210, 0),
			new Tile(3212, 3211, 0), new Tile(3213, 3211, 0),
			new Tile(3214, 3211, 0), new Tile(3215, 3211, 0),
			new Tile(3215, 3212, 0), new Tile(3215, 3213, 0),
			new Tile(3215, 3214, 0), new Tile(3214, 3215, 0),
			new Tile(3214, 3216, 0), new Tile(3214, 3217, 0),
			new Tile(3215, 3218, 0), new Tile(3215, 3219, 0),
			new Tile(3216, 3219, 0), new Tile(3217, 3219, 0),
			new Tile(3218, 3219, 0), new Tile(3219, 3219, 0),
			new Tile(3220, 3219, 0), new Tile(3221, 3219, 0),
			new Tile(3222, 3219, 0), new Tile(3223, 3219, 0),
			new Tile(3224, 3219, 0), new Tile(3225, 3219, 0),
			new Tile(3226, 3219, 0), new Tile(3227, 3219, 0),
			new Tile(3228, 3219, 0), new Tile(3229, 3219, 0),
			new Tile(3230, 3219, 0), new Tile(3231, 3219, 0),
			new Tile(3232, 3219, 0), new Tile(3232, 3220, 0),
			new Tile(3232, 3221, 0), new Tile(3232, 3222, 0),
			new Tile(3232, 3223, 0), new Tile(3232, 3224, 0),
			new Tile(3232, 3225, 0), new Tile(3232, 3226, 0),
			new Tile(3232, 3227, 0), new Tile(3232, 3228, 0),
			new Tile(3232, 3229, 0), new Tile(3232, 3230, 0),
			new Tile(3232, 3231, 0), new Tile(3232, 3232, 0),
			new Tile(3232, 3233, 0), new Tile(3232, 3234, 0),
			new Tile(3233, 3235, 0), new Tile(3233, 3236, 0),
			new Tile(3233, 3237, 0), new Tile(3233, 3238, 0),
			new Tile(3233, 3239, 0), new Tile(3233, 3240, 0),
			new Tile(3233, 3241, 0), new Tile(3233, 3242, 0),
			new Tile(3233, 3243, 0), new Tile(3233, 3244, 0),
			new Tile(3233, 3245, 0), new Tile(3233, 3246, 0),
			new Tile(3232, 3245, 0), new Tile(3231, 3244, 0),
			new Tile(3230, 3244, 0), new Tile(3229, 3244, 0),
			new Tile(3228, 3245, 0), new Tile(3228, 3246, 0),
			new Tile(3228, 3247, 0), new Tile(3228, 3248, 0),
			new Tile(3228, 3249, 0), new Tile(3229, 3250, 0),
			new Tile(3230, 3251, 0), new Tile(3230, 3252, 0),
			new Tile(3222, 3293, 0), new Tile(3230, 3253, 0),
			new Tile(3230, 3254, 0), new Tile(3230, 3255, 0),
			new Tile(3230, 3256, 0), new Tile(3230, 3257, 0),
			new Tile(3230, 3258, 0), new Tile(3229, 3259, 0),
			new Tile(3229, 3260, 0), new Tile(3229, 3261, 0),
			new Tile(3230, 3261, 0), new Tile(3231, 3261, 0),
			new Tile(3232, 3261, 0), new Tile(3233, 3261, 0),
			new Tile(3234, 3261, 0), new Tile(3235, 3262, 0),
			new Tile(3236, 3262, 0), new Tile(3237, 3262, 0),
			new Tile(3238, 3262, 0), new Tile(3239, 3262, 0),
			new Tile(3240, 3262, 0), new Tile(3241, 3262, 0),
			new Tile(3242, 3262, 0), new Tile(3242, 3263, 0),
			new Tile(3242, 3264, 0), new Tile(3242, 3265, 0),
			new Tile(3242, 3266, 0), new Tile(3242, 3267, 0),
			new Tile(3242, 3268, 0), new Tile(3242, 3269, 0),
			new Tile(3242, 3270, 0), new Tile(3242, 3271, 0),
			new Tile(3242, 3272, 0), new Tile(3242, 3273, 0),
			new Tile(3242, 3274, 0), new Tile(3242, 3275, 0),
			new Tile(3241, 3276, 0), new Tile(3241, 3277, 0) };
	Tile eToField = new Tile(3262, 3322, 0);
	Tile Field = new Tile(3258, 3308, 0);

	@Override
	protected void setup() {
		final Pick pick = new Pick();
		final Strategy pickAction = new Strategy(pick, pick);
		provide(pickAction);
		final AntiBan ab = new AntiBan();
		final Strategy abAction = new Strategy(ab, ab);
		provide(abAction);
		final Empty bank = new Empty();
		final Strategy emptyBank = new Strategy(bank, bank);
		provide(emptyBank);
		final WalkBank tooBank = new WalkBank();
		final Strategy walkBank = new Strategy(tooBank, tooBank);
		provide(walkBank);
		final Full full = new Full();
		final Strategy fullAction = new Strategy(full, full);
		provide(fullAction);

	}

	private class Pick implements Task, Condition {
		@Override
		public void run() {
			SceneObject potato = SceneEntities
					.getNearest(new Filter<SceneObject>() {
						public boolean accept(SceneObject entity) {
							return entity.getId() == 312;

						}
					});
			if (potato != null) {
				potato.interact("Pick");
				Time.sleep(1000 + rand.nextInt(2000));
			}
			if (!aField.contains(getLocation())) {
				Walking.walk(Field);
				Time.sleep(500 + rand.nextInt(1000));
			}
		}

		@Override
		public boolean validate() {
			final Tile location = Players.getLocal().getLocation();
			return !Inventory.isFull()
					&& Players.getLocal().getAnimation() == -1
					&& aField.contains(getLocation());

		}

	}

	private class AntiBan implements Task, Condition {
		@Override
		public void run() {

			switch (rand.nextInt(2000)) {
			case 1:
				Time.sleep(rand.nextInt(1));
				break;

			}

		}

		public boolean validate() {
			return true;
		}

	}

	private class Empty implements Task, Condition {
		@Override
		public void run() {
			org.powerbot.game.api.methods.widget.Bank.depositInventory();
			Time.sleep(rand.nextInt(500));
			Bank.close();

		}

		@Override
		public boolean validate() {
			return org.powerbot.game.api.methods.widget.Bank.isOpen();
		}

	}

	private class Full implements Task, Condition {
		@Override
		public void run() {
			final Tile location = Players.getLocal().getLocation();
			if (location.distance(new Tile(3258, 3308, 0)) <= 9) {
				goHome();
			}
			Walking.newTilePath(load2Bank).traverse();
			Time.sleep(1000);
			SceneObject lowStair = SceneEntities
					.getNearest(new Filter<SceneObject>() {
						public boolean accept(SceneObject entity) {
							return entity.getId() == 36773;

						}
					});
			if (lowStair != null) {
				Time.sleep(3000);
				lowStair.interact("Climb-up");
				Time.sleep(3000 + rand.nextInt(5000));

			}
			SceneObject medStair = SceneEntities
					.getNearest(new Filter<SceneObject>() {
						public boolean accept(SceneObject entity) {
							return entity.getId() == 36774;
						}
					});
			if (medStair.isOnScreen()) {
				Time.sleep(rand.nextInt(2000));
				medStair.interact("Climb-up");
				Time.sleep(3000);
			}

			Walking.walk(new Tile(3208, 3218, 2));

			SceneObject banker = SceneEntities
					.getNearest(new Filter<SceneObject>() {
						public boolean accept(SceneObject entity) {
							return entity.getId() == 36786;
						}
					});
			banker.interact("Bank");
			Time.sleep(5000);
		}

		@Override
		public boolean validate() {
			return Inventory.isFull() && !Bank.isOpen();

		}

	}

	private class WalkBank implements Task, Condition {

		@Override
		public void run() {

			if (getLocation().distance(bankerTile) <= 10) {
				goHome();
			}

			
			
			Time.sleep(rand.nextInt(2000));
			if (getLocation().equals(new Tile(3262, 3322, 0))
					|| getLocation().equals(new Tile(3262, 3323, 0))) {
				Walking.walk(Field);
				Time.sleep(5000);

			}
			SceneObject Gate = SceneEntities
					.getNearest(new Filter<SceneObject>() {
						public boolean accept(SceneObject entity) {
							return entity.getId() == 45206;

						}
					});

			if (getLocation().distance(Gate) <= 5) {
				Gate.interact("Open");
				Time.sleep(3000);
			}

			if (getLocation().distance(Gate) <= 5 && !Gate.isOnScreen()) {
				Camera.turnTo(Gate);
				Time.sleep(3000);

			}

		}

		@Override
		public boolean validate() {

			return !aField.contains(getLocation()) && !Inventory.isFull();

		}

	}

}