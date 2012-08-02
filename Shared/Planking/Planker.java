package Shared.Planking;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.powerbot.concurrent.Task;
import org.powerbot.concurrent.strategy.Condition;
import org.powerbot.concurrent.strategy.Strategy;
import org.powerbot.game.api.ActiveScript;
import org.powerbot.game.api.Manifest;
import org.powerbot.game.api.methods.Game;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.input.Mouse;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Bank;
import org.powerbot.game.api.methods.widget.Camera;

import org.powerbot.game.api.util.Time;
import org.powerbot.game.api.util.Timer;
import org.powerbot.game.api.wrappers.Area;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.interactive.NPC;
import org.powerbot.game.api.wrappers.node.Item;
import org.powerbot.game.api.wrappers.node.SceneObject;
import org.powerbot.game.bot.event.listener.PaintListener;

@Manifest(authors = { "Echocage" }, name = "EchoPlanker", description = "Start at varock east bank", version = 1.0)
public class Planker extends ActiveScript implements PaintListener {
	int bob3 = 0;
	int happy = 0;
	public long startTime = 0;

	boolean guiDone = false;

	public long millis = 0;

	public long hours = 0;

	public long minutes = 0;

	public long seconds = 0;

	public long last = 0;
	Timer ptime = new Timer(0);

	Random rand = new Random();
	int numOfPlanks = 0;
	// What you can change!! ***************************************

	static int plankSwitch = 0;

	int restValue = 40;

	int minTime = (1 * 1000);
	int maxTime = (10 * 1000);
	// End of what you can change **********************************

	// These will change automatically
	static int plankID = 8778;
	static int logID = 1521;

	static int plankPrice = 250;

	Area aBank = new Area(new Tile(3250, 3424, 0), new Tile(3257, 3419, 0));
	Tile cBank = new Tile(3253, 3420, 0);
	String status = "";
	Timer antiBanTimer;
	int musicianID = 8700;

	Tile varockCenter = new Tile(3213, 3432, 0);

	public int getPrice(int id) throws IOException {

		String price;
		URL url = new URL(
				"http://services.runescape.com/m=itemdb_rs/viewitem.ws?obj="
						+ id);
		URLConnection con = url.openConnection();
		BufferedReader in = new BufferedReader(new InputStreamReader(
				con.getInputStream()));
		String line;
		while ((line = in.readLine()) != null) {
			if (line.contains("<td>")) {
				price = line.substring(line.indexOf(">") + 1,
						line.indexOf("/") - 1);
				price = price.replace(",", "");
				try {
					return Integer.parseInt(price);
				} catch (NumberFormatException e) {
					return 0;
				}
			}
		}
		return -1;
	}

	public NPC getNpc(String a) {

		if (a.contains("banker")) {

			if (NPCs.getNearest(Bank.BANK_BOOTH_IDS) != null) {
				return NPCs.getNearest(Bank.BANK_BOOTH_IDS);
			}
		}
		if (a.contains("planker")) {

			return NPCs.getNearest(4250);
		}

		return null;
	} // START: Code generated using Enfilade's Easel

	private final Color color1 = new Color(139, 67, 227, 100);
	private final Color color2 = new Color(0, 0, 0);
	private final Color color3 = new Color(222, 255, 0, 229);

	private final BasicStroke stroke1 = new BasicStroke(1);

	private final Font font1 = new Font("Trajan Pro", 0, 14);

	public void onRepaint(Graphics g1) {

		millis = System.currentTimeMillis() - startTime;
		int bob = (int) ptime.getElapsed();
		int bob7 = (int) (happy * numOfPlanks * 3600000D / bob);
		if (numOfPlanks >= 20) {
			bob3 = (int) (numOfPlanks * 3600000D / bob);
		}
		String u = String.valueOf(bob3);
		String m = String.valueOf(numOfPlanks);
		int bob8 = (int) (happy * numOfPlanks);
		String f = String.valueOf(bob8);

		hours = millis / (1000 * 60 * 60);

		millis -= hours * (1000 * 60 * 60);

		minutes = millis / (1000 * 60);

		millis -= minutes * (1000 * 60);

		seconds = millis / 1000;
		Graphics2D g = (Graphics2D) g1;
		g.setColor(Color.YELLOW);
		g.drawLine(Mouse.getX() - 5, Mouse.getY() - 5, Mouse.getX() + 5,
				Mouse.getY() + 5);
		g.drawLine(Mouse.getX() - 5, Mouse.getY() + 5, Mouse.getX() + 5,
				Mouse.getY() - 5);
		g.setColor(color1);
		g.fillRect(547, 255, 190, 260);
		g.setColor(color2);
		g.setStroke(stroke1);
		g.drawRect(547, 255, 190, 260);
		g.setFont(font1);
		g.setColor(color3);
		g.drawString("EchoPlanker", 605, 275);
		g.drawString("Planks Made: " + m, 561, 360);
		g.setColor(color2);
		g.drawLine(605, 277, 684, 277);
		g.setColor(color3);
		g.drawString("Planks Per Hour: " + u, 562, 414);
		g.drawString("Profit:" + f, 562, 466);
		g.drawString("Profit Per Hour:" + bob7, 560, 511);
		g.drawString("Time Running:" + hours + ":" + minutes + ":" + seconds,
				560, 310);

	}

