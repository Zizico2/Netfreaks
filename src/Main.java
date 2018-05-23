import Exceptions.*;
import Netfreaks.*;
import Netfreaks.Product.*;

import java.util.Scanner;
import java.util.SortedMap;

public class Main {

    private enum Message {

        UPLOAD_SUCCESS("Database was updated:\n"),
        DUPLICATE_MESSAGE("Mensagem duplicada."),
        NO_MESSAGES_WITH_EMAIL("Nao existem mensagens trocadas com esse email."),
        NO_MESSAGES_WITH_TOPIC(NO_MESSAGES_WITH_EMAIL.msg.replace("email", "assunto")),
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
                        processSelect(in,netfreaks);
                        break;

                    case WATCH:
                        processWatch(in,netfreaks);
                        break;

                    case RATE:
                        processRate(in,netfreaks);
                        break;

                    case INFOACCOUNT:
                        processInfoAccount(netfreaks);
                        break;

                    case SEARCHBYGENRE:
                        processSearchByGenre(netfreaks);
                        break;

                    case SEARCHBYNAME:
                        processSearchByName(netfreaks);
                        break;

                    case SEARCHBYRATE:
                        processSearchByRate(netfreaks);
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

    private static void processSearchByRate(Netfreaks netfreaks) {

    }

    private static void processSearchByName(Netfreaks netfreaks) {

    }

    private static void processSearchByGenre(Netfreaks netfreaks) {

    }

    private static void processInfoAccount(Netfreaks netfreaks) {

    }

    private static void processRate(Scanner in, Netfreaks netfreaks) {

    }

    private static void processWatch(Scanner in, Netfreaks netfreaks) {

    }

    private static void processSelect(Scanner in, Netfreaks netfreaks) {
        String profile = in.nextLine();
        try{
            select(profile,netfreaks);
        } catch(NoAccountLoggedInException e){
            System.out.println("No client is logged in.\n");
        }
        catch(InexistantProfileException e){
            System.out.println("Profile does not exist.\n");
        }

    }

    private static void select(String profile, Netfreaks netfreaks) throws NoAccountLoggedInException,InexistantProfileException{
        if(!netfreaks.isAClientLoggedIn())
            throw new NoAccountLoggedInException();
        if(netfreaks.hasProfile(profile))
            throw new InexistantProfileException();
        netfreaks.select(profile);
        System.out.println("Welcome " + profile + ".\n");
    }

    private static void processProfile(Scanner in, Netfreaks netfreaks) {
        int ageRestriction = 18;
        String profileName = in.nextLine();
        String profileType = in.nextLine();
        if(profileType.equalsIgnoreCase("CHILDREN")) {
            ageRestriction = in.nextInt();
            in.nextLine();
        }
        try{
            profile(profileName,profileType,ageRestriction,netfreaks);
        } catch(NoAccountLoggedInException e){
            System.out.println("No client is logged in.\n");
        }
        catch(SameProfileNameExceptiopn e){
            System.out.println("There is already a profile " + profileName + "\n");
        }
        catch(ProfileNumberExceededException e){
            System.out.println("Not possible to add more profiles.\n");
        }
    }

    private static void profile(String profileName, String profileType, int ageRestriction, Netfreaks netfreaks) throws NoAccountLoggedInException, SameProfileNameExceptiopn, ProfileNumberExceededException{

        if(!netfreaks.isAClientLoggedIn())
            throw new NoAccountLoggedInException();
        if(netfreaks.isSameProfile(profileName))
            throw new SameProfileNameExceptiopn();
        if(netfreaks.profileNumberExceeded())
            throw new ProfileNumberExceededException();

        netfreaks.profile(profileName,profileType.equalsIgnoreCase("BASIC"),ageRestriction);
        System.out.println("New profile added.\n");
    }

