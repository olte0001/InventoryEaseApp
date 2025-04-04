package com.group17.inventoryease.ums.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "locator")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Locator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "locator_id")
    private Long locatorId;

    @Column(name = "storage1")
    private String storage1;

    @Column(name = "storage2")
    private String storage2;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;
}
