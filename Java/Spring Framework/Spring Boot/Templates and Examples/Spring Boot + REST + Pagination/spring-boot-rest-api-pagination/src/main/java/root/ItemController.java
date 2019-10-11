package root;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import root.paging.PageBuilder;
import root.paging.PageRequest;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ItemController
{
    private final ItemRepository itemRepository;

    @GetMapping("/items")
    public Page<Item> getItems(PageRequest pageable)
    {
        int itemsCount = 10;
        List<Item> items = new ArrayList<>();
        IntStream.range(1, itemsCount).forEach(i -> {
            items.add(Item.builder()
                    .id(i)
                    .name("item-" + i)
                    .description("description-" + (itemsCount - i))
                    .build());
        });        
        return PageBuilder.build(items, pageable);
    }

    @GetMapping("/items-from-repository")
    public Page<Item> getItemsFromRepository(PageRequest pageable)
    {
        return itemRepository.findAll(pageable);
    }
}
