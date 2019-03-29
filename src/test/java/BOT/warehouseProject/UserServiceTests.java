package BOT.warehouseProject;

import BOT.warehouseProject.Authentication.Entities.User;
import BOT.warehouseProject.Authentication.Enums.UserStatus;
import BOT.warehouseProject.Authentication.Services.IUserService;
import BOT.warehouseProject.Authentication.Services.Implementations.UserServiceImplementation;
import BOT.warehouseProject.Database.Repositories.UserRepository;
import org.junit.Before;
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
        User testCustomer = this.givenCustomerUser();

        ///When
        when(userRepositoryMock.getOne(argThat(aLong -> aLong.equals(testId))))
                .thenAnswer((Answer) invocation -> testCustomer);
        User customer = this.userService.getUser(testId).get();

        ///Then
        assert(customer == testCustomer);

    }

    @Test
    public void givenUserService_WhenDeletingOneUserById_ThenCheckUserListSizeDecremented()
    {
        ///given
        when(userRepositoryMock.findAll()).thenReturn(this.givenAllUsers());

        List<User> allUsers = userRepositoryMock.findAll();
        Integer userCountBeforeDelete = allUsers.size();

        Long testId = allUsers.get(0).getUserId();

        //when
        userRepositoryMock.deleteById(testId);

        //then
        Integer userCountAfterDelete = userRepositoryMock.findAll().size();
        assert(userCountBeforeDelete.intValue() == userCountAfterDelete.intValue());
    }

    @Test
    public void givenUserService_WhenDeletingOneUserByUser_ThenCheckUserListSizeDecremented()
    {
        ///given


        List<User> allUsers = userRepositoryMock.findAll();
        Integer userCountBeforeDelete = allUsers.size();

        User userToDelete=userRepositoryMock.findAll().get(0);

        //when
        userRepositoryMock.delete(userToDelete);

        //then
        Integer userCountAfterDelete = userRepositoryMock.findAll().size();
        assert(userCountBeforeDelete.intValue() == userCountAfterDelete.intValue());
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