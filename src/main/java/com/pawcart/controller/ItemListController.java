package com.pawcart.controller;

import com.pawcart.model.Item;
import com.pawcart.model.PetType;
import com.pawcart.repository.BrandRepository;
import com.pawcart.repository.ItemRepository;
import com.pawcart.repository.ItemSpecs;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;
import java.util.Set;

/**
 * Product list: filter by pet type and brand, sort by several fields, paginate.
 * Kept separate from Gianni's ItemController (form/save/detail) to avoid merge conflicts.
 */
@Controller
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemListController {

    // Sort key from the URL -> entity property. Anything else falls back to "name".
    private static final Map<String, String> SORT_FIELDS = Map.of(
            "name", "name",
            "price", "price",
            "createdAt", "createdAt",
            "brand", "brand.name");

    private static final Set<Integer> PAGE_SIZES = Set.of(4, 8, 12);

    private final ItemRepository items;
    private final BrandRepository brands;

    @GetMapping
    String list(@RequestParam(required = false) PetType petType,
                @RequestParam(required = false) Long brandId,
                @RequestParam(defaultValue = "name") String sort,
                @RequestParam(defaultValue = "asc") String dir,
                @RequestParam(defaultValue = "0") int page,
                @RequestParam(defaultValue = "4") int size,
                Model model) {

        String sortKey = SORT_FIELDS.containsKey(sort) ? sort : "name";
        String direction = "desc".equalsIgnoreCase(dir) ? "desc" : "asc";
        int pageSize = PAGE_SIZES.contains(size) ? size : 4;
        int pageNumber = Math.max(page, 0);

        Sort sorting = Sort.by(Sort.Direction.fromString(direction), SORT_FIELDS.get(sortKey));
        if (!sortKey.equals("name")) {
            sorting = sorting.and(Sort.by("name")); // tie-breaker so paging is stable
        }
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sorting);

        Page<Item> result = items.findAll(
                ItemSpecs.hasPetType(petType).and(ItemSpecs.hasBrand(brandId)), pageable);

        model.addAttribute("items", result);
        model.addAttribute("brands", brands.findAll(Sort.by("name")));
        model.addAttribute("petTypes", PetType.values());
        model.addAttribute("petType", petType);
        model.addAttribute("brandId", brandId);
        model.addAttribute("sort", sortKey);
        model.addAttribute("dir", direction);
        model.addAttribute("size", pageSize);
        return "items/list";
    }
}
