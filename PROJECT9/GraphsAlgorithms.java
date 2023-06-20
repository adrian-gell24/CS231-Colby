/**
 * Author: Adrian Gellert
 * Date: December 16, 2022
 * File: GraphsAlgorithms.java
 * Section: Lecture A, Lab D
 * Project: Path Around US
 * Course: CS231 
*/

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.Random;


public class GraphsAlgorithms{
    /**
     * @param filename
     * @return
     * @throws IOException
     */
    public static Graph<String, Object> readData(String filename) throws IOException{
        FileReader fr = new FileReader(filename);
        BufferedReader br = new BufferedReader(fr);

        Graph<String, Object> newGraph = new Graph<>();
        HashMap<String, Graph.Vertex<String, Object>> cities = new HashMap<>();

        br.readLine();
        String line = br.readLine();
        while(line != null){
            String[] contents = line.split(",");

            String state1 = contents[1];
            String state2 = contents[3];

            if (!cities.containsKey(state1)) cities.put(state1, newGraph.addVertex(state1));
            if (!cities.containsKey(state2)) cities.put(state2, newGraph.addVertex(state2));

            newGraph.addEdge(cities.get(state1), cities.get(state2), Integer.parseInt(contents[5]));
            line = br.readLine();
        }
        br.close();
        return newGraph;
    }

    /**
     * @param <V>
     * @param <E>
     * @param g
     * @param source
     * @return
     */
    public static <V, E> HashMap<Graph.Vertex<V, E>, Double> shortestPaths(Graph<V, E> g, Graph.Vertex<V, E> source){
        HashMap<Graph.Vertex<V, E>, Double> distances = new HashMap<>();
        for (Graph.Vertex<V, E> vertex : g.getVertices()){
            distances.put(vertex, Double.POSITIVE_INFINITY);
        } distances.put(source, 0.0);

        PriorityQueue<Graph.Vertex<V, E>> queue = new PriorityQueue<>(new Comparator<Graph.Vertex<V, E>>(){
            @Override
            public int compare(Graph.Vertex<V, E> o1, Graph.Vertex<V, E> o2){
                return distances.get(o1).compareTo(distances.get(o2));
            }
        });

        for (Graph.Vertex<V, E> vertex : g.getVertices()){
            queue.offer(vertex);
        }

        while (!queue.isEmpty()){ // O(V) where V is the number of vertices
            Graph.Vertex<V, E> cur = queue.poll(); // O(log V)

            for (Graph.Edge<V, E> edgeOut : cur.edgesOut()){ // O(V)
                Graph.Vertex<V, E> next = edgeOut.other(cur);

                double curDistToNext = distances.get(next);
                double newDist = distances.get(cur) + ((Graph.WeightedEdge<V, E>) edgeOut).weight;
                if (newDist < curDistToNext){
                    distances.put(next, newDist);
                    queue.remove(next); // O(log V) for Java, but could be O(1) if implemented better
                    queue.offer(next);
                }
            }
        } return distances;
    }

    /**
     * @param <V>
     * @param <E>
     * @param g
     * @param start
     * @return
     */
    public static <V, E> Collection<List<Graph.Edge<V, E>>> allHamCycles(Graph<V, E> g, Graph.Vertex<V, E> start){
        Collection<List<Graph.Edge<V, E>>> output = new ArrayList<>();
        List<Graph.Vertex<V, E>> curPath = new ArrayList<Graph.Vertex<V, E>>();
        curPath.add(start);
        allHamCycles(g, output, curPath);
        return output;
    }

    /**
     * @param <V>
     * @param <E>
     * @param g
     * @param output
     * @param curPath
     */
    private static <V, E> void allHamCycles(Graph<V, E> g, Collection<List<Graph.Edge<V, E>>> output, List<Graph.Vertex<V, E>> curPath){
        // empty sequence to contain edges
        List<Graph.Edge<V,E>> edgeSequence = new ArrayList<Graph.Edge<V,E>>();

        if (curPath.size() == g.getVertices().size()){
            // I'm done, I can convert this path into a sequence of edges and add that sequence to my output
            // loop to one less than path's size so that I can make edges using vertex pairs 
            // without going beyond the path's size
            for (int i = 0; i < curPath.size() - 1; i++){
                Graph.Vertex<V, E> vertex1 = curPath.get(i);
                Graph.Vertex<V, E> vertex2 = curPath.get(i + 1);
                edgeSequence.add(g.getEdge(vertex1, vertex2));
            }
            // adding the complete path made up of edges to the output collection
            // System.out.println("adding: "+ edgeSequence);
            output.add(edgeSequence);
            return;
        } else {
            // look at the last vertex in curPath
            Graph.Vertex<V, E> lastVertex = curPath.get(curPath.size() - 1);
            // for each neighbor of that last vertex that I haven't visited
            for (Graph.Vertex<V, E> neighbor: lastVertex.neighborsOut()){
                if (curPath.contains(neighbor)) continue; // skip ones that have been visited
                // add it to curPath
                curPath.add(neighbor);
                // recurse
                allHamCycles(g, output, curPath);
                // delete it from curPath
                curPath.remove(neighbor);
            }
        }
    }

