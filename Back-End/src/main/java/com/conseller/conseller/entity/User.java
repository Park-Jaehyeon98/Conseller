package com.conseller.conseller.entity;

import com.conseller.conseller.user.enums.AccountBanks;
import com.conseller.conseller.user.enums.UserStatus;
import lombok.*;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter @Builder
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
}
