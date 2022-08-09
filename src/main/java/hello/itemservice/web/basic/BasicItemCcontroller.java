package hello.itemservice.web.basic;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor// 자동 생성자
public class BasicItemCcontroller {
    private final ItemRepository itemRepository;

    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "basic/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/item";
    }

    //@PostMapping("/add")
    public String addItemV1(@RequestParam String itemName, @RequestParam int price, @RequestParam int quantity,Model model) {
        Item item = new Item();
        item.setItemName(itemName);
        item.setPrice(price);
        item.setQuantity(quantity);
        itemRepository.save(item);
        model.addAttribute("item", item);
        return "/basic/item";
    }

    //@PostMapping("/add")
    public String addItemV2(@ModelAttribute("item")Item item,Model model) {
        itemRepository.save(item);
        model.addAttribute("item", item);
        /**
         * @ModelAttribute는 모델을 가져다 주고 자동 추가기능까지도 한다.
         */
        return "/basic/item";
    }

    //@PostMapping("/add")
    public String addItemV3(@ModelAttribute("item")Item item) {
        itemRepository.save(item);
        return "/basic/item";
    }

    //@PostMapping("/add")
    public String addItemV4(Item item) {
        itemRepository.save(item);
        return "/basic/item";
    }

    //@PostMapping("/add")
    public String addItemV5(Item item) {
        itemRepository.save(item);
        return "redirect:/basic/items/"+ item.getId();
    }

    @PostMapping("/add")
    public String addItemV6(Item item, RedirectAttributes redirectAttributes) {
        Item saveItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", saveItem.getId());//리다이렉트 ${}로 해당 변수 치환
        redirectAttributes.addAttribute("status", true);//남는 attribute는 쿼리 파라미터로 전송
        return "redirect:/basic/items/{itemdId}";
    }
    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId,@ModelAttribute Item item) {
        itemRepository.update(itemId,item);
        return "redirect:/basic/items/{itemId}";
    }
    @GetMapping("/add")
    public String addForm() {
        return "/basic/addForm";
    }
    /**
     * 테스트용 데이터 추가
     */

    @PostConstruct
    public void init() {
        itemRepository.save(new Item("itemA", 10000, 10));
        itemRepository.save(new Item("itemB", 20000, 10));
    }

}
