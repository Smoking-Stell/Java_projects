package markup;

import java.util.List;

public class Emphasis extends AbstractThing implements Border {
    private StringBuilder result;

    public Emphasis(List<Border> store) {
        super(store);
    }

    @Override
    public void toBBCode(StringBuilder result) {
        transformBBC(result, "[i]", "[/i]");
    }

    @Override
    public void toMarkdown(StringBuilder result) {
        transformMark(result, "*");
    }

}