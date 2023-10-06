package com.example.project.reuse_component

fun convertNameToNum(filter1: String, filter2: String, filter3: String): Triple<Int, Int, Int> {
    val filter1Id = when (filter1) {
        "버거/치킨/피자" -> 1
        "편의점" -> 2
        "카페/베이커리" -> 3
        "아이스크림" -> 4
        "기타" -> 5
        else -> 0
    }

    val filter2Id = when (filter2) {
        "버거" -> 1
        "치킨" -> 2
        "피자" -> 3
        "금액권" -> 4
        "과자" -> 5
        "음료" -> 6
        "도시락/김밥류" -> 7
        "기타" -> 0
        "카페" -> 8
        "베이커리" -> 9
        "베스킨라빈스" -> 10
        else -> 0
    }

    val filter3Id = when (filter3) {
        "등록일" -> 1
        "유효기한" -> 2
        "입찰가" -> 3
        "즉시구입가" -> 4
        else -> 0
    }

    return Triple(filter1Id, filter2Id, filter3Id)
}

fun convertNameToNumTwo(filter1: String, filter2: String): Pair<Int, Int> {
    val filter1Id = when (filter1) {
        "버거/치킨/피자" -> 1
        "편의점" -> 2
        "카페/베이커리" -> 3
        "아이스크림" -> 4
        "기타" -> 5
        else -> 0
    }

    val filter2Id = when (filter2) {
        "버거" -> 1
        "치킨" -> 2
        "피자" -> 3
        "금액권" -> 4
        "과자" -> 5
        "음료" -> 6
        "도시락/김밥류" -> 7
        "기타" -> 0
        "카페" -> 8
        "베이커리" -> 9
        "베스킨라빈스" -> 10
        else -> 0
    }

    return Pair(filter1Id, filter2Id)
}