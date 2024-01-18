package com.GrowwThere.sJustOneRightWay.StocksPortfolioApp.service.Interface;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceTest {

  @BeforeEach
  void setUp() {
  }


  @SpringBootTest
  class DepartmentServiceTest {

    @Autowired
    private DepartmentService departmentService;

    @MockBean
    private DepartmentRepository departmentRepository;

    @BeforeEach
    void setUp() {
      Department department =
          Department.builder()
              .departmentName("IT")
              .departmentAddress("Ahmedabad")
              .departmentCode("IT-06")
              .departmentId(1L)
              .build();

      Mockito.when(departmentRepository.findByDepartmentNameIgnoreCase("IT"))
          .thenReturn(department);

    }

    @Test
    @DisplayName("Get Data based on Valida Department Name")
    public void whenValidDepartmentName_thenDepartmentShouldFound() {
      String departmentName = "IT";
      Department found =
          departmentService.fetchDepartmentByName(departmentName);

      assertEquals(departmentName, found.getDepartmentName());
    }
}