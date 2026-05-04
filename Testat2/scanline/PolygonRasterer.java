import java.awt.Graphics;
import java.util.Comparator;
import java.util.LinkedList;

/**
 * Rastert Polygone mit Scanline-Verfahren.
 * 
 * Zur Vereinfachung nehmen wir an, dass sich Polygone immer komplett im
 * Viewport befinden, den Rand also nicht schneiden.
 */
public class PolygonRasterer {
	/** Zum Zeichnen */
	private Graphics graphics;
	/** Höhe des Zeichenfensters */
	private int height;
	/** Die Edge Table */
	private LinkedList<Edge> edgeTable = new LinkedList<>();
	/** Die Active Edge Table */
	private LinkedList<Edge> activeEdgeTable = new LinkedList<>();

	public PolygonRasterer(int height) {
		this.height = height;
	}

	/**
	 * Umsetzung des Scan-Line-Verfahrens
	 */
	public void rasterize() {
		for (int y = 0; y < height; y++) {
			final int scanline = y;

			for (Edge e : edgeTable) {
				if (e.getyMin() == scanline) {
					activeEdgeTable.add(new Edge(e));
				}
			}

			activeEdgeTable.sort(Comparator.comparingDouble(e -> e.getxIntersect()));

			for (int i = 0; i + 1 < activeEdgeTable.size(); i += 2) {
				int x1 = (int) activeEdgeTable.get(i).getxIntersect();
				int x2 = (int) activeEdgeTable.get(i + 1).getxIntersect();
				graphics.drawLine(x1, y, x2, y);
			}

			activeEdgeTable.removeIf(e -> e.getyMax() == scanline);

			for (Edge e : activeEdgeTable) {
				e.setxIntersect(e.getxIntersect() + e.getmReci());
			}
		}
	}

	public void setGraphics(Graphics graphics) {
		this.graphics = graphics;
	}

	public void addEdges(LinkedList<Edge> edges) {
		edgeTable.addAll(edges);
	}
}
