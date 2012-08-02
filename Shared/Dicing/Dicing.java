package Shared.Dicing;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Random;

import org.powerbot.concurrent.Task;
import org.powerbot.concurrent.strategy.Condition;
import org.powerbot.concurrent.strategy.Strategy;
import org.powerbot.game.api.ActiveScript;
import org.powerbot.game.api.Manifest;
import org.powerbot.game.api.methods.input.Keyboard;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.bot.event.MessageEvent;
import org.powerbot.game.bot.event.listener.MessageListener;

@Manifest(authors = { "Preston3050" }, name = "Dicing", description = "Preston's Dicing Script", version = 1.0)
public class Dicing extends ActiveScript implements MessageListener {
	Random rand = new Random();
	Random rand2 = new Random();
	String play = "";

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

	@Override
	protected void setup() {
		final Mine mine = new Mine();
		final Strategy mineAction = new Strategy(mine, mine);

		provide(mineAction);

	}

	public class Mine implements Task, Condition {
		@Override
		public void run() {

			if (play != "") {
				int josh = 1 + rand.nextInt(100);
				log.info(josh + "");

				if (josh > 10) {
					log.info("josh > 10");
					int bob = 1 + rand.nextInt(100);

					Keyboard.sendText("/" + play + " has rolled a <" + bob
							+ "> on a percentage die.", true, 20, 35);

				} else {
					log.info("else");
					int bob = 1 + rand.nextInt(57);

					Keyboard.sendText("/" + play + " has rolled a <" + bob
							+ "> on a percentage die.", true, 20, 35);
				}
				josh = 0;
				play = "";
			}
		}

		@Override
		public boolean validate() {
			return true;
		}
	}

	private class Walk implements Task, Condition {

		@Override
		public void run() {

		}

		@Override
		public boolean validate() {
			return true;

		}

	}

	@Override
	public void messageReceived(MessageEvent e) {

		if (e.getMessage().toLowerCase().contains("!roll")
				&& getPlayerName(e.getSender())) {
			play = e.getSender();

		}

	}
}