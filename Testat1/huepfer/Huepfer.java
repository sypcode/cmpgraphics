import java.awt.Color;
import java.awt.Graphics;

class Huepfer {

	/** Zum Zeichnen in Panel */
	Graphics graphics;

	/** Breite und Höhe Zeichen-Panel */
	int width, height;

	/** Minimal-/Maximalkoordinaten des logischen Koordinatensystems (LKOS) */
	double xMin, xMax, yMin, yMax;

	/** Hüpfer-Parameter */
	double a, b, c;

	/** Anzahl Punkte */
	int num;

	public Huepfer(Graphics graphics,
			int width, int height,
			double xMin, double xMax, double yMin, double yMax,
			double a, double b, double c, 
			int num) {
		super();
		this.graphics = graphics;
		this.width = width;
		this.height = height;
		this.xMin = xMin;
		this.xMax = xMax;
		this.yMin = yMin;
		this.yMax = yMax;
		this.a = a;
		this.b = b;
		this.c = c;
		this.num = num;
	}

	/**
	 * Methode zum Zeichnen eines Pixels.
	 * 
	 * HACK: Zeichne Pixel als Linie der Länge 0. Es gibt in Java2D keine Methode
	 * zum Zeichnen eines einzelnen Pixels!
	 * 
	 * @param graphics Grafik-Kontext
	 * @param x x-Koordinate
	 * @param y y-Koordinate
	 */
	void setPixel(int x, int y) {
		graphics.drawLine(x, y, x, y);
	}

	/**
	 * Wandle LKOS-Koordinate in GKOS-Koordinate um.
	 * 
	 * @param x LKOS-Koordinate
	 * @return GKOS-Koordinate
	 */
	int transformX(double x) {
		return (int) ((x - xMin) * (width - 1) / (xMax - xMin));
	}

	/**
	 * Wandle LKOS-Koordinate in GKOS-Koordinate um.
	 * 
	 * @param y LKOS-Koordinate
	 * @return GKOS-Koordinate
	 */

	int transformY(double y) {
		return (int) ((yMax - y) * (height - 1) / (yMax - yMin));
	}
	public void render() {
		double x = 0;
		double y = 0;
		for (int i = 0; i < num; i++) {
			if (i % 100 == 0) {
				int red   = (i * 3) % 256;
				int green = (i * 7) % 256;
				int blue  = (i * 5) % 256;
				graphics.setColor(new Color(red, green, blue));
			}
			setPixel(transformX(x), transformY(y));
			double xx = y - Math.signum(x) * Math.sqrt(Math.abs(b * x - c));
			double yy = a - x;
			x = xx;
			y = yy;
		}
	}
}
