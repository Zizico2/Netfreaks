package Netfreaks;

import Netfreaks.Account.Account;
import Netfreaks.Account.PlanType;
import Netfreaks.Product.Product;

import java.util.List;
import java.util.SortedMap;
import java.util.SortedSet;

/**
 *
 * @author Bernardo Borda d'Agua    53648
 * @author Tiago Guerreiro          53649
 *
 *
 * Representa a interface da nossa Aplicacao topo.
 */
public interface Netfreaks {

    int MAX_RATE = 5;

    /**
     * Organiza todos os produtos nas estruturas de dados e devolve todos os produtos por ordem alfabetica.
     *
     * @param products - Vetor de Produtos.
     * @return products.
     */
    SortedMap<String, Product> upload(Product[] products);

    /**
     * Regista uma nova conta.
     *
     * @param name - Nome da Conta.
     * @param email - Email da Conta.
     * @param password - Password da Conta.
     * @param device - Nome do dispositivo.
     */
    void register(String name, String email, String password, String device );

    /**
     * Inicia sessao com o email dado no dispositivo ativo.
     *
     * @param email - Email da Conta.
     * @param device - Nome do dispositivo.
     */
    void login(String email, String device);

    /**
     * Desativa a conta atual deixando o dispositivo ativo.
     *
     */
    void disconnect();

    /**
     * Desativa a conta e dispositivo atual.
     *
     */
    void logout();

    /**
     * Muda o plano da conta atual para o plano dado.
     *
     * @param planName - Nome do plano.
     */
    void membership(String planName);

    /**
     * Cria um novo perfil Normal da conta atual.
     *
     * @param profileName - Nome do perfil.
     */
    void profile(String profileName);

    /**
     * Cria um novo perfil Normal da conta atual.
     *
     * @param profileName - Nome do perfil.
     * @param ageRestriction - Classificacao etaria.
     */
    void profile(String profileName, int ageRestriction);

    /**
     * Seleciona um perfil da conta atual.
     *
     * @param profileName - Nome do perfil.
     */
    void select(String profileName);

    /**
     * Adiciona o nome do filme ou serie a lista dos recentemente vistos do perfil ativo da conta ativa.
     *
     * @param title - Nome do produto.
     */
    void watch(String title);

    /**
     * Adiciona um avaliacao do perfil atual da conta ativa a um produto.
     *
     * @param title - Nome do produto.
     * @param rating - Avaliacao.
     */
    void rate(String title, int rating);

    /**
     * Devolve a conta atual para escrever as suas informacoes.
     *
     * @return currentAccount.
     */
    Account infoaccount();

    /**
     * Devolve um mapa de nomes e produtos associados aos seus respetivos que tem genero igual ao do argumento.
     *
     * @param genreName - Nome do genero.
     * @return listOfProducts.
     */
    SortedMap<String,Product> searchByGenre(String genreName);

    /**
     * Devolve um mapa de nomes e produtos associados aos seus respetivos que tem um participante com nome igual ao do argumento.
     *
     * @param participantsName - Nome do Participante.
     * @return listOfProducts.
     */
    SortedSet<Product> searchByName(String participantsName);

    /**
     * Devolve um mapa de nomes e produtos associados aos seus respetivos que tem avaliacao maior ou igual a do argumento.
     *
     * @param rate - Avaliacao.
     * @return ListOfRatedProducts.
     */
    List<SortedSet<Product>> searchByRate(int rate);

    /**
     * Verifica se existe uma conta ativa.
     *
     * @return true se houver;
     *         false se nao.
     */
    boolean isAClientLoggedIn();

    /**
     * Verifica se existe um conta email dado como argumento.
     *
     * @param email - Email.
     * @return true se houver;
     *         false se nao.
     */
    boolean isEmailUsed(String email);

    /**
     * Devolve o nome do dispositivo ativo,
     *
     * @return activeDevice.
     */
    String getActiveDevice();

    /**
     * Verifica se a conta ativa tem o email dado como argumento.
     *
     * @param email - Email da conta.
     * @return true se for igual;
     *         false se nao.
     */
    boolean isClientLoggedIn(String email);

    /**
     * Verifica se password da conta com o email do argumento esta correta
     *
     * @param email - Email da conta.
     * @param password - Password a verificar.
     * @return true se for correta;
     *         false se nao.
     */
    boolean isPasswordRight(String email, String password);

