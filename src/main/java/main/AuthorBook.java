package main;

import manager.AuthorManager;
import model.Author;

import java.util.List;

public class AuthorBook {
    public static void main(String[] args) {
        AuthorManager authorManager = new AuthorManager();
//        Author author = new Author("poxos", "poxosyan", "poxos@mail.com", 20);
//        authorManager.addAuthor(author);
//
        List<Author> allAuthors = authorManager.getAllAuthors();
        System.out.println(allAuthors);

        authorManager.removeAuthorById(1005);

        authorManager.getAllAuthors();
        System.out.println(allAuthors);

    }
}
