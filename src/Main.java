import View.ViewCalenda;
import View.ViewGrade;
import View.ViewRegister;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int i = Integer.parseInt(sc.nextLine());
        if(i==1){
            ViewCalenda.main(null);
        }
        else if(i==2){
            ViewGrade.main(null);
        }
        else if(i==3){
            ViewRegister.main(null);
        }
    }
}
