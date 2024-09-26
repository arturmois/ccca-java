
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 *
 * @author artur @arturmois
 */
public class FirebirdConnection {

    private static final Logger logger = LogManager.getLogger(FirebirdConnection.class);

    public static Connection getConnection() {
        String projectDir = System.getProperty("user.dir");
        String url = "jdbc:firebirdsql://localhost:3050/" + projectDir + "/database.fdb";
        Properties props = new Properties();
        props.setProperty("user", "SYSDBA");
        props.setProperty("password", "masterkey");
        props.setProperty("encoding", "UTF8");
        Connection connection = null;
        try {
            Class.forName("org.firebirdsql.jdbc.FBDriver");
            connection = DriverManager.getConnection(url, props);
            logger.info("Conectado ao Firebird com sucesso!");
        } catch (ClassNotFoundException e) {
            logger.error("Driver JDBC do Firebird não encontrado!", e);
        } catch (SQLException e) {
            logger.error("Erro ao conectar ao Firebird!", e);
        }
        return connection;
    }

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                if (!connection.isClosed()) {
                    connection.close();
                    logger.info("Conexão fechada com sucesso!");
                }
            } catch (SQLException e) {
                logger.error("Erro ao fechar a conexão!", e);
            }
        }
    }
}
