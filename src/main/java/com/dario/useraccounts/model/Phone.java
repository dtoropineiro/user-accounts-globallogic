package com.dario.useraccounts.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "PHONE")
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "PHONEID")
    @JsonIgnore
    private Long phoneId;
    @Column(name = "NUMBER")
    private String number;
    @JsonProperty("citycode")
    @Column(name = "CITYCODE")
    private String cityCode;
    @Column(name = "COUNTRYCODE")
    @JsonProperty("countrycode")
    private String countryCode;
    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER, cascade= CascadeType.MERGE)
    @JoinColumn(name = "USERID", nullable = false)
    private User user;


}
