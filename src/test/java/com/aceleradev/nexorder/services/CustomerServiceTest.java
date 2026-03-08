package com.aceleradev.nexorder.services;

import com.aceleradev.nexorder.dtos.customer.CreateCustomerDto;
import com.aceleradev.nexorder.dtos.customer.ReadCustomerDto;
import com.aceleradev.nexorder.dtos.customer.UpdateCustomerDto;
import com.aceleradev.nexorder.entities.Customer;
import com.aceleradev.nexorder.repositories.CustomerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    private CustomerRepository repository;

    @InjectMocks
    private CustomerService customerService;

    @Test
    void findAllShouldMapCustomersToReadDto() {
        Customer customer = customer(1L, "Alice", "alice@mail.com");
        when(repository.findAll()).thenReturn(List.of(customer));

        List<ReadCustomerDto> result = customerService.findAll();

        assertEquals(1, result.size());
        assertEquals("Alice", result.get(0).getName());
        assertEquals("alice@mail.com", result.get(0).getEmail());
    }

    @Test
    void findByIdShouldReturnDtoWhenCustomerExists() {
        Customer customer = customer(1L, "Bob", "bob@mail.com");
        when(repository.findById(1L)).thenReturn(Optional.of(customer));

        ReadCustomerDto result = customerService.findById(1L);

        assertEquals("Bob", result.getName());
        assertEquals("bob@mail.com", result.getEmail());
    }

    @Test
    void findByIdShouldThrowWhenCustomerNotFound() {
        when(repository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> customerService.findById(1L));
    }

    @Test
    void createCustomerShouldPersistMappedEntity() {
        CreateCustomerDto dto = new CreateCustomerDto();
        dto.setName("Carol");
        dto.setPassword("secret");
        dto.setActive(true);
        dto.setPhoneNumber("11999999999");
        dto.setEmail("carol@mail.com");
        dto.setDescription("VIP");
        dto.setIdentifier("12345678900");

        customerService.createCustomer(dto);

        ArgumentCaptor<Customer> captor = ArgumentCaptor.forClass(Customer.class);
        verify(repository).save(captor.capture());
        Customer saved = captor.getValue();
        assertEquals("Carol", saved.getName());
        assertEquals("carol@mail.com", saved.getEmail());
        assertEquals("12345678900", saved.getIdentifier());
        assertNotNull(saved.getCreatedAt());
    }

    @Test
    void updateCustomerShouldThrowWhenCustomerDoesNotExist() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        UpdateCustomerDto dto = new UpdateCustomerDto();
        dto.setName("New Name");
        dto.setIsActive(true);

        assertThrows(RuntimeException.class, () -> customerService.updateCustomer(1L, dto));
    }

    private Customer customer(Long id, String name, String email) {
        Customer customer = new Customer();
        customer.setId(id);
        customer.setName(name);
        customer.setEmail(email);
        customer.setCreatedAt(LocalDate.now());
        customer.setActive(true);
        customer.setPhoneNumber("11999999999");
        customer.setDescription("desc");
        return customer;
    }
}
