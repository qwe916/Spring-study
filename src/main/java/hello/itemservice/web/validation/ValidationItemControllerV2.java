package hello.itemservice.web.validation;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Slf4j
@Controller
@RequestMapping("/validation/v2/items")
@RequiredArgsConstructor
public class ValidationItemControllerV2 {

    private final ItemRepository itemRepository;
    private final ItemValidator itemValidator;
    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "validation/v2/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "validation/v2/item";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("item", new Item());
        return "validation/v2/addForm";
    }

    //@PostMapping("/add")
    public String addV1Item(@ModelAttribute Item item, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {

        // BindingResult로 error 전달
        if (!StringUtils.hasText(item.getItemName())) {//글자가 없으면
            bindingResult.addError(new FieldError("item", "itemName", "상품이름은 필수입니다."));
        }
        if (item.getPrice() == null || item.getPrice() < 1000 || item.getPrice() > 1000000) {
            bindingResult.addError(new FieldError("item", "price", "가격은 1,000~1,000,0000 까지 허용합니다."));
        }
        if (item.getQuantity() == null || item.getQuantity() >= 9999) {
            bindingResult.addError(new FieldError("item", "quantity", "수량은 최대 9,999까지 가능합니다."));
        }

        if (item.getPrice() != null && item.getQuantity() != null) {
            int resultPrice = item.getPrice() * item.getQuantity();
            if (resultPrice < 10000) {
                //객체 필드가 아닌 경우 new ObjectError 생성
                bindingResult.addError(new ObjectError("item","가격 x 수량의 합은 10,000원 이상이여햐 합니다. 현재 값 "+ resultPrice));
            }
        }

        if (bindingResult.hasErrors()) {//오류가 존재하면
            log.info("bindingResult= {}",bindingResult);
            return "validation/v2/addForm";
        }

        //검증 성공 로직
        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/validation/v2/items/{itemId}";
    }

    //오류 발생시 입력한 값을 유지시키는 BindingResult
    //@PostMapping("/add")
    public String addV2Item(@ModelAttribute Item item, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {

        // BindingResult로 error 전달
        if (!StringUtils.hasText(item.getItemName())) {//글자가 없으면
            bindingResult.addError(new FieldError("item", "itemName", item.getItemName(), false, null, null, "상품이름은 필수입니다."));
        }
        if (item.getPrice() == null || item.getPrice() < 1000 || item.getPrice() > 1000000) {
            bindingResult.addError(new FieldError("item", "price",item.getPrice(), false, null, null, "가격은 1,000~1,000,0000 까지 허용합니다."));
        }
        if (item.getQuantity() == null || item.getQuantity() >= 9999) {
            bindingResult.addError(new FieldError("item", "quantity",item.getQuantity(), false, null, null, "수량은 최대 9,999까지 가능합니다."));
        }

        if (item.getPrice() != null && item.getQuantity() != null) {
            int resultPrice = item.getPrice() * item.getQuantity();
            if (resultPrice < 10000) {
                //객체 필드가 아닌 경우 new ObjectError 생성, Object 에러는 넘어오는 값이 없기 때문에 입력된 값을 유지할 것이 없다.
                bindingResult.addError(new ObjectError("item", null, null, "가격 x 수량의 합은 10,000원 이상이여햐 합니다. 현재 값 " + resultPrice));
            }
        }

        if (bindingResult.hasErrors()) {//오류가 존재하면
            log.info("bindingResult= {}",bindingResult);
            return "validation/v2/addForm";
        }

        //검증 성공 로직
        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/validation/v2/items/{itemId}";
    }

    //@PostMapping("/add")
    public String addV3Item(@ModelAttribute Item item, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {

        //타켓을 이미 알고있는 bindingResult
        log.info("objectName={}", bindingResult.getObjectName());
        log.info("target={}", bindingResult.getTarget());

        // BindingResult로 error 전달
        if (!StringUtils.hasText(item.getItemName())) {//글자가 없으면
            bindingResult.addError(new FieldError("item", "itemName", item.getItemName(), false, new String[]{"required.item.itemName"}, null, null));
        }
        if (item.getPrice() == null || item.getPrice() < 1000 || item.getPrice() > 1000000) {
            bindingResult.addError(new FieldError("item", "price",item.getPrice(), false, new String[]{"range.item.price"}, new Object[]{1000,10000000}, "가격은 1,000~1,000,0000 까지 허용합니다."));
        }
        if (item.getQuantity() == null || item.getQuantity() >= 9999) {
            bindingResult.addError(new FieldError("item", "quantity",item.getQuantity(), false,  new String[]{"max.item.quantity"}, null, "수량은 최대 9,999까지 가능합니다."));
        }

        if (item.getPrice() != null && item.getQuantity() != null) {
            int resultPrice = item.getPrice() * item.getQuantity();
            if (resultPrice < 10000) {
                //객체 필드가 아닌 경우 new ObjectError 생성, Object 에러는 넘어오는 값이 없기 때문에 입력된 값을 유지할 것이 없다.
                bindingResult.addError(new ObjectError("item", new String[]{"totalPriceMin"}, new Object[]{10000,resultPrice}, "가격 x 수량의 합은 10,000원 이상이여햐 합니다. 현재 값 " + resultPrice));
            }
        }

        if (bindingResult.hasErrors()) {//오류가 존재하면
            log.info("bindingResult= {}",bindingResult);
            return "validation/v2/addForm";
        }

        //검증 성공 로직
        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/validation/v2/items/{itemId}";
    }

    //@PostMapping("/add")
    public String addV4Item(@ModelAttribute Item item, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {

        //타켓을 이미 알고있는 bindingResult
        log.info("objectName={}", bindingResult.getObjectName());
        log.info("target={}", bindingResult.getTarget());

        // BindingResult.rejectValue 로 error 전달
        //rejectValue(field 명, errorCode,에러 인자, 디폴트 인자)
        /**
         * errorCode에는 우선순위가 존재한다.
         * ex) required와  required.item.itemName 중 더 자세히 표현되어 있는게 우선순위가 높다.
         * 먼제 맨앞 메세지를 찾고 그 메시지 중 디테일한게 있으면 그에 맞는 디테일한 메세지를 보내고 없다면 후 순위 메시지를 보낸다.
        */

        /**
         * ValidationUtils.rejectIfEmptyOrWhitespace(bindingResult,"itemName","required");
         * ValidationUtils.rejectIfEmpty()를 사용하면 if문을 사용하지 않아도 된다.
         */

         if (!StringUtils.hasText(item.getItemName())) {//글자가 없으면
            bindingResult.rejectValue("itemName","required");
         }
        if (item.getPrice() == null || item.getPrice() < 1000 || item.getPrice() > 1000000) {
            bindingResult.rejectValue("price", "range",new Object[]{1000,1000000},null);
        }
        if (item.getQuantity() == null || item.getQuantity() >= 9999) {
            bindingResult.rejectValue("quantity", "max",new Object[]{9999},null);
        }

        //BindingResult.reject로 error 전달
        //reject()는 field 명은 없음
        if (item.getPrice() != null && item.getQuantity() != null) {
            int resultPrice = item.getPrice() * item.getQuantity();
            if (resultPrice < 10000) {
                bindingResult.reject("totalPriceMin", new Object[]{1000, resultPrice}, null);
            }
        }

        if (bindingResult.hasErrors()) {//오류가 존재하면
            log.info("bindingResult= {}",bindingResult);
            return "validation/v2/addForm";
        }

        //검증 성공 로직
        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/validation/v2/items/{itemId}";
    }
    @PostMapping("/add")
    public String addV5Item(@ModelAttribute Item item, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
        //검증
        itemValidator.validate(item, bindingResult);

        if (bindingResult.hasErrors()) {//오류가 존재하면
            log.info("bindingResult= {}",bindingResult);
            return "validation/v2/addForm";
        }

        //검증 성공 로직
        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/validation/v2/items/{itemId}";
    }
    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "validation/v2/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @ModelAttribute Item item) {
        itemRepository.update(itemId, item);
        return "redirect:/validation/v2/items/{itemId}";
    }

}

