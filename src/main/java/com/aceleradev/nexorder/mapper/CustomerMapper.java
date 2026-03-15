package com.aceleradev.nexorder.mapper;

import com.aceleradev.nexorder.dtos.customer.CustomerCreateRequest;
import com.aceleradev.nexorder.dtos.customer.CustomerResponse;
import com.aceleradev.nexorder.dtos.customer.CustomerUpdateRequest;
import com.aceleradev.nexorder.entities.Customer;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "lastLogin", ignore = true)
    Customer toEntity(CustomerCreateRequest dto);

    CustomerResponse toResponse(Customer customer);

    List<CustomerResponse> toResponseList(List<Customer> customers);

    // Diz ao MapStruct para ignorar campos null vindos de um update.
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "lastLogin", ignore = true)
    @Mapping(target = "identifier", ignore = true)
    @Mapping(target = "password", ignore = true)
    void updateCustomerFromDto(CustomerUpdateRequest dto, @MappingTarget Customer entity);
    // O @MappingTarget está dizendo: "Atualize essa entidade existente ao invés de criar uma nova".

}
