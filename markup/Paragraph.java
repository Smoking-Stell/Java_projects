package markup;

import java.util.List;

public class Paragraph extends AbstractThing implements ListUsed {
    public Paragraph(List<Border> store) {
        super(store);
    }

    public void toBBCode(StringBuilder result) {
        transformBBC(result, "", "");
    }


    public void toMarkdown(StringBuilder result) {
        transformMark(result, "");
    }
}