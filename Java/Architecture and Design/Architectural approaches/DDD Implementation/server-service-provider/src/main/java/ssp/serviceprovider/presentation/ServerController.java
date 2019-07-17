package ssp.serviceprovider.presentation;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import ssp.serviceprovider.domain.server.Server;
import ssp.serviceprovider.domain.server.ServerRepository;

@RestController
@RequestMapping("/api/v1/servers")
@RequiredArgsConstructor
public class ServerController
{
    private final ServerRepository serverRepository;
    
    @GetMapping
    public List<Server> getAll()
    {
        return serverRepository.findAll();
    }
}
