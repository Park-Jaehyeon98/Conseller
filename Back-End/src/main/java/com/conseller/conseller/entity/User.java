package com.conseller.conseller.entity;

import com.conseller.conseller.user.enums.AccountBanks;
import com.conseller.conseller.user.enums.UserStatus;
import lombok.*;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = "userIdx", callSuper = false)
@Table(name = "\"USER\"")
public class User extends BaseTime{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userIdx;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "user_password", nullable = false)
    private String userPassword;

    @Column(name = "user_email")
    private String userEmail;

    @Column(name = "user_phone_number", nullable = false)
    private String userPhoneNumber;

    @Column(name = "user_nickname", nullable = false)
    private String userNickname;

    @Column(name = "user_gender", nullable = false)
    private Character userGender;

    @Column(name = "user_age", nullable = false)
    private Integer userAge;

    @Column(name = "user_deposit", nullable = false)
    private Integer userDeposit;

    @Column(name = "user_deleted_date")
    private LocalDateTime userDeletedDate;

    @Column(name = "user_name", nullable = false)
    private String userName;

    @Column(name = "user_account", nullable = false)
    private String userAccount;

    @Enumerated(EnumType.STRING)
    private AccountBanks userAccountBank;

    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;

    @Column(name = "user_restrict_end_date")
    private LocalDateTime userRestrictEndDate;

    @Column(name = "user_restrict_count")
    private Integer userRestrictCount;

    @Column(name = "user_profile_url")
    private String userProfileUrl;

    @Column(name = "refresh_token")
    private String refreshToken;

    @OneToMany(mappedBy = "barterHost")
    List<Barter> barters = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    List<BarterRequest> barterRequests = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    List<Gifticon> gifticons = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    List<Inquiry> inquiries = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    List<Store> stores = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    List<Notification> notifications = new ArrayList<>();

    @Builder
    public User(String userId, String userPassword, String userEmail, String userPhoneNumber, String userNickname, Character userGender, Integer userAge, String userName, Integer userDeposit, LocalDateTime userDeletedDate, String userAccount, AccountBanks userAccountBank, UserStatus userStatus, LocalDateTime userRestrictEndDate, Integer userRestrictCount, String userProfileUrl, String refreshToken) {
        super();
        this.userId = userId;
        this.userPassword = userPassword;
        this.userEmail = userEmail;
        this.userPhoneNumber = userPhoneNumber;
        this.userNickname = userNickname;
        this.userGender = userGender;
        this.userAge = userAge;
        this.userName = userName;
        this.userDeposit = userDeposit;
        this.userDeletedDate = userDeletedDate;
        this.userAccount = userAccount;
        this.userAccountBank = userAccountBank;
        this.userStatus = userStatus;
        this.userRestrictEndDate = userRestrictEndDate;
        this.userRestrictCount = userRestrictCount;
        this.userProfileUrl = userProfileUrl;
        this.refreshToken = refreshToken;
    }
}
