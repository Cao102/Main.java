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
                case 7 -> { return; }
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
            String name = viewSubject.inputSubjectName();
            String description = viewSubject.inputSubjectDescription();
            Subject subject = new Subject(subjectId, name, description);
            subjectDAO.update(subject);
            viewSubject.notifySubjectUpdated();
            break;
        }
    }

    private void deleteSubject() {
        while (true) {
            String subjectId = viewSubject.inputSubjectId();
            if (!subjectDAO.isSubjectExist(subjectId)) {
                viewSubject.showSubjectNotExist();
                continue;
            }
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
