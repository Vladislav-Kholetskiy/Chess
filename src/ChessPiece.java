public abstract class ChessPiece {
    protected String color;
    public boolean check = true;

    public ChessPiece(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public boolean isValidMove(ChessBoard chessBoard, int line, int colum, int toLine, int toColum) {
        // Проверка на выход за пределы поля
        if (!chessBoard.checkPos(colum) || !chessBoard.checkPos(line)
                || !chessBoard.checkPos(toColum) || !chessBoard.checkPos(toLine)) {
            return false;
        }
        // Проверка на шаг с полем с фигурой того же цвета
        if (chessBoard.board[toLine][toColum] != null &&
                chessBoard.board[toLine][toColum].getColor().equals(chessBoard.board[line][colum].getColor())) {
            return false;
        }
        // Проверка на шаг в ту же точку
        return (line != toLine || colum != toColum);
    }
    public boolean isPathClear(ChessBoard chessBoard, int line, int colum, int toLine, int toColum) {
        // Определяем направление движения
        int deltaLine = toLine - line;
        int deltaColum = toColum - colum;
        int stepLine = Integer.signum(deltaLine);
        int stepColum = Integer.signum(deltaColum);
        int currentLine = line + stepLine;
        int currentColum = colum + stepColum;
        // Проверяем препятсвия
        while (currentLine != toLine || currentColum != toColum) {
            // Проверка на выход за границы массива
            if (currentLine < 0 || currentLine >= chessBoard.board.length ||
                    currentColum < 0 || currentColum >= chessBoard.board[currentLine].length) {
                return false; // Выход за границы
            }

            if (chessBoard.board[currentLine][currentColum] != null) {
                return false; // Препятствие найдено
            }
            currentLine += stepLine;
            currentColum += stepColum;
        }
        return true; // Путь чист
    }

    public abstract boolean canMoveToPosition(ChessBoard chessBoard, int line, int colum, int toLine, int toColum);

    public abstract String getSymbol();
}
