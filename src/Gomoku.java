public class Gomoku implements Cloneable {
    public String player1;
    public String player2;
    public int rows;
    public int cols;
    public int win;
    public int[][] grid;

    public Gomoku() {
        this.player1 = "Aladár";
        this.player2 = "Béla";
        this.rows = this.cols = 3;
        this.win = 3;
        this.grid = new int[rows][cols];
    }

    public void Init(String p1, String p2, int r, int c, int w) {
        this.player1 = p1;
        this.player2 = p2;
        this.rows = r;
        this.cols = c;
        this.win = w;
        this.grid = new int[rows][cols];
    }

    public void Rematch() {
        String save = this.player1;
        this.player1 = this.player2;
        this.player2 = save;
        this.grid = new int[rows][cols];
    }
    public Gomoku clone() {
        Gomoku g;
        try {
            g = (Gomoku) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
//        Gomoku g = new Gomoku();
        g.player1 = this.player1;
        g.player2 = this.player2;
        g.rows = this.rows;
        g.cols = this.cols;
        g.win = this.win;
        g.grid = new int[rows][cols];
        for(int i = 0; i < this.rows; i++) {
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
        if(!r.matches("[0-9]+")) {
            this.rows = 3;
            return;
        }
        this.rows = Integer.parseInt(r);
    }
    public void setCols(String c) {
        if(!c.matches("[0-9]+")){
            this.cols = 3;
            return;
        }
        this.cols = Integer.parseInt(c);
    }
    public void setWin(String w) {
        if(!w.matches("[0-9]+")){
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
