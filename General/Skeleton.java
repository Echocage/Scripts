import org.powerbot.concurrent.Task;
import org.powerbot.concurrent.strategy.Condition;
import org.powerbot.concurrent.strategy.Strategy;
import org.powerbot.game.api.ActiveScript;
import org.powerbot.game.api.Manifest;

@Manifest(authors = { "Preston3050" }, name = "Void", description = "This is my first and only testing script", version = 1.0)
public class Skeleton extends ActiveScript {

	@Override
	protected void setup() {
		final Sample sample = new Sample();
		final Strategy sampleAction = new Strategy(sample, sample);

		provide(sampleAction);

	}

	private class Sample implements Task, Condition {
		@Override
		public void run() {

		}

		@Override
		public boolean validate() {
			return true;
		}
	}

}