package com.github.stormwyrm.composedemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.stormwyrm.composedemo.ui.theme.ComposeDemoTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import com.github.stormwyrm.composedemo.bean.Message


class MainActivity : ComponentActivity() {
    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val msgs = mutableListOf<Message>().apply {
            for (index in 0..30) {
                add(
                    Message(
                        "Jetpack Compose 博物馆 $index",
                        "Jetpack Compose 博物馆 我们开始更新,Jetpack Compose 博物馆 我们开始更新Jetpack Compose 博物馆 我们开始更新$index"
                    )
                )
            }
        }
        setContent {
            ComposeDemoTheme {
                Conversation(messages = msgs)
            }
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun Conversation(messages: List<Message>) {
    LazyColumn {
        items(messages) { message ->
            MessageCard(message)
        }
    }
}


@ExperimentalMaterialApi
@Composable
fun MessageCard(msg: Message) {
    // 创建一个能够检测卡片是否被展开的变量
    var isExpanded by remember { mutableStateOf(false) }

    Surface(
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        elevation = 5.dp,
        onClick = { isExpanded = !isExpanded }
    ) {
        Row(
            modifier = Modifier.padding(all = 8.dp),
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "image",
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .border(1.5.dp, MaterialTheme.colors.secondary, shape = CircleShape)
            )

            Spacer(Modifier.padding(horizontal = 8.dp)) // 添加一个空的控件用来填充水平间距，设置 padding 为 8.dp

            Column {
                Text(
                    text = msg.author,
                    color = MaterialTheme.colors.secondaryVariant,
                    style = MaterialTheme.typography.subtitle2 // 添加 style
                )
                Spacer(Modifier.padding(vertical = 3.dp))
                Text(
                    text = msg.body,
                    style = MaterialTheme.typography.body2, // 添加 style
                    maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                    modifier = Modifier.animateContentSize()
                )
            }
        }
    }
}

@ExperimentalMaterialApi
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewConversation() {
    ComposeDemoTheme {
        Conversation(messages = mutableListOf<Message>().apply {
            for (index in 0..30) {
                Message(
                    "Jetpack Compose 博物馆 $index",
                    "Jetpack Compose 博物馆 我们开始更新,Jetpack Compose 博物馆 我们开始更新Jetpack Compose 博物馆 我们开始更新$index"
                )
            }
        })
    }
}
