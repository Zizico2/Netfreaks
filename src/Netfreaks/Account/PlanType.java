package Netfreaks.Account;

/**
 *
 * @author Bernardo Borda d'Agua    53648
 * @author Tiago Guerreiro          53649
 *
 *
 * Representa todos os Planos que cada conta pode adotar.
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

    /**
     * Devolve o numero maximo autorizado de perfis.
     *
     * @return maxNProfiles.
     */
    public int getMaxNProfiles() {
        return maxNProfiles;
    }

    /**
     * Devolve o output associado ao plano.
     *
     * @return output.
     */
    public String getOutput() {
        return output;
    }

    /**
     * Devolve o numero maximo autorizado de dispositivos.
     *
     * @return maxNDevices.
     */
    public int getMaxNDevices() {
        return maxNDevices;
    }
}
