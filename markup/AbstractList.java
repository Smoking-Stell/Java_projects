package markup;

import java.util.List;

public abstract class AbstractList implements ListUsed {
    private List<ListItem> store;

    // :NOTE: Доступ?
    AbstractList(List<ListItem> store) {
        this.store = store;
    }

    protected void transformBBC(StringBuilder result, String leftoutline, String rightoutline) {
        result.append(leftoutline);
        for (ListItem x : store) {
            x.toBBCode(result);
        }
        result.append(rightoutline);
    }
}