import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * A graph implementation that allows for vertices and edges to hold distinct
 * data entries of different types.
 * 
 * @author Max Bender
 */
@SuppressWarnings("unchecked")
public class Graph<V, E> {
    /**
     * A holder class for Vertices that allows each Vertex to track all incident
     * edges and adjacent vertices, as well as store a data entry associated with
     * the vertex.
     */
    public static class Vertex<V, E> {
        /**
         * The data associated with this vertex.
         */
        public V data;

        /**
         * The edges entering this vertex.
         * 
         * May have overlap with edgesOut, in the event of undirected edges.
         */
        private Map<Vertex<V, E>, Edge<V, E>> edgesIn;

        /**
         * The edges leaving this vertex.
         * 
         * May have overlap with edgesIn, in the event of undirected edges.
         */
        private Map<Vertex<V, E>, Edge<V, E>> edgesOut;

        /**
         * Constructs a vertex without any data.
         */
        public Vertex() {
            this(null);
        }

        /**
         * Constructs a vertex with the specified data.
         * 
         * @param data the data to be stored within this vertex
         */
        public Vertex(V data) {
            this.data = data;
            edgesIn = new HashMap<Vertex<V, E>, Edge<V, E>>();
            edgesOut = new HashMap<Vertex<V, E>, Edge<V, E>>();
        }

        public String toString() {
            return "<" + (data == null ? "" : data) + ">";
        }

        /**
         * Returns the edge from this vertex to the vertex specified by {@code other}.
         * If no such edge exists, returns {@code null}.
         * 
         * @param other the other endpoint of the edge to be returned
         * @return the edge from this vertex to the vertex specified by {@code other}.
         *         If no such edge exists, returns {@code null}.
         */
        public Edge<V, E> getEdgeTo(Vertex<V, E> other) {
            return edgesOut.get(other);
        }

        /**
         * Returns the edge from the vertex specified by {@code other} to this vertex.
         * If no such edge exists, returns {@code null}.
         * 
         * @param other the other endpoint of the edge to be returned
         * @return the edge from the vertex specified by {@code other} to this vertex.
         *         If no such edge exists, returns {@code null}.
         */
        public Edge<V, E> getEdgeFrom(Vertex<V, E> other) {
            return edgesIn.get(other);
        }

        /**
         * Returns the collection of vertices reachable from this vertex by a single
         * edge.
         * 
         * @return the collection of vertices reachable from this vertex by a single
         *         edge.
         */
        public Collection<Vertex<V, E>> neighborsOut() {
            return edgesOut.keySet();
        }

        /**
         * Returns the collection of vertices who can reach this vertex by a single
         * edge.
         * 
         * @return the collection of vertices who can reach this vertex by a single
         *         edge.
         */
        public Collection<Vertex<V, E>> neighborsIn() {
            return edgesIn.keySet();
        }

        /**
         * Returns the collection of edges leaving this vertex.
         * 
         * @return the collection of edges leaving this vertex.
         */
        public Collection<Edge<V, E>> edgesOut() {
            return edgesOut.values();
        }

        /**
         * Returns the collection of edges entering this vertex.
         * 
         * @return the collection of edges entering this vertex.
         */
        public Collection<Edge<V, E>> edgesIn() {
            return edgesIn.values();
        }
    }

    /**
     * A holder class for Edges that allows each Edge to track the Vertex endpoints
     * as well as the associated data of the edge.
     */
    public static class Edge<V, E> {
        /**
         * The data associated with this Edge.
         */
        public E data;
        /**
         * The Vertex endpoints of this edge.
         */
        private Vertex<V, E>[] vertices;
        /**
         * True if this is a directed edge, otherwise false.
         */
        private boolean directed;

        /**
         * Constructs an edge with the specified endpoints.
         * 
         * @param u an endpoint of this edge
         * @param v an endpoint of this edge
         */
        public Edge(Vertex<V, E> u, Vertex<V, E> v) {
            this(u, v, false, null);
        }

        /**
         * Constructs an edge with the specified endpoints holding the specified data.
         * 
         * @param u    an endpoint of this edge
         * @param v    an endpoint of this edge
         * @param data the associated data of this edge
         */
        public Edge(Vertex<V, E> u, Vertex<V, E> v, E data) {
            this(u, v, false, data);
        }

