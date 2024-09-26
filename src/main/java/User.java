
import java.util.UUID;

/**
 *
 * @author artur @arturmois
 */
public class User {

    UUID id;
    String name;
    String email;

    public User(UUID id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public static User create(String name, String email) {
        UUID id = UUID.randomUUID();
        return new User(id, name, email);
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

}
