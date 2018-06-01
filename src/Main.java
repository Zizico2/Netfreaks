import Exceptions.*;
import Netfreaks.Account.Account;
import Netfreaks.Account.Profile.Profile;
import Netfreaks.Netfreaks;
import Netfreaks.NetfreaksClass;
import Netfreaks.Product.*;

import java.util.*;

/**
 *
 * @author Bernardo Borda d'Agua    53648
 * @author Tiago Guerreiro          53649
 *
 */
public class Main {

    // Contem as mensagens a serem impressas,
    private enum Message {
        NEXT_LINE_CHAR("\n"),
        UPLOAD_SUCCESS("Database was updated:" + NEXT_LINE_CHAR.msg),
        SAME_EMAIL("There is another account with email "),
        SOMEONE_IS_LOGGEDIN("Another client is logged in." + NEXT_LINE_CHAR.msg),
        MAX_DEVICES_REACHED("Not possible to connect more devices." + NEXT_LINE_CHAR.msg),
        WRONG_PASSWORD("Wrong password." + NEXT_LINE_CHAR.msg),
        INEXISTANT_ACCOUNT("Account does not exist." + NEXT_LINE_CHAR.msg),
        ALREADY_LOGGEDIN("Client already logged in." + NEXT_LINE_CHAR.msg),
        NO_CLIENT("No client is logged in." + NEXT_LINE_CHAR.msg),
        DOWNGRADE_UNAVAILABLE("Cannot downgrade membership plan at the moment." + NEXT_LINE_CHAR.msg),
        NO_PROFILE("No profile is selected." + NEXT_LINE_CHAR.msg),
        SAME_MEMBERSHIP("No membership plan change." + NEXT_LINE_CHAR.msg),
        NO_SHOWS("No show found." + NEXT_LINE_CHAR.msg),
        SHOW_NOT_FOUND("Show does not exist." + NEXT_LINE_CHAR.msg),
        NOT_IN_HISTORY("Can only rate recently seen shows." + NEXT_LINE_CHAR.msg),
        MAX_PROFILES_REACHED("Not possible to add more profiles." + NEXT_LINE_CHAR.msg),
        PROFILE_ADDED("New profile added." + NEXT_LINE_CHAR.msg),
        SHOW_ALREADY_RATED("Show already rated." + NEXT_LINE_CHAR.msg),
        SHOW_UNAVAILABLE("Show not available." + NEXT_LINE_CHAR.msg),
        INEXISTATN_PROFILE("Profile does not exist." + NEXT_LINE_CHAR.msg),
        EMPTY_HISTORY("Empty list of recently seen shows." + NEXT_LINE_CHAR.msg),
        EMPTY_PROFILE_LIST("No profiles defined." + NEXT_LINE_CHAR.msg),
        WELCOME("Welcome "),
        THANK_YOU_RATE("Thank you for rating "),
        LOADING("Loading "),
        SAME_NAME_PROFILE("There is already a profile "),
        MEMBERSHIP_CHANGED("Membership plan was changed from "),
        GOODBYE("Goodbye "),
        CHILDREN("CHILDREN"),
        EXITING("Exiting..."),
        UNKNOWN("Unknown command." + NEXT_LINE_CHAR.msg);

        private final String msg;

        Message(String msg) {
            this.msg = msg;
        }
    }

    // Contem os comandos.
    private enum Command {
        UPLOAD(),
        REGISTER(),
        LOGIN(),
        DISCONNECT(),
        LOGOUT(),
        MEMBERSHIP(),
        PROFILE(),
        SELECT(),
        WATCH(),
        RATE(),
        INFOACCOUNT(),
        SEARCHBYGENRE(),
        SEARCHBYNAME(),
        SEARCHBYRATE(),
        EXIT(),

        Command() {
        }
    }

    /**
     *
     * Metodo main.
     *
     */
    public static void main(String[] args) {
        Netfreaks netfreaks = new NetfreaksClass();
        Scanner in = new Scanner(System.in);
        executeCommand(in, netfreaks);
    }

    /**
     * Retorna um comando especificado pelo utilizador.
     *
     * @param in - Scanner a ser usado para receber informacoes do utilizador.
     * @return Commmand.
     */
    private static Command getCommand(Scanner in) {

        String cmd = in.nextLine();

        return Command.valueOf(cmd.toUpperCase());
    }