        /**
         * Constructs an edge with the specified endpoints.
         * 
         * <p>
         * If directed is {@code true}, the constructed edge becomes a directed edge,
         * where {@code u} is the source of the edge and {@code v} is the target of the
         * edge.
         * 
         * @param u        an endpoint of this edge
         * @param v        an endpoint of this edge
         * @param directed specified whether this edge is to be directed or undirected
         */
        public Edge(Vertex<V, E> u, Vertex<V, E> v, boolean directed) {
            this(u, v, directed, null);
        }

        /**
         * Constructs an edge with the specified endpoints holding the specified data.
         * 
         * <p>
         * If directed is true, the constructed edge becomes a directed edge, where
         * {@code u} is the source of the edge and {@code v} is the target of the edge.
         * 
         * @param u        an endpoint of this edge
         * @param v        an endpoint of this edge
         * @param directed specified whether this edge is to be directed or undirected
         * @param data     the associated data of this edge
         */
        public Edge(Vertex<V, E> u, Vertex<V, E> v, boolean directed, E data) {
            vertices = (Vertex<V, E>[]) new Vertex[] { u, v };
            this.data = data;
            this.directed = directed;
        }

        /**
         * Returns a list containing the vertices in this edge.
         * 
         * <p>
         * For undirected edges the order is meaningless, but for directed edges the
         * source will be before the target.
         * 
         * @return a list containing the vertices in this edge.
         */
        public List<Vertex<V, E>> vertices() {
            return new ArrayList<Vertex<V, E>>(Arrays.asList(vertices));
        }

        /**
         * Returns whether this edge is directed.
         * 
         * @return whether this edge is directed.
         */
        public boolean isDirected() {
            return directed;
        }

        /**
         * Returns the source of this directed edge.
         * 
         * @return the source of this directed edge.
         * @throws UnsupportedOperationException if this edge is not directed.
         */
        public Vertex<V, E> source() {
            if (!directed)
                throw new UnsupportedOperationException("This edge is undirected; it has no source or sink.");

            return vertices[0];
        }

        /**
         * Returns the target of this directed edge.
         * 
         * @return the target of this directed edge.
         * @throws UnsupportedOperationException if this edge is not directed.
         */
        public Vertex<V, E> target() {
            if (!directed)
                throw new UnsupportedOperationException("This edge is undirected; it has no source or sink.");
            return vertices[1];
        }

        /**
         * Returns an edge that is the reverse of this edge.
         * 
         * <p>
         * If this edge is directed, the edge returned by this method will be directed
         * in the opposite direction.
         * 
         * @return an edge that is the reverse of this edge.
         */
        public Edge<V, E> reverse() {
            return new Edge<V, E>(vertices[1], vertices[0], directed, data);
        }

        /**
         * Returns {@code true} if the specified object is an edge with the same
         * endpoints and in the same direction (if applicable) as this edge.
         * 
         * <p>
         * Notably, the data of the edges are not considered.
         */
        public boolean equals(Object o) {
            if (!(o instanceof Edge))
                return false;
            Edge<?, ?> edge = (Edge<?, ?>) o;
            if (directed != edge.isDirected())
                return false;
            if (directed)
                return source().equals(edge.source()) && target().equals(edge.target());
            return vertices().containsAll(edge.vertices()) && edge.vertices().containsAll(vertices());
        }

        public int hashCode() {
            return vertices[0].hashCode() + vertices[1].hashCode();
        }

        /**
         * Returns the other endpoint of this edge contrasting the specified vertex, or
         * {@code null} if the specified vertex is not an endpoint of this edge.
         * 
         * @param vertex an endpoint of this edge
         * @return the other endpoint of this edge contrasting the specified vertex, or
         *         {@code null} if the specified vertex is not an endpoint of this edge.
         */
        public Vertex<V, E> other(Vertex<V, E> vertex) {
            if (!vertices().contains(vertex))
                return null;
            if (vertices[0].equals(vertex))
                return vertices[1];
            return vertices[0];
        }

        public String toString() {
            if (directed)
                return "(" + vertices[0] + ", " + vertices[1] + (data == null ? "" : ": <" + data + ">") + ")";
            else
                return "{" + vertices[0] + ", " + vertices[1] + (data == null ? "" : ": <" + data + ">") + "}";
        }
    }

    /**
     * An extension of the Edge class that also tracks a double for the associated
     * weight of the edge.
     */
    public static class WeightedEdge<V, E> extends Edge<V, E> {
        /**
         * The associated weight of this edge.
         */
        double weight;

