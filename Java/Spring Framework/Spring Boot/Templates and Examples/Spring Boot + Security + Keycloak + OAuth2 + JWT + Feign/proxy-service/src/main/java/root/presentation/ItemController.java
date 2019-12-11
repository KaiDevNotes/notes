package root.presentation;

import java.util.List;

import javax.validation.Valid;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import root.domain.Item;
import root.infrastructure.client.BackendServiceClient;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ItemController
{
    private final BackendServiceClient client;
    
    @GetMapping("/items")
    @PreAuthorize("hasAnyAuthority('FRONTEND_USER')")
    public List<Item> getAll()
    {
        return client.getAllItems();
    }

    @GetMapping("/items/{id}")
    @PreAuthorize("hasAnyAuthority('FRONTEND_USER')")
    public Item getById(@PathVariable Integer id)
    {
        return client.getById(id);
    }

    @PostMapping("/items")
    @PreAuthorize("hasAnyAuthority('FRONTEND_USER')")
    public Item create(@RequestBody @Valid Item item)
    {
        return client.createItem(item);
    }
}
