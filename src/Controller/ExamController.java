package Controller;

import DAO.ExamDAO;
import Model.Exam;

import java.time.LocalDateTime;
import java.util.List;

public class ExamController {
    private final ExamDAO dao = new ExamDAO();

    public boolean scheduleExam(Exam exam) {
        return dao.scheduleExam(exam);
    }

    public boolean updateExam(Exam exam) {
        return dao.updateExam(exam);
    }

    public boolean deleteExam(int id) {
        return dao.deleteExam(id);
    }

    public List<Exam> getExamsByClass(String classId) {
        return dao.getExamsByClass(classId);
    }

    public String getExamResults(int examId) {
        return dao.getExamResults(examId);
    }

    // ✅ Hàm kiểm tra lịch thi đã tồn tại chưa
    public boolean isExamScheduled(String classId, String subjectId, LocalDateTime examDate) {
        return dao.isExamScheduled(classId, subjectId, examDate);
    }
}