    /**
     * Executa um comando especificado pelo utilizador.
     *
     * @param in - Scanner a ser usado para receber informacoes do utilizador.
     * @param netfreaks - aplicacao "Netflix".
     */
    private static void executeCommand(Scanner in, Netfreaks netfreaks) {
        Command cmd;
            do {
                try {
                cmd = getCommand(in);
                switch (cmd) {
                    case UPLOAD:
                        processUpload(in, netfreaks);
                        break;

                    case REGISTER:
                        processRegister(in, netfreaks);
                        break;

                    case LOGIN:
                        processLogin(in,netfreaks);
                        break;

                    case DISCONNECT:
                        processDisconnect(netfreaks);
                        break;

                    case LOGOUT:
                        processLogout(netfreaks);
                        break;

                    case MEMBERSHIP:
                        processMembership(in, netfreaks);
                        break;

                    case PROFILE:
                        processProfile(in, netfreaks);
                        break;

                    case SELECT:
                        processSelect(in, netfreaks);
                        break;

                    case WATCH:
                        processWatch(in, netfreaks);
                        break;

                    case RATE:
                        processRate(in, netfreaks);
                        break;

                    case INFOACCOUNT:
                        processInfoAccount(netfreaks);
                        break;

                    case SEARCHBYGENRE:
                        processSearchByGenre(in, netfreaks);
                        break;

                    case SEARCHBYNAME:
                        processSearchByName(in, netfreaks);
                        break;

                    case SEARCHBYRATE:
                        processSearchByRate(in, netfreaks);
                        break;

                        case EXIT:
                            System.out.println(Message.EXITING.msg);
                            in.close();

                }
                } catch (IllegalArgumentException e) {
                    System.out.println(Message.UNKNOWN.msg);
                    cmd = Command.Command;
                }
            } while (!cmd.equals(Command.EXIT));
    }

    /**
     * Processa o Comando SearchByRate.
     *
     * @param in - Scanner a ser usado para receber informacoes do utilizador.
     * @param netfreaks - aplicacao "Netflix".
     */
    private static void processSearchByRate(Scanner in, Netfreaks netfreaks) {
        int rate = in.nextInt();
        in.nextLine();
        try{
            searchByRate(rate,netfreaks);
        } catch(NoAccountLoggedInException e){
            System.out.println(Message.NO_CLIENT.msg);
        }
        catch(NoProfileSelectedException e) {
            System.out.println(Message.NO_PROFILE.msg);
        }
        catch(ShowNotFoundException e){
            System.out.println(Message.NO_SHOWS.msg);
        }
    }

    /**
     * Processo de verificacao de erros e ,se passou a verificacao, a execucao do comando.
     *
     * @param rate avaliacao dada pelo utilizador.
     * @param netfreaks - aplicacao "Netflix".
     * @throws NoAccountLoggedInException - Nao existe uma conta ativa.
     * @throws NoProfileSelectedException - Nao existe um perfil ativo na conta atual.
     * @throws ShowNotFoundException - Nenhum filme ou serie foi encontrada com o criterio dado.
     */
    private static void searchByRate(int rate, Netfreaks netfreaks) throws NoAccountLoggedInException,NoProfileSelectedException, ShowNotFoundException {
        if(!netfreaks.isAClientLoggedIn())
            throw new NoAccountLoggedInException();
        if(!netfreaks.isThereProfileSelected())
            throw new NoProfileSelectedException();
        if(!netfreaks.hasShowsWithRateHigherThan(rate))
            throw new ShowNotFoundException();
        printSearchByRates(netfreaks.searchByRate(rate));
    }

    /**
     * Imprime todos os filmes e series que passaram a verificacao.
     *
     * @param listOfRatedProducts list de todos os produtos que passam a verificacao do searchByRate, organizados por sets de produtos entre duas avaliacoes inteiras.
     */
    private static void printSearchByRates(List<SortedSet<Product>> listOfRatedProducts) {
        String msg = "";
        String separator = "; ";
        for(SortedSet<Product> sortedSet: listOfRatedProducts){
            for(Product product: sortedSet){
                String title = product.getTitle();
                String genre = product.getGenre();
                String[] cast = product.getCast();
                int ageRestriction = product.getPEGI();
                int yearOfRelease = product.getYearOfRelease();
                String averageRating = String.valueOf(product.getAverageRating()).substring(0,3);
                msg += title + separator ;
                if(product instanceof Film) {
                    msg += product.getMasterName() + separator +  ((Film)product).getDuration() + separator;
                }
                else{
                    msg += product.getMasterName() + separator +  ((Series)product).getNSeasons() + separator + ((Series)product).getNEpisodesPerSeason() + separator;
                }
                msg += ageRestriction + "+" + separator + yearOfRelease + separator + genre + separator;
                msg = getCastOutput(msg,separator,cast,Double.POSITIVE_INFINITY);
                msg = msg.substring(0,msg.lastIndexOf(separator)) + ". [" + averageRating + "]" + Message.NEXT_LINE_CHAR.msg;
            }
        }
        System.out.println(msg);
    }

