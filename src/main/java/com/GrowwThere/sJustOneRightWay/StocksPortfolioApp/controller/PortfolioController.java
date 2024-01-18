package com.GrowwThere.sJustOneRightWay.StocksPortfolioApp.controller;

import com.GrowwThere.sJustOneRightWay.StocksPortfolioApp.dto.Responses.PortfolioResponseDto;
import com.GrowwThere.sJustOneRightWay.StocksPortfolioApp.service.Interface.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/portfolio")
public class PortfolioController {
    @Autowired
    PortfolioService portfolioService;
    @GetMapping("/{id}")
    public PortfolioResponseDto fetchUserPortfolioById(@PathVariable ("id") Long userId)
    {
        return portfolioService.fetchUserPortfolioById(userId);
    }
}