	Tile[] toPlanker = { new Tile(3253, 3422, 0), new Tile(3253, 3423, 0),
			new Tile(3253, 3424, 0), new Tile(3253, 3425, 0),
			new Tile(3254, 3426, 0), new Tile(3255, 3426, 0),
			new Tile(3256, 3427, 0), new Tile(3257, 3428, 0),
			new Tile(3258, 3428, 0), new Tile(3259, 3428, 0),
			new Tile(3260, 3428, 0), new Tile(3261, 3428, 0),
			new Tile(3262, 3428, 0), new Tile(3263, 3428, 0),
			new Tile(3264, 3428, 0), new Tile(3265, 3428, 0),
			new Tile(3266, 3428, 0), new Tile(3267, 3428, 0),
			new Tile(3268, 3429, 0), new Tile(3269, 3429, 0),
			new Tile(3270, 3429, 0), new Tile(3271, 3429, 0),
			new Tile(3272, 3429, 0), new Tile(3273, 3429, 0),
			new Tile(3274, 3429, 0), new Tile(3275, 3429, 0),
			new Tile(3276, 3429, 0), new Tile(3277, 3429, 0),
			new Tile(3278, 3430, 0), new Tile(3279, 3431, 0),
			new Tile(3280, 3432, 0), new Tile(3280, 3433, 0),
			new Tile(3281, 3434, 0), new Tile(3282, 3434, 0),
			new Tile(3283, 3435, 0), new Tile(3284, 3435, 0),
			new Tile(3285, 3436, 0), new Tile(3285, 3437, 0),
			new Tile(3285, 3438, 0), new Tile(3285, 3439, 0),
			new Tile(3285, 3440, 0), new Tile(3285, 3441, 0),
			new Tile(3285, 3442, 0), new Tile(3285, 3443, 0),
			new Tile(3285, 3444, 0), new Tile(3285, 3445, 0),
			new Tile(3285, 3446, 0), new Tile(3285, 3447, 0),
			new Tile(3285, 3448, 0), new Tile(3325, 3472, 0),
			new Tile(3286, 3449, 0), new Tile(3287, 3450, 0),
			new Tile(3288, 3451, 0), new Tile(3288, 3452, 0),
			new Tile(3288, 3453, 0), new Tile(3288, 3454, 0),
			new Tile(3288, 3455, 0), new Tile(3288, 3456, 0),
			new Tile(3289, 3457, 0), new Tile(3290, 3458, 0),
			new Tile(3291, 3459, 0), new Tile(3292, 3460, 0),
			new Tile(3293, 3461, 0), new Tile(3293, 3462, 0),
			new Tile(3293, 3463, 0), new Tile(3294, 3464, 0),
			new Tile(3294, 3465, 0), new Tile(3294, 3466, 0),
			new Tile(3294, 3467, 0), new Tile(3294, 3468, 0),
			new Tile(3294, 3469, 0), new Tile(3294, 3470, 0),
			new Tile(3294, 3471, 0), new Tile(3294, 3472, 0),
			new Tile(3294, 3473, 0), new Tile(3294, 3474, 0),
			new Tile(3294, 3475, 0), new Tile(3294, 3476, 0),
			new Tile(3295, 3477, 0), new Tile(3296, 3478, 0),
			new Tile(3297, 3479, 0), new Tile(3298, 3480, 0),
			new Tile(3299, 3481, 0), new Tile(3299, 3482, 0),
			new Tile(3300, 3483, 0), new Tile(3300, 3484, 0),
			new Tile(3300, 3485, 0), new Tile(3308, 3525, 0),
			new Tile(3300, 3487, 0), new Tile(3301, 3488, 0),
			new Tile(3301, 3489, 0), new Tile(3302, 3490, 0),
			new Tile(3303, 3491, 0) };

