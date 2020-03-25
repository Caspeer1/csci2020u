/*
  Eric CB-Lamontagne
  100700304
 */
public class StudentRecord {
    private String studentID;
    private float midterm;
    private float assignment;
    private float exam;
    private float finalMark;
    private char letterGrade;

    public StudentRecord(String studentID, float assignment, float midterm, float exam){
        this.studentID = studentID;
        this.midterm = midterm;
        this.assignment = assignment;
        this.exam = exam;
        Calculate();
    }
    private void Calculate(){
        this.finalMark = (0.2f * assignment) + (0.3f * midterm) + (0.5f * exam);
        if (finalMark >= 80) letterGrade = 'A';
        else if (finalMark >= 70 && finalMark < 80) letterGrade = 'B';
        else if (finalMark >= 60 && finalMark < 70) letterGrade = 'C';
        else if (finalMark >= 50 && finalMark < 60) letterGrade = 'D';
        else letterGrade = 'F';
    }
    public String getStudentID() { return studentID; }
    public float getMidterm() { return midterm; }
    public float getAssignment() { return assignment; }
    public float getExam() { return exam; }
    public float getFinalMark() { return finalMark; }
    public char getLetterGrade() { return letterGrade; }

}