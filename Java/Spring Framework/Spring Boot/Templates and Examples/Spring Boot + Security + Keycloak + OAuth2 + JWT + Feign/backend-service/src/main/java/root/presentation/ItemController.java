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
import root.application.ItemService;
import root.domain.Item;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ItemController
{
    private final ItemService itemService;

    @GetMapping("/items")
    @PreAuthorize("hasAuthority('FRONTEND_USER') or hasAuthority('BACKEND_USER')")
    public List<Item> getAll()
    {
        return itemService.getAll();
    }

    @GetMapping("/items/{id}")
    @PreAuthorize("hasAuthority('FRONTEND_USER') or hasAuthority('BACKEND_USER')")
    public Item getById(@PathVariable Integer id)
    {
        return itemService.getById(id);
    }

    @PostMapping("/items")
    @PreAuthorize("hasAuthority('FRONTEND_USER') or hasAuthority('BACKEND_USER')")
    public Item create(@RequestBody @Valid Item item)
    {
        return itemService.create(item);
    }
}
