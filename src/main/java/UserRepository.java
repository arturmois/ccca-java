
import java.sql.Connection;
import java.sql.PreparedStatement;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
/**
 *
 * @author artur @arturmois
 */
public class UserRepository {

    private final Connection connection;
    private static final Logger logger = LogManager.getLogger(UserRepository.class);

    public UserRepository() {
        this.connection = (Connection) Registry.getInstance().inject("connection");
    }

    public void save(User user) {
        String query = "INSERT INTO users (id, name, email) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, user.getId().toString());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getEmail());
            logger.info(query);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            logger.error("Error executing query!", e);
        }
    }
}
