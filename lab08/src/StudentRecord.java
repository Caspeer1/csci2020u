/*
  Eric CB-Lamontagne
  100700304
 */
public class StudentRecord {
    protected String SID;
    protected float assignment;
    protected float midterm;
    protected float finalExam;
    protected float finalMark;
    protected String letterGrade;

    public StudentRecord(String SID, float assignment, float midterm, float finalExam) {
        this.SID = SID;
        this.assignment = assignment;
        this.midterm = midterm;
        this.finalExam = finalExam;

        calcLetterGrade();
    }
    private void calcLetterGrade() {
        this.finalMark = ((0.2f * assignment) + (0.3f * midterm) + (0.5f * finalExam));
        if (finalMark >= 80 && finalMark <= 100) {
            this.letterGrade = "A";
        }
        else if (finalMark >= 70 && finalMark <= 79) {
            this.letterGrade = "B";
        }
        else if (finalMark >= 60 && finalMark <= 69) {
            this.letterGrade = "C";
        }
        else if (finalMark >= 50 && finalMark <= 59) {
            this.letterGrade = "D";
        }
        else if (finalMark >= 0 && finalMark <= 49) {
            this.letterGrade = "F";
        }
        else {
            this.letterGrade = "Invalid";
        }
    }
    public String getSID () {
        return this.SID;
    }
    public float getAssignment() {
        return this.assignment;
    }
    public float getMidterm() {
        return this.midterm;
    }
    public float getFinalExam() {
        return this.finalExam;
    }
    public float getFinalMark() {
        return this.finalMark;
    }
    public String getLetterGrade() {
        return this.letterGrade;
    }
}