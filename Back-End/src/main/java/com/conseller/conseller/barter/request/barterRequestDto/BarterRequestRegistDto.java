package com.conseller.conseller.barter.request.barterRequestDto;



import com.conseller.conseller.entity.Barter;
import com.conseller.conseller.entity.BarterRequest;
import com.conseller.conseller.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BarterRequestRegistDto {
    private Long barterIdx;
    private String userId;

    public BarterRequest toEntity(Barter barter, User user) {
        return BarterRequest.builder()
                .barter(barter)
                .user(user)
                .build();
    }
}
