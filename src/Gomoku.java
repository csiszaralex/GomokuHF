import javax.swing.*;
import java.io.*;
import java.util.Arrays;

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
        this.reset();
    }
    public void reset() {
        this.Init("", "", 3, 3, 3);
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

    public boolean isEnded() {
        return this.status == GameStatus.PLAYER1WIN || this.status == GameStatus.PLAYER2WIN || this.status == GameStatus.DRAW;
    }

    public void rematch() {
        String save = this.player1;
        this.player1 = this.player2;
        this.player2 = save;
        status = GameStatus.PLAYER1;
        this.resetGrid();
    }

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

    public void save() {
        JSONObject json = getJsonObject();

        try {
            File savesFolder = new File("saves");
            if (!savesFolder.exists()) savesFolder.mkdir();

            FileWriter fileWriter = new FileWriter("saves/" + player1 + "_" + player2 + "_" + rows + "_" + cols + "_" + System.currentTimeMillis() + ".json");
            fileWriter.write(json.toString());
            fileWriter.close();
            JOptionPane.showMessageDialog(null, "A mentés sikeres!");
        } catch (IOException e) {
            System.out.println("HIBA(IO): " + e.getMessage());
        }
    }

    public void load(File file) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String jsons = br.readLine();
            br.close();
            JSONObject json = new JSONObject(jsons);
            player1 = json.get("player1");
            player2 = json.get("player2");
            rows = Integer.parseInt(json.get("rows"));
            cols = Integer.parseInt(json.get("cols"));
            win = Integer.parseInt(json.get("win"));
            status = GameStatus.values()[Integer.parseInt(json.get("status"))];
            resetGrid();
            JSONArray array = new JSONArray(json.get("grid"));
            for (int i = 0; i < rows; i++) {
                JSONArray subArray = new JSONArray(array.get(i));
                for (int j = 0; j < cols; j++) {
                    grid[i][j] = CellStatus.values()[Integer.parseInt(subArray.get(j))];
                }
            }

        } catch (IOException e) {
            System.out.println("HIBA(IO): " + e.getMessage());
        }
    }

    private JSONObject getJsonObject() {
        JSONObject json = new JSONObject();
        json.add("player1", player1);
        json.add("player2", player2);
        json.add("rows", rows);
        json.add("cols", cols);
        json.add("win", win);
        json.add("status", status.ordinal());

        // Hozzáadjuk a mátrixot a JSON objektumhoz
        JSONArray gridArray = new JSONArray();
        for (int i = 0; i < rows; i++) {
            JSONArray rowArray = new JSONArray();
            for (int j = 0; j < cols; j++) {
                rowArray.add(grid[i][j].ordinal());
            }
            gridArray.add(rowArray);
        }
        json.add("grid", gridArray);
        return json;
    }

    public boolean equals(Gomoku g) {
        boolean eq = this.player1.equals(g.player1);
        eq = eq || this.player1.equals(g.player2);
        eq = eq || this.cols == g.cols;
        eq = eq || this.rows == g.rows;
        eq = eq || this.win == g.win;
        eq = eq || Arrays.deepEquals(this.grid, g.grid);
        return eq;
    }

    @Override
    public String toString() {
        return "P1: " + this.player1 + "\nP2: " + this.player2 + "\nRows: " + this.rows + "\nCols: " + this.cols + "\nWin: " + this.win + "\nStatus:" + this.status + "\n";
    }
}
