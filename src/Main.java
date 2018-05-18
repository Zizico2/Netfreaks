import Netfreaks.*;

import java.util.Scanner;

public class Main {

    private enum Message {

        MESSAGE_REGISTERED("Mensagem registada."),
        DUPLICATE_MESSAGE("Mensagem duplicada."),
        NO_MESSAGES_WITH_EMAIL("Nao existem mensagens trocadas com esse email."),
        NO_MESSAGES_WITH_TOPIC(NO_MESSAGES_WITH_EMAIL.msg.replace("email", "assunto")),
        EXITING("A terminar."),
        UNKNOWN("UNKNOWN");

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
        Double a = 3.15;
        System.out.println(a.toString().substring(0,3));


        Netfreaks netfreaks = new NetfreaksClass();
        Scanner in = new Scanner(System.in);
        executeCommand(in, netfreaks);
    }


    private static Command getCommand(Scanner in) {
        return Command.valueOf(in.nextLine().toUpperCase());
    }

    private static void executeCommand(Scanner in, Netfreaks netfreaks) {
        Command cmd;
        try {
            do {
                cmd = getCommand(in);
                switch (cmd) {
                    case UPLOAD:
                        processUpload(in, netfreaks);
                        break;

                    case REGISTER:
                        processRegister(in, netfreaks);
                        break;

                    case LOGIN:
                        processLogin(netfreaks);
                        break;

                    case DISCONNECT:
                        processDisconnect(netfreaks);
                        break;

                    case LOGOUT:
                        processLogout(in, netfreaks);
                        break;

                    case MEMBERSHIP:
                        processMembership(in, netfreaks);
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
            } while (!cmd.equals(Command.EXIT));

        } catch (IllegalArgumentException e) {
            System.out.println(Message.UNKNOWN.msg);
        }
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

    private static void processLogout(Scanner in, Netfreaks netfreaks) {

    }

    private static void processDisconnect(Netfreaks netfreaks) {

    }

    private static void processLogin(Netfreaks netfreaks) {

    }

    private static void processRegister(Scanner in, Netfreaks netfreaks) {

    }

    private static void processUpload(Scanner in, Netfreaks netfreaks) {
    }
}
