package root;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ApplicationController
{
    private static final String UNKNOWN_IP = "unknown";
    
    @RequestMapping(value = "/ip", method = RequestMethod.GET)
    @ResponseBody
    public IpInfo getIpInfo()
    {
        return new IpInfo(getIp());
    }

    private String getIp()
    {
        try
        {
            return InetAddress.getLocalHost().getHostAddress();
        }
        catch (final UnknownHostException e)
        {
            return UNKNOWN_IP;
        }
    }
}

class IpInfo
{
    private final String ip;

    public IpInfo(final String ip)
    {
        this.ip = ip;
    }

    public String getIp()
    {
        return ip;
    }
}
