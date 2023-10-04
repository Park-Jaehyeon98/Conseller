package com.example.project

import ShowMyAuction
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Divider
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.project.viewmodels.AuctionViewModel
import com.example.project.viewmodels.BarterViewModel
import com.example.project.viewmodels.MyPageViewModel
import com.example.project.viewmodels.StoreViewModel
import kotlinx.coroutines.delay

@Composable
fun HomePage(modifier: Modifier = Modifier, navController: NavHostController) {
    val AuctionviewModel: AuctionViewModel = hiltViewModel()
    val StoreViewModel: StoreViewModel = hiltViewModel()
    val myPageViewModel: MyPageViewModel = hiltViewModel()
    val BarterViewModel: BarterViewModel = hiltViewModel()
    LaunchedEffect(Unit) {
        AuctionviewModel.fetchPopularAuctionitems()
        AuctionviewModel.fetchPopularAuctionMain()
        AuctionviewModel.fetchPopularAuctionSub()
        StoreViewModel.fetchPopularStoreMain()
        StoreViewModel.fetchPopularStoreSub()
        myPageViewModel.getMyAuctionBid()
        myPageViewModel.getMyAuction()
        BarterViewModel.fetchPopularBarteritems()
    }
    LaunchedEffect(Unit) {
        navController.currentBackStackEntry?.also { entry ->
            navController.popBackStack(entry.id, true)
        }
    }

    Column(
        modifier = modifier.fillMaxSize(),
    ) {
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item { Spacer(modifier = Modifier.height(10.dp)) }
            item { HomeLayout5() }
            item { Spacer(modifier = Modifier.height(10.dp)) }
            item { HomeLayout3(navController) }
            item { Spacer(modifier = Modifier.height(10.dp)) }
            item { HomeLayout4(navController) }
            item { Spacer(modifier = Modifier.height(10.dp)) }
            item { HomeLayout2() }
            item { Spacer(modifier = Modifier.height(10.dp)) }
            item { HomeLayout6() }
            item { Spacer(modifier = Modifier.height(10.dp)) }
            item { HomeLayout7() }
            item { Spacer(modifier = Modifier.height(10.dp)) }
            item { HomeLayout8() }

        }
    }
}

@Composable
fun HomeLayout2() {
    val AuctionviewModel: AuctionViewModel = hiltViewModel()

    val popularAuction by AuctionviewModel.auctionPopular.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
            .background(Color.White, shape = RoundedCornerShape(8.dp)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                "현재 인기 경매글",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
            )
            Spacer(modifier = Modifier.height(10.dp))
            Divider()
            popularAuction.take(1).forEach { item ->
                ShowMyAuction(
                    image = item.gifticonDataImageName,
                    name = item.gifticonName,
                    gifticonTime = item.gifticonEndDate,
                    auctionTime = item.gifticonEndDate,
                    isDeposit = item.deposit,
                    upperprice = item.upperPrice,
                    nowprice = item.lowerPrice
                ) {

                }
            }
        }
    }
}

@Composable
fun HomeLayout3(navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .background(Color.White, shape = RoundedCornerShape(8.dp)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Spacer(modifier = Modifier.weight(1f))  // 상단에 공간을 주기 위해 weight를 사용
            Row(
                horizontalArrangement = Arrangement.Start, modifier = Modifier.fillMaxWidth()
            ) {
                Spacer(modifier = Modifier.width(24.dp))
                ImageButton("스토어") { navController.navigate("StorePage") }
                ImageButton("경매") { navController.navigate("AuctionPage") }
                ImageButton("물물교환") { navController.navigate("BarterPage") }
                ImageButton("이벤트") { navController.navigate("EventPage") }
                Spacer(modifier = Modifier.width(8.dp))
            }
            Spacer(modifier = Modifier.weight(1f))  // 하단에 공간을 주기 위해 weight를 사용
        }
    }
}

@Composable
fun ImageButton(imageName: String, onClick: () -> Unit) {
    val resourceId = when (imageName) {
        "경매" -> R.drawable.auction
        "물물교환" -> R.drawable.barter
        "스토어" -> R.drawable.store
        "이벤트" -> R.drawable.event
        else -> R.drawable.chatbot
    }

    val interactionSource = remember { MutableInteractionSource() }
    var scale by remember { mutableFloatStateOf(1f) }
    val pressedScale = 0.95f

    Image(painter = painterResource(id = resourceId),
        contentDescription = null,
        modifier = Modifier
            .size(88.dp)
            .clickable(
                interactionSource = interactionSource, indication = null, onClick = onClick
            )
            .graphicsLayer(
                scaleX = scale, scaleY = scale
            )
            .pointerInput(Unit) {
                detectTapGestures(onTap = { onClick() }, onPress = {
                    tryAwaitRelease()
                    scale = pressedScale
                    delay(100L)
                    scale = 1f
                })
            })
}

