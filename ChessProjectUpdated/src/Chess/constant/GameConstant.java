package Chess.constant;

import java.awt.Color;
import java.awt.Dimension;

public interface GameConstant {
	static final int GAME_WIDTH = 735;
	static final int GAME_HEIGHT = 735;
	static final int GAME_SIZE = 8;
	static final Dimension BOARD_PANEL_DIMENSION = new Dimension(600, 600);
	static final Dimension TILE_PANEL_DIMENSION = new Dimension(BOARD_PANEL_DIMENSION.width / GAME_SIZE, 
																BOARD_PANEL_DIMENSION.height / GAME_SIZE);
	static final Color LIGHT_COLOR = Color.decode("#e4e4ee");
	static final Color DARK_COLOR = Color.decode("#5ea24a");
	static final Color SELECTED_COLOR = Color.decode("#2596be");
	static final Color MOVE_COLOR = Color.decode("#e5d154");
	static final String RESOURCE_PATH = "resources/";
	static final Color WARNING_COLOR = Color.decode("#be4d25");
}
