import com.books.dto.UserDto;
import com.books.entity.User;
import com.books.exception.UserCreateException;
import com.books.mapper.UserMapper;
import com.books.repository.impl.UserRepositoryImpl;
import com.books.service.impl.UserServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    UserRepositoryImpl userRepository;
    @InjectMocks
    UserServiceImpl userService;

    private final User user = User.builder().id(1).name("Test user").age(50).build();
    private final UserDto userDto = UserDto.builder().id(1).name("Test user").age(50).build();

    @Test
    @DisplayName("Create user")
    public void createUser() {
        when(userService.create(userDto)).thenReturn(user);
        assertEquals(user, userService.create(userDto));
    }

    @Test
    @DisplayName("UserCreateException name is empty")
    public void createUser_ShouldBeException_name_empty() {
        UserDto userDto = UserDto.builder().name("").age(50).build();
        assertThrows(UserCreateException.class, () -> userService.create(userDto));
    }

    @Test
    @DisplayName("UserCreateException name null")
    public void createUser_ShouldBeException_name_null() {
        UserDto userDto = UserDto.builder().name(null).age(50).build();
        assertThrows(UserCreateException.class, () -> userService.create(userDto));
    }

    @Test
    @DisplayName("UserCreateException age = 0")
    public void createUser_ShouldBeException_age_0() {
        UserDto userDto = UserDto.builder().name("Test user").age(0).build();
        assertThrows(UserCreateException.class, () -> userService.create(userDto));
    }

    @Test
    @DisplayName("UserCreateException age = 121")
    public void createUser_ShouldBeException_age_121() {
        UserDto userDto = UserDto.builder().name("Test user").age(121).build();
        assertThrows(UserCreateException.class, () -> userService.create(userDto));
    }

    @Test
    @DisplayName("Get user by id")
    public void getUserById() {
        when(userRepository.getById(1)).thenReturn(user);
        User responseUser = userService.getById(1);
        assertEquals(user, responseUser);
    }

    @Test
    @DisplayName("Get all users")
    public void getAllUsers() {
        List<User> users = new ArrayList<>();
        users.add(user);
        users.add(user);

        when(userRepository.getAll()).thenReturn(users);
        List<User> responseUsers = userService.getAll();

        assertEquals(users, responseUsers);
        assertEquals(users.size(), responseUsers.size());
    }

    @Test
    @DisplayName("Update user")
    public void updateUser() {
        when(userRepository.update(user)).thenReturn(user);
        userService.update(UserMapper.toUserDto(user));

        // times(n) - вызов метода n-раз
        verify(userRepository, times(1)).update(user);
    }

    @Test
    @DisplayName("Delete user")
    public void deleteUser() {
        userService.delete(1);
        verify(userRepository, times(1)).delete(1);
    }
}
