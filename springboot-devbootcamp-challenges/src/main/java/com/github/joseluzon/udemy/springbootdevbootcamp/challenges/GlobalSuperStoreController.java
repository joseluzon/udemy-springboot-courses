package com.github.joseluzon.udemy.springbootdevbootcamp.challenges;

import java.util.UUID;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.github.joseluzon.udemy.springbootdevbootcamp.challenges.service.GlobalSuperStoreService;

@Controller
@RequestMapping("/global-superstore")
public class GlobalSuperStoreController {

    private GlobalSuperStoreService globalSuperStoreService = new GlobalSuperStoreService();

    @GetMapping("/form")
    public String getForm(final Model model, final @RequestParam(required = false) UUID uuid) {
        model.addAttribute("item", globalSuperStoreService.getItem(uuid));
        return "form";
    }

    @PostMapping("/submitForm")
    public String submitForm(@Valid final Item item, final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        final var ok = validateBinding(item, bindingResult);
        if (!ok) {
            return "form";
        }
        var status = globalSuperStoreService.submitItem(item);
        redirectAttributes.addFlashAttribute("status", status.getDisplayText());
        return "redirect:/global-superstore/inventory";
    }

    private boolean validateBinding(final Item item, final BindingResult bindingResult) {
        if (item.getPrice() < item.getDiscount()) {
            bindingResult.rejectValue("price", "", "The price cannot be less than discount");
        }
        return !bindingResult.hasErrors();
    }

    @GetMapping("/inventory")
    public String getInventory(final Model model) {
        model.addAttribute("items", globalSuperStoreService.getItems());
        return "inventory";
    }
}
 