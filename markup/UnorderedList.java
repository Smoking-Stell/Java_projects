package markup;

import java.util.List;

public class UnorderedList extends AbstractList {
    public UnorderedList(List<ListItem> store) {
        super(store);
    }

    public void toBBCode(StringBuilder result) {
        transformBBC(result, "[list]", "[/list]");
    }
}