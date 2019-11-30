package root.infrastructure;

import java.net.DatagramSocket;
import java.net.SocketException;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

public class UdpSocketFactory extends BasePooledObjectFactory<DatagramSocket>
{
    @Override
    public DatagramSocket create()
    {
        try
        {
            return new DatagramSocket();
        }
        catch (SocketException e)
        {
            throw new RuntimeException("Unable to setup UDP socket.", e);
        }
    }

    @Override
    public PooledObject<DatagramSocket> wrap(DatagramSocket socket)
    {
        return new DefaultPooledObject<DatagramSocket>(socket);
    }

    @Override
    public void destroyObject(PooledObject<DatagramSocket> pooledSocket) throws Exception
    {
        pooledSocket.getObject().close();
    }

    @Override
    public boolean validateObject(PooledObject<DatagramSocket> pooledSocket)
    {
        return !(pooledSocket.getObject().isClosed());
    }
}
