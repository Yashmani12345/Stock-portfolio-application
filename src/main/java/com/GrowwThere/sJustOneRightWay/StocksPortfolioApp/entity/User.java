package com.GrowwThere.sJustOneRightWay.StocksPortfolioApp.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;
    private String userName;
//    @OneToMany(mappedBy = "user")
//    private List<Trade> tradeList=new ArrayList<>();
}
