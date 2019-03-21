package BOT.warehouseProject;

import BOT.warehouseProject.Authentication.Entities.User;
import BOT.warehouseProject.Authentication.Enums.UserStatus;
import BOT.warehouseProject.Database.Repositories.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.mockito.Mockito.mock;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTests
{
    @Autowired
    private UserRepository usersRepository;


    @Before
    public void setUpUserRepository()
    {
        ///to można by było gdzies wyciagnać
        String customerName = "customer";
        String customerSecondName = "customer secondName";
        String customerPassword = "customer password";
        String customerAddress = "customer address";
        String customerEmail = "customer email";
        String customerPhoneNumber = "customer phoneNumber";
        UserStatus customerStatus = UserStatus.Customer;
        ///given
        User customer = new User(customerName,
                customerPassword, customerStatus,
                customerName, customerSecondName, customerAddress,
                customerEmail, customerPhoneNumber);

        String employee = "emplyee";
        String employeeSecondName = "emplyee secondName";
        String employeePassword = "emplyee  password";
        String employeeAddress = "emplyee address";
        String employeeEmail = "emplyee email";
        String employeePhoneNumber = "emplyee phoneNumber";
        UserStatus emplyeeStatus = UserStatus.Employee;

        User employeeUser = new User(employee,
                employeePassword, emplyeeStatus,employee, employeeSecondName, employeeAddress,
                employeeEmail, employeePhoneNumber);

        usersRepository.save(customer);
        usersRepository.save(employeeUser);
        usersRepository.flush();
    }

    @Test
    public void checkIfSetupProceedSuccessfully()
    {
        List<User> users = usersRepository.findAll();
        assert(users.size() > 1);
    }

    @Test
    public void whenFindByName_thenReturnUser()
    {
        ///Given
        List<User> users = usersRepository.findAll();

        ///when
        User u1 = users.get(0);
        User u2 = users.get(1);

        ///Then
        assert(!(u1.getUsername().equals(u2.getUsername())));

    }
}