package BOT.warehouseProject;

import BOT.warehouseProject.Authentication.Entities.User;
import BOT.warehouseProject.Authentication.Enums.UserStatus;
import BOT.warehouseProject.Authentication.Services.Implementations.UserServiceImplementation;
import BOT.warehouseProject.Database.Repositories.UserRepository;
import BOT.warehouseProject.Domain.Services.Implementations.WarehouseServiceImplementation;
import BOT.warehouseProject.Website.Controllers.WebController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserServiceTests
{
    @Autowired
    private UserRepository usersRepository;
    @Autowired
    private MockMvc mockMvc;


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

    @Test
    public void contextLoad_Test()
    {
        assert(usersRepository!=null);
    }


    @Test
    public void shouldReturnDefaultMessage() throws Exception {
        this.mockMvc.perform(get("/user/0")).
                andDo(print())
                .andExpect(status().isOk());
//                .andExpect(content().string(containsString("Hello World")));11

        int z=3;
    }




    @Test
    public void getAllUser_Test()
    {
        ///Given
        List<User> users = usersRepository.findAll();

        WarehouseServiceImplementation warehouseServiceImplementation
                = new WarehouseServiceImplementation();
        UserServiceImplementation userServiceImplementation
                = new UserServiceImplementation();
        WebController webController
                = new WebController(userServiceImplementation,warehouseServiceImplementation);

        ResponseEntity<List<User>> responseWithUser = webController.getAllUsers();

        ///When
        List<User> usersFromResponse = responseWithUser.getBody();

        ///Then
        assert(usersFromResponse != null);
        assert(users.size() != usersFromResponse.size());
    }
}