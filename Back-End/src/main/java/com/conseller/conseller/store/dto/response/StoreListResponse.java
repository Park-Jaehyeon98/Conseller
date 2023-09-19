package com.conseller.conseller.store.dto.response;

import com.conseller.conseller.entity.Store;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StoreListResponse {
    private Page<Store> items;
}
