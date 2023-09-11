package com.example.project

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.project.viewmodels.AuctionViewModel

@Composable
fun AuctiondetailPage(index: String?) {
//    val viewModel: AuctionViewModel = hiltViewModel()
//    val auctionItems by viewModel.auctionItems.collectAsState()
//    val scrollState = rememberScrollState()
//
//    // index를 Long으로 변환하고 상세 정보를 불러옴
//    val auctionIdx = index?.toLongOrNull()
//    auctionIdx?.let { viewModel.fetchAuctionDetail(it) }
//
//    // 이전에 들고온 게시글 데이터 불러오기
//    val auctionDetail = index?.toLongOrNull()?.let {
//        viewModel.getAuctionItemByIndex(it)
//    }
//
//    Column(
//        modifier = Modifier.fillMaxSize().padding(16.dp).verticalScroll(scrollState)
//    ) {
//        Text(text = "상세페이지 {$index}")
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        auctionDetail?.let {
//            Text("Price: ${it.upperprice} / ${it.nowprice}")
//            Text("Time: ${it.gifticonTime} / ${it.auctionTime}")
//        }
//
//        auctionDetailData?.let {
//            Text("User: ${it.auction_user_nickname}")
//
//            // List top 3 bids
//            it.actuon_vid.take(3).forEach { bid ->
//                Text("Bid: ${bid.auctionBidPrice}")
//            }
//        }
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        Row(
//            modifier = Modifier.fillMaxWidth(),
//            horizontalArrangement = Arrangement.SpaceBetween
//        ) {
//            Button(onClick = { /* Purchase logic */ }) {
//                Text("즉시구매")
//            }
//
//            Button(onClick = { /* Bidding logic */ }) {
//                Text("입찰")
//            }
//        }
//    }



}





// 나중에 개발 다되고나면 에러처리용 코드
//    if (auctionDetail != null) {
//        // 본문내용 여기 넣기
//    } else {
//        Text(text = "삭제된 글입니다.")
//    }
