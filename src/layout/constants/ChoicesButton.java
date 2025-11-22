package layout.constants;

import layout.design.Design;

public class ChoicesButton extends CustomButton{
    public ChoicesButton(String text, int arcWidth, int arcHeight) {
        super("<html><body style='font-size: 14px;'><pre>" + text + "</pre></body></html>", arcWidth, arcHeight);

    }
}
