-- Xóa cơ sở dữ liệu nếu đã tồn tại
DROP DATABASE IF EXISTS StudentManagementSystem;


-- Tạo cơ sở dữ liệu
CREATE DATABASE StudentManagementSystem;
USE StudentManagementSystem;


-- Bảng sinh viên
CREATE TABLE Students (
   student_id VARCHAR(10) PRIMARY KEY,
   name VARCHAR(100) NOT NULL,
   dob DATE NOT NULL,
   gender ENUM('Male', 'Female', 'Other') NOT NULL,
   email VARCHAR(100) UNIQUE NOT NULL,
   phone VARCHAR(15) NOT NULL,
   address VARCHAR(255)
);


-- Bảng giảng viên
CREATE TABLE Teachers (
   teacher_id VARCHAR(10) PRIMARY KEY,
   name VARCHAR(100) NOT NULL,
   email VARCHAR(100) UNIQUE NOT NULL,
   phone VARCHAR(15),
   address VARCHAR(255),
   years_of_experience INT DEFAULT 0,
   base_salary DECIMAL(10,2) DEFAULT 5000,
   salary DECIMAL(10,2) GENERATED ALWAYS AS (base_salary + (years_of_experience DIV 5) * 5000) STORED
);


-- Bảng môn học
CREATE TABLE Subjects (
   subject_id VARCHAR(10) PRIMARY KEY,
   name VARCHAR(100),
   description TEXT
);


-- Bảng lớp học
CREATE TABLE Classrooms (
   classroom_id VARCHAR(10) PRIMARY KEY,
   name VARCHAR(50),
   capacity INT
);


-- Bảng đăng ký môn học
CREATE TABLE Registrations (
   student_id VARCHAR(10),
   subject_id VARCHAR(10),
   PRIMARY KEY (student_id, subject_id),
   FOREIGN KEY (student_id) REFERENCES Students(student_id),
   FOREIGN KEY (subject_id) REFERENCES Subjects(subject_id)
);


-- Bảng điểm số
CREATE TABLE Grades (
   student_id VARCHAR(10),
   subject_id VARCHAR(10),
   grade DOUBLE,
   PRIMARY KEY (student_id, subject_id),
   FOREIGN KEY (student_id) REFERENCES Students(student_id),
   FOREIGN KEY (subject_id) REFERENCES Subjects(subject_id)
);


-- Bảng lịch học
CREATE TABLE Schedules (
   id INT AUTO_INCREMENT PRIMARY KEY,
   classroom_id VARCHAR(10),
   subject_id VARCHAR(10),
   teacher_id VARCHAR(10),
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
   student_id VARCHAR(10) PRIMARY KEY,
   amount DOUBLE,
   FOREIGN KEY (student_id) REFERENCES Students(student_id)
);


-- Bảng thư viện
CREATE TABLE Library (
   book_id VARCHAR(10) PRIMARY KEY,
   book_name VARCHAR(100),
   author VARCHAR(100),
   quantity INT
);


-- Bảng mượn sách
CREATE TABLE BorrowedBook (
   student_id VARCHAR(10),
   book_id VARCHAR(10),
   borrow_date DATE NOT NULL,
   return_date DATE,
   PRIMARY KEY (student_id, book_id),
   FOREIGN KEY (student_id) REFERENCES Students(student_id),
   FOREIGN KEY (book_id) REFERENCES Library(book_id)
);

-- Bảng kỳ thi
CREATE TABLE Exams (
   id INT AUTO_INCREMENT PRIMARY KEY,
   class_id VARCHAR(10),
   subject_id VARCHAR(10),
   exam_date DATETIME,
   FOREIGN KEY (class_id) REFERENCES Classrooms(classroom_id),
   FOREIGN KEY (subject_id) REFERENCES Subjects(subject_id)
);


-- Bảng sự kiện
CREATE TABLE Events (
   event_id  VARCHAR(10) PRIMARY KEY,
   event_name VARCHAR(100),
   event_date DATETIME,
   location VARCHAR(255)
);


-- Bảng ký túc xá
CREATE TABLE Dormitories (
   dorm_id VARCHAR(10) PRIMARY KEY,
   room_number VARCHAR(10),
   capacity INT
);


