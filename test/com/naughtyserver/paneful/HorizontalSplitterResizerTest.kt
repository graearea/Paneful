package com.naughtyserver.paneful

import com.naughtyserver.paneful.Action.CENTER
import com.naughtyserver.paneful.Action.GROW
import com.naughtyserver.paneful.Action.SHRINK
import com.naughtyserver.paneful.SplitterResizer.moveHorizontalSplitter
import org.junit.Assert.assertEquals
import org.junit.Test

class HorizontalSplitterResizerTest {

    private val minSize = 0.3f
    private val maxSize = 0.7f

    @Test
    fun shouldCentreSplitter() {
        assertEquals(moveHorizontalSplitter(CENTER, minSize), 0.5f)
    }

    @Test
    fun shouldGrow() {
        assertEquals(moveHorizontalSplitter(GROW, minSize), 0.4f)
        assertEquals(moveHorizontalSplitter(GROW, 0.5f), 0.6f)
    }

    @Test
    fun shouldShrink() {
        assertEquals(moveHorizontalSplitter(SHRINK, 0.5f), 0.4f)
        assertEquals(moveHorizontalSplitter(SHRINK, maxSize), 0.6f)
    }

    @Test
    fun shouldNotGrow() {
        assertEquals(moveHorizontalSplitter(GROW, maxSize), maxSize)
    }

    @Test
    fun shouldNotShrink() {
        assertEquals(moveHorizontalSplitter(SHRINK, minSize), minSize)
    }
}