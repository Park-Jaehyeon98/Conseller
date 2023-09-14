package com.conseller.conseller.store;

import com.conseller.conseller.category.mainCategory.MainCategoryRepository;
import com.conseller.conseller.category.subCategory.SubCategoryRepository;
import com.conseller.conseller.entity.Gifticon;
import com.conseller.conseller.entity.Store;
import com.conseller.conseller.entity.User;
import com.conseller.conseller.gifticon.GifticonRepository;
import com.conseller.conseller.store.dto.mapper.StoreMapper;
import com.conseller.conseller.store.dto.request.ModifyStoreRequest;
import com.conseller.conseller.store.dto.request.RegistStoreRequest;
import com.conseller.conseller.store.dto.request.StoreListRequest;
import com.conseller.conseller.store.dto.response.DetailStoreResponse;
import com.conseller.conseller.store.enums.StoreStatus;
import com.conseller.conseller.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class StoreServiceImpl implements StoreService {
    private final StoreRepository storeRepository;
    private final UserRepository userRepository;
    private final GifticonRepository gifticonRepository;
    private final MainCategoryRepository mainCategoryRepository;
    private final SubCategoryRepository subCategoryRepository;

    // 판매 목록
    @Transactional(readOnly = true)
    public List<Store> getStoreList(StoreListRequest storeListRequest) { //queryDSL 사용

        return null;
    }

    // 판매 글 등록
    public void registStore(RegistStoreRequest request) {

        User user = userRepository.findById(request.getUserIdx())
                .orElseThrow(() -> new RuntimeException());
        Gifticon gifticon = gifticonRepository.findById(request.getGifticonIdx())
                .orElseThrow(() -> new RuntimeException());

        Store store = StoreMapper.INSTANCE.registStoreRequestToStore(request, user, gifticon);

        storeRepository.save(store);
    }

    // 판매 글 상세보기
    @Transactional(readOnly = true)
    public DetailStoreResponse detailStore(Long storeIdx) {
        Store store = storeRepository.findById(storeIdx)
                .orElseThrow(() -> new RuntimeException());

        User user = userRepository.findById(store.getUser().getUserIdx())
                .orElseThrow(() -> new RuntimeException());

        DetailStoreResponse response = StoreMapper.INSTANCE.entityToDetailStoreResponse(user, store);

        return response;
    }

    //판매 글 수정
    public void modifyStore(Long storeIdx , ModifyStoreRequest storeRequest) {
        Store store = storeRepository.findById(storeIdx).orElseThrow(() -> new RuntimeException());

        store.setStoreEndDate(storeRequest.getStoreEndDate());
        store.setStoreText(storeRequest.getStoreText());
    }

    // 판매 글 삭제
    public void deleteStore(Long storeIdx) {
        storeRepository.deleteById(storeIdx);
    }

    // 판매 상태 변경
    public void storeStatusPermute(Long storeIdx) {
        Store store = storeRepository.findById(storeIdx).orElseThrow(() -> new RuntimeException());

        store.setStoreStatus(StoreStatus.IN_TRADE);
    }

}