        /**
         * Created a weighted edge with the given endpoints and weight.
         * 
         * @param u      an endpoint of this edge
         * @param v      an endpoint of this edge
         * @param weight the weight of this edge
         */
        public WeightedEdge(Vertex<V, E> u, Vertex<V, E> v, double weight) {
            super(u, v);
            this.weight = weight;
        }

        /**
         * Creates a weighted edge with the specified endpoints, weight, and data.
         * 
         * @param u      an endpoint of this edge
         * @param v      an endpoint of this edge
         * @param weight the weight of this edge
         * @param data   the associated data of this edge
         */
        public WeightedEdge(Vertex<V, E> u, Vertex<V, E> v, double weight, E data) {
            super(u, v, data);
            this.weight = weight;
        }

        /**
         * Creates a weighted edge with the associated end points and data.
         * 
         * <p>
         * If {@code directed} is {@code true}, this edge is directed from {@code u} to
         * {@code v}. Otherwise, this edge is undirected.
         * 
         * @param u        an endpoint of this edge
         * @param v        an endpoint of this edge
         * @param weight   the weight of this edge
         * @param directed whether this edge is to be directed
         */
        public WeightedEdge(Vertex<V, E> u, Vertex<V, E> v, double weight, boolean directed) {
            super(u, v, directed);
            this.weight = weight;
        }

        /**
         * Creates a weighted edge with the associated endpoints, weight, and data.
         * 
         * <p>
         * If {@code directed} is {@code true}, this edge is directed from {@code u} to
         * {@code v}. Otherwise, this edge is undirected.
         * 
         * @param u        an endpoint of this edge
         * @param v        an endpoint of this edge
         * @param weight   the weight of this edge
         * @param directed whether this edge is to be directed
         * @param data     the data of this edge
         */
        public WeightedEdge(Vertex<V, E> u, Vertex<V, E> v, double weight, boolean directed, E data) {
            super(u, v, directed, data);
            this.weight = weight;
        }

        public WeightedEdge<V, E> reverse() {
            Iterator<Vertex<V, E>> iter = vertices().iterator();
            Vertex<V, E> u = iter.next();
            Vertex<V, E> v = iter.next();
            return new WeightedEdge<V, E>(v, u, weight, isDirected(), data);
        }

        public String toString() {
            if (isDirected())
                return "(" + vertices().get(0) + ", " + vertices().get(1) + ": " + weight
                        + (data == null ? "" : " <" + data + ">") + ")";
            else
                return "{" + vertices().get(0) + ", " + vertices().get(1) + ": " + weight
                        + (data == null ? "" : " <" + data + ">") + "}";
        }
    }

    /**
     * The vertices that comprise this graph.
     */
    private HashSet<Vertex<V, E>> vertices;
    /**
     * An ArrayList containing the vertices of this edge for fast lookup by order
     * entered.
     */
    private ArrayList<Vertex<V, E>> verticesOrdered;
    /**
     * The edges that comprise this graph.
     */
    private HashSet<Edge<V, E>> edges;

    /**
     * Creates an empty graph.
     */
    public Graph() {
        vertices = new HashSet<Vertex<V, E>>();
        verticesOrdered = new ArrayList<Vertex<V, E>>();
        edges = new HashSet<Edge<V, E>>();
    }

    /**
     * Creates a graph with {@code n} (data-less) vertices and no edges.
     * 
     * @param n the number of vertices to create
     */
    public Graph(int n) {
        this();
        for (int i = 0; i < n; i++) {
            addVertex();
        }
    }

    /**
     * Uses the specified edges to create a graph.
     * 
     * <p>
     * This graph does not contain these edges themselves, but rather creates
     * shallow copies of the underyling vertices and edges. This means that
     * modifying the data of the graph created will result in changes to the data of
     * the edges and vertices specified.
     * 
     * @param c the collection of edges from which to build this graph
     */
    public Graph(Collection<? extends Edge<V, E>> c) {
        this();
        HashMap<Vertex<V, E>, Vertex<V, E>> vertexMap = new HashMap<Vertex<V, E>, Vertex<V, E>>();
        for (Edge<V, E> e : c) {
            for (Vertex<V, E> v : e.vertices())
                if (!vertexMap.containsKey(v))
                    vertexMap.put(v, new Vertex<V, E>(v.data));
            if (e instanceof WeightedEdge)
                addEdge(vertexMap.get(e.vertices().get(0)), vertexMap.get(e.vertices().get(1)),
                        ((WeightedEdge<V, E>) e).weight, e.isDirected(), e.data);
            else
                addEdge(vertexMap.get(e.vertices().get(0)), vertexMap.get(e.vertices().get(1)),
                        e.isDirected(), e.data);
        }
    }

