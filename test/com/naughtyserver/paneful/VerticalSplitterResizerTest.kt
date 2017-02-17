package com.naughtyserver.paneful

import com.naughtyserver.paneful.Action.CENTER
import com.naughtyserver.paneful.Action.GROW
import com.naughtyserver.paneful.Action.SHRINK
import com.naughtyserver.paneful.SplitterResizer.moveHorizontalSplitter
import com.naughtyserver.paneful.SplitterResizer.resizeToolWindow
import org.junit.Assert.assertEquals
import org.junit.Test

class VerticalSplitterResizerTest {

    @Test
    fun shouldGrow() {
        assertEquals(resizeToolWindow(GROW, 1000, 500), 150)
    }

    @Test
    fun shouldShrink() {
        assertEquals(resizeToolWindow(SHRINK, 1000, 500), -150)
    }

    @Test
    fun shouldNotGrow() {
        assertEquals(resizeToolWindow(GROW, 1000, 900), 0)
    }

    @Test
    fun shouldNotShrink() {
        assertEquals(resizeToolWindow(SHRINK, 1000, 100), 0)
    }
}