package com.example.simplestockinfo
// view를 만들어야 하기에 각 정보별 틀을 여기서 만들어야 됨
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.simplestockinfo.repository.Repository
import com.example.simplestockinfo.ui.theme.MainViewModelFactory
import com.example.simplestockinfo.ui.theme.SimpleStockInfoTheme
class MainActivity : ComponentActivity() {
    lateinit var viewModel: MainViewModel
    lateinit var viewModelFactory: MainViewModelFactory
    private var repository = Repository()
    private var Name = mutableListOf<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this,viewModelFactory).get(MainViewModel::class.java)
        setContent {
            SimpleStockInfoTheme {
//                InfoCard(viewModel)
                Spacer(modifier = Modifier.height(100.dp))
                InfoCard2(mainViewModel= viewModel)
            }
        }
    }
}

@Composable
fun InfoCard(mainViewModel: MainViewModel){
    val Name = mainViewModel.getData().observeAsState()


    Name.value?.let { it ->

        Row(modifier = Modifier
            .fillMaxWidth()
            .background(shape = RoundedCornerShape(6.dp), color = Color.LightGray)
            .padding(20.dp)
            .height(40.dp),
            verticalAlignment = Alignment.CenterVertically

        ) {
            Text(it.get(0), modifier = Modifier.width(200.dp), textAlign = TextAlign.Center)
            Spacer(modifier = Modifier.width(30.dp))
            Text(text = it.get(1))
        }
    }
}
@Composable
fun InfoCard2(mainViewModel: MainViewModel){
    val wti = mainViewModel.getWTI().observeAsState()
    Log.d("WTU", wti.toString())
    wti.value?.let { it ->
        Log.d("firstvalue", it.first)
        Row(modifier = Modifier
            .fillMaxWidth()
            .background(shape = RoundedCornerShape(6.dp), color = Color.Magenta)
            .padding(20.dp)
            .height(40.dp),
            verticalAlignment = Alignment.CenterVertically

        ) {
            Text(text = it!!.first, modifier = Modifier.width(200.dp), textAlign = TextAlign.Center)
            Spacer(modifier = Modifier.width(30.dp))
            Text(text = it.second!!.toString())
        }
    }
}
//@Composable
//fun InfoContainer(infodata: List<InfoData>){
//    LazyColumn(modifier = Modifier.fillMaxSize()){
//        items(){
//
//        }
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    SimpleStockInfoTheme {
//        InfoCard()
//    }
//}