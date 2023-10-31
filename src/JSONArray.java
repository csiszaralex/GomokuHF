import java.util.ArrayList;

public class JSONArray {
    //    private final ArrayList list = new ArrayList<>();
    private final StringBuilder sb = new StringBuilder();
    private boolean first = true;

    public JSONArray() {
        sb.append("[");
    }
    public void add(Object o) {
        if (first) {
            first = false;
        } else {
            sb.append(",");
        }
        sb.append(o);
    }

    public void add(String o) {
        if (first) {
            first = false;
        } else {
            sb.append(",");
        }
        sb.append("\"").append(o).append("\"");
    }

    @Override
    public String toString() {
        return sb + "]";
    }
}
