package Netfreaks.Account;

/**
 *
 * @author Bernardo Borda d'Agua    53648
 * @author Tiago Guerreiro          53649
 *
 */
public enum PlanType {
    BASIC(2,"Basic", 1),
    STANDARD(5,"Standard", 2),
    PREMIUM(5,"Premium", 4);

    private final int maxNProfiles;
    private final String output;
    private final int maxNDevices;

    PlanType(int maxNProfiles, String output, int maxNDevices){
        this.output = output;
        this.maxNProfiles = maxNProfiles;
        this.maxNDevices = maxNDevices;
    }

    public int getMaxNProfiles() {
        return maxNProfiles;
    }

    public String getOutput() {
        return output;
    }

    public int getMaxNDevices() {
        return maxNDevices;
    }
}
