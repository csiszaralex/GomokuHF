public class JSONObject {
    private final StringBuilder sb = new StringBuilder();
    private boolean first = true;

    public JSONObject() {
        sb.append("{");
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

    @Override
    public String toString() {
        return sb + "}";
    }

}
