package markup;

import java.util.List;

public class Strikeout extends AbstractThing implements Border {
    private StringBuilder result;

    public Strikeout(List<Border> store) {
        super(store);
    }

    @Override
    public void toBBCode(StringBuilder result) {
        transformBBC(result, "[s]", "[/s]");
    }

    public void toMarkdown(StringBuilder result) {
        transformMark(result, "~");
    }

}