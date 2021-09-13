package markup;

public class Text implements Border {
    private String word;

    public Text(String word) {
        this.word = word;
    }

    @Override
    public void toBBCode(StringBuilder result) {
        result.append(word);
    }

    @Override
    public void toMarkdown(StringBuilder result) {
        result.append(word);
    }
}