	public boolean isEmpty() {
		for (int c = 0; c <= 28; c++) {
			if (Widgets.get(679, 0).getChild(c).getChildId() != -1) {
				return false;
			}
		}

		return true;

	}

	public int x() {
		int x = Integer.parseInt(Widgets.get(750, 6).getText());
		return x;
	}

	public int greg(int a) {
		int honey = 0;
		try {
			honey = getPrice(a);
		} catch (IOException e) {

			e.printStackTrace();
		}

		return honey;
	}

	public void music() {
		NPC musician = NPCs.getNearest(musicianID);

		if (x() <= 40 && musician.getLocation().distanceTo() <= 10) {
			if (Players.getLocal().getAnimation() != 11786
					&& Players.getLocal().getAnimation() != 5713) {

				while (Players.getLocal().isMoving() && !musician.isOnScreen()) {
					Time.sleep(100);
				}
				if (musician.isOnScreen()) {
					musician.interact("Listen-to");
					Time.sleep(4500 + rand.nextInt(1000));
				} else {
					while (Players.getLocal().isMoving()) {
						Time.sleep(100);
					}
					musician.getLocation().clickOnMap();
				}
			}
			int jacob = 65 + rand.nextInt(100 - 65);
			AntibanTask ab = new AntibanTask();
			while (Players.getLocal().getAnimation() == 11786 && x() <= jacob
					|| Players.getLocal().getAnimation() == 5713
					&& x() <= jacob) {

				Time.sleep(1000);
				ab.run();

			}

		}

	}

	public int inventoryCheck() {
		while (plankSwitch == 0) {
			Time.sleep(100);

		}
		Item[] bob = Inventory.getItems();
		Time.sleep(100 + rand.nextInt(300));

		if (bob.length >= 28) {
			for (int c = 0; c <= 27; c++) {
				if (Widgets.get(679, 0).getChild(c).getChildId() != plankID) {
					break;

				} else {
					if (c == 27) {

						return 2;

					}
				}

			}

			for (int c = 0; c <= 27; c++) {
				if (Widgets.get(679, 0).getChild(c).getChildId() != logID) {
					break;

				} else {
					if (c == 27) {

						return 1;

					}
				}

			}
			for (int c = 0; c <= 27; c++) {
				if (Widgets.get(679, 0).getChild(c).getChildId() == logID
						|| Widgets.get(679, 0).getChild(c).getChildId() == plankID) {

					if (c == 27) {

						return 4;

					}
				} else {
					break;

				}

			}

		}

		return 0;
	}

	public void tradePlanker() {

		if (getNpc("planker").isOnScreen()) {
			if (!Widgets.get(403, 1).isOnScreen()) {
				getNpc("planker").interact("Buy-plank");
				Timer timer = new Timer(3000);
				while (!Widgets.get(403, 1).isOnScreen() && timer.isRunning()) {
					Time.sleep(100);
				}
				Time.sleep(200 + rand.nextInt(500));
			}
			switch (plankSwitch) {
			case 1:
				Widgets.get(403, 12).interact("Buy All");
				break;
			case 2:
				Widgets.get(403, 13).interact("Buy All");
				break;
			case 3:
				Widgets.get(403, 14).interact("Buy All");
				break;
			case 4:
				Widgets.get(403, 15).interact("Buy All");
				break;

			}
			numOfPlanks = numOfPlanks + 28;

			Time.sleep(500 + rand.nextInt(1000));
		}

	}

	public Tile getLocation() {

		Tile Location = Players.getLocal().getLocation();
		return Location;
	}

