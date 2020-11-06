package com.example.composedemo.sample

import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.compose.foundation.Image
import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.FloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Layout
import androidx.compose.ui.Modifier
import androidx.compose.ui.WithConstraints
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.loadImageResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.ui.tooling.preview.Preview
import com.example.composedemo.ui.Greeting
import com.example.composedemo.R
import com.example.composedemo.ui.resource.ComposeDemoTheme

@Preview(showBackground = true, group = "1")
@Composable
fun HelloWorldPreview() {
    ComposeDemoTheme {
        Greeting("Android")
    }
}

@Preview(group = "2")
@Composable
fun showColumnPreview() {
    ComposeDemoTheme {
        Column() {
            Text(text = "Hello 1")

            Text(text = "Hello 2")

            // async thread 执行
            val image = loadImageResource(id = R.mipmap.composelogo)
            image.resource.resource?.let {
                Image(asset = it)
            }
        }
    }
}

@Preview(group = "3")
@Composable
fun showRowPreview() {
    ComposeDemoTheme {
        Row() {
            Text(text = "Hello 1")
            Text(text = "Hello 2")
        }
    }
}

@Preview(group = "4")
@Composable
fun showBoxPreview() {

    Box(Modifier.fillMaxSize()) {
        Text("This text is drawn first", modifier = Modifier.align(Alignment.TopCenter))
        Box(
            Modifier.align(Alignment.TopCenter).fillMaxHeight().preferredWidth(
                50.dp
            ).background( Color.Blue)
        )
        Text("This text is drawn last", modifier = Modifier.align(Alignment.Center))
        FloatingActionButton(
            modifier = Modifier.align(Alignment.BottomEnd).padding(12.dp),
            onClick = {}
        ) {
            Text("+")
        }
    }
}

@Preview(group = "5")
@Composable
fun ContstraintRefsExample(){
    ConstraintLayout(Modifier.fillMaxSize()) {
        val (button, text) = createRefs()

        Button(onClick = {}, Modifier.constrainAs(button) {
            top.linkTo(parent.top, Dp(10f))
            absoluteLeft.linkTo(parent.absoluteLeft, Dp(10f))
            absoluteRight.linkTo(parent.absoluteRight, Dp(10f))
        }) {
            Text(text = "button")
        }

        Text(text = "subContent", Modifier.constrainAs(text) {
            top.linkTo(button.bottom, Dp(5f))
            absoluteLeft.linkTo(button.absoluteLeft)
            absoluteRight.linkTo(button.absoluteRight)
        })

    }
}

@Preview(group = "6")
@Composable
fun ContstraintForIdExample() {
    WithConstraints {
        val constraints = if (minWidth < 600.dp) {
            decoupledConstraints(margin = 16.dp) // Portrait constraints
        } else {
            decoupledConstraints(margin = 32.dp) // Landscape constraints
        }

        ConstraintLayout(constraints) {
            Button(
                onClick = { /* Do something */ },
                modifier = Modifier.layoutId("button")
            ) {
                Text("Button")
            }

            Text("Text", Modifier.layoutId("text"))
        }
    }
}

private fun decoupledConstraints(margin: Dp): ConstraintSet {
    return ConstraintSet {
        val button = createRefFor("button")
        val text = createRefFor("text")

        constrain(button) {
            top.linkTo(parent.top, margin= margin)
        }
        constrain(text) {
            top.linkTo(button.bottom, margin)
        }
    }
}

@Preview(group = "7")
@Composable
fun CustomViewComposable(modifier: Modifier = Modifier) {
    MyOwnColumn(modifier.padding(8.dp)) {
        Text("MyOwnColumn")
        Text("places items")
        Text("vertically.")
        Text("We've done it by hand!")
    }
}




@Composable
fun MyOwnColumn(
    modifier: Modifier = Modifier,
    children: @Composable() () -> Unit
) {
    Layout(
        modifier = modifier,
        children = children
    ) { measurables, constraints ->
        // Don't constrain child views further, measure them with given constraints
        // List of measured children
        val placeables = measurables.map { measurable ->
            // Measure each children
            measurable.measure(constraints)
        }

        // Set the size of the layout as big as it can
        layout(constraints.maxWidth, constraints.maxHeight) {
            // Track the y co-ord we have placed children up to
            var yPosition = 0

            // Place children in the parent layout
            placeables.forEach { placeable ->
                // Position item on the screen
                placeable.placeRelative(x = 0, y = yPosition)

                // Record the y co-ord placed up to
                yPosition += placeable.height
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun embeddedAndroidViewDemo() {
    Column {
        val state = remember { mutableStateOf(0) }

        //widget.ImageView
        AndroidView(viewBlock = { ctx ->
            ImageView(ctx).apply {
                val drawable = ContextCompat.getDrawable(ctx, R.mipmap.composelogo)
                setImageDrawable(drawable)
            }
        })

        //Compose Button
        androidx.compose.material.Button(onClick = { state.value++ }) {
            Text("MyComposeButton")
        }

        //widget.Button
        AndroidView(viewBlock = { ctx ->
            android.widget.Button(ctx).apply {
                text = "MyAndroidButton"
                layoutParams = LinearLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT)
                setOnClickListener {
                    state.value++
                }
            }

        }, modifier = Modifier.padding(8.dp))

        AndroidView(viewBlock = { ctx ->
            //Here you can construct your View
           TextView(ctx).apply {
                layoutParams = LinearLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT)
            }
        }, update = {
            it.text = "You have clicked the buttons: " + state.value.toString() + " times"
        })
    }
}