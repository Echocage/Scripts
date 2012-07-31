package General;

import org.powerbot.concurrent.Task;
import org.powerbot.concurrent.strategy.Strategy;
import org.powerbot.game.api.ActiveScript;
import org.powerbot.game.api.Manifest;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.input.Keyboard;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.util.Filter;
import org.powerbot.game.api.util.Time;
import org.powerbot.game.bot.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

import org.powerbot.game.api.wrappers.interactive.Player;
import org.powerbot.game.bot.event.listener.MessageListener;



@Manifest(authors = { "Preston3050" }, name = "TradeBot", description = "This is my first and only testing script", version = 1.0)
public class Tradebot extends ActiveScript implements MessageListener {
	ArrayList<String> al = new ArrayList<String>();
	Random rand = new Random();
	int z = 0;
	int c = 0;
	int gold = 0;
	String play = "";
	String list = "";

	public void give1() {
		while (!Widgets.get(335, 49).getText().contains("50,000")) {
			if (Widgets.get(335, 49).getText().contains("100,000")
					|| Widgets.get(335, 49).getText().contains("150,000")) {
				Widgets.get(335, 9).click(true);

			}
			Widgets.get(335).getChild(53).click(true);
			Time.sleep(1000);
			while (Widgets.get(137, 53).isOnScreen()) {
				Time.sleep(1000);
				Widgets.get(335).getChild(53).click(true);
			}
			Keyboard.sendText("50000", true);
			Time.sleep(1000);
		}
		Widgets.get(335, 18).click(true);
		for (; !Widgets.get(334).getChild(8).isOnScreen();) {
			Time.sleep(100);
		}
		Widgets.get(334, 21).click(true);
		for (; Widgets.get(335).getChild(9).isOnScreen()
				|| Widgets.get(334).getChild(8).isOnScreen();) {
			Time.sleep(100);
		}
		play = "";
	}

	public void tradePlayer() {

		give1();

	}

	public void messageReceived(MessageEvent e) {
		if (!Widgets.get(335).getChild(9).isOnScreen()
				|| !Widgets.get(334).getChild(8).isOnScreen()) {
			if (e.getMessage().toLowerCase().contains("ilovehf")
					|| e.getMessage().toLowerCase().contains("i love rs")) {
				if (!getPlayerName(e.getSender())) {
					play = e.getSender();
					writePlayername();

				}

			}
		}
	}

	protected void setup() {
		final Sample sample = new Sample();
		final Strategy sampleAction = new Strategy(sample, sample);
		provide(sampleAction);
		final Trade trade = new Trade();
		final Strategy tradeAction = new Strategy(trade, trade);
		provide(tradeAction);

	}

	public class Trade extends Strategy implements Task {

		public void run() {

			tradePlayer();
		}

		public boolean validate() {
			return (Widgets.get(335)).getChild(9).isOnScreen()
					|| Widgets.get(334).getChild(8).isOnScreen();
		}

	}

	private class Sample extends Strategy implements Task {

		public void run() {
			AntibanTask antiObject = new AntibanTask();
			if (rand.nextInt(30) == 1) {
				antiObject.run();
			}
			for (; !Widgets.get(137, 53).isOnScreen();) {
				Widgets.get(137, 4).click(true);

			}

			Player bob = Players.getNearest(new Filter<Player>() {
				public boolean accept(Player player) {
					return player.getName() == (play);
				}

			});

			if (bob != null) {

				if (bob.isOnScreen()) {

					bob.interact("Trade");

					c++;

					Time.sleep(5000);

				} else {
					Walking.walk(bob.getLocation());
				}
			}
		}

		public boolean validate() {
			return !(Widgets.get(335)).getChild(9).isOnScreen()
					&& !Widgets.get(334).getChild(8).isOnScreen();
		}
	}

	public void writePlayername() {
		try {
			FileWriter fStream = new FileWriter("playernames.txt", true);
			BufferedWriter out = new BufferedWriter(fStream);
			out.write(play + " ");
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean getPlayerName(String josh) {
		try {
			FileInputStream fStream = new FileInputStream("playernames.txt");
			DataInputStream in = new DataInputStream(fStream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));

			String line;

			while ((line = br.readLine()) != null) {
				if (line.contains(josh)) {
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
