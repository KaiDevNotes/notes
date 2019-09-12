package ssp.serviceprovider.presentation;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ssp.serviceprovider.application.ServiceProviderService;
import ssp.serviceprovider.application.command.DeleteServiceUser;
import ssp.serviceprovider.application.command.MoveServiceUser;
import ssp.serviceprovider.domain.serviceuser.ServiceUser;
import ssp.serviceprovider.domain.serviceuser.ServiceUserRepository;
import ssp.serviceprovider.presentation.dto.MoveToAnotherServerRequest;

@RestController
@RequestMapping("/api/v1/serviceusers")
@Slf4j
@RequiredArgsConstructor
public class ServiceUserController
{
    private final ServiceProviderService serviceProviderService;
    private final ServiceUserRepository serviceUserRepository;
    
    @GetMapping
    public List<ServiceUser> getAll()
    {
        return serviceUserRepository.findAll();
    }
    
    @PutMapping("/{serviceUserId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void moveUserToAnotherServer(@PathVariable String serviceUserId,
                                        @Valid @RequestBody MoveToAnotherServerRequest request)
    {
        log.info("[ServiceProvider Core Domain]: API endpoint [PUT /api/v1/serviceusers/{}] was called", serviceUserId);
        
        MoveServiceUser command = MoveServiceUser.builder()
                                                 .serviceUserId(serviceUserId)
                                                 .currentServerId(request.getCurrentServerId())
                                                 .targetServerId(request.getTargetServerId())
                                                 .build();
        
        serviceProviderService.moveUserToAnotherServer(command);
    }
    
    @DeleteMapping("/{serviceUserId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteServiceUser(@PathVariable String serviceUserId)
    {
        log.info("[ServiceProvider Core Domain]: API endpoint [DELETE /api/v1/serviceusers/{}] was called", serviceUserId);
        
        DeleteServiceUser command = new DeleteServiceUser(serviceUserId);
        serviceProviderService.deleteUser(command);
    }
}
