package Model;

import java.time.LocalDate;

public class BorrowedBook implements TableConvertible{
    private String studentId;
    private String bookId;
    private LocalDate borrowDate;
    private LocalDate returnDate;

    public BorrowedBook(String studentId, String bookId, LocalDate borrowDate, LocalDate returnDate) {
        this.studentId = studentId;
        this.bookId = bookId;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }

    // Getters & Setters
    public String getStudentId() {
        return studentId;
    }

    public String getBookId() {
        return bookId;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

//    @Override
//    public String toString() {
//        return String.format("║ %-14s ║ %-10s ║ %-12s ║ %-12s ║",
//                studentId, bookId,
//                borrowDate.toString(),
//                returnDate != null ? returnDate.toString() : "Chưa trả");
//    }
    @Override
    public String[] toRow() {
        return new String[] {
                studentId, bookId, String.valueOf(borrowDate), String.valueOf(returnDate)
        };
    }
}
