package Netfreaks.Product;

/**
 *
 * @author Bernardo Borda d'Agua    53648
 * @author Tiago Guerreiro          53649
 *
 */
public interface Product {

    /**
     * Devolve o titulo do produto
     *
     * @return title.
     */
    String getTitle();

    /**
     * Devolve o ano de estreia.
     *
     * @return yearOfRelease.
     */
    int getYearOfRelease();

    /**
     * Devolve a classificacao etaria do produto.
     *
     * @return PEGI.
     */
    int getPEGI();

    /**
     * Devolve o genero do produto.
     *
     * @return genre.
     */
    String getGenre();

    /**
     * Devolve todos so nomes dos participantes(excepto criador ou realizador).
     *
     * @return cast.
     */
    String[] getCast();

    /**
     * Adiciona a lista de todas as avaliacoes dadas a este produto.
     *
     * @param profileName - Nome do perfil.
     * @param rate - Avaliacao dada.
     */
    void rate(String profileName, int rate);

    /**
     * Devolve a avaliacao dada pela o nome dado com argumento.
     *
     * @param name - Nome do perfil.
     * @return Avaliacao.
     */
    int getRate(String name);

    /**
     * Verififca se existe uma avaliacao dada pelo o perfil com nome dado com argumento.
     *
     * @param profileName - Nome do perfil.
     * @return true se existir;
     *         false se nao.
     */
    boolean isRatedBy(String profileName);

    /**
     * Devolve o realizador(Filme) ou criador(Serie) do produto.
     *
     * @return masterName.
     */
    String getMasterName();

    /**
     * Devolve a media de todas as avaliacoes.
     *
     * @return averageRating.
     */
    float getAverageRating();
}
