package com.pawcart.repository;

import com.pawcart.model.Item;
import com.pawcart.model.PetType;
import org.springframework.data.jpa.domain.Specification;

/**
 * Optional filters for the list page. A null argument means "no filter",
 * so the same query handles every combination of pet type and brand.
 */
public final class ItemSpecs {

    private ItemSpecs() {}

    public static Specification<Item> hasPetType(PetType petType) {
        return (root, query, cb) ->
                petType == null ? cb.conjunction() : cb.equal(root.get("petType"), petType);
    }

    public static Specification<Item> hasBrand(Long brandId) {
        return (root, query, cb) ->
                brandId == null ? cb.conjunction() : cb.equal(root.get("brand").get("id"), brandId);
    }
}
