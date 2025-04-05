package com.group17.inventoryease.ums.models;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "alert")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Alert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "alert_id")
    private Long alertId;

    @Column(name = "item_sn")
    private String itemSn;

    @Column(name = "has_expired")
    private Boolean hasExpired;

    @Column(name = "low_stock")
    private Boolean lowStock;

    @Column(name = "alert_date")
    private LocalDateTime alertDate;
}
