import java.io.*;
import java.net.*;
import java.util.Random;


public class Server{

    private static Integer port = 4000;
    private static String welcomeMsg = "Bem vindo a Pedra Papel Tesoura Lagarto Spock\n";

    public static void main(String args[]) throws Exception {

        Integer resClient_1;
        Integer inputClient_1;
        Integer inputClient_2;
        Integer py = 0;
        Integer jav = 0;
        Integer dr = 0;

        String resultados = "   PYTHON    JAVA     RESULTADO \n";


        System.out.println(Server.welcomeMsg);


        ServerSocket welcomeSocket = new ServerSocket(Server.port);
        System.out.println("\nRodando na porta: " + welcomeSocket.getLocalPort() + " ...");

        Socket client_1 = welcomeSocket.accept();
        if (client_1.isConnected()) {
            System.out.println("\nAdversario (" + (client_1.getLocalAddress().toString()).substring(1) + ":"
                    + client_1.getLocalPort() + ") entrou no jogo");
        }

        while (!welcomeSocket.isClosed()) {
            for(int i=1; i<16; i++) {
                DataOutputStream outClient_1 = new DataOutputStream(client_1.getOutputStream());
                outClient_1.flush();
                BufferedReader inClient_1 = new BufferedReader(new InputStreamReader(client_1.getInputStream()));
                System.out.println(i);
                inputClient_1 = inClient_1.read() - 48;
                System.out.println(i);
                System.out.println(inputClient_1 + "\n");
                Integer input;
                Random rand = new Random();
                int aleatorio = rand.nextInt(100);
                if (aleatorio % 5 == 0) {
                    input = 0;
                } else if (aleatorio % 5 == 1) {
                    input = 2;
                } else if (aleatorio % 5 == 2) {
                    input = 1;
                } else if (aleatorio % 5 == 3) {
                    input = 4;
                } else {
                    input = 3;
                }
                
                inputClient_2 = input;
                System.out.println(inputClient_2 + "\n");
                resClient_1 = inputClient_2;
                outClient_1.writeInt(resClient_1);

                if (inputClient_1.equals(inputClient_2)) {
                    resultados = resultados + i + "   " + inputClient_1 + "      " + inputClient_2 + "          Draw\n";
                    dr++;
                } else if (inputClient_1.equals(1) && inputClient_2.equals(4)) {
                    resultados = resultados + i + "   " + "SPOCK" + "      " + "TESOURA" + "         DERROTA\n";
                    py++;
                } else if (inputClient_1.equals(1) && inputClient_2.equals(2)) {
                    resultados = resultados + i + "   " + "SPOCK" + "      " + "PAPEL" + "            VITORIA\n";
                    jav++;
                } else if (inputClient_1.equals(0) && inputClient_2.equals(2)) {
                    resultados = resultados + i + "   PEDRA      PAPEL          VITORIA\n";
                    jav++;
                } else if (inputClient_1.equals(2) && inputClient_2.equals(0)) {
                    resultados = resultados + i + "   PAPEL      PEDRA         DERROTA\n";
                    py++;
                } else if (inputClient_1.equals(1) && inputClient_2.equals(0)) {
                    resultados = resultados + i + "   SPOCK      PEDRA        DERROTA\n";
                    py++;
                } else if (inputClient_1.equals(2) && inputClient_2.equals(3)) {
                    resultados = resultados + i + "   PAPEL       LAGARTO            VITORIA\n";
                    jav++;
                } else if (inputClient_1.equals(1) && inputClient_2.equals(3)) {
                    resultados = resultados + i + "   SPOCK       LAGARTO           VITORIA\n";
                    jav++;
                } else if (inputClient_1.equals(2) && inputClient_2.equals(4)) {
                    resultados = resultados + i + "   PAPEL      TESOURA            VITORIA\n";
                    jav++;
                } else if (inputClient_1.equals(3) && inputClient_2.equals(4)) {
                    resultados = resultados + i + "   LAGARTO      TESOURA            VITORIA\n";
                    jav++;
                } else if (inputClient_1.equals(3) && inputClient_2.equals(0)) {
                    resultados = resultados + i + "   LAGARTO       PEDRA             VITORIA\n";
                    jav++;
                } else if (inputClient_1.equals(4) && inputClient_2.equals(1)) {
                    resultados = resultados + i + "   TESOURA        SPOCK            VITORIA\n";
                    jav++;
                } else if (inputClient_1.equals(4) && inputClient_2.equals(0)) {
                    resultados = resultados + i + "   TESOURA        PEDRA            VITORIA\n";
                    jav++;
                } else if (inputClient_1.equals(0) && inputClient_2.equals(1)) {
                    resultados = resultados + i + "   PEDRA       SPOCK            VITORIA\n";
                    jav++;
                } else if (inputClient_1.equals(2) && inputClient_2.equals(1)) {
                    resultados = resultados + i + "   PAPEL          SPOCK         DERROTA\n";
                    py++;
                } else if (inputClient_1.equals(3) && inputClient_2.equals(2)) {
                    resultados = resultados + i + "   LAGARTO           PAPEL         DERROTA\n";
                    py++;
                } else if (inputClient_1.equals(3) && inputClient_2.equals(1)) {
                    resultados = resultados + i + "   LAGARTO         SPOCK         DERROTA\n";
                    py++;
                } else if (inputClient_1.equals(4) && inputClient_2.equals(2)) {
                    resultados = resultados + i + "   TESOURA         PAPEL         DERROTA\n";
                    py++;
                } else if (inputClient_1.equals(4) && inputClient_2.equals(3)) {
                    resultados = resultados + i + "   TESOURA          LAGARTO        DERROTA\n";
                    py++;
                } else if (inputClient_1.equals(0) && inputClient_2.equals(3)) {
                    resultados = resultados + i + "   PEDRA          LAGARTO         DERROTA\n";
                    py++;
                } else if (inputClient_1.equals(0) && inputClient_2.equals(4)) {
                    resultados = resultados + i + "   PEDRA          TESOURA         DERROTA\n";
                    py++;
                }
                System.out.println("\n" + resultados + "\n");
                System.out.println(i);

            }
            client_1.close();
        }

        System.out.println("\n" + resultados + "\n");
        System.out.println("\nVitórias: " + jav + "\n" +       "Derrotas: " + py + "\n" + "Empates: " + dr + "\n");
        if(jav>py){
            System.out.println("\nServidor ganhou\n");
        }
        else if(py>jav){
            System.out.println("\nCliente ganhou\n");
        }
        else{
            System.out.println("\nEmpate\n");
        }
    }
}