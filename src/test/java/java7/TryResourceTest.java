package java7;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.Closeable;
import java.io.IOException;

import org.junit.Test;


public class TryResourceTest {

    boolean calledCloseA = false;
    boolean calledCloseB = false;

    int cntEx = 0;

    interface Executer {
        void execute();
    }

    class TestClose implements Closeable {
        Executer onClose;

        public TestClose(Executer onClose) {
            this.onClose  = onClose;
        }

        public TestClose(Executer onClose, Executer onNew) {
            this.onClose  = onClose;
            onNew.execute();
        }

        @Override
        public void close() throws IOException {
            onClose.execute();
        }
    }

    @Test
    public void closeCalledOnExeption() {
        assertFalse(calledCloseA);
        try (Closeable foo = new TestClose(() -> calledCloseA = true)) {
            throw new IllegalStateException();
        } catch (Exception e) {
            cntEx++;
        }
        assertTrue(calledCloseA);
        assertEquals(1, cntEx);
    }

    @Test
    public void closeCalledEvenWithExeptionDuringClose() {
        assertFalse(calledCloseA);
        assertFalse(calledCloseB);
        try (Closeable first = new TestClose(() -> { calledCloseA = true; throw new IllegalArgumentException("first");}) ;
                Closeable scnd = new TestClose(() -> { calledCloseB = true; throw new IllegalStateException("second");})) {
            assertTrue(true);
        } catch (Exception e) {
            cntEx++;
            System.out.println(e.getMessage());
        }
        assertTrue(calledCloseA);
        assertTrue(calledCloseB);
        assertEquals(1, cntEx);
    }

    @Test
    public void closeCalledEvenWithExeptionDuringTry() {
        assertFalse(calledCloseA);
        assertFalse(calledCloseB);
        try (Closeable first = new TestClose(() -> calledCloseA = true) ;
                Closeable scnd = new TestClose(() -> calledCloseB = true, () -> {throw new IllegalStateException();})) {
            assertTrue(false);
        } catch (Exception e) {
            cntEx++;
        }
        assertTrue(calledCloseA);
        assertFalse(calledCloseB);
        assertEquals(1, cntEx);
    }


}
