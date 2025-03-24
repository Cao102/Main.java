package View;

import java.util.List;

public interface ObjectView<T> {
    int menuObject();
    T addObject();
    void getAllObject(List<T> tList);
    T updateObject();
    int deleteObject();
}