CREATE TABLE StudentDormitory (
   student_id VARCHAR(10) PRIMARY KEY,
   dorm_id VARCHAR(10),
   FOREIGN KEY (student_id) REFERENCES Students(student_id),
   FOREIGN KEY (dorm_id) REFERENCES Dormitories(dorm_id)
);


-- Bảng hỗ trợ sinh viên
CREATE TABLE SupportRequests (
   id INT AUTO_INCREMENT PRIMARY KEY,
   student_id VARCHAR(10),
   message TEXT,
   status ENUM('Pending', 'Resolved'),
   FOREIGN KEY (student_id) REFERENCES Students(student_id)
);


-- Dữ liệu mẫu


-- Students
INSERT INTO Students (student_id, name, dob, gender, email, phone, address) VALUES
('S001', 'Nguyen Van A', '2002-05-10', 'Male', 'a.nguyen@example.com', '0987654321', 'Hanoi'),
('S002', 'Tran Thi B', '2003-08-15', 'Female', 'b.tran@example.com', '0976543210', 'Ho Chi Minh City'),
('S003', 'Le Van C', '2001-12-20', 'Male', 'c.le@example.com', '0965432109', 'Da Nang'),
('S004', 'Pham Thi D', '2000-07-25', 'Female', 'd.pham@example.com', '0954321098', 'Hai Phong'),
('S005', 'Do Van E', '2004-03-18', 'Male', 'e.do@example.com', '0943210987', 'Can Tho'),
('S006', 'Hoang Minh F', '2002-11-30', 'Male', 'f.hoang@example.com', '0932109876', 'Hue'),
('S007', 'Bui Thi G', '2003-09-05', 'Female', 'g.bui@example.com', '0921098765', 'Vung Tau'),
('S008', 'Nguyen Van H', '2001-06-12', 'Male', 'h.nguyen@example.com', '0910987654', 'Nha Trang'),
('S009', 'Tran Van I', '2002-10-02', 'Male', 'i.tran@example.com', '0901122334', 'Quang Ninh'),
('S010', 'Le Thi J', '2003-02-14', 'Female', 'j.le@example.com', '0902233445', 'Nam Dinh'),
('S011', 'Pham Van K', '2001-03-09', 'Male', 'k.pham@example.com', '0903344556', 'Bac Giang'),
('S012', 'Do Thi L', '2002-06-25', 'Female', 'l.do@example.com', '0904455667', 'Nghe An'),
('S013', 'Nguyen Van M', '2004-01-19', 'Male', 'm.nguyen@example.com', '0905566778', 'Thanh Hoa');


-- Teachers
INSERT INTO Teachers (teacher_id, name, email, phone, address, years_of_experience, base_salary) VALUES
('T001', 'Dr. Hoang Minh', 'hoang.minh@example.com', '0912345678', 'Hanoi', 10, 5000),
('T002', 'Ms. Nguyen Thu', 'nguyen.thu@example.com', '0923456789', 'Ho Chi Minh City', 7, 5000),
('T003', 'Mr. Tran Van An', 'tran.an@example.com', '0934567890', 'Da Nang', 15, 6000),
('T004', 'Mrs. Le Hai Yen', 'le.haiyen@example.com', '0945678901', 'Hai Phong', 3, 5500),
('T005', 'Mr. Pham Quoc Bao', 'bao.pham@example.com', '0901234567', 'Can Tho', 12, 6000),
('T006', 'Mrs. Dang Thi Lan', 'lan.dang@example.com', '0902345678', 'Lang Son', 8, 5500);


-- Subjects
INSERT INTO Subjects (subject_id, name, description) VALUES
('SUB001', 'Mathematics', 'Basic and advanced math concepts'),
('SUB002', 'Physics', 'Introduction to physics'),
('SUB003', 'Computer Science', 'Programming and algorithms'),
('SUB004', 'English', 'Fundamental English and Communication'),
('SUB005', 'Chemistry', 'Basic Chemistry Concepts and Lab Practice');


-- Classrooms
INSERT INTO Classrooms (classroom_id, name, capacity) VALUES
('C001', 'Room 101', 50),
('C002', 'Room 102', 40),
('C003', 'Lab 201', 30),
('C004', 'Room 103', 35),
('C005', 'Lab 202', 25);


