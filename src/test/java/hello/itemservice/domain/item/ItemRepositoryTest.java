package hello.itemservice.domain.item;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ItemRepositoryTest {
    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach() {
        itemRepository.clearStore();
    }

    @Test
    void save() {
        //given
        Item item = new Item("itemA", 1000, 100);
        //when
        Item saveItem = itemRepository.save(item);
        //then
        Item findItem = itemRepository.findById(saveItem.getId());
        Assertions.assertThat(findItem).isEqualTo(saveItem);
    }
    @Test
    void find() {
        //given
        Item item1 = new Item("item1", 1000, 100);
        Item item2 = new Item("item2", 2000, 00);

        itemRepository.save(item1);
        itemRepository.save(item2);
        //when
        List<Item> result = itemRepository.findAll();
        //then
        Assertions.assertThat(result.size()).isEqualTo(2);
        Assertions.assertThat(result).contains(item1, item2);
    }@Test
    void update() {
        //given
        Item item = new Item("item", 1000, 100);
        Item savedItem = itemRepository.save(item);
        Long itemId = savedItem.getId();
        //when
        Item updateItem = new Item("updateItem", 2000, 200);
        itemRepository.update(itemId,updateItem);
        //then
        Item findItem = itemRepository.findById(itemId);
        Assertions.assertThat(findItem.getItemName()).isEqualTo(updateItem.getItemName());
        Assertions.assertThat(findItem.getGuantity()).isEqualTo(updateItem.getGuantity());
        Assertions.assertThat(findItem.getPrice()).isEqualTo(updateItem.getPrice());

    }
}