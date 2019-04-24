package root;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Controller
public class ApplicationController
{
    @Value("${ipprovider.service.url}")
    private String ipProviderServiceURL;
    
    @Value("${greeting.format}")
    private String greetingFormat;
    
    private final RestTemplate restTemplate = new RestTemplate();
    
    @RequestMapping(value = "/greeting", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<String> getGreeting()
    {
        return ResponseEntity.ok(formGreeting());
    }
    
    private String formGreeting()
    {
        String ip = getBackendIp();
        return String.format(greetingFormat, ip);
    }
    
    private String getBackendIp()
    {
        IpInfoDTO ipInfo = restTemplate.getForObject(ipProviderServiceURL, IpInfoDTO.class);
        return ipInfo.getIp();
    }
}

class IpInfoDTO
{
    private String ip;

    public String getIp()
    {
        return ip;
    }

    public void setIp(String ip)
    {
        this.ip = ip;
    }
}
