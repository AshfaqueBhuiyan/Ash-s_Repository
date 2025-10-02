package lab3;

import static org.junit.Assert.*;
import org.junit.Test;
import balloon.Balloon;

public class BalloonTests {

    @Test
    public void testNewBalloonRadius() {
        Balloon b = new Balloon(10);
        assertEquals("A newly constructed Balloon should have radius zero.", 0, b.getRadius());
    }

    @Test
    public void testNewBalloonPoppedStatus() {
        Balloon b = new Balloon(10);
        assertFalse("A newly constructed Balloon should not be popped.", b.isPopped());
    }

    @Test
    public void testInflateWithinMaxRadius() {
        Balloon b = new Balloon(10);
        b.blow(5);
        assertEquals("After calling blow(5) on a Balloon with maximum radius 10, the radius should be 5.", 5, b.getRadius());
        assertFalse("Balloon should not be popped.", b.isPopped());
    }

    @Test
    public void testInflateBeyondMaxRadius() {
        Balloon b = new Balloon(10);
        b.blow(11);
        assertTrue("Inflating beyond the maximum radius should pop the balloon.", b.isPopped());
    }

    @Test
    public void testDeflate() {
        Balloon b = new Balloon(10);
        b.blow(5);
        b.deflate();
        assertEquals("Deflating should reset the balloon's radius to zero.", 0, b.getRadius());
        assertFalse("Deflating should not change the popped status of a non-popped balloon.", b.isPopped());
    }

    @Test
    public void testPop() {
        Balloon b = new Balloon(10);
        b.blow(5);
        b.pop();
        assertTrue("Popping the balloon should set its popped status to true.", b.isPopped());
        assertEquals("Popping the balloon should set its radius to zero.", 0, b.getRadius());
    }

    @Test
    public void testInflateAfterPop() {
        Balloon b = new Balloon(10);
        b.pop();
        b.blow(5);
        assertEquals("A popped balloon should not be able to inflate.", 0, b.getRadius());
    }
}
