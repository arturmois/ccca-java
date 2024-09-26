
import java.sql.Connection;
import java.sql.SQLException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author artur
 */
public class CreateUserTest {

    public CreateUserTest() {
    }

    @BeforeEach
    public void setUp() {
        Registry.getInstance().provide("connection", FirebirdConnection.getConnection());
        Registry.getInstance().provide("userRepository", new UserRepository());
    }

    @AfterEach
    public void tearDown() throws SQLException {
        Connection connection;
        connection = (Connection) Registry.getInstance().inject("connection");
        connection.close();
    }

    @Test
    public void createUser() {
        CreateUserUseCase createUser = new CreateUserUseCase();
        createUser.execute("Artur Moises", "artur@email.com");
    }
}