    public static <E, V> List<Graph.Edge<V, E>> minTSP(Graph<V, E> g, Graph.Vertex<V, E> source){
        // minimal path to return
        // something to hold current path
        List<Graph.Edge<V, E>> minimalPath = new ArrayList<Graph.Edge<V, E>>();

        // something to hold current total weight
        Double minimalWeight = Double.POSITIVE_INFINITY;
        double totalWeight = 0;

        // loop through each Hamiltonian cycle
        for (List<Graph.Edge<V, E>> somePath : allHamCycles(g, source)){
            for (Graph.Edge<V, E> edge : somePath){
                // sum up the total weight of a given Hamiltonian cycle
                totalWeight += ((Graph.WeightedEdge<V, E>) edge).weight;
            }
            // if the weight is less than the current weight replace the minimal weight holder and the path holder
            if (totalWeight < minimalWeight){
                minimalWeight = totalWeight;
                minimalPath = somePath;
            }
        }

        // return that path
        return minimalPath;
    }



    public static <V, E> Collection<Graph.Edge<V, E>> mst(Graph<V, E> g){

        // vertices
        Collection<Graph.Vertex<V,E>> vertices = g.getVertices();
        // current starting vertex
        Graph.Vertex<V,E> cur = vertices.iterator().next();
        // visited vertices
        ArrayList<Graph.Vertex<V, E>> visited = new ArrayList<Graph.Vertex<V,E>>();
        visited.add(cur);

        // hold mst
        ArrayList<Graph.Edge<V, E>> mst = new ArrayList<Graph.Edge<V, E>>();

        // add all edges from cur to a priority queue ordered based on the edge's weight
        PriorityQueue<Graph.WeightedEdge<V,E>> queue = new PriorityQueue<>(new Comparator<Graph.Edge<V,E>> () {
            @Override
            public int compare(Graph.Edge<V,E> o1, Graph.Edge<V,E> o2){
                if (((Graph.WeightedEdge<V,E>)o1).weight > ((Graph.WeightedEdge<V,E>)o2).weight) return 1;
                else if (((Graph.WeightedEdge<V,E>)o1).weight < ((Graph.WeightedEdge<V,E>)o2).weight) return -1;
                else return 0;
            }
        });
        for (Graph.Edge<V,E> edge : cur.edgesOut()){
            queue.add((Graph.WeightedEdge<V, E>) edge);
        }

        while (visited.size() != g.getVertices().size()){
            Graph.Edge<V, E> minEdge = queue.poll();
            for (Graph.Vertex<V, E> vertex : minEdge.vertices()){
                if (visited.contains(vertex)) continue;
                mst.add(minEdge);
                visited.add(vertex);
                cur = vertex;
                for (Graph.Edge<V, E> edge : vertex.edgesOut()){
                    queue.add((Graph.WeightedEdge<V,E>)edge);
                }
            }
        }
        return mst;
    }

