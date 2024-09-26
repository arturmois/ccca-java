
/**
 *
 * @author artur @arturmois
 */
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class Registry {

    private static final Logger logger = LogManager.getLogger(Registry.class);
    private final Map<String, Object> dependencies;
    private static Registry instance;

    private Registry() {
        this.dependencies = new HashMap<>();
    }

    public void provide(String name, Object dependency) {
        this.dependencies.put(name, dependency);
    }

    public Object inject(String name) {
        return this.dependencies.get(name);
    }

    public static Registry getInstance() {
        if (instance == null) {
            instance = new Registry();
        }
        return instance;
    }

    public static void inject(Object target, String name, String propertyName) {
        try {
            Object proxy = Proxy.newProxyInstance(
                    target.getClass().getClassLoader(),
                    target.getClass().getInterfaces(),
                    (proxyObj, method, args) -> {
                        Object dependency = Registry.getInstance().inject(name);
                        return method.invoke(dependency, args);
                    }
            );
            target.getClass().getDeclaredField(propertyName).set(target, proxy);
        } catch (IllegalAccessException | IllegalArgumentException | NoSuchFieldException | SecurityException e) {
            logger.error("Error inject dependency!", e);
        }
    }
}
