package Manager;

import Dijkstra.Dijkstra;
import Graph.*;
import java.io.*;
import java.util.*;

/**
 * Gere carregamento de mapas, verifica e adiciona vertices e arestas
 * @author José Canelas
 */
public final class PathManager{
    
    private final WeightedGraph<Local, Path> graph;

    /**
     *
     */
    public PathManager() {
        graph = new Dijkstra<>();
    }

    /**
     * Importação do Ficheiro
     * @throws IOException erro de não encontrar ficheiro
     * @return graph Grafo
     */
    
    public WeightedGraph readFile() throws IOException{

        Vertex<Local> local = null;
        InputStream input=null;
        Properties prop = new Properties();
        try {        
            input = new FileInputStream("file.properties");
            prop.load(input);
            String filePath = prop.getProperty("fileread");
            File arq = new File(filePath);
            FileReader fileReader = new FileReader(arq);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String read = bufferedReader.readLine();
            int numLocals = Integer.parseInt(read);
            int count = 0;
            while ((read = bufferedReader.readLine()) != null && count != numLocals) {
                StringTokenizer st = new StringTokenizer(read, ", ");
                int localId = Integer.parseInt(st.nextToken());
                String localName = st.nextToken();
                addLocal(new Local(localId, localName));
                count++;
            }
            numLocals = Integer.parseInt(read);
            count = 0;
            while ((read = bufferedReader.readLine()) != null && count != numLocals) {
       
                StringTokenizer st = new StringTokenizer(read, ", ");
                int pathId = Integer.parseInt(st.nextToken());
                String pathType = st.nextToken();
                String pathName = st.nextToken();
                int localP = Integer.parseInt(st.nextToken());
                int localC = Integer.parseInt(st.nextToken());
                boolean isWay = Boolean.parseBoolean(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());
                int dist = Integer.parseInt(st.nextToken());
                if (isWay) addPath(localP, localC, new Path(pathId, pathType, pathName, isWay, cost, dist)); 
                count++; 
            }
            for (Vertex<Local> v: graph.vertices()){
                local = checkLocalId(v.element().getLocalId());
                if(graph.checkAlone(local)) graph.removeVertex(v);
            }
            fileReader.close();
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return graph;
    }

    /**
     * Verifica id do Local 
     * @param position LocalId
     * @return local Local
     */
    
    public Vertex<Local> checkLocalId(int position) throws InvalidVertexException {
        if (position <= 0) {
            throw new InvalidVertexException("Local não encontrado!!");
        }
        Vertex<Local> local = null;
        for (Vertex<Local> l : graph.vertices()) {
            if (l.element().getLocalId() == position) {
                local = l;

            }

        }
        if (local == null) {
            return null;
            //throw new InvalidVertexException("Local não encontrado!!");
        }
        return local;

    }

    /**
     * Verifica nome do Local 
     * @param name LocalName
     * @return local Local
     */
    
    
    public Vertex<Local> checkLocalName(String name) throws InvalidVertexException {
        if (name == null) {
            throw new InvalidVertexException("Local não encontrado!!");
        }
        Vertex<Local> local = null;
        for (Vertex<Local> l : graph.vertices()) {
            if (l.element().getName().equalsIgnoreCase(name)) {
                local = l;

            }

        }
        if (local == null) {
            throw new InvalidVertexException("Local não encontrado!!");
        }
        return local;

    }

    
    /**
     * Adiciona local
     * @param local local
     */
    
    public void addLocal(Local local) throws InvalidVertexException{

        if (local == null) {
            throw new InvalidVertexException("Local não pode ser nulo!");
        }

        try {
            graph.insertVertex(local);
        } catch (IllegalArgumentException e) {
            throw new InvalidVertexException("Local (" + local.getName() + ") já existe");

        }
    }
    
    /**
     * Adiciona Caminho 
     * @param origin número
     * @param destination número
     * @param path Caminho
     */

    public void addPath(int origin, int destination, Path path)
            throws InvalidEdgeException {

        if (path == null) {
            throw new InvalidEdgeException("Path is null!!!");
        }

        Vertex<Local> orig = checkLocalId(origin);
        Vertex<Local> dest = checkLocalId(destination);

        try {
            graph.insertEdge(orig, dest,path);
        } catch (IllegalArgumentException e) {
            throw new InvalidEdgeException("Path (" + path.getName() + ") already exists");
        }
    }

}
