package Test;

import api.GeoLocation;
import core.Glocation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GlocationTest {
    GeoLocation g=new Glocation(1.5,4.1,2.3);
    GeoLocation g2=new Glocation(2.0,4.5,7.1);
    @Test
    void x() {
        assertEquals(g.x(),1.5);
        assertNotEquals(g.x(),10);
    }

    @Test
    void y() {
        assertEquals(g.y(),4.1);
        assertNotEquals(g.y(),1.1);
    }

    @Test
    void z() {
        assertEquals(g.z(),2.3);
        assertNotEquals(g.z(),5.1);
    }

    @Test
    void distance() {
        assertEquals(g.distance(g2),7.128814768248646);
    }
}