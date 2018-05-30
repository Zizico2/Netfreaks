import Exceptions.*;
import Netfreaks.*;
import Netfreaks.Account.PlanType;
import Netfreaks.Product.*;

import java.awt.geom.Point2D;
import java.util.*;

public class Main {

    private enum Message {

        UPLOAD_SUCCESS("Database was updated:\n"),
        SAME_EMAIL("There is another account with email "),
        SOMEONE_IS_LOGGEDIN("Another client is logged in.\n"),
        MAX_DEVICES_REACHED("Not possible to connect more devices.\n"),
        WRONG_PASSWORD("Wrong password.\n"),
        INEXISTANT_ACCOUNT("Account does not exist.\n"),
        ALREADY_LOGGEDIN("Client already logged in.\n"),
        NO_CLIENT("No client is logged in.\n"),
        DOWNGRADE_UNAVAILABLE("Cannot downgrade membership plan at the moment.\n"),
        NO_PROFILE("No profile is selected.\n"),
        SAME_MEMBERSHIP("No membership plan change.\n"),
        NO_SHOWS("No show found.\n"),
        SHOW_NOT_FOUND("Show does not exist.\n"),
        NOT_IN_HISTORY("Can only rate recently seen shows.\n"),
        MAX_PROFILES_REACHED("Not possible to add more profiles.\n"),
        PROFILE_ADDED("New profile added.\n"),
        SHOW_ALREADY_RATED("Show already rated.\n"),
        SHOW_UNAVAILABLE("Show not available.\n"),
        INEXISTATN_PROFILE("Profile does not exist.\n"),
        WELCOME("Welcome "),
        THANK_YOU_RATE("Thank you for rating "),
        CHILDREN("CHILDREN"),
        EXITING("Exiting..."),
        UNKNOWN("Unknown command.\n");

        private final String msg;

        Message(String msg) {
            this.msg = msg;
        }
    }

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


    public static void main(String[] args) {
        Netfreaks netfreaks = new NetfreaksClass();
        Scanner in = new Scanner(System.in);
        executeCommand(in, netfreaks);
    }


    private static Command getCommand(Scanner in) {

        String cmd = in.nextLine();

        return Command.valueOf(cmd.toUpperCase());
    }

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

    private static void searchByRate(int rate, Netfreaks netfreaks) throws NoAccountLoggedInException,NoProfileSelectedException, ShowNotFoundException {
        if(!netfreaks.isAClientLoggedIn())
            throw new NoAccountLoggedInException();
        if(!netfreaks.isThereProfileSelected())
            throw new NoProfileSelectedException();
        if(!netfreaks.hasShowsWithRateHigherThan(rate))
            throw new ShowNotFoundException();
        printSearchByRates(netfreaks.searchByRate(rate));
    }

