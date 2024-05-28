package ru.otus.hw.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.hw.models.WatchList;

import java.util.List;
import java.util.Optional;

public interface WatchListRepository extends JpaRepository<WatchList, Long> {

    Optional<WatchList> findById(long id);

    List<WatchList> findAllByUserUsername(String name);
}
