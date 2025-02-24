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
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.ilyaushenin.flexdrag.R
import com.ilyaushenin.flexdrag.data.ItemsDropList
import com.ilyaushenin.flexdrag.data.move
import com.ilyaushenin.flexdrag.horizontal.DragDropRow
import com.ilyaushenin.flexdrag.ui.textStyles.textStylesMainScreen
import com.ilyaushenin.flexdrag.ui.theme.FlexDragComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FlexDragComposeTheme {
                Greeting()
            }
        }
    }
}

@Composable
fun Greeting() {
    val listItems = remember {
        mutableStateListOf(
            ItemsDropList("Favorite Attractiveness", R.drawable.favorite),
            ItemsDropList("Tram", R.drawable.tram),
            ItemsDropList("MacDonald", R.drawable.mac),
            ItemsDropList("Plane Mode", R.drawable.airplanemode),
            ItemsDropList("Code", R.drawable.code),
            ItemsDropList("QR Code", R.drawable.qr_code),
            ItemsDropList("Magic", R.drawable.magic),
            ItemsDropList("Electric Car", R.drawable.electric_car),
            ItemsDropList("Fire Place", R.drawable.fireplace),
            ItemsDropList("Snow Mobile", R.drawable.snowmobile),
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
    ) {
        DragDropRow(
            modifier = Modifier.wrapContentSize(),
            items = listItems,
            colorBox = Color(0xFFECECEC),
            textColor = Color(0xFF000748),
            textStyle = textStylesMainScreen(),
            onMove = { fromIndex, toIndex ->
                listItems.move(fromIndex, toIndex)
            }
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

/**
 * For using Vertical scroll
 *     DragDropList(
 *       items = ReorderItem,
 *       onMove = { fromIndex, toIndex -> ReorderItem.move(fromIndex, toIndex) }
 *     )
 */