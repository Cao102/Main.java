package Model;

public class Library implements TableConvertible{
    private String bookId;
    private String bookName;
    private String author;
    private int quantity;

    public Library(String bookId, String bookName, String author, int quantity) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.author = author;
        this.quantity = quantity;
    }

    public String getBookId() {
        return bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public String getAuthor() {
        return author;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return String.format("║ %-10s ║ %-25s ║ %-20s ║ %-8d ║", bookId, bookName, author, quantity);
    }
    public String[] toRow() {
        return new String[] {
                bookId, bookName, author, String.valueOf(quantity)
        };
    }
}
