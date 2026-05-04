import static org.junit.Assert.assertEquals;

import java.awt.Color;

import org.junit.Test;

public class LinesTest {

	private static int[] endPoints1 = { 0, 1, 2, 4, 8, 14, 19, };
	
	private static String expected1 = 
			"XXXXXXXXXXXXXXXXXXXX\n" + 
			".XXXXXXXXXXXXXXXXXXX\n" + 
			"..XXXXXX..XXXXX....X\n" + 
			"...X.X..XX.....XXXX.\n" + 
			"....X.X...XX.......X\n" + 
			".....X.XX...XXX.....\n" + 
			"......X..X.....XX...\n" + 
			".......X..X......XX.\n" + 
			"........X..XX......X\n" + 
			".........X...X......\n" + 
			"..........X...X.....\n" + 
			"...........X...XX...\n" + 
			"............X....X..\n" + 
			".............X....X.\n" + 
			"..............X....X\n" + 
			"...............X....\n" + 
			"................X...\n" + 
			".................X..\n" + 
			"..................X.\n" + 
			"...................X\n";
	
	private static int[] endPoints2 = { 4, 5, 6, 8, 10, 14, 19, };
	
	private static String expected2 = 
			  "....................\n"
			+ "....................\n"
			+ "....................\n"
			+ "..XXXXXXXXXXXXXXXXX.\n"
			+ "....XXXXXXXXXXXXXXXX\n"
			+ ".....XXXXXXXX.XXXXXX\n"
			+ "......XXX.XX.XXX...X\n"
			+ ".......X.X..XXX.XXX.\n"
			+ "........X.XX...XX..X\n"
			+ ".........X..X....XX.\n"
			+ "..........X..XX....X\n"
			+ "...........X...X....\n"
			+ "............X...XX..\n"
			+ ".............X....X.\n"
			+ "..............X....X\n"
			+ "...............X....\n"
			+ "................X...\n"
			+ ".................X..\n"
			+ "..................X.\n"
			+ "...................X\n";
	
	

	@Test
	public void testDrawLineEquation1() {
		BufferedGraphics bufferedGraphics = new BufferedGraphics(20, 20);
		bufferedGraphics.setColor(Color.red);
		Lines lineDrawer = new Lines(bufferedGraphics);
		for (int ep : endPoints1) {
			lineDrawer.drawLineEquation(0, 0, 19, ep);
		}
		assertEquals("Zeichnen mit Geradengleichung", expected1, bufferedGraphics.toString());
	}

	@Test
	public void testDrawLineEquation2() {
		BufferedGraphics bufferedGraphics = new BufferedGraphics(20, 20);
		bufferedGraphics.setColor(Color.red);
		Lines lineDrawer = new Lines(bufferedGraphics);
		for (int ep : endPoints2) {
			lineDrawer.drawLineEquation(2, 3, 19, ep);
		}
		assertEquals("Zeichnen mit Geradengleichung", expected2, bufferedGraphics.toString());
	}
	
	@Test
	public void testDrawDda1() {
		BufferedGraphics bufferedGraphics = new BufferedGraphics(20, 20);
		bufferedGraphics.setColor(Color.red);
		Lines lineDrawer = new Lines(bufferedGraphics);
		for (int ep : endPoints1) {
			lineDrawer.drawDda(0, 0, 19, ep);
		}		
		assertEquals("Zeichnen mit DDA", expected1, bufferedGraphics.toString());
	}

	@Test
	public void testDrawDda2() {
		BufferedGraphics bufferedGraphics = new BufferedGraphics(20, 20);
		bufferedGraphics.setColor(Color.red);
		Lines lineDrawer = new Lines(bufferedGraphics);
		for (int ep : endPoints2) {
			lineDrawer.drawDda(2, 3, 19, ep);
		}		
		assertEquals("Zeichnen mit DDA", expected2, bufferedGraphics.toString());
	}

	@Test
	public void testDrawBresenham1() {
		BufferedGraphics bufferedGraphics = new BufferedGraphics(20, 20);
		bufferedGraphics.setColor(Color.red);
		Lines lineDrawer = new Lines(bufferedGraphics);
		for (int ep : endPoints1) {
			lineDrawer.drawBresenham(0, 0, 19, ep);
		}
		assertEquals("Zeichnen mit Bresenham", expected1, bufferedGraphics.toString());
	}

	@Test
	public void testDrawBresenham2() {
		BufferedGraphics bufferedGraphics = new BufferedGraphics(20, 20);
		bufferedGraphics.setColor(Color.red);
		Lines lineDrawer = new Lines(bufferedGraphics);
		for (int ep : endPoints2) {
			lineDrawer.drawBresenham(2, 3, 19, ep);
		}
		assertEquals("Zeichnen mit Bresenham", expected2, bufferedGraphics.toString());
	}
}
