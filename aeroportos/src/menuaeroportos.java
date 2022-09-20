import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.lang.*;

public class menuaeroportos {
    public static void main(String[] args){
        int V = 40;
        int src=-1, dest=-1;
        List<airports> lista = new ArrayList<airports>();

        List<List<Node> > adj
                = new ArrayList<List<Node> >();

        for (int i = 0; i < V; i++) {
            List<Node> item = new ArrayList<Node>();
            adj.add(item);
        }
        int source = 0;//indice do aeroporto de origem! buscar aeroporto e demais infos
        System.out.println("SIGLA / CIDADE / ESTADO");

        //Coleta dos dados do banco de dados
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cadastro",
                    "root", "felipeds18");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from aeroportos");

            //armazena lista de aeroportos
            while (resultSet.next()) {
                int id = Integer.parseInt(resultSet.getString("id"));
                double latitude = Double.parseDouble((resultSet.getString("latitude")));
                double longitude = Double.parseDouble((resultSet.getString("longitude")));
                lista.add(new airports(id, resultSet.getString("sigla"),
                        resultSet.getString("nome"), resultSet.getString("cidade"),
                        resultSet.getString("estado"), latitude, longitude));
                System.out.println(resultSet.getString("sigla") + " " + resultSet.getString("estado") +
                        " " + resultSet.getString("cidade"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Interação com o usuário
        System.out.println("Escolha o aeroporto de origem: ");
        Scanner aeroportoOrigem = new Scanner(System.in);
        String origem = aeroportoOrigem.nextLine();
        System.out.println("Escolha o aeroporto de destino: ");
        Scanner aeroportoDestino = new Scanner(System.in);
        String destino = aeroportoDestino.nextLine();

        for(int i=0; i<V; i++){
            if(origem.equals(lista.get(i).sigla)){
                src = lista.get(i).id-1;
            }
            if(destino.equals(lista.get(i).sigla)){
                dest = lista.get(i).id-1;
;           }
        }

        //Cáculo das distancias
        for(int k=0; k<V; k++) {
            for(int j = 0; j<V; j++){
                if((k == src && j == dest) || (k == dest && j == src)){
                }
                else {
                    if (k != j) {
                        double temp = calcDist.distance(lista.get(k).latitude, lista.get(k).longitude,
                                lista.get(j).latitude, lista.get(j).longitude);
                        adj.get(k).add(new Node(lista.get(j).id - 1, temp));

                    }
                }
            }
        }

        //Montagem do grafo
        grafo dpq = new grafo(V);
        dpq.dijkstra(adj, src);

        int idans = 0;
        for(int i=0; i<40; i++){
            double a = calcDist.distance(lista.get(src).latitude, lista.get(src).longitude, lista.get(i).latitude,
                    lista.get(i).longitude);
            double b = calcDist.distance(lista.get(i).latitude, lista.get(i).longitude, lista.get(dest).latitude,
                    lista.get(dest).longitude);
            if(a+b == dpq.dist[dest]){
                idans = i+1;
            }
        }

        //Salvando a consulta no banco de dados
        for(int i=0; i<40; i++){
            if(idans == lista.get(i).id){
                String caminho = origem + '-' + lista.get(i).sigla + '-' + destino;
                System.out.println(origem + '-' + lista.get(i).sigla + '-' + destino);
                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cadastro",
                            "root", "felipeds18");
                    Statement statement = connection.createStatement();
                    String sqlInsertPath = "insert into consultas (origem,destino,rota) values ('"+origem+"','"+destino+"','"+caminho+"');";
                    statement.executeUpdate(sqlInsertPath);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