    /**
     * Processa o Comando SearchByName.
     *
     * @param in - Scanner a ser usado para receber informacoes do utilizador.
     * @param netfreaks - aplicacao "Netflix".
     */
    private static void processSearchByName(Scanner in, Netfreaks netfreaks) {
        String name = in.nextLine();
        try{
            searchByName(name,netfreaks);
        } catch(NoAccountLoggedInException e){
            System.out.println(Message.NO_CLIENT.msg);
        }
        catch(NoProfileSelectedException e) {
            System.out.println(Message.NO_PROFILE.msg);
        }
        catch(ShowNotFoundException e){
            System.out.println(Message.NO_SHOWS.msg);
        }
    }

    /**
     * Processo de verificacao de erros e ,se passou a verificacao, a execucao do comando.
     *
     * @param name nome dado pelo utilizador.
     * @param netfreaks - aplicacao "Netflix".
     * @throws NoAccountLoggedInException - Nao existe uma conta ativa.
     * @throws NoProfileSelectedException - Nao existe um perfil ativo na conta atual.
     * @throws ShowNotFoundException - Nenhum filme ou serie foi encontrada com o criterio dado.
     */
    private static void searchByName(String name, Netfreaks netfreaks) throws NoAccountLoggedInException,NoProfileSelectedException, ShowNotFoundException {
        if(!netfreaks.isAClientLoggedIn())
            throw new NoAccountLoggedInException();
        if(!netfreaks.isThereProfileSelected())
            throw new NoProfileSelectedException();
        if(!netfreaks.hasDude(name))
            throw new ShowNotFoundException();
        System.out.println(getShowByShowOutput(netfreaks.searchByName(name),Double.POSITIVE_INFINITY));
    }

    /**
     * Processa o Comando SearchByGenre.
     *
     * @param in - Scanner a ser usado para receber informacoes do utilizador.
     * @param netfreaks - aplicacao "Netflix".
     */
    private static void processSearchByGenre(Scanner in, Netfreaks netfreaks) {
        String genre = in.nextLine();
        try{
            searchByGenre(genre,netfreaks);
        } catch(NoAccountLoggedInException e){
            System.out.println(Message.NO_CLIENT.msg);
        }
        catch(NoProfileSelectedException e) {
            System.out.println(Message.NO_PROFILE.msg);
        }
        catch(ShowNotFoundException e){
            System.out.println(Message.NO_SHOWS.msg);
        }
    }

    /**
     * Processo de verificacao de erros e ,se passou a verificacao, a execucao do comando.
     *
     * @param genre genero dada pelo utilizador.
     * @param netfreaks - aplicacao "Netflix".
     */
    private static void searchByGenre(String genre, Netfreaks netfreaks) throws NoAccountLoggedInException,NoProfileSelectedException, ShowNotFoundException {
        if(!netfreaks.isAClientLoggedIn())
            throw new NoAccountLoggedInException();
        if(!netfreaks.isThereProfileSelected())
            throw new NoProfileSelectedException();
        if(!netfreaks.hasGenre(genre))
            throw new ShowNotFoundException();
        System.out.println(getShowByShowOutput(netfreaks.searchByGenre(genre).values(),Double.POSITIVE_INFINITY));
    }

    /**
     * Processa o Comando InfoAccount.
     *
     * @param netfreaks - aplicacao "Netflix".
     */
    private static void processInfoAccount(Netfreaks netfreaks) {
        try {
            infoAccount(netfreaks);
        } catch(NoAccountLoggedInException e){
            System.out.println(Message.NO_CLIENT.msg);
        }
    }

    /**
     * Processo de verificacao de erros e ,se passou a verificacao, a execucao do comando.
     *
     * @param netfreaks - aplicacao "Netflix".
     * @throws NoAccountLoggedInException - Nao existe uma conta ativa.
     */
    private static void infoAccount(Netfreaks netfreaks) throws NoAccountLoggedInException{
        if(!netfreaks.isAClientLoggedIn())
            throw new NoAccountLoggedInException();
        printInfoAccount(netfreaks.infoaccount());
    }

    /**
     * Inicializa e imprime a informacao geral da conta.
     *
     * @param account conta ativa.
     */
    private static void printInfoAccount(Account account) {
        String msg = account.getName() + ":" + Message.NEXT_LINE_CHAR.msg +
                account.getPlanType().getOutput() + " (";
        String separator = "; ";
        for(String device : account.getDevices())
            msg += device + separator;
        msg = msg.substring(0,msg.lastIndexOf(separator)) + ")." + Message.NEXT_LINE_CHAR.msg;

        printProfiles(account.infoAccount().values(),msg);

    }

