package net.thumbtack.school.winobjects.v3;

import net.thumbtack.school.winobjects.v3.Desktop;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestDesktop {

    @Test
    public void testDesktop() {
        Desktop desktop = new Desktop(1920, 1080);
        assertAll(
                () -> assertEquals(1920, desktop.getWidth()),
                () -> assertEquals(1080, desktop.getHeight()),
                () -> assertEquals(2073600, desktop.getArea())
        );
    }

    @Test
    public void testVgaDesktop() {
        Desktop desktop = new Desktop();
        assertAll(
                () -> assertEquals(640, desktop.getWidth()),
                () -> assertEquals(480, desktop.getHeight()),
                () -> assertEquals(307200, desktop.getArea())
        );
    }

    @Test
    public void testDesktopsAreEqual() {
        Desktop desktop1 = new Desktop();
        Desktop desktop2 = new Desktop(640, 480);
        Desktop desktop3 = new Desktop(1920, 1080);
        assertEquals(desktop1, desktop2);
        assertNotEquals(desktop1, desktop3);
    }
}