    /**
     * Verifica se o numero de dispositivos da conta com o email do argumento ultrapassao o limite.
     *
     * @param email - Email da conta.
     * @return true se excedeu;
     *         false se nao.
     */
    boolean deviceNumberExceeded(String email);

    /**
     * Verifica se e preciso registar um novo dispositvo na conta com email do argumento.
     *
     * @param email - Email da conta.
     * @param device - Nome do dispositivo.
     * @return true se for preciso;
     *         false se nao.
     */
    boolean needToRegisterDevice(String email, String device);

    /**
     * Regista um novo dispositivo a conta com email do argumento.
     *
     * @param email - Email da conta.
     * @param device Nome do dispositivo.
     */
    void registerDevice(String email, String device);

    /**
     * Devolve o plano atual da conta ativa.
     *
     * @return PlanType.
     */
    PlanType getActiveProfilePlan();

    /**
     * Verifica se a conta atual tem o mesmo plano que o plano com o nome do argumento.
     *
     * @param membershipName - Nome do plano.
     * @return true se tiver;
     *         false se nao.
     */
    boolean SameMembership(String membershipName);

    /**
     * Verifica se e possivel mudar plano da conta ativa para um plano reduzido.
     *
     * @param membershipName - Nome do Plano-
     * @return true se for possivel;
     *         false se nao.
     */
    boolean isDowngradePossible(String membershipName);

    /**
     * Verifica se o plano do argumento e um plano reduzido comparado com o plano da conta ativa.
     *
     * @param membershipName - Nome do plano.
     * @return true se for;
     *         false se nao.
     */
    boolean isItDowngrade(String membershipName);

    /**
     * Verifica se existe um perfil com o mesmo nome.
     *
     * @param profileName - Nome do perfil.
     * @return true se existir;
     *         false se nao.
     */
    boolean isSameProfile(String profileName);

    /**
     * Verifica se a conta atual excedeu o numero de perfis maximo.
     *
     * @return true se excedeu;
     *         false se nao.
     */
    boolean profileNumberExceeded();

    /**
     * Verifica se existe perfil com o nome dado por argumento.
     *
     * @param profile - Nome do perfil.
     * @return true se existir;
     *         false se nao.
     */
    boolean hasProfile(String profile);

    /**
     * Devolve o nome da conta ativa.
     *
     * @return currentAccount.
     */
    String getActiveAccountName();

    /**
     * Verifica se a conta ativa tem um perfil selecionado.
     *
     * @return true se tiver;
     *         false se nao.
     */
    boolean isThereProfileSelected();

    /**
     * Verifica se existe um produto com o titulo igual ao do argumento.
     *
     * @param productName Titulo.
     * @return true se houver;
     *         false se nao.
     */
    boolean isThereAProductNamed(String productName);

    /**
     * Verifica se o perfil selecionado da conta ativa pode ver o produto com nome dado como argumento.
     *
     * @param productName - Nome do produto.
     * @return true se pode;
     *         false se nao.
     */
    boolean isPEGICompatible(String productName);

    /**
     * Verifica o perfil selecionado da conta ativa tem um produto na lista dos recentemente vistos com o nome do argumento.
     *
     * @param productName - Nome do produto.
     * @return true se tiver;
     *         false se nao.
     */
    boolean isInRecentHistory(String productName);

    /**
     * Verifica se o perfil selecionado da conta atual ja avaliou o produto com nome dado como argumento.
     *
     * @param productName - Nome do produto.
     * @return true se tiver;
     *         false se nao.
     */
    boolean isProductRated(String productName);

    /**
     * Verifica se existe algum produto com o genero do argumento.
     *
     * @param genre - Nome do genero.
     * @return true se existir;
     *         false se nao.
     */
    boolean hasGenre(String genre);

    /**
     * Verifica se existe algum participante com o nome dado com argumento.
     *
     * @param name - Nome do participante.
     * @return true se existir;
     *         false se nao.
     */
    boolean hasDude(String name);

    /**
     * Verifica se existem produtos com media de avaliacao maior ou igual ao do argumento.
     *
     * @param rate - Avaliacao.
     * @return true se exisitr;
     *         false se nao.
     */
    boolean hasShowsWithRateHigherThan(int rate);
}
