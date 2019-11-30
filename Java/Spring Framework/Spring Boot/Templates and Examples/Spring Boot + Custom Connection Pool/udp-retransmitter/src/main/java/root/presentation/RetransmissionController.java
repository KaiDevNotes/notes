package root.presentation;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import root.application.RetransmissionService;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class RetransmissionController
{
    private final RetransmissionService retransmissionService;

    @PostMapping("/retransmit")
    public void retransmit(@RequestParam String message)
    {
        retransmissionService.retransmit(message);
    }
}
