package Netfreaks.Account.Profile;

public enum ProfileType {
    BASIC(2,"Basic"),
    STANDARD(5,"Standard"),
    PREMIUM(5,"Premium");

    private final int maxNProfiles;
    private final String output;
    ProfileType(int maxNProfiles,String output){
        this.output = output;
        this.maxNProfiles = maxNProfiles;
    }

    public int getMaxNProfiles() {
        return maxNProfiles;
    }

    public String getOutput() {
        return output;
    }
}
