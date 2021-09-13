package markup;

import java.util.List;

public abstract class AbstractThing {
    private List<Border> store;

    AbstractThing(List<Border> store) {
        this.store = store;
    }

    protected void transformBBC(StringBuilder result, String leftoutline, String rightoutline) {
        result.append(leftoutline);
        for (Border x : store) {
            x.toBBCode(result);
        }
        result.append(rightoutline);
    }

    protected void transformMark(StringBuilder result, String outline) {
        result.append(outline);
        for (Border x : store) {
            x.toMarkdown(result);
        }
        result.append(outline);
    }
}