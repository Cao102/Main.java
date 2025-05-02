package Controller;

import DAO.SubjectDAO;
import Model.Subject;
import View.ViewSubject;

import java.util.List;

public class SubjectController {
    private final SubjectDAO subjectDAO;
    private final ViewSubject viewSubject;

    public SubjectController() {
        this.subjectDAO = new SubjectDAO();
        this.viewSubject = new ViewSubject();
    }

    public void startSubject() {
        while (true) {
            int choice = viewSubject.menuSubject();
            switch (choice) {
                case 1 -> addSubject();
                case 2 -> updateSubject();
                case 3 -> deleteSubject();
                case 4 -> displaySubjects();
                case 5 -> displaySubjectById();
                case 6 -> displaySubjectsByTeacher();
                case 0 -> {
                    return;
                }
                default -> viewSubject.errorChoose();
            }
        }
    }

    private void addSubject() {
        while (true) {
            String subjectId = viewSubject.inputSubjectId();
            if (subjectDAO.isSubjectExist(subjectId)) {
                boolean update = viewSubject.confirmUpdateSubject();
                if (update) {
                    String name = viewSubject.inputSubjectName();
                    String description = viewSubject.inputSubjectDescription();
                    Subject subject = new Subject(subjectId, name, description);
                    subjectDAO.update(subject);
                    viewSubject.notifySubjectUpdated();
                } else {
                    continue;
                }
            } else {
                String name = viewSubject.inputSubjectName();
                String description = viewSubject.inputSubjectDescription();
                Subject subject = new Subject(subjectId, name, description);
                subjectDAO.add(subject);
                viewSubject.notifySubjectAdded();
            }
            break;
        }
    }

    private void updateSubject() {
        while (true) {
            String subjectId = viewSubject.inputSubjectId();
            if (!subjectDAO.isSubjectExist(subjectId)) {
                viewSubject.showSubjectNotExist();
                continue;
            }

            Subject oldSubject = subjectDAO.getById(subjectId);
            if (oldSubject == null) {
                viewSubject.showSubjectNotExist();
                continue;
            }

            // Hiển thị gợi ý nhập lại (nhấn Enter để giữ cũ)
            System.out.println("Nhấn Enter để giữ lại thông tin cũ.");

            String name = viewSubject.inputSubjectNameWithDefault(oldSubject.getName());
            String description = viewSubject.inputSubjectDescriptionWithDefault(oldSubject.getDescription());

            Subject updatedSubject = new Subject(subjectId, name, description);
            subjectDAO.update(updatedSubject);
            viewSubject.notifySubjectUpdated();
            break;
        }
    }

    private void deleteSubject() {
        while (true) {
            String subjectId = viewSubject.inputSubjectId();

            if (!subjectDAO.isSubjectExist(subjectId)) {
                viewSubject.showSubjectNotExist();
                break;
            }

            // Nếu môn học đang bị ràng buộc, không cho xóa
            if (subjectDAO.hasSubjectDependency(subjectId)) {
                System.out.println("Không thể xoá môn học này vì đang được sử dụng trong bảng kỳ thi (exams).");
                System.out.println("\nDanh sách các môn học có thể xoá:");
                List<Subject> deletableSubjects = subjectDAO.getDeletableSubjects();
                if (deletableSubjects.isEmpty()) {
                    System.out.println("Hiện không có môn học nào có thể xoá.");
                    break;
                } else {
                    viewSubject.displaySubjects(deletableSubjects);
                    System.out.println("Vui lòng chọn một môn học khác để xoá.\n");
                    continue; // quay lại vòng lặp yêu cầu nhập lại
                }
            }

            // Nếu không có phụ thuộc, tiến hành xoá
            subjectDAO.delete(subjectId);
            viewSubject.notifySubjectDeleted();
            break;
        }
    }


    private void displaySubjects() {
        List<Subject> subjects = subjectDAO.getAll();
        viewSubject.displaySubjects(subjects);
    }

    private void displaySubjectById() {
        String subjectId = viewSubject.inputSubjectId();
        Subject subject = subjectDAO.getById(subjectId);
        if (subject == null) {
            viewSubject.showSubjectNotExist();
        } else {
            viewSubject.displaySubject(subject);
        }
    }

    private void displaySubjectsByTeacher() {
        String teacherId = viewSubject.inputTeacherId();
        List<Subject> subjects = subjectDAO.getByTeacherId(teacherId);
        if (subjects.isEmpty()) {
            viewSubject.showNoSubjectsForTeacher();
        } else {
            viewSubject.displaySubjects(subjects);
        }
    }
}