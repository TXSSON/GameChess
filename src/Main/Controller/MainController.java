package Main.Controller;

import java.awt.BorderLayout;

import ChessEngine.board.Board;
import ChessEngine.board.Gameplay;
import ChessEngine.board.Tile;
import ChessEngine.piece.Piece;
import Main.Frame.GameFrame;
import Main.Frame.GameOptionsFrame;
import Main.Pnl.PnlBoardChess;
import Main.Pnl.PnlGameOptions;
import Main.Pnl.PnlHome;
import Main.Pnl.PnlTutorial;
import Main.Pnl.PnlSideBar;
import Main.Utils.ColorOption;
import Main.Utils.PieceOption;

public class MainController {

    private HomeScreenController homeScreenController;
    private GameOptionsController gameOptionsController;
    private SideBarController sideBarController;
    private BoardChessController boardChessController;
    
    private GameFrame gameFrame;
    public GameOptionsFrame gameOptionsFrame;
    
    public PnlHome pnlHome;
    public PnlGameOptions pnlGameOptions;
    public PnlSideBar pnlSideBar;
    public PnlTutorial pnlTutorial;
    
	public ColorOption selectedColor;
	public PieceOption selectedPiece;
	public String playTime;
	public PnlBoardChess pnlBoardChess;
	
	public Gameplay gameplay;
	
	public Boolean playingChess = false;
	public Boolean readedTutorial = false;
	
    
    public MainController() {
        // Khởi tạo các màn hình và controller riêng
        pnlHome = PnlHome.getPnlHomeInstance();
        homeScreenController = new HomeScreenController(pnlHome, this);

        pnlGameOptions = PnlGameOptions.getPnlGameOptionsInstance();
        gameOptionsController = new GameOptionsController(pnlGameOptions, this);

        pnlBoardChess = new PnlBoardChess();
        boardChessController = new BoardChessController(pnlBoardChess, this);
        
        pnlSideBar = PnlSideBar.getSideBarInstance();
        sideBarController = new SideBarController(pnlSideBar, this);
        

        // Tiến hành hiển thị các màn hình
        initGameFrame();
    }
    private void initGameFrame() {
    	gameFrame = new GameFrame();
    	gameFrame.setPnlHome(pnlHome);
    	gameFrame.setPnlSideBar(pnlSideBar);
    	gameFrame.setVisible(true);
    }
    public void initGameOptionsFrame() {
    	gameOptionsFrame = GameOptionsFrame.getgameOptionsFrameInstance(pnlGameOptions);
    }
    public void initNewGame() {
    	playingChess = true;
    	pnlBoardChess.setPnlBoardChess(selectedColor);
    	pnlHome.setPnlNewGame(pnlBoardChess);	
    }
    public void initTutorial() {
    	readedTutorial = true;
    	pnlTutorial = PnlTutorial.getPnlTutorialInstance();
    	pnlHome.setPnlTutorial(pnlTutorial);
    }
	public void addPice() {
	    // Thêm quân cờ vào bàn cờ
	    if (selectedPiece.getName().equals("Black")) {
	        for (int row = 0; row < Board.MAX_ROW; row++) {
	            for (int col = 0; col < Board.MAX_COL; col++) {
	                Tile tile = gameplay.board.tiles[row][col]; // Lấy ô tương ứng cho quân đen
	                performAddPiece(tile, row, col);      	
	            }
	        }
	    } else {
	        for (int row = 0; row < Board.MAX_ROW; row++) {
	            for (int col = 0; col < Board.MAX_COL; col++) {
	                Tile tile = gameplay.board.tiles[Board.MAX_ROW - row - 1][col]; // Lấy ô tương ứng cho quân đen
	                performAddPiece(tile, row, col);           	
	            }
	        }
	    }
	}
	private void performAddPiece(Tile tile, int row,int col) {
       
        try {
            Piece piece = tile.getPiece(); // Lấy quân cờ ở ô đó
            if (piece != null) {
                String path = piece.getImagePath();
                System.out.println("Adding piece at (" + row + ", " + col + "): " + path);
                try {
                pnlBoardChess.addPieceToPanel(path, row, col); // Thêm quân cờ vào panel
                }
                catch(Exception ex) {
                	ex.printStackTrace();
                }
                } else {
                System.out.println("No piece at (" + row + ", " + col + ")");
            }
        } catch (Exception ex) {
            ex.printStackTrace(); // Ghi lại ngoại lệ
        }
	}
	

	
	

}


