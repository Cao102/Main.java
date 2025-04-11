package Controller;

import DAO.LibraryManagementDAO;
import Model.Library;
import Model.BorrowedBook;
import View.ViewLibraryManagement;

import java.time.LocalDate;
import java.util.List;

public class LibraryManagementController {

    private final LibraryManagementDAO libraryManagementDAO;
    private final ViewLibraryManagement viewLibraryManagement;

    public LibraryManagementController() {
        this.libraryManagementDAO = new LibraryManagementDAO();
        this.viewLibraryManagement = new ViewLibraryManagement();
    }

    public void startLibrary() {
        while (true) {
            int input = viewLibraryManagement.menuLibraryManagement();
            switch (input) {
                case 1:
                    addBook();
                    break;
                case 2:
                    displayBooks();
                    break;
                case 3:
                    updateBook();
                    break;
                case 4:
                    deleteBook();
                    break;
                case 5:
                    searchBookByName();
                    break;
                case 6:
                    borrowBook();
                    break;
                case 7:
                    returnBook();
                    break;
                case 8:
                    displayBorrowedBooks();
                    break;
                case 9:
                    findBorrowedByStudent();
                    break;
                case 10:
                    displayAvailableBooks();
                    break;
                case 11:
                    return; // Thoát về main menu hoặc thoát chương trình
                default:
                    viewLibraryManagement.errorChoose();
                    break;
            }
        }
    }

    private void addBook() {
        while (true) {
            String bookId = viewLibraryManagement.inputBookId();
            if (libraryManagementDAO.isBookIdExist(bookId)) {
                boolean update = viewLibraryManagement.confirmUpdateBook();
                if (update) {
                    Library book = viewLibraryManagement.inputBookDetails(bookId);
                    libraryManagementDAO.updateBook(book);
                    viewLibraryManagement.notifyBookUpdated();
                } else {
                    continue;
                }
            } else {
                Library book = viewLibraryManagement.inputBookDetails(bookId);
                libraryManagementDAO.addBook(book);
                viewLibraryManagement.notifyBookAdded();
            }
            break;
        }
    }

    private void displayBooks() {
        List<Library> books = libraryManagementDAO.getAllBooks();
        viewLibraryManagement.displayAllBooks(books);
    }

    private void updateBook() {
        while (true) {
            String bookId = viewLibraryManagement.inputBookId();
            if (!libraryManagementDAO.isBookIdExist(bookId)) {
                viewLibraryManagement.showBookNotExist();
                continue;
            } else {
                Library book = viewLibraryManagement.inputBookDetails(bookId);
                libraryManagementDAO.updateBook(book);
                viewLibraryManagement.notifyBookUpdated();
            }
            break;
        }
    }

    private void deleteBook() {
        while (true) {
            String bookId = viewLibraryManagement.inputBookId();
            if (!libraryManagementDAO.isBookIdExist(bookId)) {
                viewLibraryManagement.showBookNotExist();
                continue;
            } else {
                libraryManagementDAO.deleteBook(bookId);
                viewLibraryManagement.notifyBookDeleted();
            }
            break;
        }
    }

    private void searchBookByName() {
        while (true) {
            String keyWord = viewLibraryManagement.inputKeyWord();
            List<Library> results = libraryManagementDAO.searchByBookName(keyWord);
            if (results.isEmpty()) {
                viewLibraryManagement.showKeyWordNotFound();
                continue;
            } else {
                viewLibraryManagement.showBookByKeyWord(results);
            }
            break;
        }
    }

    private void borrowBook() {
        String studentId;
        while (true) {
            studentId = viewLibraryManagement.inputStudentId();
            if (!libraryManagementDAO.isStudentExist(studentId)) {
                viewLibraryManagement.showStudentNotExist();
            } else {
                break;
            }
        }

        String bookId;
        while (true) {
            bookId = viewLibraryManagement.inputBookId();
            if (!libraryManagementDAO.isBookIdExist(bookId)) {
                viewLibraryManagement.showBookNotExist();
            } else {
                break;
            }
        }

        // Kiểm tra số lượng
        if (!libraryManagementDAO.decreaseQuantity(bookId)) {
            viewLibraryManagement.notifyOutOfStock();
            return; // Không cần tiếp tục
        }

        BorrowedBook borrowedBook = new BorrowedBook(
                studentId,
                bookId,
                LocalDate.now(),
                null
        );
        libraryManagementDAO.borrowBook(borrowedBook);
        viewLibraryManagement.notifyBorrow();
    }

    private void returnBook() {
        String studentId;
        while (true) {
            studentId = viewLibraryManagement.inputStudentId();
            if (!libraryManagementDAO.isStudentExist(studentId)) {
                viewLibraryManagement.showStudentNotExist();
            } else {
                break;
            }
        }

        String bookId;
        while (true) {
            bookId = viewLibraryManagement.inputBookId();
            if (!libraryManagementDAO.isBookIdExist(bookId)) {
                viewLibraryManagement.showBookNotExist();
            } else {
                break;
            }
        }

        // Kiểm tra sinh viên có đang mượn sách này không
        if (!libraryManagementDAO.isBookBorrowedByStudent(studentId, bookId)) {
            viewLibraryManagement.showBookNotBorrowed();
            return;
        }
        libraryManagementDAO.returnBook(studentId, bookId);
        libraryManagementDAO.increaseQuantity(bookId);
        viewLibraryManagement.notifyReturn();
    }

    private void displayBorrowedBooks() {
        List<BorrowedBook> borrowedBooks = libraryManagementDAO.getAllBorrowedBooks();
        viewLibraryManagement.displayBorrowedBooks(borrowedBooks);
    }

    private void findBorrowedByStudent() {
        while (true){
            String studentId = viewLibraryManagement.inputStudentId();
            if (!libraryManagementDAO.isStudentExist(studentId)) {
                viewLibraryManagement.showStudentNotExist();
                continue;
            }
            List<BorrowedBook> list = libraryManagementDAO.findByStudentId(studentId);
            viewLibraryManagement.showBorrowedBooksByStudentId(list,studentId);
            break;
        }
    }

    private void displayAvailableBooks() {
        List<Library> availableBooks = libraryManagementDAO.getAvailableBooks();
        viewLibraryManagement.displayAvailableBooks(availableBooks);
    }

}
