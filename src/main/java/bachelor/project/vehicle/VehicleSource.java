package bachelor.project.vehicle;

import bachelor.project.graph.network.IEdge;
import bachelor.project.graph.network.IGraph;
import bachelor.project.graph.network.INode;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;


// TODO  for main program, 5 instances of VehicleSource needed with one starting node each
/*
 *  VehicleSource source_X = new VehicleSource(X0, int maxAttempts, int probability, IGraph graphObject);
 *
 */

/*
 * nodes' numbers vs. streets' names
 * roundabout is defined in YAML file as follows
 * 1X --> Drove Road (= 6 o'clock entry/exit)           ??, medium traffic (45%)
 * 2X --> Fleming Way (= 8 o'clock entry/exit)          from town centre, medium to higher traffic (55%)
 * 3x --> County Road (= 10 o'clock entry/exit)         from bus + train stations, medium to higher traffic (55%)
 * 4x --> Shrivenham Road (= 1 o'clock entry/exit)      residential area, low traffic (30%)
 * 5x --> Queens Drive (= 4 o'clock entry/exit)         from motorway, high traffic (65%)
 */

//public class VehicleSource implements IVehicleFactory {
public class VehicleSource extends CVehicleFactory {
    /**
     * QUELLE!
     *
     * Startpunkt bekommt sie vorgegeben --> @param p_startNode
     */
    private final INode<Integer> m_startNode;
    private final int m_maxAttemptsOfGeneration;
    private final int m_probabilityOfGeneration;
    private final IGraph m_graph;

    public VehicleSource(final INode<Integer> p_startNode, int p_maxAttemptsOfGeneration, int p_probabilityOfGeneration, final IGraph p_graph) {
        m_startNode = p_startNode;
        m_maxAttemptsOfGeneration = p_maxAttemptsOfGeneration;
        m_probabilityOfGeneration = p_probabilityOfGeneration;
        m_graph = p_graph;
    }

//    public void generateVehicles() {
    //TODO redo constructor to "produce" vehicles again, missusing it to generate a list for painting route
    public List generateVehicles() {
        /*
         * could be modified to create different types of vehicles (if Classes are present)
         * vehicles.add(new Car());
         * vehicles.add(new Lorry());
         * vehicles.add(new Bus());
         * vehicles.add(new Motorbike());
         */
        int randomInt;
        Random myRandomizer = new Random(System.currentTimeMillis());
        List<INode> endNodesList = m_graph.getEndNodesList();

        List randomRouteCells = null;
        LinkedList randomRouteLanes = null;
//        for (int i = 0; i < m_maxAttemptsOfGeneration; i++) {
//            randomInt = myRandomizer.nextInt(100);
//            if (randomInt < m_probabilityOfGeneration) {
                List<IEdge> randomRoute = generateRandomRoute(endNodesList, myRandomizer);
                randomRouteCells = convertedRouteToCells( randomRoute );
                    System.out.println( randomRouteCells );     // to be removed
                    System.out.println( randomRouteCells.size() );     // to be removed
                randomRouteLanes = convertedRouteToLanes( randomRoute );
                    System.out.println( randomRouteLanes );     // to be removed
                    System.out.println( randomRouteLanes.size() );     // to be removed

                    System.out.println();
                    System.out.println( "half is " + Math.floorDiv( randomRouteCells.size(), 2 ) );
                    System.out.println( randomRouteCells.get( Math.floorDiv( randomRouteCells.size(), 2 ) ) + " - " + randomRouteLanes.get( Math.floorDiv( randomRouteCells.size(), 2 ) )  );

//                System.out.println("Versuch " + i + "/" + m_maxAttemptsOfGeneration + ":\t" + randomRoutesCells);

                // TODO  declare parameters of Car (Car class) --- going with route, color for the moment
//                vehicles.add(new Car(10,randomRouteCells,randomRouteLanes,"yellow"));

//            }
//        }
        return randomRouteCells;
    }

    /**
     * converts a given path of graph's edges to a route with lane information
     *
     * @param p_randomRoute
     * @return
     */
    private LinkedList<ImmutablePair<Integer,Integer>> convertedRouteToLanes (List<IEdge> p_randomRoute) {
        LinkedList<ImmutablePair<Integer,Integer>> ll_routeLaneList = new LinkedList<>();
        for ( IEdge edge : p_randomRoute ) {
            ll_routeLaneList.addAll( edge.getLaneInfo() );
        }
        return ll_routeLaneList;
    }

    /**
     * converts a given path of graph's edges to a route of coordiate tupels
     *
     * @param p_randomRoute
     * @return
     */
    private List convertedRouteToCells(List<IEdge> p_randomRoute) {
        List routeCellList = new ArrayList();
        for ( IEdge edge : p_randomRoute ) {
            routeCellList.addAll( edge.getCells() );
        }
        return routeCellList;
    }

    /**
     * Returns a path (list of edges) between start node and a random end node
     *
     * @param p_endNodes
     * @param p_random
     * @return
     */
    private List<bachelor.project.graph.network.IEdge> generateRandomRoute(List<INode> p_endNodes, Random p_random) {
        return m_graph.route( m_startNode, p_endNodes.get( p_random.nextInt( p_endNodes.size() ) ) );
    }

}
