package bachelor.project.graph.network;

import java.text.MessageFormat;

/**
 * define a graph node
 * @tparam T any typo of the node identifier to get a more flexible structure
 */
public final class CNode<T> implements INode<T> {
    /**
     * unique node identifier
     */
    private final T m_id;
    /**
     * x-position within the spheroid earth (longitude)
     */
    private final double m_xposition;
    /**
     * y-position within the spheroid earth (latitude)
     */
    private final double m_yposition;
    /**
     * status of node blocked by a routing vehicle
     */
    protected boolean m_isBlocked;


    /**
     * ctor
     *
     * @param p_id unique identifier
     */
    public CNode(final T p_id)
    {
        // call other constructor
        this( p_id, 0, 0 );
    }

    /**
     * ctor
     *
     * @param p_id unique identifier
     * @param m_xposition x-position
     * @param m_yposition y-position
     */
    public CNode( final T p_id, final double m_xposition, final double m_yposition )
    {
        if (p_id == null)
            throw new IllegalArgumentException( "node ID need not to be null" );
        m_id = p_id;
        this.m_xposition = m_xposition;
        this.m_yposition = m_yposition;
    }


    @Override
    public final T id()
    {
        return m_id;
    }

    @Override
    public final double xposition() { return m_xposition; }

    @Override
    public final double yposition()
    {
        return m_yposition;
    }

    /**
     * overload the default hashcode method for
     * checking if nodes are equal, so we use the hashcode
     * of the identifier for returning, because if the hashcode
     * of the identifier is equal, the nodes are also equal
     *
     * @return hashcode value based on the identifier
     */
    @Override
    public final int hashCode()
    {
        return m_id.hashCode();
    }

    /**
     * overload equals for checking of equal nodes, nodes
     * are equal if the use the equal identifier
     *
     * @param p_object any object
     * @return boolean if objects are equal
     */
    @Override
    public final boolean equals( final Object p_object )
    {
        return ( p_object != null ) && ( p_object instanceof INode<?> ) && ( this.hashCode() == p_object.hashCode() );
    }

    /**
     * block nodes on route to prevent collisions in nodes
     * when routes cross
     * @param p_signal
     */
    public void blockNode(boolean p_signal) { m_isBlocked = p_signal; }
    public boolean isBlocked() { return m_isBlocked; }


    /**
     * overload toString, for getting a human-readable
     * output on a System.println call
     * @return human-readable string
     */
    @Override
    public final String toString()
    {
        return MessageFormat.format( "(id: {0}, x: {1}, y:{2})", m_id, m_xposition, m_yposition );
    }
}
