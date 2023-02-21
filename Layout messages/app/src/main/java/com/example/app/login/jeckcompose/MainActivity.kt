package com.example.app.login.jeckcompose
import android.content.res.Configuration
import androidx.compose.material.*
import android.os.Bundle
import androidx.*
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.app.login.jeckcompose.ui.theme.JeckComposeTheme
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

// ...

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JeckComposeTheme() {
                Surface(modifier = Modifier.fillMaxSize()) {
//                    MessageCard(Message("Android", "Jetpack Compose"))
                    PreviewConversation()
                }
            }
        }
    }
}

data class Message(val author: String, val body: String)

@Composable
fun Conversation(messages: List<Message>){
    LazyColumn() {
        items (messages) { messages ->
            MessageCard(messages)
        }
    }
}

@Composable
fun PreviewConversation() {
    JeckComposeTheme() {
        val list = mutableListOf<Message>()
        val list1 = Message("quang","Quê hương em là một ngôi làng nhỏ phía bên bờ dòng sông Hồng. Nơi đây có cánh đồng lúa rộng thẳng cánh cò bay, có vườn cây trĩu ăn quả ngọt, có những luống rau xanh mướt mắt…Chiều chiều, bên bờ đê, lại bay lên những cánh diều của lũ trẻ đủ hình thù màu sắc. Thấp thoáng xa xa những hơi khói mỏng manh bay lên từ căn bếp nhỏ. Vọng lại văng vẳng tiếng cười, tiếng nói của những gia đình nhỏ mà ấm áp. Ôi! Sao mà bình yên đến thế!")
        val list2 = Message("quang","Tuần trước em có dịp tham quan biển Phan Thiết. Cảnh nơi đây rất đẹp. Những hàng dừa trải dài xanh ngắt nằm dọc bờ biển. Giữa biển có một bãi cát trắng dẫn thẳng vào các tòa nhà nghỉ của khách du lịch, sau tòa nhà là những dãy núi tim tím. Xa xa trên mặt biển, có những chiếc thuyền chở khách du lịch đi thăm quan. Cảnh vật ở biển Phan Thiết thật yên bình. Em yêu cảnh biển nơi đây, và cũng yêu đất nước Việt Nam của mình.")
        list.add(list1)
        list.add(list2)
        Conversation(list)
    }
}


@Preview(name = "Light Mode")
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark Mode"
)

@Composable
fun PreviewMessageCard() {
    JeckComposeTheme() {
        Surface {
            MessageCard(
                msg = Message("Colleague", "Take a look at Jetpack Compose, it's great!")
            )
        }
    }
}

@Composable
fun MessageCard(msg: Message) {
    Row(modifier = Modifier.padding(all = 8.dp)) {
        Image(
            painter = painterResource(R.drawable.avatar_generations_prsz),
            contentDescription = null,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .border(1.5.dp, MaterialTheme.colors.secondary, CircleShape)
        )

        Spacer(modifier = Modifier.width(8.dp))

        var isExpanded by remember { mutableStateOf(false) }
        val surfaceColor by animateColorAsState(
            if (isExpanded) MaterialTheme.colors.primary else MaterialTheme.colors.surface,
        )

        Column(modifier = Modifier.clickable { isExpanded = !isExpanded }) {
            Text(
                text = msg.author,
                color = MaterialTheme.colors.secondaryVariant,
                style = MaterialTheme.typography.subtitle2
            )

            Spacer(modifier = Modifier.height(4.dp))

            Surface(
                shape = MaterialTheme.shapes.medium,
                elevation = 1.dp,
                color = surfaceColor,
                modifier = Modifier.animateContentSize().padding(1.dp)
            ) {
                Text(
                    text = msg.body,
                    modifier = Modifier.padding(all = 4.dp),
                    maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                    style = MaterialTheme.typography.body2
                )
            }
        }
    }
}

  