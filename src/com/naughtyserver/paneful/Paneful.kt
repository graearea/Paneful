package com.naughtyserver.paneful

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.fileEditor.impl.FileEditorManagerImpl
import com.intellij.openapi.ui.Splitter
import javax.swing.JPanel


abstract class ResizeAction(val grow: Boolean) : AnAction() {
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
                child.proportion = Resizer.growProportion(grow, isLeftWindow, child.proportion)

            }
        }
    }
}


class Enbiggener : ResizeAction(true)

class Ensmallener : ResizeAction(false)

internal object Resizer {
    fun growProportion(grow: Boolean, isLeftWindow: Boolean, splitterProportion: Float): Float {
        val rightSplit = 7
        val leftSplit = 3
        val currentSplit = (splitterProportion * 10).toInt()

        if (isLeftWindow) {
            if (grow && currentSplit >= rightSplit) {
                return toProportionFromInt(rightSplit)
            } else if (!grow && currentSplit <= leftSplit) {
                return toProportionFromInt(leftSplit)
            } else
                return toProportionFromInt(currentSplit + if (grow) 1 else -1)
        } else {
            if (grow && currentSplit <= leftSplit) {
                return toProportionFromInt(leftSplit)
            } else if (!grow && currentSplit >= rightSplit) {
                return toProportionFromInt(rightSplit)
            } else {
                return toProportionFromInt(currentSplit + if (grow) -1 else 1)
            }

        }
    }

    private fun toProportionFromInt(leftSplit: Int) = leftSplit / 10f
}
