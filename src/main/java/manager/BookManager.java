package manager;

import db.DBConnectionProvider;
import model.Book;

import java.sql.*;

public class BookManager {

    private Connection connection;

    public BookManager() {
        connection = DBConnectionProvider.getInstance().getConnection();
    }

    public void addBook(Book book) {
        String query = "INSERT INTO book(title, description, price, author_id) " +
                "VALUES(?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setString(2, book.getDescription());
            preparedStatement.setDouble(3, book.getPrice());
            preparedStatement.setInt(4, book.getAuthorId());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                book.setId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