    /**
     * Returns the collection of vertices that backs this Graph.
     * 
     * <p>
     * Changing this collection has effects on the Graph; the collection is
     * cloneable, so any changes should probably be applied to a cloned copy of this
     * collection.
     * 
     * @return the collection of vertices in this Graph.
     */
    public Collection<Vertex<V, E>> getVertices() {
        return vertices;
    }

    /**
     * Returns the collection of edges that backs this Graph.
     * 
     * <p>
     * Changing this collection has effects on the Graph; the collection is
     * cloneable, so any changes should probably be applied to a cloned copy of this
     * collection.
     * 
     * @return the collection of edges in this Graph.
     */
    public Collection<Edge<V, E>> getEdges() {
        return edges;
    }

    /**
     * Returns the vertex created at the specified index (so {@code getVertex(0)} is
     * the first vertex created, {@code getVertex(1)} is the second, etc.).
     * 
     * @param index the position of the vertex to be returned
     * @return the vertex created at the specified index.
     */
    public Vertex<V, E> getVertex(int index) {
        return verticesOrdered.get(index);
    }

    /**
     * Returns the edge between the vertices returned by {@code getVertex(i)} and
     * {@code getVertex(j)}, or {@code null} if no such edge exists.
     * 
     * <p>
     * Specifically, an edge will be returned if and only if there is an undirected
     * edge between {@code getVertex(i)} and {@code getVertex(j)} or a directed edge
     * from {@code getVertex(i)} to {@code getVertex(j)}.
     * 
     * @param i the position of the first vertex
     * @param j the position of the second vertex
     * @return the edge between the vertices returned by {@code getVertex(i)} and
     *         {@code getVertex(j)}, or {@code null} if no such edge exists.
     */
    public Edge<V, E> getEdge(int i, int j) {
        return getEdge(getVertex(i), getVertex(j));
    }

    /**
     * Returns the edge between the vertices {@code u} and {@code v}, or
     * {@code null} if no such edge exists.
     * 
     * <p>
     * Specifically, an edge will be returned if and only if there is an undirected
     * edge between {@code u} and {@code v} or a directed edge from {@code u} to
     * {@code v}.
     * 
     * @param u the source vertex
     * @param v the target vertex
     * @return the edge between the vertices u and v, or null if no such edge
     *         exists.
     */
    public Edge<V, E> getEdge(Vertex<V, E> u, Vertex<V, E> v) {
        Edge<V, E> uToV = u.getEdgeTo(v);
        if (uToV != null)
            return uToV;
        else
            return v.getEdgeTo(u);
    }

    /**
     * Creates a new data-less vertex, adds it to this graph, then returns it.
     * 
     * @return a new data-less vertex.
     */
    public Vertex<V, E> addVertex() {
        return addVertex(null);
    }

    /**
     * Creates, add to the graph, and returns a new vertex with the specified data.
     * 
     * @param data the associated data of the vertex to be created
     * @return a new vertex with the specified data.
     */
    public Vertex<V, E> addVertex(V data) {
        Vertex<V, E> newV = new Vertex<V, E>(data);
        vertices.add(newV);
        verticesOrdered.add(newV);
        return newV;
    }

    /**
     * Adds the specified edge to the graph.
     * 
     * <p>
     * If this edge already exists in the graph, ie if there is any edge in the
     * graph which {@code equals} this edge, does nothing.
     * 
     * @param edge the edge to be added
     * @return true if this edge did not already exist in the graph.
     */
    public boolean addEdge(Edge<V, E> edge) {
        if (edges.add(edge)) {
            for (Vertex<V, E> vertex : edge.vertices()) {
                vertices.add(vertex);
            }
            if (edge.isDirected()) {
                edge.source().edgesOut.put(edge.source(), edge);
                edge.target().edgesIn.put(edge.source(), edge);
            } else {
                for (Vertex<V, E> vertex : edge.vertices()) {
                    vertex.edgesIn.put(edge.other(vertex), edge);
                    vertex.edgesOut.put(edge.other(vertex), edge);
                }
            }
            return true;
        }
        return false;
    }

