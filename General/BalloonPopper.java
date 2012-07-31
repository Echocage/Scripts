import org.powerbot.concurrent.Task;
import org.powerbot.concurrent.strategy.Condition;
import org.powerbot.concurrent.strategy.Strategy;
import org.powerbot.game.api.ActiveScript;
import org.powerbot.game.api.Manifest;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.GroundItems;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Bank;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.util.Filter;
import org.powerbot.game.api.util.Time;
import org.powerbot.game.api.wrappers.Area;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.node.GroundItem;
import org.powerbot.game.api.wrappers.node.SceneObject;

@Manifest(authors = { "Preston3050" }, name = "Balloon Popper!", description = "Pops balloons at yes", version = 1.0)
public class BalloonPopper extends ActiveScript {
	private void rePull() {
		if (aParty.contains(getLocation())) {
			log.info("Repulling");
			SceneObject chest = SceneEntities.getNearest(26193);
			if (chest != null) {
				if (chest.isOnScreen()) {
					chest.click(true);

					Time.sleep(5000);

				} else {

					chest.getLocation().clickOnMap();
					while (Players.getLocal().isMoving()) {
						Time.sleep(100);

					}
				}
			}

			SceneObject chest2 = SceneEntities.getNearest(2418);
			if (chest2 != null) {
				if (chest2.isOnScreen()) {
					chest2.click(true);

					Time.sleep(5000);

				} else {
					chest2.getLocation().clickOnMap();
					while (Players.getLocal().isMoving()) {
						Time.sleep(100);

					}
				}
			}
			if (Widgets.get(647, 24).getChild(1).getChildStackSize() != 0) {
				if (Widgets.get(647, 24).isOnScreen()) {
					Widgets.get(647, 7).click(true);
				}

			} else {
				Time.sleep(1000000);
			}

		}
		SceneObject lever = SceneEntities.getNearest(26194);
		lever.interact("Pull");
		Time.sleep(1000);
		while (Players.getLocal().isMoving()) {
			Time.sleep(1000);
		}
		Time.sleep(1000);
		Widgets.get(1188, 3).click(true);

	}

	private boolean areThereItems() {
		GroundItem ClosestItem = GroundItems
				.getNearest(new Filter<GroundItem>() {
					@Override
					public boolean accept(GroundItem o) {
						if (o != null) {
							return true;
						}
						return false;
					}
				});
		if (ClosestItem != null) {
			return true;
		}
		return false;
	}

	private void pickItem() {
		GroundItem ClosestItem = GroundItems
				.getNearest(new Filter<GroundItem>() {
					@Override
					public boolean accept(GroundItem o) {
						if (o != null) {
							return true;
						}
						return false;
					}
				});

		if (ClosestItem.isOnScreen()) {
			ClosestItem.click(true);
			Time.sleep(1000);
			while (Players.getLocal().isMoving()) {
				Time.sleep(1000);

			}

		} else {

			Camera.turnTo(ClosestItem);
			ClosestItem.click(true);

		}

	}

	private void burstBalloon() {
		log.info("Popping!");
		SceneObject balloon = SceneEntities.getNearest(balloonID);
		if (getLocation().distance(balloon) <= 10) {
			if (getLocation().distance(balloon) <= 5) {
				if (balloon.isOnScreen()) {
					balloon.interact("Burst");
					Time.sleep(1000);
					while (Players.getLocal().isMoving()) {
						Time.sleep(100);
					}

				}

			} else {
				if (balloon.getLocation().isOnMap()) {
					Walking.walk(balloon);
				}
			}

		}

	}

	private void bankItems() {

		if (Bank.isOpen()) {
			while (Inventory.isFull()) {
				Bank.depositInventory();
				Time.sleep(3000);
			}
		} else {
			Bank.open();
		}

	}

	boolean shouldIRepull = false;// Yes = true
	int[] Bankers = Bank.BANK_BOOTH_IDS;
	Area aParty = new Area(new Tile(3055, 3371, 0), new Tile(3037, 3385, 0));
	Tile destinationTile = new Tile(0, 0, 0);
	int[] balloonID = { 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125,
			126, 127, 128, 129, 130 };
	Tile cBank = new Tile(3013, 3355, 0);
	Tile[] toBank = { new Tile(3045, 3373, 0), new Tile(3045, 3372, 0),
			new Tile(3045, 3371, 0), new Tile(3044, 3371, 0),
			new Tile(3043, 3371, 0), new Tile(3042, 3371, 0),
			new Tile(3041, 3371, 0), new Tile(3041, 3370, 0),
			new Tile(3040, 3370, 0), new Tile(3039, 3370, 0),
			new Tile(3038, 3369, 0), new Tile(3037, 3369, 0),
			new Tile(3036, 3369, 0), new Tile(3035, 3369, 0),
			new Tile(3034, 3369, 0), new Tile(3033, 3369, 0),
			new Tile(3032, 3369, 0), new Tile(3031, 3369, 0),
			new Tile(3030, 3369, 0), new Tile(3029, 3369, 0),
			new Tile(3028, 3369, 0), new Tile(3027, 3369, 0),
			new Tile(3026, 3369, 0), new Tile(3025, 3368, 0),
			new Tile(3024, 3367, 0), new Tile(3023, 3366, 0),
			new Tile(3022, 3365, 0), new Tile(3021, 3364, 0),
			new Tile(3020, 3363, 0), new Tile(3019, 3363, 0),
			new Tile(3018, 3363, 0), new Tile(3017, 3363, 0),
			new Tile(3016, 3362, 0), new Tile(3015, 3361, 0),
			new Tile(3014, 3360, 0), new Tile(3013, 3359, 0),
			new Tile(3013, 3358, 0), new Tile(3013, 3357, 0),
			new Tile(3013, 3356, 0), new Tile(3013, 3355, 0) };
	Tile cParty = new Tile(3046, 3377, 0);
	Area aBank = new Area(new Tile(3018, 3355, 0), new Tile(3009, 3358, 0));

	public Tile getLocation() {
		Tile Location = Players.getLocal().getLocation();
		return Location;

	}

	@Override
	protected void setup() {
		final Mine mine = new Mine();
		final Strategy mineAction = new Strategy(mine, mine);
		provide(mineAction);
		final Banking bank = new Banking();
		final Strategy bankAction = new Strategy(bank, bank);
		provide(bankAction);

	}

	private class Mine implements Task, Condition {

		@Override
		public void run() {
			log.info("Begin Mine");
			burstBalloon();
			if (Bank.isOpen()) {
				Bank.close();
			}
			if (!aParty.contains(getLocation())) {
				Walking.newTilePath(toBank).reverse().traverse();
			}
			SceneObject balloon = SceneEntities.getNearest(balloonID);
			if (aParty.contains(getLocation()) && balloon == null
					&& shouldIRepull == true) {
				rePull();
			}
			if (areThereItems()) {
				pickItem();
			}
		}

		@Override
		public boolean validate() {

			return !Inventory.isFull();
		}

	}

	private class Banking implements Task, Condition {

		@Override
		public void run() {

			Walking.newTilePath(toBank);

			if (aBank.contains(getLocation())) {
				bankItems();
			}
		}

		@Override
		public boolean validate() {

			return Inventory.isFull();
		}
	}
}