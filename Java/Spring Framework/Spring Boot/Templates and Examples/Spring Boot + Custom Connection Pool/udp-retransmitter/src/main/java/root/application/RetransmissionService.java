package root.application;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import org.apache.commons.pool2.impl.GenericObjectPool;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import root.configuration.DestinationProperties;

@RequiredArgsConstructor
public class RetransmissionService
{
    private final GenericObjectPool<DatagramSocket> udpSocketPool;
    private final DestinationProperties properties;

    @SneakyThrows
    public void retransmit(String message)
    {
        DatagramSocket socket = udpSocketPool.borrowObject();
        try
        {
            byte[] content = message.getBytes();
            InetAddress host = InetAddress.getByName(properties.getHost());
            DatagramPacket udpPacket = new DatagramPacket(content, content.length, host, properties.getPort());
            socket.send(udpPacket);
        }
        finally
        {
            udpSocketPool.returnObject(socket);
        }
    }
}
