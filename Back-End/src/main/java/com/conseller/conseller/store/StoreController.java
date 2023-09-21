package com.conseller.conseller.store;

import com.conseller.conseller.store.dto.request.ModifyStoreRequest;
import com.conseller.conseller.store.dto.request.RegistStoreRequest;
import com.conseller.conseller.store.dto.request.StoreListRequest;
import com.conseller.conseller.store.dto.response.DetailStoreResponse;
import com.conseller.conseller.store.dto.response.StoreListResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/store")
public class StoreController {

    private final StoreService storeService;

    private final String SUCCESS = "success";

    // 판매 목록
    @PostMapping
    public ResponseEntity<StoreListResponse> getStoreList(@RequestBody StoreListRequest request) {
            StoreListResponse response = storeService.getStoreList(request);

        return ResponseEntity.ok()
                .body(response);
    }

    // 판매 글 등록
    @PostMapping("/regist")
    public ResponseEntity<Object> registStore(@RequestBody RegistStoreRequest request) {
        storeService.registStore(request);

        return ResponseEntity.ok()
                .build();
    }

    // 판매 글 상세보기
    @GetMapping("/{store_idx}")
    public ResponseEntity<DetailStoreResponse> detailStore(@PathVariable("store_idx") Long storeIdx) {
        DetailStoreResponse detailStoreResponse = storeService.detailStore(storeIdx);

        return ResponseEntity.ok()
                .body(detailStoreResponse);
    }

    // 판매 글 수정
    @PatchMapping("/{store_idx}")
    public ResponseEntity<Object> modifyStore(@PathVariable("store_idx") Long storeIdx, @RequestBody ModifyStoreRequest storeRequest) {
        storeService.modifyStore(storeIdx, storeRequest);
        return ResponseEntity.ok()
                .build();
    }

    // 판매 글 삭제
    @DeleteMapping("/{store_idx}")
    public ResponseEntity<Object> deleteStore(@PathVariable("store_idx") Long storeIdx) {
        storeService.deleteStore(storeIdx);
        return ResponseEntity.ok()
                .build();
    }

    // 판매 상태 변경
    @PatchMapping("/{store_idx}/status")
    public ResponseEntity<Object> storeStatusPermute(@PathVariable("store_idx") Long storeIdx) {
        storeService.storeStatusPermute(storeIdx);
        return ResponseEntity.ok()
                .build();
    }


}
