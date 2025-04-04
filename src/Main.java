import java.util.Scanner;
public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Xin moi nhap lua chon : ");
        System.out.println("Them diem     : 1");
        System.out.println("Sua diem      : 2");
        System.out.println("Xoa diem      : 3");
        System.out.println("Hien thi diem : 4");
        int t = Integer.parseInt(sc.nextLine());
        if(t==1){
            QL_Diem_Sv ql = new QL_Diem_Sv();
            ql.themDiem(1,3,10);
        }
        else if(t==2){
            //suaDiem()
        }
        else if(t==3){
            //xoaDiem()
        }
        else if(t==4){
            //hienThiDiem()
        }
        else{
            System.out.println("Xin moi nhap lai diem");
        }
    }
}