package com.github.joseluzon.udemy.springbootdevbootcamp.challenges.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Repository;
import com.github.joseluzon.udemy.springbootdevbootcamp.challenges.Item;
import lombok.Getter;

@Repository
public class GlobalSuperStoreRepository {
    
    @Getter
    public List<Item> itemStore = new ArrayList<>();

    public boolean existsItem(final UUID uuid) {
        return getItemIfAny(uuid).isPresent();
    }

    public Optional<Item> getItem(final UUID uuid) {
        final var itemOptional = getItemIfAny(uuid);
        // return a copy.
        return itemOptional.isPresent() ? Optional.of(itemOptional.get().toBuilder().build()) : Optional.empty();
    }

    public void addItem(final Item item) {
        itemStore.add(item);
    }

    public void updateItem(final UUID uuid, final Item item) {
        final var itemOptional = getItemIfAny(uuid);
        if (itemOptional.isPresent()) {
            final var actualItem = itemOptional.get();
            actualItem.setCategory(item.getCategory());
            actualItem.setName(item.getName());
            actualItem.setPrice(item.getPrice());
            actualItem.setDiscount(item.getDiscount());
            actualItem.setDate(item.getDate());
        }
    }

    private Optional<Item> getItemIfAny(final UUID uuid) {
        return itemStore.stream().filter(item -> item.getUuid().equals(uuid)).findFirst();
    }
}