    public static <V, E> List<Graph.Edge<V, E>> tspApprox(Graph<V, E> g){
        // get an MST of the graph
        Collection<Graph.Edge<V,E>> tree = mst(g); 
        // System.out.println(tree);
        // visited vertices
        ArrayList<Graph.Vertex<V,E>> visited = new ArrayList<Graph.Vertex<V,E>>();
        // stack to go through vertices 
        Stack<Graph.Vertex<V,E>> stack = new Stack<>();
        // spanning tree to return
        List<Graph.Edge<V,E>> toReturn = new ArrayList<Graph.Edge<V,E>>();

        // choose a current (starting) vertex
        // vertices
        Collection<Graph.Vertex<V,E>> vertices = g.getVertices();
        List<Graph.Vertex<V,E>> vertexList = new ArrayList<>(vertices);

        // declaration of random variable
        Random rand = new Random();
        // current starting vertex
        // rand.nextInt(vertexList.size())
        Graph.Vertex<V,E> start = vertexList.get(0);
        // reference to last vertex
        Graph.Vertex<V,E> prev = null;

        stack.push(start);
        visited.add(start);

        while(!stack.isEmpty() && visited.size() != g.getVertices().size()){
            // pop the next vertex from the stack
            Graph.Vertex<V, E> cur = stack.pop();
            // System.out.println(cur);

            // adding edge from prev to cur
            if (prev != null && g.getEdge(prev, cur) != null){
                toReturn.add(g.getEdge(prev, cur));
            }

            // adding vertex popped from stack to the visited list
            if (!visited.contains(cur)){
                visited.add(cur);
            }

            // creating temperary list to randomize how neighbor vertices are added to the stack
            ArrayList<Graph.Vertex<V,E>> temp = new ArrayList<>();
            // for each edge in the tree
            for (Graph.Edge<V, E> edge : tree){
                // check whether one of its vertices is cur
                if (edge.vertices().contains(cur)){
                    Graph.Vertex<V,E> neighbor = edge.other(cur);
                    if (visited.contains(neighbor)) continue;
                    // fifty percent chance that the other vertex is immediately added to stack
                    if (rand.nextDouble() <= 0.51){
                        temp.add(neighbor);
                        continue;
                    } else{
                        stack.add(neighbor);
                    }
                    // visited.add(neighbor); I don't think I need this here
                }
            }
            // add vertices that were not immediately added to the stack 
            if (temp.size() != 0){
                for (Graph.Vertex<V, E> neighbor : temp){
                    stack.add(neighbor);
                }
            }

            /* 
            * Secondary attempt at randomization that dramatically decreases path size
            // // go through each edge in the tree
            // // at the first encounter of an edge that contains cur
            // // set endpoint1 to the other vertex of the edge and break out of the loop
            // for (Graph.Edge<V,E> edge : tree){
            //     if (edge.vertices().contains(cur)){
            //         endpoint1 = edge.other(cur);
            //         if (visited.contains(endpoint1)) continue;
            //         else break;
            //     }
            // }
            // // go through each edge in the tree
            // // at each encounter of an edge that contains cur
            // // set endpoint2 to the other vertex of the edge
            // // such that endpoint2 is a different possible direction during the search
            // for (Graph.Edge<V,E> edge : tree){
            //     if (edge.vertices().contains(cur)){
            //         endpoint2 = edge.other(cur);
            //         if (visited.contains(endpoint2)) continue;
            //     }
            // }

            // // randomize the direction of the search if the endpoints are different by
            // // choosing a probability for adding 
            // if (endpoint1 != endpoint2){
            //     System.out.print("endpoint1: " + endpoint1);
            //     System.out.print("endpoint2: " + endpoint2);
            //     if (rand.nextDouble() >= 0.6){
            //         stack.add(endpoint1);
            //         visited.add(endpoint1);
            //         System.out.println("chose endpoint 1");
            //     } else{
            //         stack.add(endpoint2);
            //         visited.add(endpoint2);
            //         System.out.println("chose endpoint 2");
            //     }
            // } else{ // otherwise just go the direction of endpoint1
            //     stack.add(endpoint1);
            //     visited.add(endpoint1);
            // } */

            prev = cur;
        }
        int totalWeight = 0;    // Hold cost of traversing all edges
        for (Graph.Edge<V, E> edge : toReturn){ // For each edge in the path

                totalWeight += ((Graph.WeightedEdge<V, E>) edge).weight;    // Add the weight of the edge to totalWeight
            }
        System.out.println("Total cost of travelling this path: " + totalWeight);    // Print out total cost
        return toReturn;
    }

    public static void main(String[] args) throws IOException{
        // Graph<String, Object> capitols = readData("StateData.csv");
        // HashMap<Graph.Vertex<String, Object>, Double> shortPath = shortestPaths(capitols, capitols.getVertex(0));
        // if (shortPath != null){
        //     System.out.println(shortPath.toString());
        // }
        /* Collection<List<Graph.Edge<String, Object>>> HamPath = allHamCycles(capitols, capitols.getVertex(0));
        if (HamPath != null){
            System.out.println(HamPath.toString());
        } */

        // Graph<String, Object> g = new Graph<>();
        // g.addVertex("AB");
        // g.addVertex("BC");
        // g.addVertex("CD");
        // g.addVertex("DE");
        // g.addVertex("EF");
        // g.addVertex("FG");
        // g.addVertex("GH");
        // g.addVertex("HI");
        // g.addVertex("JK");

        // g.addEdge(0, 1, 1);
        // g.addEdge(1, 2, 4);
        // g.addEdge(2, 0, 2);
        // g.addEdge(3, 4, 1);
        // g.addEdge(4, 5, 1);
        // g.addEdge(5, 6, 4);
        // g.addEdge(6, 7, 3);
        // g.addEdge(7, 8, 1);
        // g.addEdge(8, 0, 7);
        
        // // System.out.println(g);
        // // System.out.println(g.getVertex(0));
        // // System.out.println(shortestPaths(g, g.getVertex(0)));
        // // System.out.println(allHamCycles(g, g.getVertex(1)));
        // // System.out.println(minTSP(g, g.getVertex(3)));
        // System.out.println(mst(g));

        // Graph<String,Object> g = readData("StateData.csv");
        // Collection<Graph.Edge<String,Object>> tsp=tspApprox(g);
        // System.out.println(tsp.toString());
        // System.out.println(tsp.size());
        Graph<String, Object> graph = readData("stateData.csv");
        Collection<Graph.Edge<String, Object>> mstEdges = mst(graph);
        List<Graph.Edge<String, Object>> cycle = tspApprox(graph);
        double sum = 0.0;
        for (Graph.Edge<String, Object> edge : mstEdges){
            sum += ((Graph.WeightedEdge<String, Object>) edge).weight;
        } System.out.println("MST Cost: " + sum);
        sum = 0.0;
        for (Graph.Edge<String, Object> edge : cycle){
            sum += ((Graph.WeightedEdge<String, Object>) edge).weight;
        } System.out.println("Cycle Cost: " + sum);
    }
}