import java.util.ArrayList;

public class JSONArray {
    private final StringBuilder sb = new StringBuilder();
    private boolean first = true;

    public JSONArray() {
        sb.append("[");
    }

    public JSONArray(String s) {
        sb.append(s);
        sb.deleteCharAt(sb.length() - 1);
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

    public String get(int index) {
        String s = sb.toString();
        boolean tomb = s.charAt(0) == '[' && s.charAt(1) == '[';
        int start = index == 0 && tomb ? 0 : 1;
        int end = 0;
        if (tomb) {
            int count = 0;
            int seged = 0;
            while (count < index) {
                if (s.charAt(start) == '[') {
                    seged++;
                } else if (s.charAt(start) == ']') {
                    seged--;
                    if (seged == 0) count++;
                }

                start++;
            }
            start++;
            end = start + 1;
            while (s.charAt(end) != ']' || seged != 0) {
                if (s.charAt(end) == '[') {
                    seged++;
                } else if (s.charAt(end) == ']') {
                    seged--;
                }
                end++;
            }
            end++;
        } else {
            //START
            int count = 0;
            while (count < index) {
                if (s.charAt(start) == ',') count++;
                start++;
            }
            //END
            end = start + 1;
            while (end < s.length() && s.charAt(end) != ',') end++;
        }

        return s.substring(start, end);
    }

    @Override
    public String toString() {
        return sb + "]";
    }
}
