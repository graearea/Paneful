package com.naughtyserver.paneful

internal object SplitterResizer {
    fun moveHorizontalSplitter(action: Action, splitterProportion: Float): Float {
        val maxRight = 7
        val maxLeft = 3
        val currentSplit = (splitterProportion * 10).toInt()

        return when {
            action == Action.CENTER -> toProportionFromInt(5)
            action == Action.GROW && currentSplit >= maxRight -> toProportionFromInt(maxRight)
            action == Action.SHRINK && currentSplit <= maxLeft -> toProportionFromInt(maxLeft)
            else -> toProportionFromInt(currentSplit + action.value)
        }
    }

    private val STRETCH_AMOUNT = 150

    fun resizeToolWindow(action: Action, maxHeight: Int, current: Int): Int {
        return when {
            action == Action.GROW && maxHeight - (STRETCH_AMOUNT+50) > current -> (STRETCH_AMOUNT * action.value)
            action == Action.SHRINK && current > STRETCH_AMOUNT -> (STRETCH_AMOUNT * action.value)
            else -> 0
        }
    }

    private fun toProportionFromInt(proportion: Int) = proportion / 10f
}