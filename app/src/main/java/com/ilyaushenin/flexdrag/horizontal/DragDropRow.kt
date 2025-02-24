package com.ilyaushenin.flexdrag.horizontal

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import kotlinx.coroutines.launch

@Composable
fun DragDropRow(
    items: List<String>,
    onMove: (Int, Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val dragDropListState = rememberDragDropRowState(onMove = onMove)

    LazyRow(
        modifier = modifier
            .longPressChecker(dragDropListState = dragDropListState)
            .wrapContentSize()
            .padding(10.dp),
        state = dragDropListState.lazyListState
    ) {
        itemsIndexed(items) { index, item ->
            val animatedTranslationX by animateFloatAsState(
                targetValue = if (index == dragDropListState.currentIndexOfDraggedItem) {
                    dragDropListState.elementDisplacement ?: 0f
                } else {
                    0f
                }, label = "change box animation"
            )
            val isDragged = index == dragDropListState.currentIndexOfDraggedItem
            Column(
                modifier = Modifier
                    .zIndex(if (isDragged) 1f else 0f)
                    .graphicsLayer {
                        translationX = animatedTranslationX
                    }
                    .background(Color.White, shape = RoundedCornerShape(8.dp))
                    .padding(20.dp)
            ) {
                Text(
                    text = item,
                    fontSize = 16.sp,
                    fontFamily = FontFamily.Serif
                )
            }
            Spacer(modifier = Modifier.width(10.dp))
        }
    }
}

@Composable
fun Modifier.longPressChecker(
    dragDropListState: DragDropRowState,
): Modifier {
    val scope = rememberCoroutineScope()
    return this.then(
        pointerInput(Unit) {
            detectDragGesturesAfterLongPress(
                onDrag = { change, offset ->
                    change.consume()
                    dragDropListState.onDrag(offset = offset)

                    val overScroll = dragDropListState.checkForOverScroll()
                    if (overScroll != 0f) {
                        scope.launch {
                            dragDropListState.lazyListState.scrollBy(overScroll)
                        }
                    }
                },
                onDragStart = { offset -> dragDropListState.onDragStart(offset) },
                onDragEnd = { dragDropListState.onDragInterrupted() },
                onDragCancel = { dragDropListState.onDragInterrupted() }
            )
        })
}