
/**
 *
 * @author artur @arturmois
 */
public class CreateUserUseCase {
    
    UserRepository userRepository;
    
    public CreateUserUseCase() {
        userRepository = (UserRepository) Registry.getInstance().inject("userRepository");
    }
    
    public void execute(String name, String email) {
        User user = User.create(name, email);
        userRepository.save(user);
    }
}
