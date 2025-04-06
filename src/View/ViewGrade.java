package View;

import Controller.GradeController;

import java.util.Scanner;

public class ViewGrade {
    public static void main(String[] args) {
        GradeController controller = new GradeController();
        Scanner sc = new Scanner(System.in);
        System.out.println("Thao tác điểm");
        System.out.println("Nhập điểm");
        System.out.println("Sửa điểm");
        System.out.println("Show điểm các môn của 1 sinh viên");
        System.out.println("Show điểm các sinh viên của 1 môn");
        int n = sc.nextInt();
        if(n==1){
            System.out.println("Nhập ID sinh viên : ");
            int a = sc.nextInt();
            System.out.println("Nhập mã môn sinh viên");
            int b = sc.nextInt();
            System.out.println("Nhập điểm sinh viên");
            double c = sc.nextDouble();
            controller.addGradeController(a,b,c);
        }
        else if(n==2){
            System.out.println("Nhập ID sinh viên : ");
            int a = sc.nextInt();
            System.out.println("Nhập mã môn sinh viên");
            int b = sc.nextInt();
            System.out.println("Nhập điểm sinh viên");
            double c = sc.nextDouble();
            controller.updateGradeController(a,b,c);
        }
        else if(n==3){
            System.out.println("Nhập ID sinh viên : ");
            int a = sc.nextInt();
            controller.showGradeByStudentController(a);
        }
        else if(n==4){
            System.out.println("Nhập ID môn học : ");
            int a = sc.nextInt();
            controller.showGradeBySubjectController(a);
        }
    }
}