    /**
     * Adds an undirected edge with the specified endpoints to the graph.
     * 
     * <p>
     * If this edge already exists in the graph, ie if there is any edge in the
     * graph which {@code equals} this edge, does nothing.
     * 
     * @param u an endpoint of the edge to be created
     * @param v an endpoint of the edge to be created
     * @return the edge created, or {@code null} if the edge already exists.
     */
    public Edge<V, E> addEdge(Vertex<V, E> u, Vertex<V, E> v) {
        return addEdge(u, v, false, null);
    }

    /**
     * Adds an edge with the specified endpoints and data to the graph. If
     * {@code directed}, the resulting edge will be a directed edge, otherwise it is
     * undirected.
     * 
     * <p>
     * If this edge already exists in the graph, ie if there is any edge in the
     * graph which {@code equals} this edge, does nothing.
     * 
     * @param i a positional endpoint of the edge to be created
     * @param j a positional endpoint of the edge to be created
     * @return the edge if it doesn't already exist in this graph, otherwise
     *         {@code null}.
     */
    public Edge<V, E> addEdge(int i, int j) {
        return addEdge(getVertex(i), getVertex(j));
    }

    /**
     * Adds an edge with the specified endpoints to the graph. If {@code directed},
     * the resulting edge will be a directed edge, otherwise it is undirected.
     * 
     * <p>
     * If this edge already exists in the graph, ie if there is any edge in the
     * graph which {@code equals} this edge, does nothing.
     * 
     * @param u        an endpoint of the edge to be created
     * @param v        an endpoint of the edge to be created
     * @param directed whether the edge to be created will be directed
     * @return the edge if it doesn't already exist in this graph, otherwise
     *         {@code null}.
     */
    public Edge<V, E> addEdge(Vertex<V, E> u, Vertex<V, E> v, boolean directed) {
        return addEdge(u, v, directed, null);
    }

    /**
     * Adds an edge with the specified endpoints to the graph. If {@code directed},
     * the resulting edge will be a directed edge, otherwise it is undirected.
     * 
     * <p>
     * If this edge already exists in the graph, ie if there is any edge in the
     * graph which {@code equals} this edge, does nothing.
     * 
     * @param i        a positional endpoint of the edge to be created
     * @param j        a positional endpoint of the edge to be created
     * @param directed whether the edge to be created will be directed
     * @return the edge if it doesn't already exist in this graph, otherwise
     *         {@code null}.
     */
    public Edge<V, E> addEdge(int i, int j, boolean directed) {
        return addEdge(getVertex(i), getVertex(j), directed);
    }

    /**
     * Adds an undirected edge with the specified endpoints and data to the graph.
     * 
     * <p>
     * If this edge already exists in the graph, ie if there is any edge in the
     * graph which {@code equals} this edge, does nothing.
     * 
     * @param u    an endpoint of the edge to be created
     * @param v    an endpoint of the edge to be created
     * @param data the data of the edge to be created
     * @return the edge if it doesn't already exist in this graph, otherwise
     *         {@code null}.
     */
    public Edge<V, E> addEdge(Vertex<V, E> u, Vertex<V, E> v, E data) {
        return addEdge(u, v, false, data);
    }

    /**
     * Adds an undirected edge with the specified endpoints and data to the graph.
     * 
     * <p>
     * If this edge already exists in the graph, ie if there is any edge in the
     * graph which {@code equals} this edge, does nothing.
     * 
     * @param i    a positional endpoint of the edge to be created
     * @param j    a positional endpoint of the edge to be created
     * @param data the data of the edge to be created
     * @return the edge if it doesn't already exist in this graph, otherwise
     *         {@code null}.
     */
    public Edge<V, E> addEdge(int i, int j, E data) {
        return addEdge(getVertex(i), getVertex(j), data);
    }

    /**
     * Adds an edge with the specified endpoints and data to the graph. If
     * {@code directed}, the resulting edge will be a directed edge, otherwise it is
     * undirected.
     * 
     * <p>
     * If this edge already exists in the graph, ie if there is any edge in the
     * graph which {@code equals} this edge, does nothing.
     * 
     * @param i        a positional endpoint of the edge to be created
     * @param j        a positional endpoint of the edge to be created
     * @param directed whether the edge to be created will be directed
     * @param data     the data of the edge to be created
     * @return the edge if it doesn't already exist in this graph, otherwise
     *         {@code null}.
     */
    public Edge<V, E> addEdge(int i, int j, boolean directed, E data) {
        return addEdge(getVertex(i), getVertex(j), directed, data);
    }

