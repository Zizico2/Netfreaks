package Netfreaks.Product;

/**
 *
 * @author Bernardo Borda d'Agua    53648
 * @author Tiago Guerreiro          53649
 *
 *
 * Representa um serie.
 */
public interface Series {

    /**
     * Devolve o numero de temporardas.
     *
     * @return nSeasons.
     */
    int getNSeasons();

    /**
     * Devolve o numero de episodios por temporada.
     *
     * @return nEpisodesPerSeason.
     */
    int getNEpisodesPerSeason();
}
