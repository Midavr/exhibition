package ua.epam.radchenko.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import ua.epam.radchenko.persistence.dao.ExhibitionDao;
import ua.epam.radchenko.persistence.entity.Exhibition;
import ua.epam.radchenko.util.type.Status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class ExhibitionServiceTest {
    @InjectMocks
    private final ExhibitionService exhibitionService = ExhibitionService.getInstance();
    @Mock
    private ExhibitionDao exhibitionDao;

    @Test
    void createExhibitionTest() {
        int exhibitionId = 1;
        Exhibition exhibition = Exhibition.newBuilder().build();
        when(exhibitionDao.insert(exhibition)).then((Answer<Exhibition>) invocationOnMock -> {
            Exhibition toReturn = invocationOnMock.getArgument(0);
            toReturn.setExhibitionId(exhibitionId);
            return toReturn;
        });

        Exhibition actual = exhibitionService.createExhibition(exhibition);

        assertEquals(exhibitionId, actual.getExhibitionId());
        verify(exhibitionDao, times(1)).insert(exhibition);
    }

    @Test
    void updateExhibitionTest() {
        Exhibition exhibition = Exhibition.newBuilder().build();
        exhibitionService.updateExhibition(exhibition);
        verify(exhibitionDao, times(1)).update(exhibition);
    }

    @Test
    void changeStatusOnAnotherStatusTest() {
        Exhibition exhibition = Exhibition.newBuilder()
                .setExhibitionStatus(Status.ACTIVE)
                .build();

        exhibitionService.changeStatus(exhibition, Status.SUSPENDED);

        assertEquals(Status.SUSPENDED, exhibition.getExhibitionStatus());
        verify(exhibitionDao, times(1)).update(exhibition);
    }

    @Test
    void changeStatusOnSameStatusTest() {
        Exhibition exhibition = Exhibition.newBuilder()
                .setExhibitionStatus(Status.ACTIVE)
                .build();

        exhibitionService.changeStatus(exhibition, Status.ACTIVE);

        assertEquals(Status.ACTIVE, exhibition.getExhibitionStatus());
        verify(exhibitionDao, never()).update(exhibition);
    }

    @Test
    void findExhibitionByIdWithExistingExhibitionTest() {
        int exhibitionId = 1;
        Optional<Exhibition> expected = Optional.of(
                Exhibition.newBuilder()
                        .setExhibitionId(exhibitionId)
                        .build());
        when(exhibitionDao.findOne(exhibitionId)).thenReturn(expected);

        Optional<Exhibition> actual = exhibitionService.findExhibitionById(exhibitionId);

        assertEquals(expected, actual);
        verify(exhibitionDao, times(1)).findOne(exhibitionId);
    }

    @Test
    void findExhibitionByIdWithNotExistingExhibitionTest() {
        int exhibitionId = 1;
        when(exhibitionDao.findOne(exhibitionId)).thenReturn(Optional.empty());

        Optional<Exhibition> exhibitionOpt = exhibitionService.findExhibitionById(exhibitionId);

        assertFalse(exhibitionOpt.isPresent());
        verify(exhibitionDao, times(1)).findOne(exhibitionId);
    }

    @Test
    void findAllExhibitionsTest() {
        long skip = 0;
        long limit = 3;
        List<Exhibition> expected = new ArrayList<Exhibition>() {{
            add(Exhibition.newBuilder().setExhibitionId(1).build());
            add(Exhibition.newBuilder().setExhibitionId(2).build());
            add(Exhibition.newBuilder().setExhibitionId(3).build());
        }};
        when(exhibitionDao.findAll(skip, limit)).thenReturn(expected);

        List<Exhibition> actual = exhibitionService.findAllExhibitions(skip, limit);

        assertEquals(3, actual.size());
        verify(exhibitionDao, times(1)).findAll(skip, limit);
    }

    @Test
    void getExhibitionsCountTest() {
        long expected = 10;
        when(exhibitionDao.getCount()).thenReturn(expected);

        long actual = exhibitionService.getExhibitionCount();

        assertEquals(expected, actual);
        verify(exhibitionDao, times(1)).getCount();
    }

}