    private static void processMembership(Scanner in, Netfreaks netfreaks) {
        String membershipName = in.nextLine();
        try{
            membership(membershipName,netfreaks);
        } catch(NoAccountLoggedInException e){
            System.out.println("No client is logged in.\n");
        }
        catch(SameMembershipException e){
            System.out.println("No membership plan change.\n");
        }
        catch(DowngradeUnavaliableException e){
            System.out.println("Cannot downgrade membership plan at the moment.\n");
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

        netfreaks.membership(membershipName);
        String smthn = netfreaks.getActiveProfilePlan().getOutput();
        System.out.println("Membership plan was changed from " + netfreaks.getActiveProfilePlan().getOutput() + " to " + membershipName + ".");

    }

    private static void processLogout(Netfreaks netfreaks) {
        try{
            logout(netfreaks);
        } catch(NoAccountLoggedInException e){
            System.out.println("No client is logged in.\n");
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
            System.out.println("No client is logged in.\n");
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
            System.out.println("Client already logged in.\n");
        }
        catch (NetfreaksAppOccupiedException e){
            System.out.println("Another client is logged in.\n");
        } catch (InexistantAccountException e){
            System.out.println("Account does not exist.\n");
        } catch (WrongPasswordException e){
            System.out.println("Wrong password.\n");
        } catch (DeviceNumberExceededException e){
            System.out.println("Not possible to connect more devices.\n");
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
        System.out.println("Welcome " + netfreaks.getActiveAccountName() + " (" +  device + ").\n");
    }

    private static void processRegister(Scanner in, Netfreaks netfreaks) {
        String name = in.nextLine();
        String email = in.nextLine();
        String password = in.nextLine();
        String device = in.nextLine();

        try{
           register(name, email, password, device, netfreaks);
        } catch (NetfreaksAppOccupiedException e){
            System.out.println("Another client is logged in.\n");
        } catch (SameEmailExceptiopn e){
            System.out.println("There is another account with email " + email + ".\n");
        }

    }

    private static void register(String name, String email, String password, String device, Netfreaks netfreaks) throws NetfreaksAppOccupiedException, SameEmailExceptiopn {
        if(netfreaks.isAClientLoggedIn())
            throw new NetfreaksAppOccupiedException();
        else if (netfreaks.isEmailUsed(email))
            throw new SameEmailExceptiopn();

        netfreaks.register(name, email, password, device);

        System.out.println("Welcome " + name + " (" + device + ").\n");


    }

    private static void processUpload(Scanner in, Netfreaks netfreaks) {
        Product[] products = getUploadInput(in);
        SortedMap<String, Product> IteratableProducts = netfreaks.upload(products);


        System.out.println(Message.UPLOAD_SUCCESS.msg + getUploadOutput(IteratableProducts));
    }

    private static String getUploadOutput(SortedMap<String, Product> IteratableProducts) {
        String msg = "";
        String separator = "; ";
        for (Product product:IteratableProducts.values()) {
            String title = product.getTitle();
            String genre = product.getGenre();
            String[] cast = product.getCast();
            int ageRestriction = product.getAgeRestriction();
            int yearOfRelease = product.getYearOfRelease();
            msg += title + separator ;
            if(product instanceof Film) {
                Film film = (Film) product;
                msg += film.getDirector() + separator +  film.getDuration() + separator;
            }
            else{
                Series iteratableSeries = (Series) product;
                msg += iteratableSeries.getCreatorName() + separator +  iteratableSeries.getNSeasons() + separator + iteratableSeries.getNEpisodesPerSeason() + separator;
            }
            msg += ageRestriction + "+" + separator + yearOfRelease + separator + genre + separator;
            for(int i = 0; i < 3 && i < cast.length; i++)
                msg += cast[i] + separator;
            msg = msg.substring(0,msg.lastIndexOf(separator)) + "." + "\n";
        }
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
            String[] cast = new String[nCast];
            int castCounter = 0;
            while(castCounter < nCast)
                cast[castCounter++] = in.nextLine();
            movies[movieCounter++] = new FilmClass(title,directorName,duration,ageRestriction,yearOfRelease,genre,cast,nCast);
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
            String[] cast = new String[nCast];
            int castCounter = 0;
            while(castCounter < nCast)
                cast[castCounter++] = in.nextLine();
            series[seriesCounter++] = new SeriesClass(title,creatorName,nSeasons,nEpisodesPerSeason,ageRestriction,yearOfRelease,genre,cast,nCast);
        }
        Product[] products = new Product[nMovies + nSeries];
        System.arraycopy(movies,0,products,0,nMovies);
        System.arraycopy(series,0,products,nMovies,nSeries);

        return products;
    }
}
