package BOT.warehouseProject;

import BOT.warehouseProject.Authentication.Entity.User;
import BOT.warehouseProject.Authentication.Enum.UserStatus;
import BOT.warehouseProject.Authentication.Service.IUserService;
import BOT.warehouseProject.Authentication.Service.Implementations.UserServiceImplementation;
import BOT.warehouseProject.Database.Repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTests
{
    @TestConfiguration
    static class UserServiceTestsConfiguration{
        @Bean IUserService userServiceTest(){
            return new UserServiceImplementation();
        }
    }

    @MockBean
    private UserRepository userRepositoryMock;

    @Autowired
    @Qualifier("userServiceTest")
    private IUserService userService;

    @Test
    public void givenUserService_WhenGettingAllUsers_ThenCheckUserListSize()
    {
        when(userRepositoryMock.findAll()).thenReturn(this.givenAllUsers());
        List<User> users = userService.getAllUsers();

        assert(users.size() == 2);
    }

    @Test
    public void givenUserId_WhenGetUser_thenReturnUser()
    {
        ///Given
        Long testId = 0L;
        Optional<User> testCustomer = Optional.of(this.givenCustomerUser());

        ///When
        when(userRepositoryMock.findById(argThat(aLong -> aLong.equals(testId))))
                .thenAnswer((Answer) invocation -> testCustomer);
        User customer = this.userService.getUser(testId).get();

        ///Then
        assert(customer == testCustomer.get());

    }

    private List<User> givenAllUsers(){
        List <User> usersList = new ArrayList<>();

        User customer = this.givenCustomerUser();
        User employee = this.givenEmployeeUser();

        usersList.add(customer);
        usersList.add(employee);

        return usersList;
    }

    public User givenCustomerUser(){
        String customerName = "customer";
        String customerSecondName = "customer secondName";
        String customerPassword = "customer password";
        String customerAddress = "customer address";
        String customerEmail = "customer email";
        String customerPhoneNumber = "customer phoneNumber";
        UserStatus customerStatus = UserStatus.Customer;

        User customer = new User(customerName,
                customerPassword, customerStatus,
                customerName, customerSecondName, customerAddress,
                customerEmail, customerPhoneNumber);

        return customer;
    }

    public User givenEmployeeUser(){
        String employeeName = "employee";
        String employeeSecondName = "employee secondName";
        String employeePassword = "employee  password";
        String employeeAddress = "employee address";
        String employeeEmail = "employee email";
        String employeePhoneNumber = "employee phoneNumber";
        UserStatus employeeStatus = UserStatus.Employee;

        User employee = new User(employeeName,
                employeePassword, employeeStatus,employeeName, employeeSecondName, employeeAddress,
                employeeEmail, employeePhoneNumber);

        return employee;
    }
}