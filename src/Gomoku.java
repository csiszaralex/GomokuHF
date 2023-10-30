public class Gomoku implements Cloneable {
    private static Gomoku instance;
    public String player1;
    public String player2;
    public int rows;
    public int cols;
    public int win;
    public CellStatus[][] grid;
    public GameStatus status;

    private Gomoku() {
        this.Init("Aladár", "Béla", 3, 3, 3);
    }

    public static Gomoku getInstance() {
        if (instance == null)
            instance = new Gomoku();

        return instance;
    }

    public void Init(String p1, String p2, int r, int c, int w) {
        this.player1 = p1;
        this.player2 = p2;
        this.rows = r;
        this.cols = c;
        this.win = w;
        this.status = GameStatus.PLAYER1;
        this.resetGrid();
    }

    private void resetGrid() {
        this.grid = new CellStatus[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                this.grid[i][j] = CellStatus.EMPTY;
            }
        }
    }

    public void step(int i, int j) {
        if (this.grid[i][j] != CellStatus.EMPTY) {
            return;
        }
        if (this.status == GameStatus.PLAYER1) {
            this.grid[i][j] = CellStatus.PLAYER1;
            this.status = GameStatus.PLAYER2;
            if (isDraw()) this.status = GameStatus.DRAW;
            if (isWinner(CellStatus.PLAYER1)) this.status = GameStatus.PLAYER1WIN;
        } else {
            this.grid[i][j] = CellStatus.PLAYER2;
            this.status = GameStatus.PLAYER1;
            if (isDraw()) this.status = GameStatus.DRAW;
            if (isWinner(CellStatus.PLAYER2)) this.status = GameStatus.PLAYER2WIN;
        }
    }

    public boolean isDraw() {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                if (this.grid[i][j] == CellStatus.EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isWinner(CellStatus cell) {
        int count = 0;
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols - this.win + 1; j++) {
                for (int k = 0; k < this.win; k++) {
                    if (this.grid[i][j + k] == cell) {
                        count++;
                    } else {
                        count = 0;
                        break;
                    }
                }
                if (count == this.win) {
                    return true;
                }
            }
        }
        count = 0;
        for (int i = 0; i < this.rows - this.win + 1; i++) {
            for (int j = 0; j < this.cols; j++) {
                for (int k = 0; k < this.win; k++) {
                    if (this.grid[i + k][j] == cell) {
                        count++;
                    } else {
                        count = 0;
                        break;
                    }
                }
                if (count == this.win) {
                    return true;
                }
            }
        }
        count = 0;
        for (int i = 0; i < this.rows - this.win + 1; i++) {
            for (int j = 0; j < this.cols - this.win + 1; j++) {
                for (int k = 0; k < this.win; k++) {
                    if (this.grid[i + k][j + k] == cell) {
                        count++;
                    } else {
                        count = 0;
                        break;
                    }
                }
                if (count == this.win) {
                    return true;
                }
            }
        }
        count = 0;
        for (int i = this.win - 1; i < this.rows; i++) {
            for (int j = 0; j < this.cols - this.win + 1; j++) {
                for (int k = 0; k < this.win; k++) {
                    if (this.grid[i - k][j + k] == cell) {
                        count++;
                    } else {
                        count = 0;
                        break;
                    }
                }
                if (count == this.win) {
                    return true;
                }
            }
        }
        return false;

    }

    /*public void Rematch() {
        String save = this.player1;
        this.player1 = this.player2;
        this.player2 = save;
        this.grid = new int[rows][cols];
    }*/
    public Gomoku clone() {
        Gomoku g;
        try {
            g = (Gomoku) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
        g.player1 = this.player1;
        g.player2 = this.player2;
        g.rows = this.rows;
        g.cols = this.cols;
        g.win = this.win;
        g.grid = new CellStatus[rows][cols];
        for (int i = 0; i < this.rows; i++) {
            System.arraycopy(this.grid[i], 0, g.grid[i], 0, this.cols);
        }
        return g;
    }

    public void setPlayer1(String p) {
        this.player1 = p;
    }

    public void setPlayer2(String p) {
        this.player2 = p;
    }

    public void setRows(String r) {
        if (!r.matches("[0-9]+")) {
            this.rows = 3;
            this.resetGrid();
            return;
        }
        this.rows = Integer.parseInt(r);
        this.resetGrid();
    }

    public void setCols(String c) {
        if (!c.matches("[0-9]+")) {
            this.cols = 3;
            this.resetGrid();
            return;
        }
        this.cols = Integer.parseInt(c);
        this.resetGrid();
    }

    public void setWin(String w) {
        if (!w.matches("[0-9]+")) {
            this.win = 3;
            return;
        }
        this.win = Integer.parseInt(w);
    }

    @Override
    public String toString() {
        return "P1: " + this.player1 + "\nP2: " + this.player2 + "\nRows: " + this.rows + "\nCols: " + this.cols + "\nWin: " + this.win + "\n";
    }
}
