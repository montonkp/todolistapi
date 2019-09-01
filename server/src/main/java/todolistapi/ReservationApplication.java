package todolistapi;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import todolistapi.entity.*;
import todolistapi.repository.*;

import java.time.LocalDate;
import java.util.stream.Stream;

@SpringBootApplication
public class ReservationApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReservationApplication.class, args);
	}

	@Bean
    ApplicationRunner init(ReservationRepository reservationRepository, EmployeeRepository employeeRepository,
                           GenderRepository genderRepository, ItemRepository itemRepository,
                           TypeRepository typeRepository) {
        return args -> {
            Stream.of("Male", "Female").forEach(genderType -> {
                Gender gender = new Gender();
                gender.setGenderType(genderType);
                genderRepository.save(gender);
            });
            Stream.of("Projector", "Laptop", "Taplet").forEach(itemType -> {
                Type type = new Type();
                type.setItemType(itemType);
                typeRepository.save(type);
            });

            Item item = new Item();
            item.setType(typeRepository.findById(1l).get());
            item.setName("CANON LV-S300");
            itemRepository.save(item);

            Item item2 = new Item();
            item2.setType(typeRepository.findById(2l).get());
            item2.setName("Lenovo ideapad L340-81LG00LUTA");
            itemRepository.save(item2);

            Item item3 = new Item();
            item3.setType(typeRepository.findById(3l).get());
            item3.setName("Apple iPad 9.7");
            itemRepository.save(item3);

            Employee employee = new Employee();
            employee.setUsername("admin");
            employee.setPassword("admin");
            employee.setFirstName("Monthon");
            employee.setLastName("Kanpoh");
            employee.setGender(genderRepository.findById(1l).get());
            employee.setIdNumber("9876543210123");
            employee.setBirthday(LocalDate.now().minusYears(18));
            employee.setPhone("0814463573");
            employee.setEmail("monton.kp@gmail.com");
            employeeRepository.save(employee);

            Employee employee2 = new Employee();
            employee2.setUsername("admin2");
            employee2.setPassword("admin2");
            employee2.setFirstName("Abcd");
            employee2.setLastName("Efgh");
            employee2.setGender(genderRepository.findById(2l).get());
            employee2.setIdNumber("1234567890123");
            employee2.setBirthday(LocalDate.now().minusYears(18));
            employee2.setPhone("0987654321");
            employee2.setEmail("abcdefgh@gmail.com");
            employeeRepository.save(employee2);

            Reservation reservation = new Reservation();
            reservation.setEmployee(employee);
            reservation.setItem(item);
            reservation.setIssuedDate(LocalDate.now());
            reservation.setExpiryDate(LocalDate.now().plusMonths(1));
            reservationRepository.save(reservation);

        };
    }
}
