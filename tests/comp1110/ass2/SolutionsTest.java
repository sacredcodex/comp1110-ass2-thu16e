package comp1110.ass2;

import org.junit.jupiter.api.*;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static comp1110.ass2.Games.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SolutionsTest {

    private void test(String objective, String expected) {
        String out = IQStars.getSolution(objective);
        assertNotNull(out, "No solution returned for objective " + objective + ", expected " + expected);
        assertTrue(out.equals(expected), "For objective " + objective + ", was expecting " + expected + ", but got " + out);
    }

    @Test
    @Timeout(value = 5000, unit = MILLISECONDS)
    public void testStarter() {
        for (int i = 0; i < 24; i++) {
            test(ALL_CHALLENGES[i], ALL_CHALLENGES_SOLUTIONS[i]);
        }
    }

    @Test
    @Timeout(value = 5000, unit = MILLISECONDS)
    public void testJunior() {
        for (int i = 24; i < 24*2; i++) {
            test(ALL_CHALLENGES[i], ALL_CHALLENGES_SOLUTIONS[i]);
        }
    }

    @Test
    @Timeout(value = 5000, unit = MILLISECONDS)
    public void testExpert() {
        for (int i = 24*2; i < 24*3; i++) {
            test(ALL_CHALLENGES[i], ALL_CHALLENGES_SOLUTIONS[i]);
        }
    }

    @Test
    @Timeout(value = 5000, unit = MILLISECONDS)
    public void testMaster() {
        for (int i = 24*3; i < 24*4; i++) {
            test(ALL_CHALLENGES[i], ALL_CHALLENGES_SOLUTIONS[i]);
        }
    }

    @Test
    @Timeout(value = 5000, unit = MILLISECONDS)
    public void testWizard() {
        for (int i = 24*4; i < 24*5; i++) {
            test(ALL_CHALLENGES[i], ALL_CHALLENGES_SOLUTIONS[i]);
        }
    }

    @Test
    @Timeout(value = 1000, unit = MILLISECONDS)
    public void testMasterFast() {
        for (int i = 24*3; i < 24*4; i++) {
            test(ALL_CHALLENGES[i], ALL_CHALLENGES_SOLUTIONS[i]);
        }
    }

    @Test
    @Timeout(value = 1000, unit = MILLISECONDS)
    public void testWizardFast() {
        for (int i = 24*4; i < 24*5; i++) {
            test(ALL_CHALLENGES[i], ALL_CHALLENGES_SOLUTIONS[i]);
        }
    }
}
