import org.powerbot.concurrent.Task;
import org.powerbot.concurrent.strategy.Condition;
import org.powerbot.concurrent.strategy.Strategy;
import org.powerbot.game.api.ActiveScript;
import org.powerbot.game.api.Manifest;
import org.powerbot.game.api.methods.Tabs;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.input.Mouse;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.util.Filter;
import org.powerbot.game.api.util.Time;
import org.powerbot.game.api.wrappers.Area;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.node.SceneObject;

import java.util.Random;

@Manifest(authors = { "Preston3050" }, name = "Potato V.2", description = "Draynor Potatos(Have dranor's lodestone activated)Start in Dranor bank!", version = 1.0)
public class PotatoPicker extends ActiveScript {

	public void goHome() {
		if (!Tabs.MAGIC.isOpen()) {
			Tabs.MAGIC.open();
			Time.sleep(500);
		}
		Widgets.get(192, 24).click(true);
		Time.sleep(1000 + rand.nextInt(1500));
		Widgets.get(1092, 44).click(true);
		Time.sleep(2000);
		Tabs.INVENTORY.open();
		for (; Players.getLocal().getAnimation() != -1;) {
			Time.sleep(100);
		}

	}

	Tile[] tiles = { new Tile(3104, 3296, 0), new Tile(3105, 3296, 0),
			new Tile(3106, 3296, 0), new Tile(3107, 3296, 0),
			new Tile(3108, 3295, 0), new Tile(3109, 3294, 0),
			new Tile(3109, 3293, 0), new Tile(3109, 3292, 0),
			new Tile(3109, 3291, 0), new Tile(3109, 3290, 0),
			new Tile(3109, 3289, 0), new Tile(3109, 3288, 0),
			new Tile(3109, 3287, 0), new Tile(3109, 3286, 0),
			new Tile(3109, 3285, 0), new Tile(3109, 3284, 0),
			new Tile(3109, 3283, 0), new Tile(3109, 3282, 0),
			new Tile(3109, 3281, 0), new Tile(3108, 3280, 0),
			new Tile(3107, 3279, 0), new Tile(3106, 3278, 0),
			new Tile(3106, 3277, 0), new Tile(3106, 3276, 0),
			new Tile(3106, 3275, 0), new Tile(3105, 3274, 0),
			new Tile(3105, 3273, 0), new Tile(3105, 3272, 0),
			new Tile(3105, 3271, 0), new Tile(3105, 3270, 0),
			new Tile(3105, 3269, 0), new Tile(3105, 3268, 0),
			new Tile(3105, 3267, 0), new Tile(3105, 3266, 0),
			new Tile(3105, 3265, 0), new Tile(3105, 3225, 0),
			new Tile(3105, 3264, 0), new Tile(3105, 3263, 0),
			new Tile(3104, 3262, 0), new Tile(3103, 3261, 0),
			new Tile(3103, 3260, 0), new Tile(3103, 3259, 0),
			new Tile(3103, 3258, 0), new Tile(3103, 3257, 0),
			new Tile(3103, 3256, 0), new Tile(3103, 3255, 0),
			new Tile(3103, 3254, 0), new Tile(3104, 3253, 0),
			new Tile(3104, 3252, 0), new Tile(3103, 3251, 0),
			new Tile(3102, 3251, 0), new Tile(3101, 3251, 0),
			new Tile(3100, 3251, 0), new Tile(3099, 3251, 0),
			new Tile(3098, 3251, 0), new Tile(3097, 3251, 0),
			new Tile(3096, 3250, 0), new Tile(3095, 3249, 0),
			new Tile(3094, 3248, 0), new Tile(3093, 3247, 0),
			new Tile(3093, 3246, 0), new Tile(3093, 3245, 0),
			new Tile(3093, 3241, 0) };

	autoPick other = new autoPick();
	boolean inField = false;
	boolean inBank = false;
	boolean inventoryFull = false;
	boolean gateOpen = false;
	Tile Field = new Tile(3147, 3280, 0);
	Area aField = new Area(new Tile(3138, 3291, 0), new Tile(3156, 3267, 0));
	Random rand = new Random();
	Area aBank = new Area(new Tile(3096, 3246, 0), new Tile(3092, 3240, 0));
	Tile centerBank = new Tile(3098, 3247, 0);
	Tile lodestone = new Tile(3105, 3298, 0);

	public SceneObject getGate() {
		SceneObject Gate = SceneEntities.getNearest(new Filter<SceneObject>() {
			public boolean accept(SceneObject entity) {
				return entity.getId() == 45208;

			}
		});
		return Gate;

	}

	public Tile getLocation() {
		Tile Location = Players.getLocal().getLocation();
		return Location;

	}

