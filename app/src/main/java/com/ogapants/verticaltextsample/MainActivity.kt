package com.ogapants.verticaltextsample

import android.graphics.Paint
import android.graphics.Paint.VERTICAL_TEXT_FLAG
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ogapants.verticaltextsample.ui.theme.VerticalTextSampleTheme

/**
 * Sample for Vertical Text
 * https://android-developers.googleblog.com/2025/01/first-beta-android16.html
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VerticalTextSampleTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    VerticalText(
                        text = """
                            「春は、曙。やうやうしろくなりゆく山ぎは、すこしあかりて、紫だちたる雲のほそくたなびきたる。」
                        """.trimIndent(),
                        modifier = Modifier.padding(innerPadding),
                    )
                }
            }
        }
    }
}


@Composable
fun VerticalText(text: String, modifier: Modifier = Modifier) {
    Box(Modifier
        .padding(10.dp)
        .background(Color.White)
        .fillMaxSize()
        .drawWithContent {
            drawIntoCanvas { canvas ->
                val paint = Paint().apply {
                    textSize = 18.sp.toPx()
                }
                // Draw text vertically
                paint.flags = paint.flags or VERTICAL_TEXT_FLAG
                val height = paint.measureText(text)
                canvas.nativeCanvas.drawText(
                    text, 0, text.length, size.width / 2, (size.height - height) / 2, paint
                )
            }
        })
    {}
}

@Preview(showBackground = true)
@Composable
fun VerticalTextPreview() {
    VerticalTextSampleTheme {
        VerticalText("Android")
    }
}