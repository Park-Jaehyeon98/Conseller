package com.conseller.conseller.store;

import com.conseller.conseller.entity.Gifticon;
import com.conseller.conseller.entity.Store;
import com.conseller.conseller.entity.User;
import com.conseller.conseller.exception.CustomException;
import com.conseller.conseller.exception.CustomExceptionStatus;
import com.conseller.conseller.gifticon.enums.GifticonStatus;
import com.conseller.conseller.gifticon.repository.GifticonRepository;
import com.conseller.conseller.store.dto.mapper.StoreMapper;
import com.conseller.conseller.store.dto.request.ModifyStoreRequest;
import com.conseller.conseller.store.dto.request.RegistStoreRequest;
import com.conseller.conseller.store.dto.request.StoreListRequest;
import com.conseller.conseller.store.dto.response.*;
import com.conseller.conseller.store.enums.StoreStatus;
import com.conseller.conseller.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class StoreServiceImpl implements StoreService {
    private final StoreRepository storeRepository;
    private final UserRepository userRepository;
    private final GifticonRepository gifticonRepository;
    private final StoreRepositoryImpl storeRepositoryImpl;

    // 스토어 목록
    @Transactional(readOnly = true)
    public StoreListResponse getStoreList(StoreListRequest request) { //queryDSL 사용
        Pageable pageable = PageRequest.of(request.getPage() - 1, 10);

        Page<Store> stores = storeRepositoryImpl.findStoreList(request, pageable);

        List<StoreItemData> storeItemDataList = StoreMapper.INSTANCE.storesToItemDatas(stores.getContent());

        StoreListResponse response = new StoreListResponse(storeItemDataList,
                stores.getTotalElements(),
                stores.getTotalPages());

        return response;
    }

    // 스토어 글 등록
    public Long registStore(RegistStoreRequest request) {

        User user = userRepository.findById(request.getUserIdx())
                .orElseThrow(() -> new CustomException(CustomExceptionStatus.USER_INVALID));
        Gifticon gifticon = gifticonRepository.findById(request.getGifticonIdx())
                .orElseThrow(() -> new CustomException(CustomExceptionStatus.GIFTICON_INVALID));

        if(!gifticon.getGifticonStatus().equals(GifticonStatus.KEEP.getStatus())){
            throw new CustomException(CustomExceptionStatus.GIFTICON_NOT_KEEP);
        }else {
            Store store = StoreMapper.INSTANCE.registStoreRequestToStore(request, user, gifticon);

            store.setStoreEndDate(gifticon.getGifticonEndDate());

            gifticon.setGifticonStatus(GifticonStatus.STORE.getStatus());

            Store saveStore = storeRepository.save(store);

            return saveStore.getStoreIdx();
        }
    }

    // 스토어 글 상세보기
    @Transactional(readOnly = true)
    public DetailStoreResponse detailStore(Long storeIdx) {
        Store store = storeRepository.findById(storeIdx)
                .orElseThrow(() -> new CustomException(CustomExceptionStatus.STORE_INVALID));

        DetailStoreResponse response = StoreMapper.INSTANCE.entityToDetailStoreResponse(store);

        return response;
    }

    //스토어 글 수정
    public void modifyStore(Long storeIdx , ModifyStoreRequest storeRequest) {
        Store store = storeRepository.findById(storeIdx)
                .orElseThrow(() -> new CustomException(CustomExceptionStatus.STORE_INVALID));

        if(store.getStoreStatus().equals(StoreStatus.IN_PROGRESS.getStatus())){
            store.setStorePrice(storeRequest.getStorePrice());
            store.setStoreText(storeRequest.getStoreText());
        }
        else {
            throw new CustomException(CustomExceptionStatus.ALREADY_TRADE_STORE);
        }
    }

    // 스토어 글 삭제
    public void deleteStore(Long storeIdx) {
        Store store = storeRepository.findById(storeIdx)
                .orElseThrow(() -> new CustomException(CustomExceptionStatus.STORE_INVALID));
        Gifticon gifticon = gifticonRepository.findById(store.getGifticon().getGifticonIdx())
                        .orElseThrow(() -> new CustomException(CustomExceptionStatus.GIFTICON_INVALID));

        gifticon.setGifticonStatus(GifticonStatus.KEEP.getStatus());

        storeRepository.deleteById(storeIdx);
    }

    // 스토어 거래 진행
    @Override
    public StoreTradeResponse tradeStore(Long storeIdx, Long consumerIdx) {
        Store store = storeRepository.findById(storeIdx)
                .orElseThrow(() -> new CustomException(CustomExceptionStatus.STORE_INVALID));
        User consumer = userRepository.findById(consumerIdx)
                .orElseThrow(() -> new CustomException(CustomExceptionStatus.USER_INVALID));

        StoreTradeResponse response = null;

        if(store.getStoreStatus().equals(StoreStatus.IN_PROGRESS.getStatus())){
            // 구매자의 인덱스를 저장
            store.setConsumer(consumer);

            // 거래 상태 거래중으로 변경
            store.setStoreStatus(StoreStatus.IN_TRADE.getStatus());

            response = new StoreTradeResponse(store.getUser().getUsername() ,store.getUser().getUserAccount(),
                    store.getUser().getUserAccountBank());
        }
        else {
            throw new CustomException(CustomExceptionStatus.ALREADY_TRADE_STORE);
        }

        return response;
    }

    // 스토어 거래 취소
    @Override
    public void cancelStore(Long storeIdx) {
        Store store = storeRepository.findById(storeIdx)
                .orElseThrow(() -> new CustomException(CustomExceptionStatus.STORE_INVALID));

        // 거래 상태를 진행중으로 변경
        store.setStoreStatus(StoreStatus.IN_PROGRESS.getStatus());

        // 구매자 정보 삭제
        store.setConsumer(null);
    }

    // 스토어 거래 확정
    @Override
    public void confirmStore(Long storeIdx) {
        Store store = storeRepository.findById(storeIdx)
                .orElseThrow(() -> new CustomException(CustomExceptionStatus.STORE_INVALID));
        Gifticon gifticon = gifticonRepository.findById(store.getGifticon().getGifticonIdx())
                .orElseThrow(() -> new CustomException(CustomExceptionStatus.GIFTICON_INVALID));
        User user = userRepository.findById(store.getConsumer().getUserIdx())
                .orElseThrow(() -> new CustomException(CustomExceptionStatus.USER_INVALID));

        store.setStoreStatus(StoreStatus.AWARDED.getStatus());

        gifticon.setUser(user);
        gifticon.setGifticonStatus(GifticonStatus.KEEP.getStatus());
    }

    // 스토어 판매자 입금확인
    @Override
    public StoreConfirmResponse getConfirmStore(Long storeIdx) {
        Store store = storeRepository.findById(storeIdx)
                .orElseThrow(() -> new CustomException(CustomExceptionStatus.STORE_INVALID));

        StoreConfirmResponse response = StoreMapper.INSTANCE.storeToComfirm(store);

        return response;
    }

    @Override
    public List<Store> getStoreConfirmList() {
        List<Store> stores = storeRepository.findByStoreListConfirm();

        return stores;
    }

    @Override
    public List<Store> getStoreExpiredList() {
        return storeRepository.findStoreAllExpired();
    }

    @Override
    public void rejectStore(Long storeIdx) {
        Store store = storeRepository.findById(storeIdx)
                .orElseThrow(() -> new CustomException(CustomExceptionStatus.STORE_INVALID));
        Gifticon gifticon = gifticonRepository.findById(store.getGifticon().getGifticonIdx())
                        .orElseThrow(() -> new CustomException(CustomExceptionStatus.GIFTICON_INVALID));

        store.setStoreStatus(StoreStatus.EXPIRED.getStatus());
        gifticon.setGifticonStatus(GifticonStatus.KEEP.getStatus());
    }

    @Override
    public List<Integer> getMainCategory() {
        List<Store> stores = storeRepository.findAwardedStoreList();

        int[] mainCategoryCount = new int[6];

        for(Store store : stores) {
            int idx = store.getGifticon().getMainCategory().getMainCategoryIdx();
            mainCategoryCount[idx]++;
        }

        int maxIdx = 1;
        for(int i = 1; i < 6; i++) {
            if(mainCategoryCount[i] > mainCategoryCount[maxIdx]){
                maxIdx = i;
            }
        }

        int secondIdx = 2;
        for(int i = 1; i < 6; i++) {
            if(maxIdx != i && mainCategoryCount[i] > mainCategoryCount[maxIdx]){
                secondIdx = i;
            }
        }

        List<Integer> list = new ArrayList<>();
        list.add(maxIdx);
        list.add(secondIdx);

        return list;
    }

    @Override
    public List<Integer> getSubCategory() {
        List<Store> stores = storeRepository.findAwardedStoreList();

        int[] subCategoryCount = new int[14];

        for(Store store : stores) {
            int idx = store.getGifticon().getSubCategory().getSubCategoryIdx();
            subCategoryCount[idx]++;
        }

        int maxIdx = 1;
        for(int i = 1; i < 14; i++) {
            if(subCategoryCount[i] > subCategoryCount[maxIdx]){
                maxIdx = i;
            }
        }

        int secondIdx = 2;
        for(int i = 1; i < 14; i++) {
            if(maxIdx != i && subCategoryCount[i] > subCategoryCount[maxIdx]){
                secondIdx = i;
            }
        }

        List<Integer> list = new ArrayList<>();
        list.add(maxIdx);
        list.add(secondIdx);

        return list;
    }

}
