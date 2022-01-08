package GUI;

import api.DirectedWeightedGraph;
import api.EdgeData;
import api.NodeData;
import ex4_java_client.Agent;
import ex4_java_client.Client;
import ex4_java_client.Pokemon;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.XMLFormatter;

public class Panel extends JPanel {
    DirectedWeightedGraph graph;
    private double minX;
    private double minY;
    private final int ARR_SIZE = 8;
    private double maxX;
    private double maxY;
    ArrayList<Pokemon> pokemons=new ArrayList<>();
    ArrayList<Agent> agent=new ArrayList<>();
    Client client;
    private double scaleX;
    private double scaleY;
    public Panel(DirectedWeightedGraph graph,Client c) throws IOException {
        this.setFocusable(true);
        this.client=c;
        this.graph = graph;
        this.agent= Agent.loadAgent(c.getAgents());
        this.pokemons= Pokemon.loadPokemon(c.getPokemons());
        MaxMinForXY();
        image();
    }
    static BufferedImage img=null;
    static BufferedImage pokeImg=null;
    static BufferedImage ashImg=null;
    private void image() {
        try{
          this.img=ImageIO.read(new File("C:\\Users\\HP\\IdeaProjects\\Ex4\\src\\Image\\peke.jpg"));
          this.pokeImg=ImageIO.read(new File("C:\\Users\\HP\\IdeaProjects\\Ex4\\src\\Image\\pokemon.png"));
          ashImg=ImageIO.read(new File("C:\\Users\\HP\\IdeaProjects\\Ex4\\src\\Image\\Ash_icon-icons.com_67492.png"));

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void MaxMinForXY() {
        Iterator<NodeData> n = graph.nodeIter();
        NodeData node = n.next();
        minX = node.getLocation().x();
        minY = node.getLocation().y();

        maxX = node.getLocation().x();
        maxY = node.getLocation().y();
        while (n.hasNext()) {
            node = n.next();
            minX = Math.min(minX, node.getLocation().x());
            minY = Math.min(minY, node.getLocation().y());

            maxX = Math.max(maxX, node.getLocation().x());
            maxY = Math.max(maxY, node.getLocation().y());
        }

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.scaleX = this.getWidth() / Math.abs(maxX - minX)*0.89999 ;
        this.scaleY = this.getHeight() / Math.abs(maxY - minY)*0.855;
        Graphics2D g2d = (Graphics2D) g.create();
        paintBackground(g2d);
        drawGraph(g);
        drawAgents(g2d);
        drawPokemon(g2d);
        drawTimer(g);
        drawScore(g);
    }

    private void drawAgents(Graphics2D g) {
        Iterator<Agent> itrAgent=this.agent.iterator();
        while(itrAgent.hasNext()) {
            Agent OneAgent = itrAgent.next();
            int Xp = (int) ((OneAgent.getPos().x() - this.minX) * this.scaleX);
            int Yp = (int) ((OneAgent.getPos().y() - this.minY) * this.scaleY);
            g.drawImage(ashImg,Xp,Yp,null,this);
            repaint();
        }

    }

    private void drawPokemon(Graphics2D g) {
        Iterator<Pokemon> itrPokemon=this.pokemons.iterator();
        while(itrPokemon.hasNext()){
            Pokemon OnePoke=itrPokemon.next();
            int Xp=(int)((OnePoke.getLocation().x()-this.minX)*this.scaleX);
            int Yp=(int)((OnePoke.getLocation().y()-this.minY)*this.scaleY);
            g.drawImage(pokeImg,Xp,Yp,null,this);
            repaint();


        }


    }
    private void drawTimer(Graphics g) {
        g.setColor(Color.BLACK);
        g.setFont(new Font("OOP", Font.BOLD, 12));
        g.drawString("Time: " + (Integer.parseInt(this.client.timeToEnd()) / 1000), 40, 60);
    }

    private void paintBackground(Graphics2D g2d) {
        g2d.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);

    }

    private void drawGraph(Graphics g) {
        Iterator<NodeData> NodesIter=this.graph.nodeIter();
        while(NodesIter.hasNext()){
            NodeData n=NodesIter.next();
            drawNode(g,n);
            Iterator<EdgeData> edgesIter=this.graph.edgeIter(n.getKey());
            while(edgesIter.hasNext()){
                EdgeData e=edgesIter.next();
                drawEdge(g,e);
            }
        }
    }

    public void drawNode(Graphics g,NodeData node) {
            int x = (int) ((node.getLocation().x() - this.minX) * this.scaleX);
            int y = (int) ((node.getLocation().y() - this.minY) * this.scaleY);
            g.setColor(Color.BLUE);
            g.fillOval(x, y, 24, 24);
            g.setColor(Color.WHITE);
            g.setFont(new Font("OOP", Font.BOLD, 15));
            g.drawString(String.valueOf(node.getKey()), x+8 , y+14 );

    }


    public void drawEdge(Graphics g,EdgeData edge) {
            double x1 = graph.getNode(edge.getSrc()).getLocation().x();
            x1 = ((x1 - minX) * this.scaleX) + 16.5;
            double x2 = graph.getNode(edge.getSrc()).getLocation().y();
            x2 = ((x2 - minY) * this.scaleY) + 16.5;

            double y1 = this.graph.getNode(edge.getDest()).getLocation().x();
            y1 = ((y1 - this.minX) * this.scaleX) + 16.5;
            double y2 = this.graph.getNode(edge.getDest()).getLocation().y();
            y2 = ((y2 - this.minY) * this.scaleY) + 16.5;

            g.setColor(Color.BLACK);
            drawArrow(g,(int) x1, (int) x2, (int) y1, (int) y2);
            String weightString =String.valueOf(edge.getWeight()) ;
            weightString = weightString.substring(0,weightString.indexOf(".")+2);

            g.setColor(Color.RED);
            g.setFont(new Font("OOP", Font.BOLD, 15));
            g.drawString(weightString, (int)(x1*0.25 + y1*0.75),(int)(x2*0.25 + y2*0.75));

    }
    private void drawScore(Graphics g) {
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 12));
        String info = client.getInfo();
        org.json.JSONObject o = new org.json.JSONObject(info);
        org.json.JSONObject ob = o.getJSONObject("GameServer");
        int score = ob.getInt("agents");
        g.drawString("Score: " + score, 40, 80);
    }
    void drawArrow(Graphics g1, int x1, int y1, int x2, int y2) {
        Graphics2D g = (Graphics2D) g1.create();
        double dx = x2 - x1, dy = y2 - y1;
        double angle = Math.atan2(dy, dx);
        int len = (int) Math.sqrt(dx*dx + dy*dy);
        AffineTransform at = AffineTransform.getTranslateInstance(x1, y1);
        at.concatenate(AffineTransform.getRotateInstance(angle));
        g.transform(at);
        g.drawLine(0, 0, len, 0);
        g.fillPolygon(new int[] {len, len-ARR_SIZE, len-ARR_SIZE, len},
                new int[] {0, -ARR_SIZE, ARR_SIZE, 0}, 4);
    }










}