    /**
     * Lista todos os filmes e series que tem participantes com o nome dado pelo utilizador.
     *
     * @param profiles Colecao de todos os perfis da conta ativa.
     * @param msg Mensagem inicial.
     */
    private static void printProfiles(Collection<Profile> profiles,String msg) {
        if(profiles.isEmpty())
            msg += Message.EMPTY_PROFILE_LIST.msg;
        else
            for(Profile profile: profiles) {
                msg += "Profile: ";
                String name = profile.getName();
                msg += name;
                int age = profile.getAge();
                if (age != (int)Double.POSITIVE_INFINITY)
                    msg +=  " (" + age + ")" + Message.NEXT_LINE_CHAR.msg;
                else
                    msg += Message.NEXT_LINE_CHAR.msg;

                List<String> history = profile.getHistory();
                if (history.isEmpty())
                    msg += Message.EMPTY_HISTORY.msg;
                else {
                    List<String> tempHistory = new ArrayList<>(history);
                    Collections.reverse(tempHistory);
                    for (String productName : tempHistory)
                        msg += productName + "; ";
                    msg = msg.substring(0, msg.lastIndexOf("; ")) + "." + Message.NEXT_LINE_CHAR.msg;
                    List<Product> ratedProducts = profile.getRatedProducst();
                    if (!ratedProducts.isEmpty()) {
                        for (Product product : ratedProducts)
                            msg += product.getTitle() + " (" + product.getRate(name) + "); ";
                        msg = msg.substring(0, msg.lastIndexOf("; ")) + "." + Message.NEXT_LINE_CHAR.msg;
                    }
                }
            }
        System.out.println(msg);
    }

    /**
     * Processa o Comando Rate.
     *
     * @param in - Scanner a ser usado para receber informacoes do utilizador.
     * @param netfreaks - aplicacao "Netflix".
     */
    private static void processRate(Scanner in, Netfreaks netfreaks) {
        String productName = in.nextLine();
        int rate = in.nextInt();
        in.nextLine();
        try{
            rate(productName,rate,netfreaks);
        } catch(NoAccountLoggedInException e){
            System.out.println(Message.NO_CLIENT.msg);
        }
        catch(NoProfileSelectedException e){
            System.out.println(Message.NO_PROFILE.msg);
        }
        catch(InexistantProductException e){
            System.out.println(Message.SHOW_NOT_FOUND.msg);
        }
        catch(NotInRecentHistoryException e){
            System.out.println(Message.NOT_IN_HISTORY.msg);
        }
        catch(ProductAlreadyRatedException e){
            System.out.println(Message.SHOW_ALREADY_RATED.msg);
        }
    }

    /**
     * Processo de verificacao de erros e ,se passou a verificacao, a execucao do comando.
     *
     * @param productName Nome do filme ou seria a avaliar.
     * @param netfreaks - aplicacao "Netflix".
     * @param  rate Avaliacao a dar.
     * @throws NoAccountLoggedInException - Nao existe uma conta ativa.
     * @throws NoProfileSelectedException - Nao existe um perfil ativo na conta atual.
     * @throws InexistantProductException - Nao existe uma conta as informacoes dadas.
     * @throws IncompatiblePEGIException - Classificacao etaria do perfil demasiado baixa.
     */
    private static void rate(String productName, int rate, Netfreaks netfreaks) throws NoAccountLoggedInException, NoProfileSelectedException, InexistantProductException, IncompatiblePEGIException {
        if(!netfreaks.isAClientLoggedIn())
            throw new NoAccountLoggedInException();
        if(!netfreaks.isThereProfileSelected())
            throw new NoProfileSelectedException();
        if(!netfreaks.isThereAProductNamed(productName))
            throw new InexistantProductException();
        if(!netfreaks.isInRecentHistory(productName))
            throw new NotInRecentHistoryException();
        if(netfreaks.isProductRated(productName))
            throw new ProductAlreadyRatedException();

        netfreaks.rate(productName,rate);
        System.out.println(Message.THANK_YOU_RATE.msg + productName + "." + Message.NEXT_LINE_CHAR.msg);
    }

