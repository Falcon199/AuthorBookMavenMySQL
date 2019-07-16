package manager;

import db.DBConnectionProvider;
import model.Author;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class AuthorManager {

    private Connection connection;

    public AuthorManager() {
        connection = DBConnectionProvider.getInstance().getConnection();
    }

    public void addAuthor(Author author) {
        try {
            Statement statement = connection.createStatement();
            String query = "INSERT INTO author(`name`,`surname`,`email`,`age`) " +
                    "VALUES('" + author.getName() + "','" + author.getSurname() + "','" + author.getEmail() + "'," + author.getAge() + ");";
            System.out.println("executing the following statement ->" + query);
            statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int id = generatedKeys.getInt(1);
                author.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Author getAuthorById(int id) {
        String query = "SELECT * FROM author WHERE id = " + id;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                Author author = new Author();
                author.setId(resultSet.getInt(1));
                author.setName(resultSet.getString(2));
                author.setSurname(resultSet.getString(3));
                author.setEmail(resultSet.getString(4));
                author.setAge(resultSet.getInt(5));
                return author;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Author> getAllAuthors() {
        String query = "SELECT * FROM author";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            List<Author> authors = new LinkedList<Author>();
            while (resultSet.next()) {
                Author author = new Author();
                author.setId(resultSet.getInt(1));
                author.setName(resultSet.getString(2));
                author.setSurname(resultSet.getString(3));
                author.setEmail(resultSet.getString(4));
                author.setAge(resultSet.getInt(5));
                authors.add(author);
            }
            return authors;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void removeAuthorById(int id) {
        String query = "DELETE  FROM author WHERE id = " + id;
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
