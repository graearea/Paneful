package com.naughtyserver.paneful

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.fileEditor.impl.FileEditorManagerImpl
import com.intellij.openapi.ui.Splitter
import com.intellij.openapi.wm.ToolWindowManager
import com.intellij.openapi.wm.impl.InternalDecorator
import com.intellij.openapi.wm.impl.ToolWindowImpl
import com.intellij.openapi.wm.impl.ToolWindowManagerImpl
import com.naughtyserver.paneful.Action.CENTER
import com.naughtyserver.paneful.Action.GROW
import com.naughtyserver.paneful.Action.SHRINK
import com.naughtyserver.paneful.SplitterResizer.moveHorizontalSplitter
import com.naughtyserver.paneful.SplitterResizer.resizeToolWindow
import java.awt.Container
import javax.swing.JPanel

class Entallener : ResizeVerticallyAction(GROW)
class Enshortener : ResizeVerticallyAction(SHRINK)
class Encenterer : ResizeVerticallyAction(CENTER)

class Enrightener : ResizeHorizontallyAction(GROW)
class Enleftener : ResizeHorizontallyAction(SHRINK)

abstract class ResizeVerticallyAction(action: Action) : NoDisplayAction(action) {

    override fun actionPerformed(anActionEvent: AnActionEvent) {
        val project = anActionEvent.project
        val toolWindowManager = ToolWindowManager.getInstance(project!!) as ToolWindowManagerImpl
        val toolWindow: ToolWindowImpl = toolWindowManager.getToolWindow(toolWindowManager.getLastActiveToolWindowId({
            comp -> onTheBottom(comp.parent)
        })) as ToolWindowImpl ?: return

        val maxHeight = toolWindow.component.rootPane.height
        val currentHeight = toolWindow.component.height

        toolWindow.stretchHeight(resizeToolWindow(action, maxHeight, currentHeight))
    }

    private fun onTheBottom(ting: Container): Boolean {
        return if (ting !is InternalDecorator) onTheBottom(ting.parent) else ting.y > 0
    }
}

abstract class ResizeHorizontallyAction(action: Action) : NoDisplayAction(action) {

    override fun actionPerformed(anActionEvent: AnActionEvent) {
        val project = anActionEvent.project
        val fileEditorManager = FileEditorManager.getInstance(project!!) as FileEditorManagerImpl
        val splitters = fileEditorManager.splitters
        val component = splitters.getComponent(0)

        if (component is JPanel && component.componentCount > 0) {
            val child = component.getComponent(0)
            if (child is Splitter) {
                child.proportion = moveHorizontalSplitter(action, child.proportion)
            }
        }
    }
}

enum class Action(val value: kotlin.Int) { GROW(1), SHRINK(-1), CENTER(0) }

abstract class NoDisplayAction(val action: Action) : AnAction() {
    override fun update(event: AnActionEvent) {
        val project = event.getData(CommonDataKeys.PROJECT)
        val editor = event.getData(CommonDataKeys.EDITOR)
        event.presentation.isVisible = project != null && editor != null
    }
}