    /**
     * Processa o Comando Watch.
     *
     * @param in - Scanner a ser usado para receber informacoes do utilizador.
     * @param netfreaks - aplicacao "Netflix".
     */
    private static void processWatch(Scanner in, Netfreaks netfreaks) {
        String productName = in.nextLine();
        try{
            watch(productName,netfreaks);
        } catch(NoAccountLoggedInException e){
            System.out.println(Message.NO_CLIENT.msg);
        }
        catch(NoProfileSelectedException e){
            System.out.println(Message.NO_PROFILE.msg);
        }
        catch(InexistantProductException e){
            System.out.println(Message.SHOW_NOT_FOUND.msg);
        }
        catch(IncompatiblePEGIException e){
            System.out.println(Message.SHOW_UNAVAILABLE.msg);
        }
    }

    /**
     * Processo de verificacao de erros e ,se passou a verificacao, a execucao do comando.
     *
     * @param productName - Nome do filme ou serie.
     * @param netfreaks - aplicacao "Netflix".
     * @throws NoAccountLoggedInException - Nao existe uma conta ativa.
     * @throws NoProfileSelectedException - Nao existe um perfil ativo na conta atual.
     * @throws InexistantProductException - Nao existe uma conta as informacoes dadas.
     * @throws IncompatiblePEGIException - Classificacao etaria do perfil demasiado baixa.
     */
    private static void watch(String productName, Netfreaks netfreaks) throws NoAccountLoggedInException, NoProfileSelectedException, InexistantProductException, IncompatiblePEGIException {
        if(!netfreaks.isAClientLoggedIn())
            throw new NoAccountLoggedInException();
        if(!netfreaks.isThereProfileSelected())
            throw new NoProfileSelectedException();
        if(!netfreaks.isThereAProductNamed(productName))
            throw new InexistantProductException();
        if(!netfreaks.isPEGICompatible(productName))
            throw new IncompatiblePEGIException();

        netfreaks.watch(productName);
        System.out.println(Message.LOADING.msg+ productName + "..." + Message.NEXT_LINE_CHAR.msg);
    }

    /**
     * Processa o Comando Select.
     *
     * @param in - Scanner a ser usado para receber informacoes do utilizador.
     * @param netfreaks - aplicacao "Netflix".
     */
    private static void processSelect(Scanner in, Netfreaks netfreaks) {
        String profile = in.nextLine();
        try{
            select(profile,netfreaks);
        } catch(NoAccountLoggedInException e){
            System.out.println(Message.NO_CLIENT.msg);
        } catch(InexistantProfileException e){
            System.out.println(Message.INEXISTATN_PROFILE.msg);
        }

    }

    /**
     * Processo de verificacao de erros e ,se passou a verificacao, a execucao do comando.
     *
     * @param profile - Nome do perfil.
     * @param netfreaks - aplicacao "Netflix".
     * @throws NoAccountLoggedInException - Nao existe uma conta ativa.
     * @throws InexistantProfileException - Nao existe uma conta as informacoes dadas.
     */
    private static void select(String profile, Netfreaks netfreaks) throws NoAccountLoggedInException,InexistantProfileException{
        if(!netfreaks.isAClientLoggedIn())
            throw new NoAccountLoggedInException();
        if(!netfreaks.hasProfile(profile))
            throw new InexistantProfileException();
        netfreaks.select(profile);
        System.out.println(Message.WELCOME.msg + profile + "." + Message.NEXT_LINE_CHAR.msg);
    }

    /**
     * Processa o Comando Profile.
     *
     * @param in - Scanner a ser usado para receber informacoes do utilizador.
     * @param netfreaks - aplicacao "Netflix".
     */
    private static void processProfile(Scanner in, Netfreaks netfreaks) {
        int ageRestriction = 18;
        String profileName = in.nextLine();
        String profileType = in.nextLine();
        if(profileType.equalsIgnoreCase(Message.CHILDREN.msg)) {
            ageRestriction = in.nextInt();
            in.nextLine();
        }
        try{
            profile(profileName,profileType,ageRestriction,netfreaks);
        } catch(NoAccountLoggedInException e){
            System.out.println(Message.NO_CLIENT.msg);
        } catch(SameProfileNameExceptiopn e){
            System.out.println( Message.SAME_NAME_PROFILE.msg + profileName + "." + Message.NEXT_LINE_CHAR.msg);
        } catch(ProfileNumberExceededException e){
            System.out.println(Message.MAX_PROFILES_REACHED.msg);
        }
    }


