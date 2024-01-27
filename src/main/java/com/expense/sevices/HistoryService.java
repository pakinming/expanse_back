package com.expense.sevices;

import com.expense.dto.ReqPaginationDto;
import com.expense.dto.ResPaginationDto;
import com.expense.entity.HistoryEntity;
import com.expense.repository.HistoryRepository;
import jakarta.transaction.Transactional;
import org.apache.coyote.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collections;


@Service
public class HistoryService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private HistoryRepository historyRepository;


    public Object getAllHistory(ReqPaginationDto req) throws Exception {
        Sort sort = Sort.by(Sort.Direction.fromString(req.getSortDirection()), req.getSortBy());
        Pageable pageable = PageRequest.of(req.getPageNo(), req.getPageSize(), sort);

        Page<HistoryEntity> paging = historyRepository.findAll(pageable);
        logger.debug("getAllHistory {}", paging);


        return ResPaginationDto.builder()
                .content(Collections.singletonList(paging.getContent()))
                .numberOfResult(paging.getNumberOfElements())
                .totalResult(paging.getTotalElements())
                .totalPages(paging.getTotalPages())
                .build();

    }

    public HistoryEntity getHistoryOne(Integer id) throws Exception {

        var history = historyRepository.findById(id);
        if (history.isPresent()) {
            return history.get();
        } else {
            throw new BadRequestException("Not found your id " + id);
        }

    }

    @Transactional(rollbackOn = Exception.class)
    public void deleteHistoryById(Integer id) throws Exception {
        historyRepository.deleteById(getHistoryOne(id).getId());
    }

    @Transactional(rollbackOn = Exception.class)
    public HistoryEntity createHistory(HistoryEntity history) throws Exception {
        return historyRepository.save(history);

    }


}
