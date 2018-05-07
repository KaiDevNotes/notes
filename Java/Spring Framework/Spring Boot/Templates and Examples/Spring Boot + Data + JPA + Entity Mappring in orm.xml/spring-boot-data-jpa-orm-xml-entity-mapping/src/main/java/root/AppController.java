package root;

import javax.servlet.ServletException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AppController 
{
    @Autowired
    private VpnChannelRepository vpnChannelRepository;
    
    @RequestMapping(value="/", method=RequestMethod.GET)
    public String showVpnChannelInfo(Model model)
        throws ServletException
    {
        VpnChannel channel = vpnChannelRepository.findOne("2192fc129ff34c079a1b8763721d140fs");
        model.addAttribute("channel", channel);
        return "channel-info";
    }    
}