@Composable
fun HomeLayout4(navController: NavController) {
    val myPageViewModel: MyPageViewModel = hiltViewModel()
    val error by myPageViewModel.error.collectAsState()
    val myBids by myPageViewModel.getMyAuctionBidResponse.collectAsState()
    val myauction by myPageViewModel.getMyAuctionResponse.collectAsState()

    var selectedTab by remember { mutableStateOf("입찰") } // 초기값은 "입찰"

    var showSnackbar by remember { mutableStateOf(false) } // 에러처리스낵바
    var snackbarText by remember { mutableStateOf("") }


    LaunchedEffect(error) {
        if (error != null) {
            showSnackbar = true
            snackbarText = error!!
        }
    }
    LaunchedEffect(showSnackbar) {
        if (showSnackbar) {
            delay(5000)
            showSnackbar = false
        }
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(330.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        if (showSnackbar) {
            Snackbar {
                Text(
                    text = snackbarText,
                    style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
                )
            }
        }
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .background(Color.White, shape = RoundedCornerShape(8.dp)),
        ) {
            Column(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                ) {
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        "입찰",
                        modifier = Modifier.clickable { selectedTab = "입찰" },
                        fontSize = 18.sp,
                        color = if (selectedTab == "입찰") Color.Black else Color.Gray
                    )
                    Spacer(modifier = Modifier.weight(0.8f))
                    Text(
                        "경매",
                        modifier = Modifier.clickable { selectedTab = "경매" },
                        fontSize = 18.sp,
                        color = if (selectedTab == "경매") Color.Black else Color.Gray
                    )
                    Spacer(modifier = Modifier.weight(1f))
                }

                Spacer(modifier = Modifier.height(10.dp))
                Divider()
            }

            // 선택된 탭에 따른 내용
            when (selectedTab) {
                "입찰" -> {
                    Box(
                        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
                    ) {
                        if (myBids.isEmpty()) {
                            Text(text = "등록된 입찰이 없습니다.", fontSize = 24.sp)
                        } else {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.padding(top = 50.dp)
                            ) {
                                myBids.take(2).forEach { item ->
                                    val itemInteractionState =
                                        remember { MutableInteractionSource() }
                                    val itemIsPressed by itemInteractionState.collectIsPressedAsState()
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(horizontal = 10.dp)
                                            .clickable(interactionSource = itemInteractionState,
                                                indication = rememberRipple(bounded = true),
                                                onClick = {
                                                    navController.navigate("AuctionDetailPage/${item.auctionItemData.auctionIdx}")
                                                })
                                            .background(
                                                if (itemIsPressed) Color.LightGray else Color.Transparent
                                            )
                                    ) {
                                        Row(
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.Start,
                                            modifier = Modifier.padding(8.dp)
                                        ) {
                                            val painter =
                                                rememberAsyncImagePainter(model = item.auctionItemData.gifticonDataImageName)
                                            Image(
                                                painter = painter,
                                                contentDescription = null,
                                                modifier = Modifier
                                                    .size(85.dp)
                                                    .align(Alignment.CenterVertically)
                                            )
                                            Spacer(modifier = Modifier.width(25.dp))
                                            Column(
                                                modifier = Modifier.align(Alignment.CenterVertically)
                                            ) {
                                                Text(
                                                    item.auctionItemData.gifticonName,
                                                    fontSize = 18.sp
                                                )
                                                Spacer(modifier = Modifier.height(5.dp))
                                                Text(
                                                    "최고 입찰가: ${item.auctionItemData.auctionHighestBid}원 (${if (item.auctionItemData.auctionHighestBid == item.auctionBidPrice) "본인" else "타인"})",
                                                    fontSize = 18.sp
                                                )
                                            }
                                        }
                                    }
                                    Spacer(modifier = Modifier.height(10.dp))
                                }
                            }
                        }
                    }
                }

                "경매" -> {
                    Box(
                        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
                    ) {
                        if (myauction.isEmpty()) {
                            Text(text = "등록된 경매가 없습니다.", fontSize = 24.sp)
                        } else {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.padding(top = 50.dp)
                            ) {
                                myauction.take(2).forEach { item ->
                                    val itemInteractionState =
                                        remember { MutableInteractionSource() }
                                    val itemIsPressed by itemInteractionState.collectIsPressedAsState()

                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(horizontal = 10.dp)
                                            .clickable(interactionSource = itemInteractionState,
                                                indication = rememberRipple(bounded = true),
                                                onClick = {
                                                    navController.navigate("AuctionDetailPage/${item.auctionIdx}")
                                                })
                                            .background(
                                                if (itemIsPressed) Color.LightGray else Color.Transparent
                                            )
                                    ) {
                                        Row(
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.Start,
                                            modifier = Modifier.padding(8.dp)
                                        ) {
                                            val painter =
                                                rememberAsyncImagePainter(model = item.gifticonDataImageName)
                                            Image(
                                                painter = painter,
                                                contentDescription = null,
                                                modifier = Modifier
                                                    .size(85.dp)
                                                    .align(Alignment.CenterVertically)
                                            )
                                            Spacer(modifier = Modifier.width(25.dp))
                                            Column(
                                                modifier = Modifier.align(Alignment.CenterVertically)
                                            ) {
                                                Text("${item.gifticonName}", fontSize = 18.sp)
                                                Spacer(modifier = Modifier.height(5.dp))
                                                Text(
                                                    "최고 입찰가: ${item.auctionHighestBid}원",
                                                    fontSize = 18.sp
                                                )
                                            }
                                        }
                                    }
                                    Spacer(modifier = Modifier.height(10.dp))
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun HomeLayout5() {
    val viewModel: AuctionViewModel = hiltViewModel()
    val auctionMain by viewModel.auctionMainResponse.collectAsState()

    // 주어진 id에 대응되는 카테고리명 반환
    fun getCategoryName(id: Int?): String {
        return when (id) {
            1 -> "버거/치킨/피자"
            2 -> "편의점"
            3 -> "카페/베이커리"
            4 -> "아이스크림"
            5 -> "기타"
            else -> "알 수 없는 카테고리"
        }
    }

    fun getCategoryImage(id: Int?): Int {
        return when (id) {
            1 -> R.drawable.fastfood
            2 -> R.drawable.conveniencestore
            3 -> R.drawable.bakery
            4 -> R.drawable.icecream
            else -> R.drawable.etc
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
            .background(Color.White, shape = RoundedCornerShape(8.dp)),
        contentAlignment = Alignment.Center
    ) {
        val imageModifier = Modifier
            .size(60.dp)
            .clip(CircleShape)
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "\uD83D\uDD25 이번 주 스토어 인기 품목 \uD83D\uDD25",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
            )
            Spacer(modifier = Modifier.height(10.dp))
            Divider()
            Spacer(modifier = Modifier.height(40.dp))
            if (auctionMain.isNotEmpty()) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(id = getCategoryImage(auctionMain[0])),
                            contentDescription = null,
                            modifier = imageModifier,
                            contentScale = ContentScale.Crop
                        )
                        Text(
                            text = "${getCategoryName(auctionMain[0])}",
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(id = getCategoryImage(auctionMain[1])),
                            contentDescription = null,
                            modifier = imageModifier,
                            contentScale = ContentScale.Crop
                        )
                        Text(
                            text = "${getCategoryName(auctionMain[1])}",
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            } else {
                Text("아직 정보가 없습니다.")
            }
        }
    }
}


@Composable
fun HomeLayout6() {
    val viewModel: StoreViewModel = hiltViewModel()
    val storeMain by viewModel.storeMainResponse.collectAsState()

    // 주어진 id에 대응되는 카테고리명 반환
    fun getCategoryName(id: Int?): String {
        return when (id) {
            1 -> "버거/치킨/피자"
            2 -> "편의점"
            3 -> "카페/베이커리"
            4 -> "아이스크림"
            5 -> "기타"
            else -> "알 수 없는 카테고리"
        }
    }

    fun getCategoryImage(id: Int?): Int {
        return when (id) {
            1 -> R.drawable.fastfood
            2 -> R.drawable.conveniencestore
            3 -> R.drawable.bakery
            4 -> R.drawable.icecream
            else -> R.drawable.etc
        }
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
            .background(Color.White, shape = RoundedCornerShape(8.dp)),
        contentAlignment = Alignment.Center
    ) {
        val imageModifier = Modifier
            .size(60.dp)
            .clip(CircleShape)
        Column(
            modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "\uD83D\uDD25 이번 주 인기 판매 품목 \uD83D\uDD25",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
            )
            Spacer(modifier = Modifier.height(10.dp))
            Divider()
            Spacer(modifier = Modifier.height(40.dp))
            if (storeMain.isNotEmpty()) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(id = getCategoryImage(storeMain[0])),
                            contentDescription = null,
                            modifier = imageModifier,
                            contentScale = ContentScale.Crop
                        )
                        Text(
                            text = "${getCategoryName(storeMain[0])}", fontWeight = FontWeight.Bold
                        )
                    }
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(id = getCategoryImage(storeMain[1])),
                            contentDescription = null,
                            modifier = imageModifier,
                            contentScale = ContentScale.Crop
                        )
                        Text(
                            text = "${getCategoryName(storeMain[1])}", fontWeight = FontWeight.Bold
                        )
                    }
                }
            } else {
                Text("아직 정보가 없습니다.")
            }
        }
    }
}


@Composable
fun HomeLayout7() {
    val BarterViewModel: BarterViewModel = hiltViewModel()

    val popularBarter by BarterViewModel.barterPopular.collectAsState()
    val showPopular = popularBarter
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
            .background(Color.White, shape = RoundedCornerShape(8.dp)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                "현재 인기 물물교환 글",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
            )
            Spacer(modifier = Modifier.height(10.dp))
            Divider()
            ShowMyBarter(
                image = showPopular!!.gifticonDataImageName,
                name = showPopular.gifticonName,
                gifticonTime = showPopular.gifticonEndDate,
                isDeposit = showPopular.isDeposit,
                barterTime = showPopular.barterEndDate,
                preper = showPopular.preper,
                title = showPopular.barterName
            ) {

            }
        }
    }
}

@Composable
fun HomeLayout8() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(Color.White, shape = RoundedCornerShape(8.dp)),
        contentAlignment = Alignment.Center
    ) {
        Text("Made by SSAFY")
    }
}