    /**
     * Processo de verificacao de erros e ,se passou a verificacao, a execucao do comando.
     *
     * @param profileName - Nome do perfil.
     * @param profileType - Nome do tipo de perfil.
     * @param ageRestriction - Classificicacao etaria do perfil.
     * @param netfreaks - aplicacao "Netflix".
     * @throws NoAccountLoggedInException - Nao existe uma conta ativa.
     * @throws SameProfileNameExceptiopn - Existe um perfil na conta atual com o nome dado.
     * @throws ProfileNumberExceededException - Numero maximo de perfis autorizados foi excedido.
     */
    private static void profile(String profileName, String profileType, int ageRestriction, Netfreaks netfreaks) throws NoAccountLoggedInException, SameProfileNameExceptiopn, ProfileNumberExceededException{

        if(!netfreaks.isAClientLoggedIn())
            throw new NoAccountLoggedInException();
        if(netfreaks.isSameProfile(profileName))
            throw new SameProfileNameExceptiopn();
        if(netfreaks.profileNumberExceeded())
            throw new ProfileNumberExceededException();

        if (profileType.equalsIgnoreCase(Message.CHILDREN.msg))
            netfreaks.profile(profileName,ageRestriction);
        else
            netfreaks.profile(profileName);
        System.out.println(Message.PROFILE_ADDED.msg);
    }

    /**
     * Processa o Comando Membership.
     *
     * @param in - Scanner a ser usado para receber informacoes do utilizador.
     * @param netfreaks - aplicacao "Netflix".
     */
    private static void processMembership(Scanner in, Netfreaks netfreaks) {
        String membershipName = in.nextLine();
        try{
            membership(membershipName,netfreaks);
        } catch(NoAccountLoggedInException e){
            System.out.println(Message.NO_CLIENT.msg);
        } catch(SameMembershipException e){
            System.out.println(Message.SAME_MEMBERSHIP.msg);
        } catch(DowngradeUnavaliableException e){
            System.out.println(Message.DOWNGRADE_UNAVAILABLE.msg);
        }
    }

    /**
     * Processo de verificacao de erros e ,se passou a verificacao, a execucao do comando.
     *
     * @param membershipName - Nome do Plano.
     * @param netfreaks - aplicacao "Netflix".
     * @throws NoAccountLoggedInException - Nao existe uma conta ativa.
     * @throws SameMembershipException - O plano atual e igual ao novo plano.
     * @throws DowngradeUnavaliableException - Mudanca de plano reduzido nao disponivel.
     */
    private static void membership(String membershipName, Netfreaks netfreaks) throws NoAccountLoggedInException,SameMembershipException,DowngradeUnavaliableException{
        if(!netfreaks.isAClientLoggedIn())
            throw new NoAccountLoggedInException();
        if(netfreaks.SameMembership(membershipName))
            throw new SameMembershipException();
        if(netfreaks.isItDowngrade(membershipName))
            if(!netfreaks.isDowngradePossible(membershipName))
                throw new DowngradeUnavaliableException();
        System.out.println(Message.MEMBERSHIP_CHANGED.msg + netfreaks.getActiveProfilePlan().getOutput() + " to " + membershipName + "." + Message.NEXT_LINE_CHAR.msg);
        netfreaks.membership(membershipName);
    }

    /**
     * Processa o Comando Logout.
     *
     * @param netfreaks - aplicacao "Netflix".
     */
    private static void processLogout(Netfreaks netfreaks) {
        try{
            logout(netfreaks);
        } catch(NoAccountLoggedInException e){
            System.out.println(Message.NO_CLIENT.msg);
        }
    }

    /**
     * Processo de verificacao de erros e ,se passou a verificacao, a execucao do comando.
     *
     * @param netfreaks aplicacao "Netflix".
     * @throws NoAccountLoggedInException - Nao existe uma conta ativa.
     */
    private static void logout(Netfreaks netfreaks) throws NoAccountLoggedInException {
        if(!netfreaks.isAClientLoggedIn())
            throw new NoAccountLoggedInException();
        System.out.println(Message.GOODBYE.msg + netfreaks.getActiveAccountName() + " (" + netfreaks.getActiveDevice() + " still connected)." + Message.NEXT_LINE_CHAR.msg);
        netfreaks.logout();
    }

    /**
     * Processa o Comando Disconnect.
     *
     * @param netfreaks - aplicacao "Netflix".
     */
    private static void processDisconnect(Netfreaks netfreaks) {
        try{
            disconnect(netfreaks);
        } catch(NoAccountLoggedInException e){
            System.out.println(Message.NO_CLIENT.msg);
        }
    }

    /**
     * Processo de verificacao de erros e ,se passou a verificacao, a execucao do comando.
     *
     * @param netfreaks - aplicacao "Netflix".
     * @throws NoAccountLoggedInException - Nao existe uma conta ativa.
     */
    private static void disconnect(Netfreaks netfreaks) throws NoAccountLoggedInException {
        if(!netfreaks.isAClientLoggedIn())
            throw new NoAccountLoggedInException();
        System.out.println(Message.GOODBYE.msg + netfreaks.getActiveAccountName() + " (" + netfreaks.getActiveDevice() + " was disconnected)." + Message.NEXT_LINE_CHAR.msg);
        netfreaks.disconnect();
    }

