package ua.epam.radchenko.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ua.epam.radchenko.persistence.dao.ExhibitionDao;
import ua.epam.radchenko.persistence.dao.factory.DaoFactory;
import ua.epam.radchenko.persistence.entity.Exhibition;
import ua.epam.radchenko.service.ExhibitionService;
import ua.epam.radchenko.util.timed.Timed;
import ua.epam.radchenko.util.type.Status;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Timed
@Service
public class ExhibitionServiceImpl implements ExhibitionService {
    private static final Logger LOGGER =
            LoggerFactory.getLogger(ExhibitionServiceImpl.class);
    private ExhibitionDao exhibitionDao =
            DaoFactory.getInstance().getExhibitionDao();

    public Exhibition createExhibition(Exhibition exhibition) {
        return exhibitionDao.insert(exhibition);
    }

    @Override
    public void updateExhibition(Exhibition exhibition) {
        LOGGER.debug("Attempt to update exhibition");
        exhibitionDao.update(exhibition);
    }

    @Override
    public Optional<Exhibition> findExhibitionById(int id) {
        LOGGER.debug("Attempt to find exhibition by id");
        return exhibitionDao.findOne(id);
    }

    @Override
    public List<Exhibition> findAllExhibitions(long skip, long limit) {
        LOGGER.debug("Attempt to find all exhibitions");
        return exhibitionDao.findAll(skip, limit);
    }

    @Override
    public List<Exhibition> findAllExhibitionsSorted(long skip, long limit, String sorting, String sortingType, LocalDate sortDate) {
        LOGGER.debug("Attempt to find all exhibitions");
        return exhibitionDao.viewAllSorted(skip, limit, sorting, sortingType, sortDate);
    }

    @Override
    public long getExhibitionCount() {
        LOGGER.debug("Attempt to get count of exhibitions");
        return exhibitionDao.getCount();
    }

    @Override
    public long getExhibitionDateFilteredCount(LocalDate sortDate) {
        LOGGER.debug("Attempt to get count of exhibitions");
        return exhibitionDao.countDateFiltered(sortDate);
    }

    @Override
    public void changeStatus(Exhibition exhibition, Status newStatus) {
        LOGGER.debug("Attempt to change status of exhibition");
        if (exhibition.getExhibitionStatus() != newStatus) {
            exhibition.setExhibitionStatus(newStatus);
            updateExhibition(exhibition);
        }
    }


}
