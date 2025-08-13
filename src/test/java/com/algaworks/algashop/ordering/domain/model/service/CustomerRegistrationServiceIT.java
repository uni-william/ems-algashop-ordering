package com.algaworks.algashop.ordering.domain.model.service;

import com.algaworks.algashop.ordering.domain.model.entity.Customer;
import com.algaworks.algashop.ordering.domain.model.valueobject.*;
import com.algaworks.algashop.ordering.domain.model.valueobject.BirthDate;
import com.algaworks.algashop.ordering.domain.model.valueobject.Email;
import com.algaworks.algashop.ordering.domain.model.valueobject.FullName;
import com.algaworks.algashop.ordering.domain.model.valueobject.Phone;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
class CustomerRegistrationServiceIT {

    @Autowired
    private CustomerRegistrationService customerRegistrationService;

    @Test
    public void shouldRegister() {
        Customer customer = customerRegistrationService.register(
                new FullName("John", "Doe"),
                new BirthDate(LocalDate.of(1991, 7, 5)),
                new Email("johndoe@email.com"),
                new Phone("478-256-2604"),
                new com.algaworks.algashop.ordering.domain.model.valueobject.Document("255-08-0578"),
                true,
                Address.builder()
                        .street("Bourbon Street")
                        .number("1134")
                        .neighborhood("North Ville")
                        .city("Yostfort")
                        .state("South Carolina")
                        .zipCode(new ZipCode("70283"))
                        .complement("Apt. 901")
                        .build()
        );

        Assertions.assertThat(customer.fullName()).isEqualTo(new FullName("John", "Doe"));
        Assertions.assertThat(customer.email()).isEqualTo(new Email("johndoe@email.com"));
    }

}