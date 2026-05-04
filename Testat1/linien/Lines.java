import java.awt.Graphics;

public class Lines {
	private final Graphics graphics;

	/**
	 * Verschiedene Methoden zum Zeichnen von Linien
	 * 
	 * @param graphics Grafik-Kontext, in den gezeichnet wird
	 */
	public Lines(Graphics graphics) {
		this.graphics = graphics;
	}

	/**
	 * Methode zum Zeichnen eines Pixels.
	 * 
	 * HACK: Zeichne Pixel als Linie der Länge 0. Es gibt in Java2D keine Methode
	 * zum Zeichnen eines einzelnen Pixels!
	 * 
	 * @param x x-Koordinate
	 * @param y y-Koordinate
	 */
	void setPixel(int x, int y) {
		graphics.drawLine(x, y, x, y);
	}

	/**
	 * Konventionelle Linien-Berechnung über y = m*x + b
	 * 
	 * @param x0 x-Koordinate Startpunkt
	 * @param y0 y-Koordinate Startpunkt
	 * @param x1 x-Koordinate Endpunkt
	 * @param y1 y-Koordinate Endpunkt
	 */
	void drawLineEquation(int x0, int y0, int x1, int y1) {
		double m = (double)(y1 - y0) / (x1 - x0);
		double b = y0 - m * x0;
		for (int i = x0; i <= x1; i++) {
			setPixel(i, (int)(m * i + b));
		}
	}

	// Shift geeignet bis Fensterhöhe 8192
	private final static int SHIFT = 18;
	private final static int GAMMA = 1 << (SHIFT - 5);

	/**
	 * Linien-Berechnung über Digital Differential Analyzer (DDA)
	 * 
	 * @param x0 x-Koordinate Startpunkt
	 * @param y0 y-Koordinate Startpunkt
	 * @param x1 x-Koordinate Endpunkt
	 * @param y1 y-Koordinate Endpunkt
	 */
	void drawDda(int x0, int y0, int x1, int y1) {
		int m = ((y1 - y0) << SHIFT) / (x1 - x0);
        int y = (y0 << SHIFT) + GAMMA;

		for(int x = x0; x <= x1; x++){
			setPixel(x, y >> SHIFT);
			y += m;
		}

    }

	/**
	 * Linien-Berechnung über Bresenham
	 * 
	 * @param x0 x-Koordinate Startpunkt
	 * @param y0 y-Koordinate Startpunkt
	 * @param x1 x-Koordinate Endpunkt
	 * @param y1 y-Koordinate Endpunkt
	 */
	void drawBresenham(int x0, int y0, int x1, int y1) {
		int dx = x1 - x0;
		int dy = y1 - y0;
		int D = 2 * (dy - dx);   // Abweichung: 2(dy-dx) statt 2dy-dx
		int deltaE  = 2 * dy;
		int deltaNE = 2 * (dy - dx);

		int x = x0;
		int y = y0;
		setPixel(x, y);

		while (x < x1) {
			if (D < 0) {
				D += deltaE;
			} else {
				D += deltaNE;
				y++;
			}
			x++;
			setPixel(x, y);
		}
	}
	}

