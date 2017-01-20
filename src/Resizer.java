import com.intellij.openapi.ui.Splitter;

public class Resizer {
    static float growProportion(Splitter splitter, boolean makeLeftWindowBigger) {
        float rightSplit = 0.8f;
        float leftSplit = 0.2f;

        float currentSplit = splitter.getProportion();

        if (makeLeftWindowBigger && currentSplit >= rightSplit) {
            return rightSplit;
        } else if (!makeLeftWindowBigger && currentSplit <= leftSplit) {
            return leftSplit;
        } else {
            return (currentSplit + (makeLeftWindowBigger ? 0.1f : -0.1f));
        }

    }
}
