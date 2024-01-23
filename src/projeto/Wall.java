package projeto;
import pt.iscte.poo.gui.ImageTile;
import pt.iscte.poo.utils.Point2D;
public class Wall extends GameElement {
		
		public Wall(Point2D position) {
			super(position);
		}

		@Override
		public String getName() {
			return "Wall";
		}

		@Override
		public Point2D getPosition() {
			return position;
		}

		@Override
		public int getLayer() {
			return 0;
		}
}