    /**
     * Adds an edge with the specified endpoints and data to the graph. If
     * {@code directed}, the resulting edge will be a directed edge, otherwise it is
     * undirected.
     * 
     * <p>
     * If this edge already exists in the graph, ie if there is any edge in the
     * graph which {@code equals} this edge, does nothing.
     * 
     * @param u        an endpoint of the edge to be created
     * @param v        an endpoint of the edge to be created
     * @param directed whether the edge to be created will be directed
     * @param data     the data of the edge to be created
     * @return the edge if it doesn't already exist in this graph, otherwise
     *         {@code null}.
     */
    public Edge<V, E> addEdge(Vertex<V, E> u, Vertex<V, E> v, boolean directed, E data) {
        Edge<V, E> newEdge = new Edge<V, E>(u, v, directed, data);
        if (addEdge(newEdge))
            return newEdge;
        return null;
    }

    /**
     * Adds a weighted edge with the specified endpoints, weight and data to the
     * graph. If {@code directed}, the resulting edge will be a directed edge,
     * otherwise it is undirected.
     * 
     * <p>
     * If this edge already exists in the graph, ie if there is any edge in the
     * graph which {@code equals} this edge, does nothing.
     * 
     * @param u      an endpoint of the edge to be created
     * @param v      an endpoint of the edge to be created
     * @param weight the weight of the edge to be created
     * @return the newly created edge, or {@code null} if this edge already exists.
     */
    public WeightedEdge<V, E> addEdge(Vertex<V, E> u, Vertex<V, E> v, double weight) {
        return addEdge(u, v, weight, false, null);
    }

    /**
     * Adds a weighted edge with the specified endpoints and weight to the graph.
     * 
     * <p>
     * If this edge already exists in the graph, ie if there is any edge in the
     * graph which {@code equals} this edge, does nothing.
     * 
     * @param i      the position of an endpoint of the edge to be created
     * @param j      the position of an endpoint of the edge to be created
     * @param weight the weight of the edge to be created
     * @return the newly created edge, or {@code null} if this edge already exists.
     */
    public WeightedEdge<V, E> addEdge(int i, int j, double weight) {
        return addEdge(getVertex(i), getVertex(j), weight);
    }

    /**
     * Adds a weighted edge with the specified endpoints and weight to the graph. If
     * {@code directed}, the resulting edge will be a directed edge, otherwise it is
     * undirected.
     * 
     * <p>
     * If this edge already exists in the graph, ie if there is any edge in the
     * graph which {@code equals} this edge, does nothing.
     * 
     * @param u        an endpoint of the edge to be created
     * @param v        an endpoint of the edge to be created
     * @param weight   the weight of the edge to be created
     * @param directed whether the edge to be created will be directed
     * @return the newly created edge, or {@code null} if this edge already exists.
     */
    public WeightedEdge<V, E> addEdge(Vertex<V, E> u, Vertex<V, E> v, double weight, boolean directed) {
        return addEdge(u, v, weight, directed, null);
    }

    /**
     * Adds a weighted edge with the specified endpoints and weight to the graph. If
     * {@code directed}, the resulting edge will be a directed edge, otherwise it is
     * undirected.
     * 
     * <p>
     * If this edge already exists in the graph, ie if there is any edge in the
     * graph which {@code equals} this edge, does nothing.
     * 
     * @param i        the position of an endpoint of the edge to be created
     * @param j        the position of an endpoint of the edge to be created
     * @param weight   the weight of the edge to be created
     * @param directed whether the edge to be created will be directed
     * @return the newly created edge, or {@code null} if this edge already exists.
     */
    public WeightedEdge<V, E> addEdge(int i, int j, double weight, boolean directed) {
        return addEdge(getVertex(i), getVertex(j), weight, directed);
    }

    /**
     * Adds a weighted edge with the specified endpoints, weight and data to the
     * graph.
     * 
     * <p>
     * If this edge already exists in the graph, ie if there is any edge in the
     * graph which {@code equals} this edge, does nothing.
     * 
     * @param u      an endpoint of the edge to be created
     * @param v      an endpoint of the edge to be created
     * @param weight the weight of the edge to be created
     * @param data   the data of the edge to be created
     * @return the newly created edge, or {@code null} if this edge already exists.
     */
    public WeightedEdge<V, E> addEdge(Vertex<V, E> u, Vertex<V, E> v, double weight, E data) {
        return addEdge(u, v, weight, false, data);
    }

