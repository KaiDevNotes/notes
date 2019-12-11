package root.application;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import root.domain.Item;
import root.domain.ItemRepository;

@Service
@RequiredArgsConstructor
public class ItemService
{
    private final ItemRepository itemRepository;

    public List<Item> getAll()
    {
        return itemRepository.findAll();
    }

    public Item getById(Integer id)
    {
        return itemRepository.findById(id).orElseThrow();
    }

    public Item create(Item item)
    {
        return itemRepository.save(item);
    }
}
