package com.ilyaushenin.flexdrag.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.ilyaushenin.flexdrag.data.move
import com.ilyaushenin.flexdrag.horizontal.DragDropRow
import com.ilyaushenin.flexdrag.ui.theme.FlexDragComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FlexDragComposeTheme {
//                DragDropList(
//                    items = ReorderItem,
//                    onMove = { fromIndex, toIndex -> ReorderItem.move(fromIndex, toIndex) }
//                )
                Greeting()
            }
        }
    }
}

val ReorderItem = listOf(
    "Item 1",
    "Item 2",
    "Item 3",
    "Item 4",
    "Item 5",
    "Item 6",
    "Item 7",
    "Item 8",
    "Item 9",
    "Item 10",
    "Item 11",
    "Item 12",
    "Item 13",
    "Item 14",
    "Item 15",
    "Item 16",
    "Item 17",
    "Item 18",
    "Item 19",
    "Item 20"
).toMutableStateList()

@Composable
fun Greeting() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        verticalArrangement = Arrangement.Center,
    ) {
        DragDropRow(
            modifier = Modifier.wrapContentSize(),
            items = ReorderItem,
            onMove = { fromIndex, toIndex -> ReorderItem.move(fromIndex, toIndex) }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FlexDragComposeTheme {
        Greeting()
    }
}