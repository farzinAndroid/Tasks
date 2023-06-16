package com.example.tasks.task2_and_task3.ui

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.tasks.R
import com.example.tasks.task2_and_task3.data.model.AmazingItem
import com.example.tasks.task2_and_task3.data.remote.NetworkResult
import com.example.tasks.task2_and_task3.viewmodel.HomeViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.map

@SuppressLint("FlowOperatorInvokedInComposition")
@Composable
fun HotSection(
    viewModel: HomeViewModel = hiltViewModel(),
) {

    val context = LocalContext.current

    getAllDataFromServer(viewModel)

    var superMarketItemList by remember {
        mutableStateOf<List<AmazingItem>>(emptyList())
    }
    var loading by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(true){
        viewModel.filteredSuperMarketItems.collectLatest {superMarketItemResult->
            superMarketItemList = superMarketItemResult.data ?: emptyList()
        }
    }





    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        items(superMarketItemList){ list ->
           HotItem(
               painter = rememberAsyncImagePainter(list.image),
               title = list.name,
               isHaveBadge = true,
               badgeTitle = list.discountPercent.toString()
           )
        }
    }

    /*Column(

    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {

            HotItem(
                painter = painterResource(R.drawable.snapp),
                title = "اسنپ"
            )

            HotItem(
                painter = painterResource(R.drawable.alibaba),
                title = "علی بابا"
            )

            HotItem(
                painter = painterResource(R.drawable.bazar),
                title = "بازار"
            )

            HotItem(
                painter = painterResource(R.drawable.ita),
                title = "ایتا"
            )

        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {

            HotItem(
                painter = painterResource(R.drawable.mofid),
                title = "مفید"
            )

            HotItem(
                painter = painterResource(R.drawable.snap_food),
                title = "اسنپ فود",
                isHaveBadge = true,
                badgeTitle = "تخفیف بگیر"
            )

            HotItem(
                painter = painterResource(R.drawable.toranj),
                title = "ترنج",
                isHaveBadge = true,
                badgeTitle = "سرمایه\u200Cگذاری"
            )

            HotItem(
                painter = painterResource(R.drawable.torob),
                title = "ترب"
            )

        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {

            HotItem(
                painter = painterResource(R.drawable.robika),
                title = "روبیکا"
            )

            HotItem(
                painter = painterResource(R.drawable.jabama),
                title = "جاباما",
                isHaveBadge = true,
                badgeTitle = "ویلا و بومگردی"
            )

            HotItem(
                painter = painterResource(R.drawable.khanomi),
                title = "خانومی",
                isHaveBadge = true,
                badgeTitle = "${DigitHelper.digitByLang("70")}% تخفیف"
            )

            HotItem(
                painter = painterResource(R.drawable.jajiga),
                title = "جاجیگا",
                isHaveBadge = true,
                badgeTitle = "ویلا کردان"
            )

        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {

            HotItem(
                painter = painterResource(R.drawable.robika),
                title = "روبیکا"
            )

            HotItem(
                painter = painterResource(R.drawable.jabama),
                title = "جاباما"
            )

            HotItem(
                painter = painterResource(R.drawable.khanomi),
                title = "خانومی"
            )

            HotItem(
                painter = painterResource(R.drawable.jajiga),
                title = "جاجیگا"
            )

        }

    }*/

}

private fun getAllDataFromServer(vm:HomeViewModel){
    vm.getDataFromServer()
}