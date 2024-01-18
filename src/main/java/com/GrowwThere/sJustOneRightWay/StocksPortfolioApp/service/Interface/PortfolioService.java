package com.GrowwThere.sJustOneRightWay.StocksPortfolioApp.service.Interface;

import com.GrowwThere.sJustOneRightWay.StocksPortfolioApp.dto.Responses.PortfolioResponseDto;

public interface PortfolioService {
    PortfolioResponseDto fetchUserPortfolioById(Long userId);
}
