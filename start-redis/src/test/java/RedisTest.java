import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class RedisTest {

  static {
    List<String> list = new ArrayList<>();
    list.add("a1");
    list.add("a2");
    list.add("a3");
    list.add("a4");
    list.add("a5");
  }

  @Test
  public void testAdd () {

  }
}
