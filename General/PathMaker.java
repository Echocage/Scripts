package General;

import org.powerbot.concurrent.Task;
import org.powerbot.concurrent.strategy.Condition;
import org.powerbot.concurrent.strategy.Strategy;
import org.powerbot.game.api.ActiveScript;
import org.powerbot.game.api.Manifest;
import org.powerbot.game.api.methods.Calculations;
import org.powerbot.game.api.methods.Game;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.bot.event.listener.PaintListener;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
/**
* @author Re4PzZ
*                www.re4pzz.bplaced.net
*/
@Manifest(authors = "Re4PzZ", name = "~ path Maker ~", version = 1.0, description = "Records a path as you walk it.")
public class PathMaker extends ActiveScript implements PaintListener {

        private static final ArrayList<Tile> path = new ArrayList<Tile>();

        public void onStop() {
                try {
                        File file = new File("GeneratedPath.txt");
                        FileWriter outFile = new FileWriter(file);
                        PrintWriter out = new PrintWriter(outFile);
                        out.println("// Start: Code generated by ~ path Maker ~");
                        out.println("");
                        out.println("Tile[] tiles = {");
                        Tile[] arr = path.toArray(new Tile[path.size()]);
                        int i = 0;
                        for (Tile t : arr) {
                                i++;
                                out.println("  new Tile(" + t.getX() + ", " + t.getY() + ", " + t.getPlane() +
                                                (i != arr.length ? ")," : ")"));
                        }
                        out.println("};");
                        out.println("");
                        out.print("// End: Code generated by ~ path Maker ~");
                        out.close();
                        log.info("Created path in a File: GeneratedPath.txt");
                } catch (Exception e) {
                        log.info("File creation failed; path not saved.");
                }
        }

        @Override
        protected void setup() {
                final Loop loop = new Loop();
                provide(new Strategy(loop,  loop));
        }

        @Override
        public void onRepaint(Graphics graphics) {
                Graphics2D g = (Graphics2D) graphics;
                for (Tile t : path.toArray(new Tile[path.size()])) {
                        g.setColor(new Color(0, 0, 0));
                        g.drawPolygon(t.getBounds()[0]);
                        g.setColor(new Color(0, 0, 128, 75));
                        g.fillPolygon(t.getBounds()[0]);
                }

        }

        public class Loop implements Task, Condition {

                @Override
                public boolean validate() {
                        return Game.isLoggedIn();
                }

                @Override
                public void run() {
                        Tile location = Players.getLocal().getLocation();
                        if (Game.isLoggedIn() && location != null && !path.contains(location)) {
                                path.add(Players.getLocal().getLocation());
                        }
                }
        }
}