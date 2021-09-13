package markup;

import java.util.List;

public class OrderedList extends AbstractList {
    private List <ListItem> store;

    public OrderedList(List <ListItem> store) {
        super(store);
    }

    public void toBBCode(StringBuilder result) {
        transformBBC(result, "[list=1]", "[/list]");
    }

}