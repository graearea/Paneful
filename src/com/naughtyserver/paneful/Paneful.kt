package com.naughtyserver.paneful

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.fileEditor.impl.FileEditorManagerImpl
import com.intellij.openapi.ui.Splitter
import com.naughtyserver.paneful.Action.GROW
import com.naughtyserver.paneful.Action.SHRINK
import com.naughtyserver.paneful.Window.LEFT_PANE
import com.naughtyserver.paneful.Window.RIGHT_PANE
import javax.swing.JPanel


abstract class ResizeAction(val action: Action) : AnAction() {
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

        val window = if (splitters.windows[0] == splitters.currentWindow) LEFT_PANE else RIGHT_PANE


        val component = splitters.getComponent(0)

        if (component is JPanel && component.componentCount > 0) {
            val child = component.getComponent(0)
            if (child is Splitter) {
                child.proportion = Resizer.movePane(action, window, child.proportion)

            }
        }
    }
}

enum class Action(val value: kotlin.Int) { GROW(1), SHRINK(-1) }

enum class Window { LEFT_PANE, RIGHT_PANE }

class Enbiggener : ResizeAction(GROW)

class Ensmallener : ResizeAction(SHRINK)

internal object Resizer {
    fun movePane(action: Action, window: Window, splitterProportion: Float): Float {
        val rightSplit = 7
        val leftSplit = 3
        val currentSplit = (splitterProportion * 10).toInt()

        if (window==LEFT_PANE) {
            if (action == GROW && currentSplit >= rightSplit) {
                return toProportionFromInt(rightSplit)
            } else if (action == SHRINK && currentSplit <= leftSplit) {
                return toProportionFromInt(leftSplit)
            } else
                return toProportionFromInt(currentSplit + action.value)
        } else {
            if (action == GROW && currentSplit <= leftSplit) {
                return toProportionFromInt(leftSplit)
            } else if (action == SHRINK && currentSplit >= rightSplit) {
                return toProportionFromInt(rightSplit)
            } else {
                return toProportionFromInt(currentSplit + (action.value * -1))
            }

        }
    }

    private fun toProportionFromInt(proportion: Int) = proportion / 10f
}
