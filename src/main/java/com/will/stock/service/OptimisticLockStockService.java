package com.will.stock.service;

import com.will.stock.domain.Stock;
import com.will.stock.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OptimisticLockStockService {

    private final StockRepository stockRepository;

    @Transactional
    public void decrease(Long stockId, Long quantity) {
        Stock stock = stockRepository.findByIdWithPessimisticLock(stockId);
        stock.decrease(quantity);
        stockRepository.saveAndFlush(stock);
    }
}
