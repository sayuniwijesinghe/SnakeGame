package model;

import org.testng.annotations.Test;
//import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.testng.Assert.assertEquals;

public class MySnakeTest {

    @Test
    public void testInitialLength() {
        // Arrange
        MySnake mySnake = new MySnake(0, 0, 1);

        // Act
        int initialLength = mySnake.getLength();

        // Assert
        assertEquals(1, initialLength, "Initial length should be one");
    }
}
