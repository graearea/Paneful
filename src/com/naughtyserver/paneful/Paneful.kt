package com.naughtyserver.paneful
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.fileEditor.impl.FileEditorManagerImpl
import com.intellij.openapi.ui.Splitter
import javax.swing.JPanel


abstract class ResizeAction(val grows: Boolean) : AnAction() {
    override fun update(event: AnActionEvent) {
        val project = event.getData(CommonDataKeys.PROJECT)
        val editor = event.getData(CommonDataKeys.EDITOR)

        //Set visibility only in case of existing project and editor
        event.presentation.isVisible = project != null && editor != null
    }

    override fun actionPerformed(anActionEvent: AnActionEvent) {
        val project = anActionEvent.project
        val fileEditorManager = FileEditorManager.getInstance(project!!) as FileEditorManagerImpl
        val splitters = fileEditorManager.splitters

        val isLeftWindow = splitters.windows[0] == splitters.currentWindow

        val component = splitters.getComponent(0)

        if (component is JPanel && component.componentCount > 0) {
            val child = component.getComponent(0)
            if (child is Splitter) {
                child.proportion = Resizer.growProportion(child, grows && isLeftWindow)

            }
        }
    }
}

class Enbiggener : ResizeAction(true)

class Ensmallener : ResizeAction(false)

internal object Resizer {
    fun growProportion(splitter: Splitter, makeLeftWindowBigger: Boolean): Float {
        val rightSplit = 7
        val leftSplit = 3
        val currentSplit = (splitter.proportion*10).toInt()

        if (makeLeftWindowBigger && currentSplit >= rightSplit) {
            return toProportionFromInt(rightSplit)
        } else if (!makeLeftWindowBigger && currentSplit <= leftSplit) {
            return toProportionFromInt(leftSplit)
        } else {
            return toProportionFromInt(currentSplit + if (makeLeftWindowBigger) 1 else -1)
        }

    }

    private fun toProportionFromInt(leftSplit: Int) = leftSplit / 10f
}
