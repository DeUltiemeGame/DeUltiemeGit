package TheUltimateGame;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class StartingClass extends Applet implements Runnable, KeyListener {
	@Override
	public void init() {
		// Bepaald het speelveld grote
		setSize(1280, 800);
		// Bepaald de achtergrond
		setBackground(Color.black);
		// Zorgt ervoor dat de input naar de app gaat
		setFocusable(true);
		// zorgt ervoor dat er input kan plaats vinden
		addKeyListener(this);
		// Frame zorgt ervoor dat het speeldveld even groot is als het scherm
		Frame frame = (Frame) this.getParent().getParent();
		// Titel van het frame
		frame.setTitle("TheUltimateGame");
	}

	@Override
	public void start() {
		// Maakt een refresh systeem zodat de game op 60fps gaat lopen
		Thread thread = new Thread();
		thread.start();
	}

	@Override
	public void stop() {

	}

	@Override
	public void destroy() {

	}

	@Override
	public void run() {
		while (true) {
			repaint();
			try {
				Thread.sleep(17);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	// de Keylisners, Wat gaan de knoppen doen
	@Override
	public void keyPressed(KeyEvent key) {
		switch (key.getKeyCode()) {
		   case KeyEvent.VK_UP:
		   break;

		   case KeyEvent.VK_DOWN:
		   break;

		   case KeyEvent.VK_LEFT:
		   break;

		   case KeyEvent.VK_RIGHT:
		   break;

		   case KeyEvent.VK_SPACE:
		   break;
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}
}
