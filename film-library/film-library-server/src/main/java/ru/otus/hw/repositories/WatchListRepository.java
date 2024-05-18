package ru.otus.hw.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.otus.hw.models.WatchList;

public interface WatchListRepository extends CrudRepository<WatchList, Long> {

}
