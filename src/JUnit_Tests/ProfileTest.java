import Netfreaks.Account.Profile.Profile;
import Netfreaks.Account.Profile.ProfileClass;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 *
 * Class que testa a lista de recentemente vistos da classe profile.
 *
 */
class ProfileTest {

    /**
     * Testa se os produtos sao corretamente adicionados ao historico e se
     * o produto mais antigo e removido quando o historico esta cheio.
     *
     */
    @Test
    void watch() {
        Profile testProfile = new ProfileClass("testName");
        for(int i = 0; i <= 11; i++){
            int size = testProfile.getHistory().size();
            testProfile.watch("testName" + i);
            int difference = testProfile.getHistory().size() - size;
            if (size < 10)
                assert difference == 1;
            else
                assert difference == 0;
        }
    }


    /**
     * Verifica se o funcionamento do metodo isInHistory()
     *
     *
     */
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


    /**
     *
     * Verifica se a ordem da List devolvida pelo getHistory() esta na ordem correta.
     *
     */
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