package com.will.stock.facade;

import com.will.stock.repository.LockRepository;
import com.will.stock.repository.StockRepository;
import com.will.stock.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class NamedLockStockFacade {

    private final StockService stockService;

    private final LockRepository lockRepository;

    @Transactional
    public void decrease(Long stockId, Long quantity) {
        try {
            lockRepository.getLock(stockId.toString());
            stockService.decrease(stockId, quantity);
        } finally {
            lockRepository.releaseLock(stockId.toString());
        }
    }
}
