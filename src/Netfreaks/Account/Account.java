package Netfreaks.Account;

import Netfreaks.Account.Profile.Profile;
import Netfreaks.Product.Product;

import java.util.List;
import java.util.SortedMap;

/**
 *
 * @author Bernardo Borda d'Agua    53648
 * @author Tiago Guerreiro          53649
 *
 */
public interface Account {

    boolean NORMAL = true;

    /**
     * Remove o dispositivo atual da lista de dispositivos associados a conta.
     *
     */
    void disconnect();

    /**
     * Sai da conta sem desligar o dispositivo.
     *
     */
    void logout();

    /**
     * Devolve o nome do perfil ativo.
     *
     * @return currentProfile.
     */
    String getCurrentProfile();

    /**
     * Verifica se a password dada coincide com a password da conta.
     *
     * @param password - Password dada
     * @return true se coincidir;
     *         false se nao.
     */
    boolean isCorrectPassword(String password);

    /**
     * Verifica se lista de dispositivos esta cheia.
     *
     * @return true se estiver;
     *         false se nao estiver.
     */
    boolean isDeviceListFull();

    /**
     * Devolve o nome do dispositivo ativo da conta.
     *
     * @return currentDevice.
     */
    String getActiveDevice();

    /**
     * Coloca o dispositivo dado com ativo na conta.
     *
     * @param device - Nome do dispositivo.
     */
    void login(String device);

    /**
     * Verifica se e preciso adicionar um dispositivo novo.
     *
     * @param device - Nome do dispositvo a verificar.
     * @return true se for preciso;
     *         false se nao.
     */
    boolean needToRegisterDevice(String device);

    /**
     * Regista um novo dispositivo.
     *
     * @param device - Nome do dispositivo.
     */
    void registerDevice(String device);

    /**
     * Devolve o numero do dispositivos associados a conta.
     *
     * @return nDevices.
     */
    int getNDevices();

    /**
     * Devolve o todos os dispositivos associados a conta.
     *
     * @return - devices.
     */
    List<String> getDevices();

    /**
     * Adiciona um novo perfil criança.
     *
     * @param profileName - Nome do perfil.
     * @param ageRestriction - Classificacao etaria.
     */
    void addProfile(String profileName, int ageRestriction);

    /**
     * Adiciona um novo perfil normal.
     *
     * @param profileName - Nome do perfil.
     */
    void addProfile(String profileName);

    /**
     * Verifica se existe um perfil com o nome dado por argumento.
     *
     * @param profileName - Nome do perfil.
     * @return true se exisitr;
     *         fasle se nao.
     */
    boolean hasProfile(String profileName);

    /**
     * Devolve o numero total de perfis na conta.
     *
     * @return profiles.size().
     */
    int getNProfiles();


    /**
     * Muda o perfil ativo para o perfil com o nome dado por argumento.
     *
     * @param profileName - Nome do perfil.
     */
    void selectProfile(String profileName);

    /**
     * Muda o Plano da conta.
     *
     * @param type - Plano novo.
     */
    void setPlanType(PlanType type);

    /**
     * Devolve o plano atual da conta.
     *
     * @return type.
     */
    PlanType getPlanType();

    /**
     * Devolve o nome da conta.
     *
     * @return name.
     */
    String getName();

    /**
     * Adiciona um filme ou serie a lista dos recentemente vistos do perfil ativo.
     *
     * @param productName - Nome do produto.
     */
    void watch(String productName);

    /**
     * Adiciona um filme ou serie a lista dos classificados do perfil ativo.
     *
     * @param product - Produto (Filme ou serie).
     */
    void rate(Product product);

    /**
     * Devolve a lista de perfis.
     *
     * @return profiles.
     */
    SortedMap<String,Profile> infoAccount();

    /**
     * Devolve a classificacao etaria do perfil ativo.
     *
     * @return Classificacao etaria.
     */
    int getCurrentProfileAge();

    /**
     * Verifica se existe filme ou serie, com o nome dado como argumento, na lista dos recentemente visto do perfil ativo.
     *
     * @param productName - Nome do produto.
     * @return - true se exisitr;
     *           false se nao.
     */
    boolean isInHistory(String productName);
}
