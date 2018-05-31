package Netfreaks.Account.Profile;

import Netfreaks.Product.Product;

import java.util.List;

/**
 *
 * @author Bernardo Borda d'Agua    53648
 * @author Tiago Guerreiro          53649
 *
 */
public interface Profile {

    int MAX_CAPACITY = 10;

    /**
     * Adiciona a lista dos recentemente vistos.
     *
     * @param productName - Nome do filme ou serie.
     */
    void watch(String productName);

    /**
     * Guarda o produto como avaliado.
     *
     * @param product - Filme ou Serie.
     */
    void rate(Product product);

    /**
     * Devolve a Classificacao etaria do perfil.
     *
     * @return age.
     */
    int getAge();

    /**
     * Devolve o nome do perfil.
     *
     * @return name
     */
    String getName();

    /**
     * Verifica se existe um produto na lista dos recentemente vistos com titulo dado como argumento.
     *
     * @param productName - Titulo do produto
     * @return true se existir;
     *         false se nao existir.
     */
    boolean isInHistory(String productName);

    /**
     * Devolve a lista dos recentemente vistos.
     *
     * @return history.
     */
    List<String> getHistory();

    /**
     * Devolve todos os produtos avaliados pelo perfil.
     *
     * @return ratedProducts.
     */
    List<Product> getRatedProducst();
}