    /**
     * Processa o Comando Login.
     *
     * @param in - Scanner a ser usado para receber informacoes do utilizador.
     * @param netfreaks - aplicacao "Netflix".
     */
    private static void processLogin(Scanner in, Netfreaks netfreaks) {
        String email = in.nextLine();
        String password = in.nextLine();
        String device = in.nextLine();

        try{
            login(email, password, device, netfreaks);
        } catch (AlreadyLoggedInException e){
            System.out.println(Message.ALREADY_LOGGEDIN.msg);
        } catch (NetfreaksAppOccupiedException e){
            System.out.println(Message.SOMEONE_IS_LOGGEDIN.msg);
        } catch (InexistantAccountException e){
            System.out.println(Message.INEXISTANT_ACCOUNT.msg);
        } catch (WrongPasswordException e){
            System.out.println(Message.WRONG_PASSWORD.msg);
        } catch (DeviceNumberExceededException e){
            System.out.println(Message.MAX_DEVICES_REACHED.msg);
        }
    }

    /**
     * Processo de verificacao de erros e ,se passou a verificacao, a execucao do comando.
     *
     * @param email - Email da conta.
     * @param password - Password da conta.
     * @param device - Nome do dispositivo.
     * @param netfreaks - aplicacao "Netflix".
     * @throws AlreadyLoggedInException - A conta ativa e a conta a fazer login e a mesma.
     * @throws NetfreaksAppOccupiedException - Neste momento, ha uma conta ativa
     * @throws InexistantAccountException - Nao existe uma conta as informacoes dadas.
     * @throws WrongPasswordException - Password errada.
     * @throws DeviceNumberExceededException - O numero maximo de dispositivos autorizados foi excedido.
     */
    private static void login(String email, String password, String device, Netfreaks netfreaks) throws AlreadyLoggedInException, NetfreaksAppOccupiedException, InexistantAccountException, WrongPasswordException, DeviceNumberExceededException{
        if(netfreaks.isClientLoggedIn(email))
            throw new AlreadyLoggedInException();
        if(netfreaks.isAClientLoggedIn())
            throw new NetfreaksAppOccupiedException();
        if(!netfreaks.isEmailUsed(email))
            throw new InexistantAccountException();
        if(!netfreaks.isPasswordRight(email,password))
            throw new WrongPasswordException();
        if (netfreaks.needToRegisterDevice(email, device)) {
            if (netfreaks.deviceNumberExceeded(email))
                throw new DeviceNumberExceededException();
            netfreaks.registerDevice(email, device);
        }

        netfreaks.login(email, device);
        System.out.println(Message.WELCOME.msg + netfreaks.getActiveAccountName() + " (" +  device + ")." + Message.NEXT_LINE_CHAR.msg);
    }

    /**
     * Processa o Comando Register.
     *
     * @param in - Scanner a ser usado para receber informacoes do utilizador.
     * @param netfreaks - aplicacao "Netflix".
     */
    private static void processRegister(Scanner in, Netfreaks netfreaks) {
        String name = in.nextLine();
        String email = in.nextLine();
        String password = in.nextLine();
        String device = in.nextLine();

        try{
           register(name, email, password, device, netfreaks);
        } catch (NetfreaksAppOccupiedException e){
            System.out.println(Message.SOMEONE_IS_LOGGEDIN.msg);
        } catch (SameEmailExceptiopn e){
            System.out.println(Message.SAME_EMAIL.msg + email + "." + Message.NEXT_LINE_CHAR.msg);
        }
    }

    /**
     * Processo de verificacao de erros e ,se passou a verificacao, a execucao do comando.
     *
     * @param name - Nome da Conta.
     * @param email - Email (Identificador) da Conta.
     * @param password - Password Da Conta.
     * @param device - Nome do
     * @param netfreaks - aplicacao "Netflix".
     * @throws NetfreaksAppOccupiedException - Neste momento, ha uma conta ativa
     * @throws SameEmailExceptiopn - Ja existe uma conta com esse email
     */
    private static void register(String name, String email, String password, String device, Netfreaks netfreaks) throws NetfreaksAppOccupiedException, SameEmailExceptiopn {
        if(netfreaks.isAClientLoggedIn())
            throw new NetfreaksAppOccupiedException();
        else if (netfreaks.isEmailUsed(email))
            throw new SameEmailExceptiopn();

        netfreaks.register(name, email, password, device);
        System.out.println(Message.WELCOME.msg + name + " (" + device + ")." + Message.NEXT_LINE_CHAR.msg);
    }

