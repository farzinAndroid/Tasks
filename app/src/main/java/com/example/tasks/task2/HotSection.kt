package com.example.tasks.task2

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.tasks.R

@Composable
fun HotSection() {


    Column(

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

    }

}