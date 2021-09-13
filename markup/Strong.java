package markup;

import java.util.List;

public class Strong extends AbstractThing implements Border {
    private StringBuilder result;

    public Strong(List<Border> store) {
        super(store);
    }

    @Override
    public void toBBCode(StringBuilder result) {
        transformBBC(result, "[b]", "[/b]");
    }

    @Override
    public void toMarkdown(StringBuilder result) {
        transformMark(result, "__");
    }

}