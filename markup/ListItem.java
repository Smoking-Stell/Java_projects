package markup;

import java.util.List;

public class ListItem {
    private List<ListUsed> store;

    public ListItem(List<ListUsed> store) {
        this.store = store;
    }

    public void toBBCode(StringBuilder result) {
        result.append("[*]");
        for (ListUsed x : store) {
            x.toBBCode(result);
        }
    }
}