	@Override
	protected void setup() {
		Timer kevin = new Timer(10000);
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				iTPlankerGUI itpg = new iTPlankerGUI();
				itpg.setVisible(true);
			}
		});

		antiBanTimer = new Timer(maxTime + rand.nextInt(minTime));

		happy = greg(plankID) - greg(logID) - plankPrice;
		startTime = System.currentTimeMillis();

		final Log log = new Log();
		final Strategy logAction = new Strategy(log, log);

		provide(logAction);

		final Plank plank = new Plank();
		final Strategy plankAction = new Strategy(plank, plank);
		provide(plankAction);
		final Banker banker = new Banker();
		final Strategy bankAction = new Strategy(banker, banker);
		provide(bankAction);
		final antiBan anti = new antiBan();
		final Strategy abAction = new Strategy(anti, anti);
		provide(abAction);

		final Problem problem = new Problem();
		final Strategy problemAction = new Strategy(problem, problem);
		provide(problemAction);

	}

	private class Log implements Task, Condition {

		@Override
		public void run() {

			if (getNpc("planker") != null) {

				if (getNpc("planker").isOnScreen()) {
					tradePlanker();

				} else {
					getNpc("planker").getLocation().clickOnMap();
					while (Players.getLocal().isMoving()
							&& !getNpc("planker").isOnScreen()) {
						Time.sleep(100);
					}
				}

			} else {
				NPC musician = NPCs.getNearest(musicianID);
				if (musician != null) {
					if (musician.getLocation().isOnMap() && x() <= restValue) {

						musician.getLocation().clickOnMap();
						while (Players.getLocal().isMoving()
								&& !musician.isOnScreen()) {
							Time.sleep(100);
						}
						music();

					} else {
						if (new Tile(3302, 3491, 0).canReach()) {

							Walking.findPath(new Tile(3302, 3491, 0))
									.traverse();
						} else {
							Walking.newTilePath(toPlanker).traverse();
						}
					}
				} else {

					if (new Tile(3302, 3491, 0).canReach()) {

						Walking.findPath(new Tile(3302, 3491, 0)).traverse();
					} else {
						Walking.newTilePath(toPlanker).traverse();
					}
				}

			}

		}

		@Override
		public boolean validate() {

			return inventoryCheck() == 1 && !aBank.contains(getLocation())
					&& Game.getClientState() != 12;

		}
	}

	private class Plank implements Task, Condition {

		@Override
		public void run() {

			NPC musician = NPCs.getNearest(musicianID);
			if (musician != null) {
				if (musician.getLocation().isOnMap() && x() <= restValue) {
					if (!musician.isOnScreen()) {
						musician.getLocation().clickOnMap();
						while (Players.getLocal().isMoving()
								&& !musician.isOnScreen()) {
							Time.sleep(100);
						}
					}
					music();

				} else {

					Walking.findPath(cBank).traverse();
				}
			} else {
				if (cBank.canReach()) {

					Walking.findPath(cBank).traverse();
				} else {
					Walking.newTilePath(toPlanker).reverse().traverse();
				}
			}

		}

		@Override
		public boolean validate() {

			return inventoryCheck() == 2 && !aBank.contains(getLocation())
					|| isEmpty() && !aBank.contains(getLocation())
					&& Game.getClientState() != 12;
		}

	}

	private class Banker implements Task, Condition {

		@Override
		public void run() {

			if (inventoryCheck() != 1) {
				if (!Bank.isOpen()) {

					SceneObject josh = SceneEntities
							.getNearest(Bank.BANK_BOOTH_IDS);
					if (josh.isOnScreen()) {
						Bank.open();

						Timer timer = new Timer(3000);
						while (!Bank.isOpen() && timer.isRunning()) {
							Time.sleep(100);
						}
					} else {
						Camera.turnTo(josh);
					}
					Timer timer = new Timer(3000);
					while (!Bank.isOpen() && timer.isRunning()) {
						Time.sleep(100);

					}
					Time.sleep(500 + rand.nextInt(500));
				}
				if (Bank.isOpen()) {
					Bank.depositInventory();
					if (Bank.getItem(logID) != null
							&& Bank.getItem(logID).getStackSize() >= 28) {
						Bank.withdraw(logID, 28);

						Bank.close();
					} else {
						log.info("Ran out of planks!");
						ScreenCapture u = new ScreenCapture();
						try {
							u.ScreenCapture2();
						} catch (IOException e) {

							e.printStackTrace();
						}
						Time.sleep(1000000);

					}
				}

			} else {

				Walking.newTilePath(toPlanker).traverse();
			}

		}

		@Override
		public boolean validate() {
			return aBank.contains(getLocation());

		}

	}

	private class Problem implements Task, Condition {

		@Override
		public void run() {
			log.info("There has been an error");
			log.info("Attempting to fix");
			if (getNpc("planker").isOnScreen()) {
				tradePlanker();

			}

		}

		@Override
		public boolean validate() {

			return inventoryCheck() == 4;
		}

	}

	private class antiBan implements Task, Condition {

		@Override
		public void run() {

			AntibanTask ab = new AntibanTask();
			if (!antiBanTimer.isRunning()) {
				ab.run();
				antiBanTimer.setEndIn(minTime + rand.nextInt(maxTime));
			}
		}

		@Override
		public boolean validate() {

			return true;
		}

	}
}