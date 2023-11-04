import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class JSONObjectTest {
    JSONObject obj;

    @BeforeEach
    public void init() {
        obj = new JSONObject();
    }

    @Test
    public void addAndGetKeys() {
        obj.add("p1", "Aladár");
        obj.add("p2", "Béla");
        obj.add("rows", 5);
        obj.add("cols", 9);
        assertEquals(obj.toString(), "{\"p1\":\"Aladár\",\"p2\":\"Béla\",\"rows\":5,\"cols\":9}");
        assertEquals(obj.get("p1"), "Aladár");
        assertEquals(obj.get("rows"), "5");

    }
}
