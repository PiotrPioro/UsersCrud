package pl.coderslab.databaseClasses;




import org.mindrot.jbcrypt.BCrypt;
import java.sql.*;
import java.util.Arrays;

public class UserDao {

    private static final String CreateUserQuery = "insert into Users (username, email, password) values (?,?,?);";
    private static final String DeleteUserQuery = "delete from Users where id = ?;";
    private static final String UpdateUserQuery = "update Users set username = ?, email = ?, password = ? where id = ?;";
    private static final String ReadUserQuery = "select *from Users where id = ?;";
    private static final String SelectNotEmptyRows = "select *from Users where id is not null;";


    public static String hashPassword(String password){
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public User create(User user){
        try(Connection conn = DbUtil.getConnection()){
            PreparedStatement stmt = conn.prepareStatement(CreateUserQuery, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, hashPassword(user.getPassword()));
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if(rs.next()){
                user.setId(rs.getInt(1));
            }
            return user;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public User read(int userID){
        try(Connection conn = DbUtil.getConnection()){
            PreparedStatement stmt2 = conn.prepareStatement(ReadUserQuery);
            stmt2.setInt(1, userID);
            User userRead = new User();
            ResultSet rs2 = stmt2.executeQuery();
            if(rs2.next()){
                userRead.setId(userID);
                userRead.setUsername(rs2.getString("username"));
                userRead.setEmail(rs2.getString("email"));
                userRead.setPassword(rs2.getString("password"));
            }
            return userRead;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public void printUser(User user){
        System.out.println(user.toString());
    }

    public void update(User user){
        try(Connection conn = DbUtil.getConnection()){
            PreparedStatement stmt3 = conn.prepareStatement(UpdateUserQuery);
            stmt3.setInt(4, user.getId());
            stmt3.setString(1, user.getUsername());
            stmt3.setString(2, user.getEmail());
            stmt3.setString(3, hashPassword(user.getPassword()));
            stmt3.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void delete(int userId){
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement stmt4 = conn.prepareStatement(DeleteUserQuery);
            stmt4.setInt(1, userId);
            stmt4.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public User[] findAll(){
        User[] arrUser = new User[0];
        try(Connection conn = DbUtil.getConnection()){
            PreparedStatement stmt5 = conn.prepareStatement(SelectNotEmptyRows);
            ResultSet rs3 = stmt5.executeQuery();
            while(rs3.next()) {
                User user5 = new User();
                user5.setId(rs3.getInt(1));
                user5.setUsername(rs3.getString("username"));
                user5.setEmail(rs3.getString("email"));
                user5.setPassword(rs3.getString("password"));
                arrUser = Arrays.copyOf(arrUser, arrUser.length + 1);
                arrUser[arrUser.length - 1] = user5;
            }
            return arrUser;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
