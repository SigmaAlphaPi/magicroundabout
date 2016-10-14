package bachelor.project.vehicle;

import bachelor.project.graph.network.IEdge;
import bachelor.project.graph.network.IGraph;
import bachelor.project.graph.network.INode;
import bachelor.project.ui.Map;

import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.Set;


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
    /*
     * QUELLE!
     *
     * Startpunkt bekommt sie vorgegeben --> @param p_startNode
     * // TODO Zielpunkt zufällig ziehen
     * Route berechnen
     *
     * Wann soll sie Auto erzeugen? Zufall
     * Wieviele Autos
     */
    private final INode m_startNode;
    private final int m_maxAttemptsOfGeneration;
    private final int m_probabilityOfGeneration;
    private final IGraph m_graph;

    public VehicleSource(final INode p_startNode, int p_maxAttemptsOfGeneration, int p_probabilityOfGeneration, final IGraph p_graph) {
        m_startNode = p_startNode;
        m_maxAttemptsOfGeneration = p_maxAttemptsOfGeneration;
        m_probabilityOfGeneration = p_probabilityOfGeneration;
        m_graph = p_graph;
    }

    public void generateVehicles() {
        /*
         * could be modified to create different types of vehicles (if Classes are present)
         * vehicles.add(new Car());
         * vehicles.add(new Lorry());
         * vehicles.add(new Bus());
         * vehicles.add(new Motorbike());
         */
        int randomInt;
        Random randomGeneration = new Random(System.currentTimeMillis());

//        Collection endNodes = m_graph.getEndNodes();
//        System.out.println(endNodes);
        System.out.println( m_graph );

//        Collection endNodes = m_graph.getEndNodes();

        for (int i = 0; i < m_maxAttemptsOfGeneration; i++) {
            randomInt = randomGeneration.nextInt(100);
            if (randomInt < m_probabilityOfGeneration) {

                // TODO  declare parameters of Car (Car class) --- going with route, color for the moment
//                List randomRoute = generateRandomRoute(m_startNode , endNodes, randomGeneration);
//                vehicles.add(new Car());
//                vehicles.add(new CVehicle());
                System.out.println(randomInt);
            }
        }


    }

    protected List<IEdge> generateRandomRoute(INode p_startNode, Collection p_endNodes, Random p_random) {
//        m_startNode = p_startNode;
        Collection m_endnodes = p_endNodes;
//        int roadStart = m_startNode;
//        int roadEnd = p_random.nextInt(5) + 1; // chooses int between 0 and 4 (including), labels are 1 to 5

        /*
         * chance of start point being the same as the end point was roughly at 20%
         * reassigning a new end point in case of equality drops the rate to around 4%
         */
//        if (roadStart == roadEnd) {
//            roadEnd = p_random.nextInt(5) + 1;
//        }

        return null;
    }


}