package com.pawcart.web;

import com.pawcart.model.*;
import com.pawcart.repository.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {
    private final ItemRepository items;
    private final BrandRepository brands;
    private final CategoryRepository categories;

    private void options(Model m) {
        m.addAttribute("brands", brands.findAll());
        m.addAttribute("categories", categories.findAll());
        m.addAttribute("petTypes", PetType.values());
    }

    @GetMapping("/new")
    String form(Model m) {
        m.addAttribute("itemForm", new ItemForm());
        options(m);
        return "items/form";
    }

    @PostMapping
    String save(@Valid @ModelAttribute("itemForm") ItemForm f, BindingResult br, Model m) {
        Brand brand = f.getBrandId() == null ? null : brands.findById(f.getBrandId()).orElse(null);
        Category cat = f.getCategoryId() == null ? null : categories.findById(f.getCategoryId()).orElse(null);
        if (f.getBrandId() != null && brand == null) br.rejectValue("brandId", "invalid", "Unknown brand");
        if (f.getCategoryId() != null && cat == null) br.rejectValue("categoryId", "invalid", "Unknown category");
        if (br.hasErrors()) {
            options(m);
            return "items/form";
        }
        Item i = new Item();
        i.setName(f.getName());
        i.setDescription(f.getDescription());
        i.setSku(f.getSku());
        i.setPrice(f.getPrice());
        i.setPetType(f.getPetType());
        i.setImageUrl(f.getImageUrl());
        i.setBrand(brand);
        i.setCategory(cat);
        return "redirect:/items/" + items.save(i).getId();
    }

    @GetMapping("/{id}")
    String detail(@PathVariable Long id, Model m) {
        m.addAttribute("item", items.findById(id).orElseThrow());
        return "items/detail";
    }
}
