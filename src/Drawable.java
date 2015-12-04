import print.color.ColoredPrinter;

/**
 * Type de base pour touts les elements dessinables
 */
public abstract class Drawable {

	/**
	 * Methode de dessin
	 * @param printer
	 */
	public abstract void draw(ColoredPrinter printer);
}
