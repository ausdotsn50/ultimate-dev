package layout.constants;

public class ChoicesButton extends CustomButton{
    private final String rawText;

    public ChoicesButton(String text, int arcWidth, int arcHeight) {
        super("<html><body style='font-size: 11px;'><pre>" + text + "</pre></body></html>", arcWidth, arcHeight);
        rawText = text;
    }

    public String getUnformattedText() {
        return rawText;
    }
}
