package Controller;

import DAO.DAO;
import DAO.ScoreDAO;
import Model.Score;
import View.ObjectView;
import View.ViewScore;

import java.util.List;

public class ScoreController {
    private final ObjectView<Score> viewObject = new ViewScore();
    private final DAO<Score> objectDAO = new ScoreDAO();

    private final MainController mainController;
    public ScoreController(MainController mainController){
        this.mainController = mainController;
    }
    public void start(){
        while (true) {
            int input = viewObject.menuObject();
            switch (input) {
                case 1:
                    Score add = viewObject.addObject();
                    objectDAO.add(add);
                    break;
                case 2:
                    List<Score> objectList = objectDAO.getAll();
                    viewObject.getAllObject(objectList);
                    break;
                case 3:
                    Score updateObject = viewObject.updateObject();
                    objectDAO.update(updateObject);
                    break;
                case 4:
                    int id = viewObject.deleteObject();
                    objectDAO.delete(id);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Vui lòng nhập lại");
            }
        }
    }
}
