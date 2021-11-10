package ua.epam.radchenko.service;

import ua.epam.radchenko.persistence.entity.Exhibition;
import ua.epam.radchenko.util.type.Status;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ExhibitionService {

    Exhibition createExhibition(Exhibition exhibition);

    void updateExhibition(Exhibition exhibition);

    Optional<Exhibition> findExhibitionById(int id);

    List<Exhibition> findAllExhibitions(long skip, long limit);

    List<Exhibition> findAllExhibitionsSorted(long skip, long limit, String sorting, String sortingType, LocalDate sortDate);

    long getExhibitionCount();

    long getExhibitionDateFilteredCount(LocalDate sortDate);

    void changeStatus(Exhibition exhibition, Status newStatus);
}
