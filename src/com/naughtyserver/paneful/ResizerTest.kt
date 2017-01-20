package com.naughtyserver.paneful

import org.junit.Assert.*
import org.junit.Test

class ResizerTest{


    @Test
   fun shouldGrowLeftPane(){
        assertEquals(Resizer.growProportion(true,true, 0.3f), 0.4f)
        assertEquals(Resizer.growProportion(true,true, 0.5f), 0.6f)
    }

    @Test
    fun shouldNotGrowLeftPane(){
        assertEquals(Resizer.growProportion(true,true, 0.7f), 0.7f)
    }

    @Test
    fun shouldNotShrinkLeftPane(){
        assertEquals(Resizer.growProportion(false,true, 0.3f), 0.3f)
    }

    @Test
    fun shouldShrinkLeftPane(){
        assertEquals(Resizer.growProportion(false,true, 0.3f), 0.3f)
        assertEquals(Resizer.growProportion(false,true, 0.5f), 0.4f)
        assertEquals(Resizer.growProportion(false,true, 0.7f), 0.6f)
    }

    @Test
   fun shouldShrinkRightPane(){
        assertEquals(Resizer.growProportion(false,false, 0.3f), 0.4f)
        assertEquals(Resizer.growProportion(false,false, 0.5f), 0.6f)
    }

    @Test
    fun shouldGrownRightPane(){
        assertEquals(Resizer.growProportion(true,false, 0.5f), 0.4f)
        assertEquals(Resizer.growProportion(true,false, 0.7f), 0.6f)
    }

    @Test
   fun shouldNotShrinkRightPane(){
        assertEquals(Resizer.growProportion(false,false, 0.7f), 0.7f)
    }

    @Test
    fun shouldNotGrownRightPane(){
        assertEquals(Resizer.growProportion(true,false, 0.3f), 0.3f)
    }
}