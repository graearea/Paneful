package com.naughtyserver.paneful

import com.naughtyserver.paneful.Action.GROW
import com.naughtyserver.paneful.Action.SHRINK
import com.naughtyserver.paneful.Window.LEFT_PANE
import com.naughtyserver.paneful.Window.RIGHT_PANE
import org.junit.Assert.*
import org.junit.Test

class ResizerTest{


    @Test
   fun shouldGrowLeftPane(){
        assertEquals(Resizer.movePane(GROW, LEFT_PANE, 0.3f), 0.4f)
        assertEquals(Resizer.movePane(GROW, LEFT_PANE, 0.5f), 0.6f)
    }

    @Test
    fun shouldNotGrowLeftPane(){
        assertEquals(Resizer.movePane(GROW, LEFT_PANE, 0.7f), 0.7f)
    }

    @Test
    fun shouldNotShrinkLeftPane(){
        assertEquals(Resizer.movePane(SHRINK, LEFT_PANE, 0.3f), 0.3f)
    }

    @Test
    fun shouldShrinkLeftPane(){
        assertEquals(Resizer.movePane(SHRINK, LEFT_PANE, 0.3f), 0.3f)
        assertEquals(Resizer.movePane(SHRINK, LEFT_PANE, 0.5f), 0.4f)
        assertEquals(Resizer.movePane(SHRINK, LEFT_PANE, 0.7f), 0.6f)
    }

    @Test
   fun shouldShrinkRightPane(){
        assertEquals(Resizer.movePane(SHRINK,RIGHT_PANE, 0.3f), 0.4f)
        assertEquals(Resizer.movePane(SHRINK,RIGHT_PANE, 0.5f), 0.6f)
    }

    @Test
    fun shouldGrownRightPane(){
        assertEquals(Resizer.movePane(GROW,RIGHT_PANE, 0.5f), 0.4f)
        assertEquals(Resizer.movePane(GROW,RIGHT_PANE, 0.7f), 0.6f)
    }

    @Test
   fun shouldNotShrinkRightPane(){
        assertEquals(Resizer.movePane(SHRINK,RIGHT_PANE, 0.7f), 0.7f)
    }

    @Test
    fun shouldNotGrownRightPane(){
        assertEquals(Resizer.movePane(GROW,RIGHT_PANE, 0.3f), 0.3f)
    }
}