-- Xóa cơ sở dữ liệu nếu đã tồn tại
DROP DATABASE IF EXISTS StudentManagementSystem;

-- Tạo cơ sở dữ liệu
CREATE DATABASE StudentManagementSystem;
USE StudentManagementSystem;

-- Bảng sinh viên
CREATE TABLE Students (
    student_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    dob DATE,
    email VARCHAR(100) UNIQUE,
    phone VARCHAR(15)
);

-- Bảng giảng viên
CREATE TABLE Teachers (
    teacher_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    email VARCHAR(100) UNIQUE,
    phone VARCHAR(15)
);

-- Bảng môn học
CREATE TABLE Subjects (
    subject_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    description TEXT
);

-- Bảng lớp học
CREATE TABLE Classrooms (
    classroom_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50),
    capacity INT
);

-- Bảng đăng ký môn học
CREATE TABLE Registrations (
    student_id INT,
    subject_id INT,
    PRIMARY KEY (student_id, subject_id),
    FOREIGN KEY (student_id) REFERENCES Students(student_id),
    FOREIGN KEY (subject_id) REFERENCES Subjects(subject_id)
);

-- Bảng điểm số
CREATE TABLE Grades (
    student_id INT,
    subject_id INT,
    grade DOUBLE,
    PRIMARY KEY (student_id, subject_id),
    FOREIGN KEY (student_id) REFERENCES Students(student_id),
    FOREIGN KEY (subject_id) REFERENCES Subjects(subject_id)
);

-- Bảng lịch học
CREATE TABLE Schedules (
    id INT AUTO_INCREMENT PRIMARY KEY,
    classroom_id INT,
    subject_id INT,
    teacher_id INT,
    schedule_time DATETIME,
    FOREIGN KEY (classroom_id) REFERENCES Classrooms(classroom_id),
    FOREIGN KEY (subject_id) REFERENCES Subjects(subject_id),
    FOREIGN KEY (teacher_id) REFERENCES Teachers(teacher_id)
);

-- Bảng tài khoản người dùng
CREATE TABLE Users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE,
    password VARCHAR(255)
);

-- Bảng học phí
CREATE TABLE Tuition (
    student_id INT PRIMARY KEY,
    amount DOUBLE,
    FOREIGN KEY (student_id) REFERENCES Students(student_id)
);

-- Bảng thư viện
CREATE TABLE Library (
    book_id INT AUTO_INCREMENT PRIMARY KEY,
    book_name VARCHAR(100),
    author VARCHAR(100)
);

-- Bảng mượn sách
CREATE TABLE BorrowedBooks (
    student_id INT,
    book_id INT,
    borrow_date DATE,
    return_date DATE,
    PRIMARY KEY (student_id, book_id),
    FOREIGN KEY (student_id) REFERENCES Students(student_id),
    FOREIGN KEY (book_id) REFERENCES Library(book_id)
);

-- Bảng kỳ thi
CREATE TABLE Exams (
    id INT AUTO_INCREMENT PRIMARY KEY,
    class_id INT,
    subject_id INT,
    exam_date DATETIME,
    FOREIGN KEY (class_id) REFERENCES Classrooms(classroom_id),
    FOREIGN KEY (subject_id) REFERENCES Subjects(subject_id)
);

-- Bảng sự kiện
CREATE TABLE Events (
    id INT AUTO_INCREMENT PRIMARY KEY,
    event_name VARCHAR(100),
    event_date DATETIME,
    location VARCHAR(255)
);

-- Bảng ký túc xá
CREATE TABLE Dormitories (
    dorm_id INT AUTO_INCREMENT PRIMARY KEY,
    room_number VARCHAR(10),
    capacity INT
);

CREATE TABLE StudentDormitory (
    student_id INT PRIMARY KEY,
    dorm_id INT,
    FOREIGN KEY (student_id) REFERENCES Students(student_id),
    FOREIGN KEY (dorm_id) REFERENCES Dormitories(dorm_id)
);

