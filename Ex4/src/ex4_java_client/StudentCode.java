package ex4_java_client; /**
 * @author AchiyaZigi
 * A trivial example for starting the server and running all needed commands
 */
import GUI.GUI_graph;
import api.DirectedWeightedGraphAlgorithms;
import core.Graph_Algorithms;
import org.json.JSONObject;

import java.io.IOException;
import java.security.GuardedObject;
import java.util.Scanner;

public class StudentCode {
    public static void main(String[] args) throws IOException {
        Client client = new Client();
        try {
            client.startConnection("127.0.0.1", 6666);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String graphStr = client.getGraph();
        System.out.println(graphStr);

        DirectedWeightedGraphAlgorithms graph=new Graph_Algorithms();
        graph.load(graphStr);
        int center=graph.center().getKey();
        String Info=client.getInfo();
        JSONObject obj=new JSONObject(Info);
        JSONObject game=obj.getJSONObject("GameServer");
        int sizeAgent=game.getInt("agents");

        for(int i=0;i<sizeAgent;i++){
            client.addAgent("{\"id\":" + center + "}");
        }

        String isRunningStr = client.isRunning();
        System.out.println(isRunningStr);

        GUI_graph gui =new GUI_graph(graph,client);
        client.start();

        while (client.isRunning().equals("true")) {
            client.move();
            System.out.println(client.getAgents());
            System.out.println(client.timeToEnd());

            Scanner keyboard = new Scanner(System.in);
            System.out.println("enter the next dest: ");
            int next = keyboard.nextInt();
            client.chooseNextEdge("{\"agent_id\":0, \"next_node_id\": " + next + "}");
            client.addAgent("{\"id\":" + next + "}");
            gui=new GUI_graph(graph,client);
        }
    }

}
