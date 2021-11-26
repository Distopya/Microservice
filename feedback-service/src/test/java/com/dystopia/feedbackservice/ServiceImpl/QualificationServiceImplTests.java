package com.dystopia.feedbackservice.ServiceImpl;

import com.dystopia.feedbackservice.core.entity.Qualification;
import com.dystopia.feedbackservice.core.entity.Qualification;
import com.dystopia.feedbackservice.core.repository.QualificationRepository;
import com.dystopia.feedbackservice.core.service.QualificationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class QualificationServiceImplTests {

    @Mock
    private QualificationRepository qualificationRepository;

    @InjectMocks
    private QualificationService qualificationService;

    @Test
    public void saveTestQualification(){
        List<Qualification> qualificationList=new ArrayList<>();
        Qualification qualification=new Qualification("1",5,"Story","Jaime");
        given(qualificationRepository.save(qualification)).willReturn(qualification);
        Qualification savedQualification=null;
        savedQualification=qualificationService.saveQualification(qualification,"1","2");
        assertThat(savedQualification).isNotNull();
        verify(qualificationRepository).save(any(Qualification.class));
    }

    @Test
    void findByIdTestQualification() throws Exception {
        String id = "1";
        List<Qualification> qualificationList = new ArrayList<>();
        Qualification qualification=new Qualification("1",5,"Story","Jaime");
        given(qualificationRepository.findById(id)).willReturn(Optional.of(qualification));
        Optional<Qualification> expected = qualificationService.getQualificationById(id);
        assertThat(expected).isNotNull();
    }

    @Test
    void findAllTestQualification() throws Exception {
        List<Qualification> qualificationList = new ArrayList<>();
        Qualification qualification1=new Qualification("1",5,"Story","Jaime");
        Qualification qualification2=new Qualification("2",3,"Story","Carlos");
        Qualification qualification3=new Qualification("3",4,"Story","Pedro");
        List<Qualification> qualificationList1 = new ArrayList<>();
        qualificationList1.add(qualification2);
        qualificationList1.add(qualification1);
        qualificationList1.add(qualification3);
        given(qualificationRepository.findAll()).willReturn(qualificationList);
        List<Qualification> expected = qualificationService.getAllQualificationsByUserId("1");
        assertEquals(expected, qualificationList1);
    }

    @Test
    void deleteTestQualification() throws Exception {
        String id = "1";
        qualificationService.deleteQualification(id);
        verify(qualificationRepository, times(1)).deleteById(id);
    }

}
