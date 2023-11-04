import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class JSONArrayTest {
    JSONArray array;

    @BeforeEach
    public void init() {
        array = new JSONArray();
    }

    @Test
    public void goodInit() {
        assertEquals(array.toString(), "[]");
    }

    @Test
    public void addAndGetInt() {
        array.add(5);
        array.add(6);
        array.add(999);
        assertEquals(array.toString(), "[5,6,999]");
        assertEquals(array.get(0), "5");
        assertEquals(array.get(2), "999");
    }

    @Test
    public void addAndGetStr() {
        array.add("Alma");
        array.add("Kacsa");
        assertEquals(array.toString(), "[\"Alma\",\"Kacsa\"]");
        assertEquals(array.get(1), "\"Kacsa\"");
    }

    @Test
    public void addAndGetBoth() {
        array.add(5);
        array.add(6);
        array.add("Alma");
        array.add("Kacsa");
        assertEquals(array.toString(), "[5,6,\"Alma\",\"Kacsa\"]");
        assertEquals(array.get(0), "5");
        assertEquals(array.get(2), "\"Alma\"");
    }

    @Test
    public void initWithString() {
        JSONArray arr = new JSONArray("[[1,2,3],[4,5,6][7,8,9]]");
        assertEquals(arr.toString(), "[[1,2,3],[4,5,6][7,8,9]]");
        JSONArray sub = new JSONArray(arr.get(1));
        assertEquals(sub.toString(), "[4,5,6]");
        assertEquals(sub.get(0), "4");
    }

}