-- Bảng hỗ trợ sinh viên
CREATE TABLE SupportRequests (
    id INT AUTO_INCREMENT PRIMARY KEY,
    student_id INT,
    message TEXT,
    status ENUM('Pending', 'Resolved'),
    FOREIGN KEY (student_id) REFERENCES Students(student_id)
);
-- Chèn dữ liệu vào bảng Students
INSERT INTO Students (name, dob, email, phone) VALUES
('Nguyen Van A', '2002-05-10', 'a.nguyen@example.com', '0987654321'),
('Tran Thi B', '2003-08-15', 'b.tran@example.com', '0976543210'),
('Le Van C', '2001-12-20', 'c.le@example.com', '0965432109');

-- Chèn dữ liệu vào bảng Teachers
INSERT INTO Teachers (name, email, phone) VALUES
('Dr. Hoang Minh', 'hoang.minh@example.com', '0912345678'),
('Ms. Nguyen Thu', 'nguyen.thu@example.com', '0923456789');

-- Chèn dữ liệu vào bảng Subjects
INSERT INTO Subjects (name, description) VALUES
('Mathematics', 'Basic and advanced math concepts'),
('Physics', 'Introduction to physics'),
('Computer Science', 'Programming and algorithms');

-- Chèn dữ liệu vào bảng Classrooms
INSERT INTO Classrooms (name, capacity) VALUES
('Room 101', 50),
('Room 102', 40),
('Lab 201', 30);

-- Chèn dữ liệu vào bảng Registrations
INSERT INTO Registrations (student_id, subject_id) VALUES
(1, 1), (1, 2), (2, 1), (3, 3);

-- Chèn dữ liệu vào bảng Grades
INSERT INTO Grades (student_id, subject_id, grade) VALUES
(1, 1, 8.5), (1, 2, 7.0), (2, 1, 9.0), (3, 3, 8.0);

-- Chèn dữ liệu vào bảng Schedules
INSERT INTO Schedules (classroom_id, subject_id, teacher_id, schedule_time) VALUES
(1, 1, 1, '2025-04-01 08:00:00'),
(2, 2, 2, '2025-04-02 10:00:00');

-- Chèn dữ liệu vào bảng Users
INSERT INTO Users (username, password) VALUES
('admin', 'admin123'),
('student1', 'pass123'),
('teacher1', 'pass456');

-- Chèn dữ liệu vào bảng Tuition
INSERT INTO Tuition (student_id, amount) VALUES
(1, 5000.00), (2, 4800.00), (3, 5200.00);

-- Chèn dữ liệu vào bảng Library
INSERT INTO Library (book_name, author) VALUES
('Data Structures', 'Mark Allen'),
('Physics Principles', 'John Doe');

-- Chèn dữ liệu vào bảng BorrowedBooks
INSERT INTO BorrowedBooks (student_id, book_id, borrow_date, return_date) VALUES
(1, 1, '2025-03-01', '2025-03-15'),
(2, 2, '2025-03-05', '2025-03-20');

-- Chèn dữ liệu vào bảng Exams
INSERT INTO Exams (class_id, subject_id, exam_date) VALUES
(1, 1, '2025-06-10 09:00:00'),
(2, 2, '2025-06-15 14:00:00');

-- Chèn dữ liệu vào bảng Events
INSERT INTO Events (event_name, event_date, location) VALUES
('Tech Fair', '2025-05-01 10:00:00', 'Main Hall'),
('Math Olympiad', '2025-05-10 09:00:00', 'Auditorium');

-- Chèn dữ liệu vào bảng Dormitories
INSERT INTO Dormitories (room_number, capacity) VALUES
('D101', 4),
('D102', 3);

-- Chèn dữ liệu vào bảng StudentDormitory
INSERT INTO StudentDormitory (student_id, dorm_id) VALUES
(1, 1), (2, 2);

-- Chèn dữ liệu vào bảng SupportRequests
INSERT INTO SupportRequests (student_id, message, status) VALUES
(1, 'Need help with tuition payment', 'Pending'),
(2, 'Lost student ID card', 'Resolved');

