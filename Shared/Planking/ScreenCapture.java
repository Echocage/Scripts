package Shared.Planking;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ScreenCapture {

	public void ScreenCapture2() throws IOException {
		try {
			Robot robot = new Robot();
			Rectangle captureSize = new Rectangle(Toolkit.getDefaultToolkit()
					.getScreenSize());
			BufferedImage bufferedImage = robot
					.createScreenCapture(captureSize);
			ImageIO.write(bufferedImage, "jpg", new File("ScreenShot.jpg"));
		} catch (AWTException e) {
			System.err.println("Someone call a doctor!");
		}

	}
}