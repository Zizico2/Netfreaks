import Exceptions.*;
import Netfreaks.*;
import Netfreaks.Product.FilmClass;
import Netfreaks.Product.Product;
import Netfreaks.Product.SeriesClass;

import java.util.Scanner;

public class Main {

    private enum Message {

        UPLOAD_SUCCESS("Database was updated:\n"),
        DUPLICATE_MESSAGE("Mensagem duplicada."),
        NO_MESSAGES_WITH_EMAIL("Nao existem mensagens trocadas com esse email."),
        NO_MESSAGES_WITH_TOPIC(NO_MESSAGES_WITH_EMAIL.msg.replace("email", "assunto")),
        EXITING("Exiting..."),
        UNKNOWN("Unknown command.");

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
        return Command.valueOf(in.nextLine().toUpperCase());
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
                        processSelect(netfreaks);
                        break;

                    case WATCH:
                        processWatch(netfreaks);
                        break;

                    case RATE:
                        processRate(netfreaks);
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

    private static void processProfile(Scanner in, Netfreaks netfreaks) {

    }

    private static void processSearchByRate(Netfreaks netfreaks) {

    }

    private static void processSearchByName(Netfreaks netfreaks) {

    }

    private static void processSearchByGenre(Netfreaks netfreaks) {

    }

    private static void processInfoAccount(Netfreaks netfreaks) {

    }

    private static void processRate(Netfreaks netfreaks) {

    }

    private static void processWatch(Netfreaks netfreaks) {

    }

    private static void processSelect(Netfreaks netfreaks) {

    }

    private static void processMembership(Scanner in, Netfreaks netfreaks) {

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
        System.out.println("Goodbye " + netfreaks.getActiveProfile() + " (" + netfreaks.getActiveDevice() + " still connected).\n");
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
        System.out.println("Goodbye " + netfreaks.getActiveProfile() + " (" + netfreaks.getActiveDevice() + " was disconnected).\n");
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
        System.out.println("Welcome " + netfreaks.getActiveProfile() + " (" +  device + ").\n");
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
        System.out.println(Message.UPLOAD_SUCCESS.msg + netfreaks.upload(products));
    }
}
