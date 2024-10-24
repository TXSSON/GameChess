package ChessEngine;

public enum ChessColor {
        white, black;

        public static ChessColor opposite(ChessColor color) {
                return (color == ChessColor.white) ? ChessColor.black : ChessColor.white;
        }
}