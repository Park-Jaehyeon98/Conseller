package com.conseller.conseller.entity;

import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Builder
@Getter @Setter @ToString
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@AllArgsConstructor
public class BlackList {

    @Id @GeneratedValue
    private Long id;

    @Column(name = "access_token", nullable = false)
    private String accessToken;

    @OneToOne
    @JoinColumn(name = "refresh_token", referencedColumnName = "refresh_token")
    private User user;
}