    private static void printSearchByRates(List<SortedSet<Product>> sortedSets) {
        String msg = "";
        String separator = "; ";
        for(SortedSet<Product> sortedSet: sortedSets){
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
                msg = msg.substring(0,msg.lastIndexOf(separator)) + ". [" + averageRating + "]\n";
            }
        }
        System.out.println(msg);
    }

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

    private static void searchByName(String name, Netfreaks netfreaks) throws NoAccountLoggedInException,NoProfileSelectedException, ShowNotFoundException {
        if(!netfreaks.isAClientLoggedIn())
            throw new NoAccountLoggedInException();
        if(!netfreaks.isThereProfileSelected())
            throw new NoProfileSelectedException();
        if(!netfreaks.hasDude(name))
            throw new ShowNotFoundException();
        System.out.println(getShowByShowOutput(netfreaks.searchByName(name),Double.POSITIVE_INFINITY));
    }

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

    private static void searchByGenre(String genre, Netfreaks netfreaks) throws NoAccountLoggedInException,NoProfileSelectedException, ShowNotFoundException {
        if(!netfreaks.isAClientLoggedIn())
            throw new NoAccountLoggedInException();
        if(!netfreaks.isThereProfileSelected())
            throw new NoProfileSelectedException();
        if(!netfreaks.hasGenre(genre))
            throw new ShowNotFoundException();
        System.out.println(getShowByShowOutput(netfreaks.searchByGenre(genre).values(),Double.POSITIVE_INFINITY));
    }

    private static void processInfoAccount(Netfreaks netfreaks) {
        try {
            infoAccount(netfreaks);
        } catch(NoAccountLoggedInException e){
            System.out.println(Message.NO_CLIENT.msg);
        }
    }

    private static void infoAccount(Netfreaks netfreaks) throws NoAccountLoggedInException{
        if(!netfreaks.isAClientLoggedIn())
            throw new NoAccountLoggedInException();

        System.out.println(netfreaks.infoaccount());
    }

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
        System.out.println(Message.THANK_YOU_RATE.msg + productName + ".\n");
    }

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
        System.out.println("Loading " + productName + "...\n");
    }

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

    private static void select(String profile, Netfreaks netfreaks) throws NoAccountLoggedInException,InexistantProfileException{
        if(!netfreaks.isAClientLoggedIn())
            throw new NoAccountLoggedInException();
        if(!netfreaks.hasProfile(profile))
            throw new InexistantProfileException();
        netfreaks.select(profile);
        System.out.println(Message.WELCOME.msg + profile + ".\n");
    }

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
            System.out.println("There is already a profile " + profileName + ".\n");
        } catch(ProfileNumberExceededException e){
            System.out.println(Message.MAX_PROFILES_REACHED.msg);
        }
    }

    private static void profile(String profileName, String profileType, int ageRestriction, Netfreaks netfreaks) throws NoAccountLoggedInException, SameProfileNameExceptiopn, ProfileNumberExceededException{

        if(!netfreaks.isAClientLoggedIn())
            throw new NoAccountLoggedInException();
        if(netfreaks.isSameProfile(profileName))
            throw new SameProfileNameExceptiopn();
        if(netfreaks.profileNumberExceeded())
            throw new ProfileNumberExceededException();

        netfreaks.profile(profileName,profileType.equalsIgnoreCase(PlanType.BASIC.getOutput()),ageRestriction);
        System.out.println(Message.PROFILE_ADDED.msg);
    }

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

    private static void membership(String membershipName, Netfreaks netfreaks) throws NoAccountLoggedInException,SameMembershipException,DowngradeUnavaliableException{
        if(!netfreaks.isAClientLoggedIn())
            throw new NoAccountLoggedInException();
        if(netfreaks.SameMembership(membershipName))
            throw new SameMembershipException();
        if(netfreaks.isItDowngrade(membershipName))
            if(!netfreaks.isDowngradePossible(membershipName))
                throw new DowngradeUnavaliableException();
        System.out.println("Membership plan was changed from " + netfreaks.getActiveProfilePlan().getOutput() + " to " + membershipName + ".\n");
        netfreaks.membership(membershipName);
    }

    private static void processLogout(Netfreaks netfreaks) {
        try{
            logout(netfreaks);
        } catch(NoAccountLoggedInException e){
            System.out.println(Message.NO_CLIENT.msg);
        }
    }

    private static void logout(Netfreaks netfreaks) {
        if(!netfreaks.isAClientLoggedIn())
            throw new NoAccountLoggedInException();
        System.out.println("Goodbye " + netfreaks.getActiveAccountName() + " (" + netfreaks.getActiveDevice() + " still connected).\n");
        netfreaks.logout();
    }

    private static void processDisconnect(Netfreaks netfreaks) {
        try{
            disconnect(netfreaks);
        } catch(NoAccountLoggedInException e){
            System.out.println(Message.NO_CLIENT.msg);
        }
    }

    private static void disconnect(Netfreaks netfreaks) {
        if(!netfreaks.isAClientLoggedIn())
            throw new NoAccountLoggedInException();
        System.out.println("Goodbye " + netfreaks.getActiveAccountName() + " (" + netfreaks.getActiveDevice() + " was disconnected).\n");
        netfreaks.disconnect();
    }

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

    private static void login(String email, String password, String device, Netfreaks netfreaks) throws AlreadyLoggedInException, NetfreaksAppOccupiedException,
                                                                                                        InexistantAccountException, WrongPasswordException, DeviceNumberExceededException{
        if(netfreaks.isClientLoggedIn(email))
            throw new AlreadyLoggedInException();
        if(netfreaks.isAClientLoggedIn())
            throw new NetfreaksAppOccupiedException();
        if(!netfreaks.isEmailUsed(email))
            throw new InexistantAccountException();
        if(!netfreaks.isPasswordRight(email,password))
            throw new WrongPasswordException();
        if (netfreaks.needToRegisterDevice(email, device)) {
            if (netfreaks.deviceNumberExceeded(email, device))
                throw new DeviceNumberExceededException();
            netfreaks.registerDevice(email, device);
        }

        netfreaks.login(email, device);
        System.out.println(Message.WELCOME.msg + netfreaks.getActiveAccountName() + " (" +  device + ").\n");
    }

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
            System.out.println(Message.SAME_EMAIL.msg + email + ".\n");
        }
    }

    private static void register(String name, String email, String password, String device, Netfreaks netfreaks) throws NetfreaksAppOccupiedException, SameEmailExceptiopn {
        if(netfreaks.isAClientLoggedIn())
            throw new NetfreaksAppOccupiedException();
        else if (netfreaks.isEmailUsed(email))
            throw new SameEmailExceptiopn();

        netfreaks.register(name, email, password, device);
        System.out.println(Message.WELCOME.msg + name + " (" + device + ").\n");
    }

    private static void processUpload(Scanner in, Netfreaks netfreaks) {
        Product[] products = getUploadInput(in);
        SortedMap<String, Product> IteratableProducts = netfreaks.upload(products);
        System.out.println(Message.UPLOAD_SUCCESS.msg + getShowByShowOutput(IteratableProducts.values(),3));
    }

    private static String getShowByShowOutput(Collection<Product> IteratableProducts,double nCast) {
        String msg = "";
        String separator = "; ";
        for (Product product:IteratableProducts) {
            String title = product.getTitle();
            String genre = product.getGenre();
            String[] cast = product.getCast();
            int ageRestriction = product.getPEGI();
            int yearOfRelease = product.getYearOfRelease();
            msg += title + separator ;
            if(product instanceof Film) {
                msg += product.getMasterName() + separator +  ((Film)product).getDuration() + separator;
            }
            else{
                msg += product.getMasterName() + separator +  ((Series)product).getNSeasons() + separator + ((Series)product).getNEpisodesPerSeason() + separator;
            }
            msg += ageRestriction + "+" + separator + yearOfRelease + separator + genre + separator;
            msg = getCastOutput(msg,separator,cast,nCast);
            msg = msg.substring(0,msg.lastIndexOf(separator)) + "." + "\n";
        }
        return msg;
    }
    private static String getCastOutput(String msg,String separator, String[] cast, double nCast){
        for(int i = 0; i < nCast && i < cast.length; i++)
            msg += cast[i] + separator;
        return msg;
    }


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
