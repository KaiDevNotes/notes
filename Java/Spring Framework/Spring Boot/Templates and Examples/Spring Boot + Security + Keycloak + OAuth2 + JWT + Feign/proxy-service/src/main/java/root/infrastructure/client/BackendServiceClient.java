package root.infrastructure.client;

import java.util.List;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import root.domain.Item;

@Headers({
    "Accept: application/json",
    "Content-Type: application/json"
})
public interface BackendServiceClient
{
    @RequestLine("GET /api/v1/items")
    List<Item> getAllItems();

    @RequestLine("GET /api/v1/items/{id}")
    Item getById(@Param("id") Integer id);

    @RequestLine("POST /api/v1/items")
    Item createItem(Item item);
}