    /**
     * Processa o Comando Upload.
     *
     * @param in - Scanner a ser usado para receber informacoes do utilizador.
     * @param netfreaks - aplicacao "Netflix".
     */
    private static void processUpload(Scanner in, Netfreaks netfreaks) {
        Product[] products = getUploadInput(in);
        SortedMap<String, Product> IterableProducts = netfreaks.upload(products);
        System.out.println(Message.UPLOAD_SUCCESS.msg + getShowByShowOutput(IterableProducts.values(),3));
    }

    /**
     * Escreve as informacoes de todos os produtos dados por argumento.
     *
     * @param IterableProducts - Colecao de todos os produtos a escrever como output.
     * @param nCast - numero de participantes num filme ou serie a escrever como output, Mais infinito pra imprimir todos os participantes.
     * @return String output
     */
    private static String getShowByShowOutput(Collection<Product> IterableProducts,double nCast) {
        String output = "";
        String separator = "; ";
        for (Product product:IterableProducts) {
            String title = product.getTitle();
            String genre = product.getGenre();
            String[] cast = product.getCast();
            int ageRestriction = product.getPEGI();
            int yearOfRelease = product.getYearOfRelease();
            output += title + separator ;
            if(product instanceof Film) {
                output += product.getMasterName() + separator +  ((Film)product).getDuration() + separator;
            }
            else{
                output += product.getMasterName() + separator +  ((Series)product).getNSeasons() + separator + ((Series)product).getNEpisodesPerSeason() + separator;
            }
            output += ageRestriction + "+" + separator + yearOfRelease + separator + genre + separator;
            output = getCastOutput(output,separator,cast,nCast);
            output = output.substring(0,output.lastIndexOf(separator)) + "." + Message.NEXT_LINE_CHAR.msg;
        }
        return output;
    }

    /**
     * Escreve todos os nomes dos participantes dado como argumento.
     *
     * @param output - Output atual.
     * @param separator - Separador de informacoes.
     * @param cast - vetor de nomes dos participantes.
     * @param nCast - numero de participantes a escrever, mais infinito pra imprimir todos.
     * @return String output
     */
    private static String getCastOutput(String output, String separator, String[] cast, double nCast){
        for(int i = 0; i < nCast && i < cast.length; i++)
            output += cast[i] + separator;
        return output;
    }

    /**
     * Processa o input e transforma o em vetor de Produtos (Filmes e Series).
     *
     * @param in - Scanner a ser usado para receber informacoes do utilizador.
     * @return Vetor de Produtos(Filmes e Series).
     */
    private static Product[] getUploadInput(Scanner in) {
        int nMovies = in.nextInt();
        in.nextLine();
        Product[] movies = new Product[nMovies];
        int movieCounter = 0;
        while(movieCounter < nMovies){
            String title = in.nextLine();
            String directorName = in.nextLine();
            int duration = in.nextInt();
            in.nextLine();
            String ageRestriction = in.nextLine();
            int yearOfRelease = in.nextInt();
            in.nextLine();
            String genre  = in.nextLine();
            int nCast = in.nextInt();
            in.nextLine();
            String[] cast = new String[nCast+1];
            int castCounter = 0;
            cast[castCounter++] = directorName;
            while(castCounter < nCast + 1)
                cast[castCounter++] = in.nextLine();
            movies[movieCounter++] = new FilmClass(title,duration,ageRestriction,yearOfRelease,genre,cast);
        }
        int nSeries = in.nextInt();
        in.nextLine();
        Product[] series = new Product[nMovies];
        int seriesCounter = 0;
        while(seriesCounter < nSeries){
            String title = in.nextLine();
            String creatorName = in.nextLine();
            int nSeasons = in.nextInt();
            in.nextLine();
            int nEpisodesPerSeason = in.nextInt();
            in.nextLine();
            String ageRestriction = in.nextLine();
            int yearOfRelease = in.nextInt();
            in.nextLine();
            String genre  = in.nextLine();
            int nCast = in.nextInt();
            in.nextLine();
            String[] cast = new String[nCast +1];
            int castCounter = 0;
            cast[castCounter++] = creatorName;
            while(castCounter < nCast + 1)
                cast[castCounter++] = in.nextLine();
            series[seriesCounter++] = new SeriesClass(title,nSeasons,nEpisodesPerSeason,ageRestriction,yearOfRelease,genre,cast);
        }
        Product[] products = new Product[nMovies + nSeries];
        System.arraycopy(movies,0,products,0,nMovies);
        System.arraycopy(series,0,products,nMovies,nSeries);

        return products;
    }
}
