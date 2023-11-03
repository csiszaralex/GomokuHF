public class JSONObject {
    private final StringBuilder sb = new StringBuilder();
    private boolean first = true;

    public JSONObject() {
        sb.append("{");
    }

    public JSONObject(String s) {
        sb.append(s);
        sb.deleteCharAt(sb.length() - 1);
    }

    public void add(String key, String value) {
        if (first) {
            first = false;
        } else {
            sb.append(",");
        }
        sb.append("\"").append(key).append("\":\"").append(value).append("\"");
    }

    public void add(String key, Object value) {
        if (first) {
            first = false;
        } else {
            sb.append(",");
        }
        sb.append("\"").append(key).append("\":").append(value);
    }

    public String get(String key) {
        String s = sb.toString();
        int start = s.indexOf("\"" + key + "\":") + key.length() + 3;
        int end = s.indexOf(",", start);
        if (s.charAt(end - 1) == '"') {
            start++;
            end--;
        }
        if (s.charAt(start) == '[') {
            int tomb = 1;
            end = start + 1;
            while (tomb > 0) {
                if (s.charAt(end) == '[') {
                    tomb++;
                } else if (s.charAt(end) == ']') {
                    tomb--;
                }
                end++;
            }
        }
        return s.substring(start, end);
    }

    @Override
    public String toString() {
        return sb + "}";
    }

}
