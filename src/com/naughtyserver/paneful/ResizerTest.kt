package com.naughtyserver.paneful

import com.naughtyserver.paneful.Action.GROW
import com.naughtyserver.paneful.Action.SHRINK
import com.naughtyserver.paneful.Window.LEFT_PANE
import com.naughtyserver.paneful.Window.RIGHT_PANE
import org.junit.Assert.*
import org.junit.Test

class ResizerTest{

    private val minSize = 0.3f
    private val maxSize = 0.7f

    @Test
   fun shouldGrowLeftPane(){
        assertEquals(Resizer.movePane(GROW, LEFT_PANE, minSize), 0.4f)
        assertEquals(Resizer.movePane(GROW, LEFT_PANE, 0.5f), 0.6f)
    }

    @Test
    fun shouldShrinkLeftPane(){
        assertEquals(Resizer.movePane(SHRINK, LEFT_PANE, 0.5f), 0.4f)
        assertEquals(Resizer.movePane(SHRINK, LEFT_PANE, maxSize), 0.6f)
    }

    @Test
    fun shouldGrowRightPane(){
        assertEquals(Resizer.movePane(GROW,RIGHT_PANE, 0.5f), 0.4f)
        assertEquals(Resizer.movePane(GROW,RIGHT_PANE, maxSize), 0.6f)
    }

    @Test
   fun shouldShrinkRightPane(){
        assertEquals(Resizer.movePane(SHRINK,RIGHT_PANE, minSize), 0.4f)
        assertEquals(Resizer.movePane(SHRINK,RIGHT_PANE, 0.5f), 0.6f)
    }

    @Test
    fun shouldNotGrowLeftPane(){
        assertEquals(Resizer.movePane(GROW, LEFT_PANE, maxSize), maxSize)
    }

    @Test
    fun shouldNotShrinkLeftPane(){
        assertEquals(Resizer.movePane(SHRINK, LEFT_PANE, minSize), minSize)
    }

    @Test
   fun shouldNotShrinkRightPane(){
        assertEquals(Resizer.movePane(SHRINK,RIGHT_PANE, maxSize), maxSize)
    }

    @Test
    fun shouldNotGrownRightPane(){
        assertEquals(Resizer.movePane(GROW,RIGHT_PANE, minSize), minSize)
    }
}