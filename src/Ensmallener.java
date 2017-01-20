import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.fileEditor.impl.EditorsSplitters;
import com.intellij.openapi.fileEditor.impl.FileEditorManagerImpl;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Splitter;

import javax.swing.*;
import java.awt.*;

public class Ensmallener extends AnAction {

    @Override
    public void update(AnActionEvent e) {
        final Project project = e.getData(CommonDataKeys.PROJECT);
        final Editor editor = e.getData(CommonDataKeys.EDITOR);

        //Set visibility only in case of existing project and editor
        e.getPresentation().setVisible((project != null && editor != null));


    }

    @Override
    public void actionPerformed(AnActionEvent anActionEvent) {
        final Project project = anActionEvent.getProject();
        final FileEditorManagerImpl fileEditorManager = (FileEditorManagerImpl) FileEditorManager.getInstance(project);
        EditorsSplitters splitters = fileEditorManager.getSplitters();

        boolean isLeftWindow = splitters.getWindows()[0].equals(splitters.getCurrentWindow());

        final Component component = splitters.getComponent(0);

        if (component instanceof JPanel && ((JPanel) component).getComponentCount() > 0) {


            final Component child = ((JPanel) component).getComponent(0);
            if (child instanceof Splitter) {
                final Splitter splitter = (Splitter) child;

                splitter.setProportion(Resizer.growProportion(splitter, !isLeftWindow));

            }
        }
    }

}
