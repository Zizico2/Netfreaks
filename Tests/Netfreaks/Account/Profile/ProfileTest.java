package Netfreaks.Account.Profile;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProfileTest {

    @Test
    void watch() {
        Profile testProfile = new ProfileClass("testName");
        for(int i = 0; i <= 11; i++){
            int size = testProfile.getHistory().size();
            testProfile.watch("testName" + i);
            int diference = testProfile.getHistory().size() - size;
            if (size < 10)
                assert diference == 1;
            else
                assert diference == 0;
        }
    }

    @Test
    void isInHistory() {
        Profile testProfile = new ProfileClass("testName");
        for(int i = 0; i <= 11; i++) {
            String testName = "testName" + i;
            testProfile.watch(testName);
            boolean isInHistory = testProfile.isInHistory(testName);
            assert isInHistory;
        }
    }

    @Test
    void getHistory() {
        String testName = "testName";

        Profile testProfile = new ProfileClass(testName);

        for (int i = 0; i <= 11; i++) {
            String testNameI = testName + i;
            String oldestProduct = "";
            if(testProfile.getHistory().size() != 0)
                oldestProduct = testProfile.getHistory().get(0);
            testProfile.watch(testNameI);
            List<String> testString = testProfile.getHistory();
            assertEquals(testNameI,testString.get(testString.size() - 1));
            if (i >= 10)
                assertNotEquals(oldestProduct, testProfile.getHistory().get(0));
            else if (testProfile.getHistory().size() != 1)
                assertEquals(oldestProduct, testProfile.getHistory().get(0));


        }
    }
}