    /**
     * Adds a weighted edge with the specified endpoints, weight and data to the
     * graph.
     * 
     * <p>
     * If this edge already exists in the graph, ie if there is any edge in the
     * graph which {@code equals} this edge, does nothing.
     * 
     * @param i      the position of an endpoint of the edge to be created
     * @param j      the position of an endpoint of the edge to be created
     * @param weight the weight of the edge to be created
     * @param data   the data of the edge to be created
     * @return the newly created edge, or {@code null} if this edge already exists.
     */
    public WeightedEdge<V, E> addEdge(int i, int j, double weight, E data) {
        return addEdge(getVertex(i), getVertex(j), weight, data);
    }

    /**
     * Adds a weighted edge with the specified endpoints, weight and data to the
     * graph. If {@code directed}, the resulting edge will be a directed edge,
     * otherwise it is undirected.
     * 
     * <p>
     * If this edge already exists in the graph, ie if there is any edge in the
     * graph which {@code equals} this edge, does nothing.
     * 
     * @param i        the position of an endpoint of the edge to be created
     * @param j        the position of an endpoint of the edge to be created
     * @param weight   the weight of the edge to be created
     * @param directed whether the edge to be created will be directed
     * @param data     the data of the edge to be created
     * @return the newly created edge, or {@code null} if this edge already exists.
     */
    public WeightedEdge<V, E> addEdge(int i, int j, double weight, boolean directed, E data) {
        return addEdge(getVertex(i), getVertex(j), weight, directed, data);
    }

    /**
     * Adds a weighted edge with the specified endpoints, weight and data to the
     * graph. If {@code directed}, the resulting edge will be a directed edge,
     * otherwise it is undirected.
     * 
     * <p>
     * If this edge already exists in the graph, ie if there is any edge in the
     * graph which {@code equals} this edge, does nothing.
     * 
     * @param u        an endpoint of the edge to be created
     * @param v        an endpoint of the edge to be created
     * @param weight   the weight of the edge to be created
     * @param directed whether the edge to be created will be directed
     * @param data     the data of the edge to be created
     * @return the newly created edge, or {@code null} if this edge already exists.
     */
    public WeightedEdge<V, E> addEdge(Vertex<V, E> u, Vertex<V, E> v, double weight, boolean directed, E data) {
        WeightedEdge<V, E> newEdge = new WeightedEdge<V, E>(u, v, weight, directed, data);
        if (addEdge(newEdge))
            return newEdge;
        return null;
    }

    /**
     * Returns the adjacency matrix of this graph.
     * 
     * <p>
     * Specifically, returns a boolean matrix {@code M} where {@code M[i][j]}
     * denotes if there is an edge from {@code getVertex(i)} to
     * {@code getVertex(j)}.
     * 
     * @return the adjaceny matrix of this graph.
     */
    public boolean[][] adjacencyMatrix() {
        int n = vertices.size();
        boolean[][] matrix = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = getVertex(i).getEdgeTo(getVertex(j)) != null;
            }
        }
        return matrix;
    }

    /**
     * Returns a shallow copy of this graph where all edges have been reversed.
     * 
     * @return a shallow copy of this graph where all edges have been reversed.
     */
    public Graph<V, E> reverse() {
        Graph<V, E> newGraph = new Graph<V, E>();
        HashMap<Vertex<V, E>, Integer> map = new HashMap<Vertex<V, E>, Integer>();
        for (int i = 0; i < vertices.size(); i++) {
            newGraph.addVertex(getVertex(i).data);
            map.put(getVertex(i), i);
        }
        for (int i = 0; i < vertices.size(); i++) {
            for (Edge<V, E> edge : getVertex(i).edgesOut()) {
                if (edge instanceof WeightedEdge)
                    newGraph.addEdge(map.get(edge.other(getVertex(i))), i, ((WeightedEdge<V, E>) edge).weight,
                            edge.isDirected(), edge.data);
                else
                    newGraph.addEdge(map.get(edge.other(getVertex(i))), i, edge.isDirected(), edge.data);

            }
        }
        return newGraph;
    }

    public String toString() {
        return "Vertices: " + vertices + "\nEdges: " + edges;
    }
}