package Model;

import DAO.Convert;

public class Score {
    private int score_id;
    private final int student_id;
    private final int course_id;
    private final float score;
    private final Convert convert = new Convert();
    public Score(int score_id, int student_id, int course_id, float score){
        this.score_id = score_id;
        this.student_id = student_id;
        this.course_id = course_id;
        this.score = score;
    }
    public Score(int student_id, int course_id, float score){
        this.student_id = student_id;
        this.course_id = course_id;
        this.score = score;
    }

    public int getScore_id() {
        return score_id;
    }

    public int getCourse_id() {
        return course_id;
    }

    public int getStudent_id() {
        return student_id;
    }

    public float getScore() {
        return score;
    }
    @Override
    public String toString(){
        return String.format("║ %-10d ║ %-13s ║ %-12s ║ %-7.2f ║", score_id, convert.getNameById("students", "student_id", "name", this.student_id), convert.getNameById("courses", "course_id", "course_name", this.course_id), score);
    }
}
