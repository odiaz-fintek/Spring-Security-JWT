package com.auth.jwt.app.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Builder
@Entity
@Table (name = "clientes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class Cliente implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clienteid; /*Antiguo contactid*/

    @Column (length = 64)
    private Long type;

    @Column
    private String accountid;

    @Column(length = 128)
    private String account;

    @Column(length = 32)
    private String department;

    @Column
    private Boolean isprimary;

    @Column(length = 32)
    private String lastname;

    @Column(length = 32)
    private String firstname;

    @Column(length = 32)
    private String middlename;

    @Column(length = 16)
    private String prefix;

    @Column(length = 16)
    private String suffix;

    @Column
    private String addressid;

    @Column
    private String shippingid;

    @Column(length = 32)
    private String workphone;

    @Column(length = 32)
    private String homephone;

    @Column(length = 32)
    private String fax;

    @Column(length = 32)
    private String mobile;

    @Column(length = 32)
    private String pager;

    @Column(length = 7)
    private String pinNumber;

    @Column(length = 32)
    private String pagerNumeric;

    @Column(length = 128)
    private String email;

    @Column(length = 128)
    private String secondaryEmail;

    @Column(length = 128)
    private String webAddress;

    @Column(length = 128)
    private String description;

    @Column(length = 64)
    private String title;

    @Column(length = 64)
    private String assistant;

    @Temporal(TemporalType.TIMESTAMP)
    private Date birthday;

    @Column(length = 64)
    private String spouse;

    @Temporal(TemporalType.TIMESTAMP)
    private Date spouseBirthday;

    @Column(length = 64)
    private String alumni;

    
    private Integer yearGraduated;

    @Column(length = 128)
    private String interests;

    @Column(length = 64)
    private String previousEmployer;

    @Lob
    private String directions;

    @Column(length = 64)
    private String reportsTo;

    @Column(nullable = false)
    private String secCodeId;

    private String accountManagerId;

    private String regionalManagerId;

    private String divisionalManagerId;

    @Builder.Default
    private boolean status=true;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    private String createUser;

    @Temporal(TemporalType.TIMESTAMP)
    private Date modifyDate;

    private String modifyUser;

    @Column(length = 64)
    private String referral;

    @Column(length = 32)
    private String lastNameUc;

    @Column(length = 32)
    private String initials;

    @Column(length = 8)
    private String alternateKeyPrefix;

    @Column(length = 24)
    private String alternateKeySuffix;

    private String locationCode;

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

    @Column(length = 35)
    private String webPassword;

    @Column(length = 30)
    private String webUsername;

    private String weight;

    private Boolean doNotSolicit;

    @Column(length = 32)
    private String score;

    @Column(length = 128)
    private String email3;

    @Column(length = 1)
    private Character doNotEmail;

    @Column(length = 1)
    private Character doNotPhone;

    @Column(length = 1)
    private Character doNotMail;

    @Column(length = 1)
    private Character doNotFax;

    @Column(length = 24)
    private String importSource;

    @Column(length = 64)
    private String subtype;

    @Column(length = 32)
    private String otherPhone;

    @Column(length = 64)
    private String preferredContact;

    @Column(length = 64)
    private String webPasswordHint;

    @Column(length = 1)
    private Character isServiceAuthorized;

    @Column(length = 64)
    private String cuisinePref;

    @Column(length = 64)
    private String children;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastHistoryDate;

    @Column(length = 12)
    private Character lastHistoryBy;

    @Column(length = 128)
    private String webAddress2;

    @Column(length = 64)
    private String salutation;

    @Column(length = 255)
    private String categories;

    @Column(length = 36)
    private String globalSyncId;

    @Column(length = 12)
    private String appId;

    private Integer tick;

    @Column(length = 1)
    private String activeFlag;

    @Column(length = 16)
    private String gender;

    @Column(length = 32)
    private String preferredName;

    @Column(length = 64)
    private String nationality;

    @Column(length = 2)
    private String primaryLanguage;

    @Column(length = 32)
    private String maritalStatus;

    @Column(length = 32)
    private String companyContext;

    @Column(length = 64)
    private String createSource;

    @Column(length = 12)
    private Character slxUserAssociationId;

    @Column(length = 36)
    private String changeKey;

    @Column(length = 32)
    private String webPasswordResetToken;

    @Temporal(TemporalType.TIMESTAMP)
    private Date webTokenExpiration;

    /*----------References de Ciente y Account -----------------*/
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "ciente_account",
            joinColumns = {@JoinColumn(name = "clienteid")},
            inverseJoinColumns = {@JoinColumn(name = "accountid")}
    )
    private Set<Account> accounts =new HashSet<>();

}
