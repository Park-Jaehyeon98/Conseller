package com.conseller.conseller.entity;

import com.conseller.conseller.user.dto.request.UserInfoRequest;
import com.conseller.conseller.user.enums.Authority;
import com.conseller.conseller.user.enums.UserStatus;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter @Setter @Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EqualsAndHashCode(of = "userIdx", callSuper = false)
@Table(name = "\"USER\"")
public class User extends BaseTime implements UserDetails {

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

    @Builder.Default
    @Column(name = "user_deposit", nullable = false)
    private Long userDeposit = (long) 0;

    @Column(name = "user_deleted_date")
    private LocalDateTime userDeletedDate;

    @Column(name = "user_name", nullable = false)
    private String userName;

    @Column(name = "user_account", nullable = false)
    private String userAccount;

    @Column(name = "user_account_bank")
    private String userAccountBank;

    @Builder.Default
    @Column(name = "user_status", nullable = false)
    private String userStatus = UserStatus.ACTIVE.getStatus();

    @Column(name = "user_restrict_end_date")
    private LocalDateTime userRestrictEndDate;

    @Builder.Default
    @Column(name = "user_restrict_count")
    private Integer userRestrictCount = 0;

    @Column(name = "user_profile_url")
    private String userProfileUrl;

    @Column(name = "refresh_token")
    private String refreshToken;

    @Column(name = "fcm_token")
    private String fcm;

    @Column(name = "user_pattern")
    private String userPattern;

    @Builder.Default
    @OneToMany(mappedBy = "user")
    private List<Auction> auctions = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "user")
    private List<AuctionBid> auctionBids = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "barterHost")
    private List<Barter> barters = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "user")
    private List<BarterRequest> barterRequests = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "user")
    private List<Gifticon> gifticons = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "user")
    private List<Inquiry> inquiries = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "user")
    private List<Store> stores = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "user")
    private List<NotificationEntity> notificationEntities = new ArrayList<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<String> roles = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return this.userPassword;
    }

    @Override
    public String getUsername() {
        return this.userId;
    }

    public String getName() {
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    //비밀번호 암호화
    public void encryptPassword(PasswordEncoder passwordEncoder) {
        this.userPassword = passwordEncoder.encode(this.userPassword);
    }

    //해당 비밀번호가 맞는지 확인
    public boolean checkPassword(PasswordEncoder passwordEncoder, String userPassword) {
        return passwordEncoder.matches(userPassword, this.userPassword);
    }

    public void addUserRole() {
        this.roles.add(Authority.USER.name());
    }

    public void addAdminRole() {
        this.roles.add(Authority.ADMIN.name());
    }

    public void updatePassword(String password) {
        this.userPassword = password;
        encryptPassword(new BCryptPasswordEncoder());
    }

    public void updateUserInfo(UserInfoRequest userInfoRequest) {

        if (!checkPassword(new BCryptPasswordEncoder(), userInfoRequest.getUserPassword())
        && !this.userPassword.equals(userInfoRequest.getUserPassword())) {
            this.userPassword = userInfoRequest.getUserPassword();
            encryptPassword(new BCryptPasswordEncoder());
        }

        this.userNickname = userInfoRequest.getUserNickname();
        this.userEmail = userInfoRequest.getUserEmail();
        this.userAccount = userInfoRequest.getUserAccount();
        this.userAccountBank = userInfoRequest.getUserAccountBank();
    }
}
