import java.awt.Graphics;

import colorscheme.ColorScheme;

public class Mandelbrot {
	/** Graphics-Objekt zum Zeichnen */
	private Graphics graphics;
	/** Fensterdimensionen */
	private int width;
	private int height;
	/** Minimal- und Maximalkoordinaten des logischen Koordinatensystems */
	private double xMin;
	private double yMin;
	private double xMax;
	private double yMax;
	/**
	 * Maximalzahl der Iterationen pro Bildpunkt, falls erreicht, handelt es sich um
	 * einen Punkt der Mandelbrotmenge
	 */
	private int maxiter;
	/** Farbschema */
	private ColorScheme colorScheme;

	/**
	 * Initialisiere den Mandelbrot-Renderer.
	 * 
	 * @param graphics    Graphics-Objekt zum Zeichnen
	 * @param width       Breite Fenster
	 * @param height      Höhe Fenster
	 * @param xMin        minimale logische x-Koordinate
	 * @param yMin        minimale logische y-Koordinate
	 * @param xMax        maximale logische x-Koordinate
	 * @param yMax        maximale logische y-Koordinate
	 * @param maxiter     maximale Zahl der Iterationen
	 * @param colorScheme ein Farbschema aus dem Paket colorscheme
	 */
	public Mandelbrot(Graphics graphics, int width, int height, double xMin, double yMin, double xMax, double yMax,
			int maxiter, ColorScheme colorScheme) {
		this.graphics = graphics;
		this.width = width;
		this.height = height;
		this.xMin = xMin;
		this.yMin = yMin;
		this.xMax = xMax;
		this.yMax = yMax;
		this.maxiter = maxiter;
		this.colorScheme = colorScheme;
	}

	/**
	 * Methode zum Zeichnen eines Pixels.
	 * 
	 * HACK: Zeichne Pixel als Linie der Länge 0. Es gibt in Java2D keine Methode
	 * zum Zeichnen eines einzelnen Pixels!
	 * 
	 * @param g Grafik-Kontext
	 * @param x x-Koordinate
	 * @param y y-Koordinate
	 */
	private void setPixel(Graphics g, int x, int y) {
		if (x >= 0 && x < width && y >= 0 && y < height)
			g.drawLine(x, y, x, y);
	}

	/**
	 * Wandle GKOS-Koordinate in LKOS-Koordinate um.
	 * 
	 * @param px GKOS-Koordinate
	 * @return LKOS-Koordinate
	 */
	double transformPx(int px) {
        return xMin + px * (xMax - xMin) / width; // TODO: Hier Code einfuegen ...
	}

	/**
	 * Wandle GKOS-Koordinate in LKOS-Koordinate um.
	 * 
	 * @param py GKOS-Koordinate
	 * @return LKOS-Koordinate
	 */
	double transformPy(int py) {
        return yMax - py * (yMax - yMin) / height; // TODO: Hier Code einfuegen ...
	}

	/**
	 * Zeichnen der Mandelbrotmenge
	 */
	public void render() {
		// Iterieren über komplexe Zahlenebene
		for (int px = 0; px < width; px++) {
			for (int py = 0; py < height; py++) {
				// Zähler für die Anzahl der Iterationen
				int iter = 0;
                double cx = transformPx(px);
                double cy = transformPy(py);
                double zx = 0, zy = 0;

                while (iter < maxiter && zx*zx + zy*zy < 4) {
                    double zxNew = zx*zx - zy*zy + cx;
                    double zyNew = 2*zx*zy + cy;
                    zx = zxNew;
                    zy = zyNew;
                    iter++;
                }
                graphics.setColor(colorScheme.colorForNumIterations(iter));
				setPixel(graphics, px, py);
			}
		}
	}
}
