package runer;

import Model.Comment;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandQuery {
    public static void main(String[] args) throws SQLException {
        create();
        delete();
    }

    public static void read() throws SQLException {
        List<Comment> comments = new ArrayList<>();
        Comment comment = null;
        Connection connection = Connector.connect();
        PreparedStatement ps = connection.prepareStatement("select * from comment limit 10;");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            comment = new Comment(rs.getString(2), rs.getInt(3));
            comments.add(comment);
        }

        comments.stream().forEach(c -> System.out.println(c.getComment()));
    }

    public static void create() throws SQLException {

        Connection connection = Connector.connect();
        PreparedStatement ps = connection.prepareStatement("insert into comment (comment, user_id) values ('testtest', 123 );");
        ps.executeUpdate();

    }

    public static void delete() throws SQLException {

        Connection connection = Connector.connect();
        PreparedStatement ps = connection.prepareStatement("delete from comment where comment = 'testtest';");
        ps.executeUpdate();

    }
}
