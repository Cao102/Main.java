package Controller;

import View.*;

public class MainController {
    private static final View view = new View();
    private final TuitionController tuitioncontroller = new TuitionController();
    public void start(){
        while (true) {
            int input = view.menuView();
            switch (input) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    tuitioncontroller.startTuition();
                    break;
                case 7:
                    System.out.println("Thoát chương trình...");
                    return;
                default:
                    System.out.println("Vui lòng nhập lại lựa chọn");
                    break;
            }
        }
    }
}