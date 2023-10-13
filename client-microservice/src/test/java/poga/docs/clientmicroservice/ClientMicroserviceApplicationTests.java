package poga.docs.clientmicroservice;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ClientMicroserviceApplicationTests {

	@Test
    public void testHello() {
        // Assert
        assertEquals("Hello World!", "Hello World!");
    }

}
