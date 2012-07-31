package General;

import org.powerbot.concurrent.Task;
import org.powerbot.concurrent.strategy.Condition;
import org.powerbot.concurrent.strategy.Strategy;
import org.powerbot.game.api.ActiveScript;
import org.powerbot.game.api.Manifest;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.wrappers.Area;
import org.powerbot.game.api.wrappers.Tile;

@Manifest(authors = { "Preston3050" }, name = "Skeleton222", description = "This is my first and only testing script", version = 1.0)
public class Trade extends ActiveScript {
	public Tile getLocation() {
		Tile Location = Players.getLocal().getLocation();
		return Location;

	}
	Tile Field = new Tile(3147, 3280, 0);
	Area aParty = new Area(new Tile(3055,3371,0), new Tile(3037,3385,0));
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

	@Override
	protected void setup() {
		final Sample sample = new Sample();
		final Strategy sampleAction = new Strategy(sample, sample);
		provide(sampleAction);
		final Bank bank = new Bank();
		final Strategy bankAction = new Strategy (bank,bank);
		provide(bankAction);
	}

	private class Sample implements Task, Condition {
		@Override
		public void run() {

		}

		@Override
		public boolean validate() {
			return !Inventory.isFull();
			// Logic boolean here

		}
		
	}
	private class Bank implements Task, Condition{

		@Override
		public void run() {
			
			
		}

		@Override
		public boolean validate() {
			// TODO Auto-generated method stub
			return Inventory.isFull();
		}}
	
	

}