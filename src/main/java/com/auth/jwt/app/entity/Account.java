package com.auth.jwt.app.entity;

import lombok.*;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "account")
public class Account {

    @Id
    @Column(length = 12, nullable = false)
    private String accountId;

    @Column
    @Builder.Default
    private boolean active = true;

    @Column( length = 64)
    private String type;

    @Column( length = 128)
    private String account;

    @Column( length = 64)
    private String division;

    @Column( length = 64)
    private String sicCode;

    @Column( length = 12)
    private String parentId;

    @Column(length = 128)
    private String description;

    @Column(length = 12)
    private String addressId;

    @Column( length = 12)
    private String shippingId;

    @Column( length = 64)
    private String region;

    @Column(length = 32)
    private String mainPhone;

    @Column(length = 32)
    private String alternatePhone;

    @Column(length = 32)
    private String fax;

    @Column(length = 32)
    private String tollFree;

    @Column(length = 32)
    private String tollFree2;

    @Column(length = 32)
    private String otherPhone1;

    @Column(length = 32)
    private String otherPhone2;

    @Column(length = 32)
    private String otherPhone3;

    @Column(length = 128)
    private String email;

    @Column(length = 64)
    private String emailType;

    @Column(length = 128)
    private String webAddress;

    @Column(length = 12, nullable = false)
    private String secCodeId;

    @Column(precision = 17, scale = 4)
    private BigDecimal revenue;

    @Column
    private Integer employees;

    @Column(length = 64)
    private String industry;

    @Column(length = 10)
    private String creditRating;

    @Column(columnDefinition = "TEXT")
    private String notes;

    @Column(length = 64)
    private String status;

    @Column(length = 12)
    private String accountManagerId;

    @Column(length = 12)
    private String regionalManagerId;

    @Column(length = 12)
    private String divisionalManagerId;

    @Column(length = 1)
    private String nationalAccount;

    @Column(length = 1)
    private String targetAccount;

    @Column(length = 64)
    private String territory;

    @Column(length = 12)
    private String createUser;

    @Column(length = 12)
    private String modifyUser;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifyDate;

    @Column(length = 128)
    private String accountUc;

    @Column(length = 64)
    private String aka;

    @Column(length = 64)
    private String currencyCode;

    @Column(length = 32)
    private String internalAccountNo;

    @Column(length = 32)
    private String externalAccountNo;

    @Column(length = 32)
    private String parentAccountNo;

    @Column(length = 8)
    private String alternateKeyPrefix;

    @Column(length = 24)
    private String alternateKeySuffix;

    @Column(length = 12)
    private String defaultTicketSecCodeId;

    @Column(length = 1)
    private String notifyDefects;

    @Column(length = 1)
    private String notifyOnClose;

    @Column(length = 1)
    private String notifyOnStatus;

    @Column(length = 255)
    private String shortNotes;

    @Column(length = 80)
    private String userField1;

    @Column(length = 80)
    private String userField2;

    @Column(length = 80)
    private String userField3;

    @Column(length = 80)
    private String userField4;

    @Column(length = 80)
    private String userField5;

    @Column(length = 80)
    private String userField6;

    @Column(length = 80)
    private String userField7;

    @Column(length = 80)
    private String userField8;

    @Column(length = 80)
    private String userField9;

    @Column(length = 80)
    private String userField10;

    @Column(length = 12)
    private String campaignId;

    @Column(length = 1)
    private String doNotSolicit;

    @Column(length = 32)
    private String score;

    @Column(length = 16)
    private String ticker;

    @Column(length = 64)
    private String subtype;

    @Column(length = 12)
    private String leadSourceId;

    @Column(length = 24)
    private String importSource;

    @Column(length = 12)
    private String engineerId;

    @Column(length = 12)
    private String salesEngineerId;

    @Column
    private Integer relationship;

    @Column(length = 12)
    private String lastHistoryBy;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastHistoryDate;

    @Column(length = 2000)
    private String businessDescription;

    @Column(length = 128)
    private String webAddress2;

    @Column(length = 128)
    private String webAddress3;

    @Column(length = 128)
    private String webAddress4;

    @Column(length = 36)
    private String globalSyncId;

    @Column(length = 12)
    private String appId;

    @Column
    private Integer tick;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastErpSyncUpdate;

    @Column(length = 12)
    private String primaryOperatingCompId;

    @Column(length = 1)
    private String promotedToAccounting;

    @Column(length = 64)
    private String createSource;

    /*----------References de Ciente y Account -----------------*/
    @ManyToMany(mappedBy = "account")
    private Set<Cliente> clientes =new HashSet<>();


}