-- Registrations
INSERT INTO Registrations (student_id, subject_id) VALUES
('S001', 'SUB001'),
('S001', 'SUB002'),
('S002', 'SUB001'),
('S003', 'SUB003'),
('S004', 'SUB001'),
('S005', 'SUB002'),
('S006', 'SUB003'),
('S007', 'SUB004'),
('S008', 'SUB005'),
('S009', 'SUB001'),
('S010', 'SUB002');


-- Grades
INSERT INTO Grades (student_id, subject_id, grade) VALUES
('S001', 'SUB001', 8.5),
('S001', 'SUB002', 7.0),
('S002', 'SUB001', 9.0),
('S003', 'SUB003', 8.0),
('S004', 'SUB001', 6.5),
('S005', 'SUB002', 7.5),
('S006', 'SUB003', 8.0),
('S007', 'SUB004', 9.0),
('S008', 'SUB005', 7.0),
('S009', 'SUB001', 6.0),
('S010', 'SUB002', 8.5);


-- Schedules
INSERT INTO Schedules (classroom_id, subject_id, teacher_id, schedule_time) VALUES
('C001', 'SUB001', 'T001', '2025-04-01 08:00:00'),
('C002', 'SUB002', 'T002', '2025-04-02 10:00:00'),
('C004', 'SUB004', 'T005', '2025-04-04 08:00:00'),
('C005', 'SUB005', 'T006', '2025-04-05 13:30:00');


-- Users
INSERT INTO Users (username, password) VALUES
('admin', 'admin123'),
('student1', 'pass123'),
('teacher1', 'pass456');


-- Tuition
INSERT INTO Tuition (student_id, amount) VALUES
('S001', 5000.00),
('S002', 4800.00),
('S003', 5200.00),
('S004', 5100.00),
('S005', 4700.00),
('S006', 5000.00),
('S007', 4900.00),
('S008', 5300.00),
('S009', 4950.00),
('S010', 5050.00);


-- Library
INSERT INTO Library (book_id, book_name, author,quantity) VALUES
('B001', 'Data Structures', 'Mark Allen',1),
('B002', 'Physics Principles', 'John Doe',1),
('B003', 'English for Beginners', 'Anna Smith',1),
('B004', 'Organic Chemistry Basics', 'Thomas Green',1);


-- BorrowedBooks
INSERT INTO BorrowedBook (student_id, book_id, borrow_date, return_date) VALUES
('S001', 'B001', '2025-04-01', '2025-04-07'),
('S002', 'B002', '2025-04-02', NULL),  -- chưa trả
('S003', 'B003', '2025-04-03', '2025-04-05'),
('S004', 'B004', '2025-04-04', NULL); -- chưa trả

-- Exams
INSERT INTO Exams (class_id, subject_id, exam_date) VALUES
('C001', 'SUB001', '2025-06-10 09:00:00'),
('C002', 'SUB002', '2025-06-15 14:00:00'),
('C004', 'SUB004', '2025-06-20 09:00:00'),
('C005', 'SUB005', '2025-06-25 14:00:00');


-- Events
INSERT INTO Events (event_id,event_name, event_date, location) VALUES
('E001','Tech Fair', '2025-05-01 10:00:00', 'Main Hall'),
('E002','Math Olympiad', '2025-05-10 09:00:00', 'Auditorium'),
('E003','English Speaking Contest', '2025-05-15 09:00:00', 'Conference Room A'),
('E004','Chemistry Lab Tour', '2025-05-20 10:00:00', 'Lab 202');


-- Dormitories
INSERT INTO Dormitories (dorm_id, room_number, capacity) VALUES
('D001', 'D101', 4),
('D002', 'D102', 3),
('D003', 'D103', 4),
('D004', 'D104', 2);


-- StudentDormitory
INSERT INTO StudentDormitory (student_id, dorm_id) VALUES
('S001', 'D001'),
('S002', 'D002'),
('S003', 'D003'),
('S004', 'D004');


-- SupportRequests
INSERT INTO SupportRequests (student_id, message, status) VALUES
('S001', 'Need help with tuition payment', 'Pending'),
('S002', 'Lost student ID card', 'Resolved'),
('S003', 'Request for extra class on physics', 'Pending'),
('S004', 'Medical leave approval needed', 'Resolved');

SELECT * from Tuition;
