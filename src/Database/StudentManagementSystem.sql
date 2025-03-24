-- Xóa cơ sở dữ liệu nếu đã tồn tại
DROP DATABASE IF EXISTS StudentManagementSystem;

-- Tạo cơ sở dữ liệu
CREATE DATABASE StudentManagementSystem;
USE StudentManagementSystem;

-- Tạo bảng giảng viên (không có khóa ngoại)
CREATE TABLE teachers (
    teacher_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    phone VARCHAR(15)
);

-- Tạo bảng lớp học (có khóa ngoại đến teachers)
CREATE TABLE classes (
    class_id INT PRIMARY KEY AUTO_INCREMENT,
    class_name VARCHAR(50) NOT NULL,
    teacher_id INT,
    FOREIGN KEY (teacher_id) REFERENCES teachers(teacher_id) ON DELETE SET NULL
);

-- Tạo bảng sinh viên (có khóa ngoại đến classes)
CREATE TABLE students (
    student_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    dob DATE,
    email VARCHAR(100) UNIQUE NOT NULL,
    phone VARCHAR(15),
    class_id INT,
    FOREIGN KEY (class_id) REFERENCES classes(class_id) ON DELETE SET NULL
);

-- Tạo bảng môn học (không có khóa ngoại)
CREATE TABLE courses (
    course_id INT PRIMARY KEY AUTO_INCREMENT,
    course_name VARCHAR(100) NOT NULL,
    credits INT CHECK (credits > 0)
);

 -- Tạo bảng điểm (có khóa ngoại đến students và subjects)
 CREATE TABLE scores (
    score_id INT PRIMARY KEY AUTO_INCREMENT,
    student_id INT,
    course_id INT,
    score FLOAT CHECK (score >= 0 AND score <= 10),
    FOREIGN KEY (student_id) REFERENCES students(student_id) ON DELETE CASCADE,
    FOREIGN KEY (course_id) REFERENCES courses(course_id) ON DELETE CASCADE
 );

-- Tạo bảng đăng ký môn học (có khóa ngoại đến students và subjects)
CREATE TABLE enrollments (
    enrollment_id INT PRIMARY KEY AUTO_INCREMENT,
    student_id INT,
    course_id INT,
    FOREIGN KEY (student_id) REFERENCES students(student_id) ON DELETE CASCADE,
    FOREIGN KEY (course_id) REFERENCES courses(course_id) ON DELETE CASCADE
);

 -- Tạo bảng người dùng (đăng nhập)
 CREATE TABLE users (
     user_id INT PRIMARY KEY AUTO_INCREMENT,
     username VARCHAR(50) UNIQUE NOT NULL,
     password VARCHAR(255) NOT NULL,
     role ENUM('student', 'teacher', 'admin') NOT NULL
 );
 -- Thêm dữ liệu vào bảng teachers
INSERT INTO teachers (name, email, phone) VALUES 
('Nguyễn Văn A', 'a.nguyen@example.com', '0987654321'),
('Trần Thị B', 'b.tran@example.com', '0976543210'),
('Lê Văn C', 'c.le@example.com', '0965432109');

-- Thêm dữ liệu vào bảng classes
INSERT INTO classes (class_name, teacher_id) VALUES 
('Lớp 1', 1),
('Lớp 2', 2),
('Lớp 3', 3);

-- Thêm dữ liệu vào bảng students
INSERT INTO students (name, dob, email, phone, class_id) VALUES 
('Phạm Minh D', '2002-05-10', 'd.pham@example.com', '0912345678', 1),
('Hoàng Anh E', '2001-07-15', 'e.hoang@example.com', '0923456789', 2),
('Vũ Thị F', '2003-09-20', 'f.vu@example.com', '0934567890', 3);

-- Thêm dữ liệu vào bảng courses
INSERT INTO courses (course_name, credits) VALUES 
('Toán', 3),
('Lý', 2),
('Hóa', 3),
('Văn', 2);

-- Thêm dữ liệu vào bảng scores
INSERT INTO scores (student_id, course_id, score) VALUES 
(1, 1, 8.5),
(1, 2, 7.0),
(2, 3, 9.0),
(2, 4, 6.5),
(3, 1, 5.5),
(3, 2, 7.5);

-- Thêm dữ liệu vào bảng enrollments
INSERT INTO enrollments (student_id, course_id) VALUES 
(1, 1),
(1, 2),
(2, 3),
(2, 4),
(3, 1),
(3, 2);

-- Thêm dữ liệu vào bảng users
INSERT INTO users (username, password, role) VALUES 
('student1', 'password1', 'student'),
('teacher1', 'password2', 'teacher'),
('admin1', 'password3', 'admin');
