package com.pawcart.controller;

import com.pawcart.model.PetType;
import com.pawcart.repository.BrandRepository;
import com.pawcart.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/** Home page plus the two info pages (About, Brands). */
@Controller
@RequiredArgsConstructor
public class PageController {

    private final ItemRepository items;
    private final BrandRepository brands;

    @GetMapping("/")
    String home(Model model) {
        model.addAttribute("petTypes", PetType.values());
        model.addAttribute("featured",
                items.findAll(PageRequest.of(0, 4, Sort.by(Sort.Direction.DESC, "createdAt"))).getContent());
        return "home";
    }

    @GetMapping("/about")
    String about() {
        return "about";
    }

    @GetMapping("/brands")
    String brandsPage(Model model) {
        model.addAttribute("brands", brands.findAll(Sort.by("name")));
        return "brands";
    }
}
