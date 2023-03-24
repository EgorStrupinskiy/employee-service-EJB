//package com.innowise.employeeserviceee.dto.converter;//package com.innowise.employeeserviceee.dto.converter;
//
//
//import com.innowise.employeeserviceee.dto.EmployeeDTO;
//import com.innowise.employeeserviceee.entity.Employee;
//import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
//import org.mapstruct.factory.Mappers;
//
//
//@Mapper
//public interface EmployeeMapper {
//    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);
//
//    @Mapping(target = "id", source = "id")
//    @Mapping(target = "name", source = "name")
//    @Mapping(target = "surname", source = "surname")
//    @Mapping(target = "salary", source = "salary")
//    @Mapping(target = "departmentId", source = "department")
//    Employee fromDto(EmployeeDTO employeeDTO);
//
//    @Mapping(target = "id", source = "id")
//    @Mapping(target = "name", source = "name")
//    @Mapping(target = "surname", source = "surname")
//    @Mapping(target = "salary", source = "salary")
//    @Mapping(target = "department", source = "departmentId")
//    EmployeeDTO toDto(Employee employee);
//}
