package Model;

public class Calenda {
    private String id;
    private String classroom_id;
    private String subject_id;
    private String teacher_id;
    private String schedule_time;

    public Calenda(String id, String classroom_id, String subject_id, String teacher_id, String schedule_time){
        this.id = id;
        this.classroom_id = classroom_id;
        this.schedule_time = schedule_time;
        this.subject_id = subject_id;
        this.teacher_id = teacher_id;
    }

    public String getID(){
        return id;
    }

    public String getClassroomID(){
        return classroom_id;
    }

    public String getSubjectID(){
        return subject_id;
    }

    public String getTeacherID(){
        return teacher_id;
    }

    public String getScheduleTime(){
        return schedule_time;
    }
}

