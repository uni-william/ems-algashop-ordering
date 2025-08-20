package com.algaworks.algashop.ordering.application.service.customer.management;

import com.algaworks.algashop.ordering.application.customer.management.CustomerManagementApplicationService;
import com.algaworks.algashop.ordering.application.customer.management.CustomerInput;
import com.algaworks.algashop.ordering.application.customer.management.CustomerOutput;
import com.algaworks.algashop.ordering.application.customer.management.CustomerUpdateInput;
import com.algaworks.algashop.ordering.domain.model.customer.CustomerArchivedException;
import com.algaworks.algashop.ordering.domain.model.customer.CustomerNotFoundException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.UUID;

@SpringBootTest
@Transactional
class CustomerManagementApplicationServiceIT {

    @Autowired
    private CustomerManagementApplicationService customerManagementApplicationService;

    @Test
    public void shouldRegister() {
        CustomerInput input = CustomerInputTestDataBuilder.aCustomer().build();

        UUID customerId = customerManagementApplicationService.create(input);
        Assertions.assertThat(customerId).isNotNull();

        CustomerOutput customerOutput = customerManagementApplicationService.findById(customerId);

        Assertions.assertThat(customerOutput)
                        .extracting(
                                CustomerOutput::getId,
                                CustomerOutput::getFirstName,
                                CustomerOutput::getLastName,
                                CustomerOutput::getEmail,
                                CustomerOutput::getBirthDate
                        ).containsExactly(
                                customerId,
                                "John",
                                "Doe",
                                "johndoe@email.com",
                                LocalDate.of(1991, 7,5)
                );
        Assertions.assertThat(customerOutput.getRegisteredAt()).isNotNull();
    }

    @Test
    public void shouldUpdate() {
        CustomerInput input = CustomerInputTestDataBuilder.aCustomer().build();
        CustomerUpdateInput updateInput = CustomerUpdateInputTestDataBuilder.aCustomerUpdate().build();

        UUID customerId = customerManagementApplicationService.create(input);
        Assertions.assertThat(customerId).isNotNull();

        customerManagementApplicationService.update(customerId, updateInput);

        CustomerOutput customerOutput = customerManagementApplicationService.findById(customerId);

        Assertions.assertThat(customerOutput)
                .extracting(
                        CustomerOutput::getId,
                        CustomerOutput::getFirstName,
                        CustomerOutput::getLastName,
                        CustomerOutput::getEmail,
                        CustomerOutput::getBirthDate
                ).containsExactly(
                        customerId,
                        "Matt",
                        "Damon",
                        "johndoe@email.com",
                        LocalDate.of(1991, 7,5)
                );
        Assertions.assertThat(customerOutput.getRegisteredAt()).isNotNull();
    }

    @Test
    public void shouldArchiveCustomer() {
        CustomerInput input = CustomerInputTestDataBuilder.aCustomer().build();
        UUID customerId = customerManagementApplicationService.create(input);
        Assertions.assertThat(customerId).isNotNull();

        customerManagementApplicationService.archive(customerId);

        CustomerOutput archivedCustomer = customerManagementApplicationService.findById(customerId);

        Assertions.assertThat(archivedCustomer)
                .isNotNull()
                .extracting(
                        CustomerOutput::getFirstName,
                        CustomerOutput::getLastName,
                        CustomerOutput::getPhone,
                        CustomerOutput::getDocument,
                        CustomerOutput::getBirthDate,
                        CustomerOutput::getPromotionNotificationsAllowed
                ).containsExactly(
                        "Anonymous",
                        "Anonymous",
                        "000-000-0000",
                        "000-00-0000",
                        null,
                        false
                );

        Assertions.assertThat(archivedCustomer.getEmail()).endsWith("@anonymous.com");
        Assertions.assertThat(archivedCustomer.getArchived()).isTrue();
        Assertions.assertThat(archivedCustomer.getArchivedAt()).isNotNull();

        Assertions.assertThat(archivedCustomer.getAddress()).isNotNull();
        Assertions.assertThat(archivedCustomer.getAddress().getNumber()).isNotNull().isEqualTo("Anonymized");
        Assertions.assertThat(archivedCustomer.getAddress().getComplement()).isNull();
    }

    @Test
    public void shouldThrowCustomerNotFoundExceptionWhenArchivingNonExistingCustomer() {
        UUID nonExistingId = UUID.randomUUID();

        Assertions.assertThatExceptionOfType(CustomerNotFoundException.class)
                .isThrownBy(() -> customerManagementApplicationService.archive(nonExistingId));
    }

    @Test
    public void shouldThrowCustomerArchivedExceptionWhenArchivingAlreadyArchivedCustomer() {
        CustomerInput input = CustomerInputTestDataBuilder.aCustomer().build();
        UUID customerId = customerManagementApplicationService.create(input);
        Assertions.assertThat(customerId).isNotNull();

        customerManagementApplicationService.archive(customerId);

        Assertions.assertThatExceptionOfType(CustomerArchivedException.class)
                .isThrownBy(() -> customerManagementApplicationService.archive(customerId));
    }


}