	@Override
	protected void setup() {
		final Check check = new Check();
		final Strategy checkAction = new Strategy(check, check);

		provide(checkAction);
		final Pick pick = new Pick();
		final Strategy pickAction = new Strategy(pick, pick);
		final Gate gate = new Gate();
		final Strategy gateAction = new Strategy(gate, gate);
		provide(gateAction);
		final Bank bank = new Bank();
		final Strategy bankAction = new Strategy(bank, bank);
		final WalkBack wBack = new WalkBack();
		final Strategy walkAction = new Strategy(wBack, wBack);
		final Full full = new Full();
		final Strategy fullAction = new Strategy(full, full);
		provide(checkAction);
		provide(fullAction);
		provide(bankAction);
		provide(walkAction);
		provide(gateAction);
		provide(pickAction);
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
				
				Time.sleep(1000);

				for (; Players.getLocal().isMoving();) {
					Time.sleep(1000);
				}
				for (; Players.getLocal().getAnimation() != -1;) {
					Time.sleep(100);
				}
			}

		}

		@Override
		public boolean validate() {
			return aField.contains(getLocation()) && !Inventory.isFull();
		}
	}

	private class Check implements Task, Condition {
		@Override
		public void run() {
			

			if (aBank.contains(getLocation())) {
				inBank = true;
			} else {
				inBank = false;
			}
			if (aField.contains(getLocation())) {
				inField = true;
			} else {
				inField = false;
			}
			if (Inventory.isFull()) {
				inventoryFull = true;

			} else {
				inventoryFull = false;
			}

			SceneObject Gate = SceneEntities
					.getNearest(new Filter<SceneObject>() {
						public boolean accept(SceneObject entity) {
							return entity.getId() == 45208;

						}
					});
			if (Gate != null) {
				gateOpen = true;
			}

		}

		@Override
		public boolean validate() {
			return true;
		}
	}

	private class Gate implements Task, Condition {
		@Override
		public void run() {
			SceneObject Gate = SceneEntities
					.getNearest(new Filter<SceneObject>() {
						public boolean accept(SceneObject entity) {
							return entity.getId() == 45208;

						}
					});

			if (getLocation().distance(Gate) <= 7 && !Gate.isOnScreen()) {
				Camera.turnTo(Gate);
			}
			Gate.interact("Open");
			Time.sleep(rand.nextInt(558));
			Time.sleep(4000);
			for (; Players.getLocal().isMoving();) {
				Time.sleep(1000);
			}
			for (; Players.getLocal().getAnimation() != -1;) {
				Time.sleep(100);
			}
			Walking.walk(Field);
			Time.sleep(1000);

		}

		@Override
		public boolean validate() {
			SceneObject Gate = SceneEntities
					.getNearest(new Filter<SceneObject>() {
						public boolean accept(SceneObject entity) {
							return entity.getId() == 45208;

						}
					});
			return !aField.contains(getLocation())
					&& getLocation().distance(Gate) <= 5 && !Inventory.isFull();

		}
	}

	private class Bank implements Task, Condition {

		@Override
		public void run() {

			SceneObject bankStall = SceneEntities
					.getNearest(new Filter<SceneObject>() {
						public boolean accept(SceneObject entity) {
							return entity.getId() == 2012;

						}
					});

			Time.sleep(rand.nextInt(2000));
			bankStall.interact("Bank");

			if (org.powerbot.game.api.methods.widget.Bank.isOpen()) {
				org.powerbot.game.api.methods.widget.Bank.depositInventory();
				org.powerbot.game.api.methods.widget.Bank.close();
			}

		}

		@Override
		public boolean validate() {
			return true;
		}

	}

	private class WalkBack implements Task, Condition {

		@Override
		public void run() {
			if (org.powerbot.game.api.methods.widget.Bank.isOpen()) {
				org.powerbot.game.api.methods.widget.Bank.close();
			}

			for (; !aField.contains(getLocation());) {
				if (getLocation().distance(getGate()) >= 5) {
					Walking.findPath(Field).traverse();
				}

			}
		}

		@Override
		public boolean validate() {

			return !Inventory.isFull() && !aField.contains(getLocation());
		}

	}

	private class Full implements Task, Condition {

		@Override
		public void run() {
			if (aField.contains(getLocation())) {
				goHome();
				if (lodestone.distance(getLocation()) >= 5) {
					goHome();
				}
			}
			for (; !aBank.contains(getLocation());) {
				Walking.newTilePath(tiles).traverse();

			}

		}

		@Override
		public boolean validate() {
			return Inventory.isFull() && !aBank.contains(getLocation());
		}

	}
}
