package Netfreaks.Account.Profile;


import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.runner.RunWith;
import java.util.List;

@RunWith(Arquillian.class)
public class ProfileClassTest {

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(ProfileClass.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    public void testProfileHistoryEmpty(){
        Profile profile1 = new ProfileClass("Rift Herald");
        Profile profile2 = new ProfileClass("Jonh Cena",12);
        List<String> history1 = profile1.getHistory();
        List<String> history2 = profile2.getHistory();
        assert history1.isEmpty();
        assert history2.isEmpty();
    }
}
