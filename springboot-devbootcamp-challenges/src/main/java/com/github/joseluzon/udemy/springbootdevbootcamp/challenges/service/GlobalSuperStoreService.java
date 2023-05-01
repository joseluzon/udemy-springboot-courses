package com.github.joseluzon.udemy.springbootdevbootcamp.challenges.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import org.springframework.stereotype.Service;
import com.github.joseluzon.udemy.springbootdevbootcamp.challenges.Item;
import com.github.joseluzon.udemy.springbootdevbootcamp.challenges.Status;
import com.github.joseluzon.udemy.springbootdevbootcamp.challenges.repository.GlobalSuperStoreRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GlobalSuperStoreService {
    // @Autowired by Ctor.
    private final GlobalSuperStoreRepository globalSuperStoreRepository;

    public List<Item> getItems() {
        return globalSuperStoreRepository.getItemStore();
    }
    public Item getItem(final UUID uuid) {
        final var itemOptional = globalSuperStoreRepository.getItem(uuid);
        return itemOptional.isPresent() ? itemOptional.get() : Item.builder().build();
    }

    public Status submitItem(final Item item) {
        Status status = Status.SUCCESS;
        final var actualItemOptional = globalSuperStoreRepository.getItem(item.getUuid());
        if (actualItemOptional.isEmpty()) {
            globalSuperStoreRepository.addItem(item);
        } else if (within5Days(item.getDate(), actualItemOptional.get().getDate())) {
            globalSuperStoreRepository.updateItem(actualItemOptional.get().getUuid(), item);
        } else {
            status = Status.ERROR;
        }
        return status;
    }

    private boolean within5Days(final Date newDate, final Date oldDate) {
        long diff = Math.abs(newDate.getTime() - oldDate.getTime());
        return (int) (TimeUnit.MILLISECONDS.toDays(diff)) <= 5;
    }
}
