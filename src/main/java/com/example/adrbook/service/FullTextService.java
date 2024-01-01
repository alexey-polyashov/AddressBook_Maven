package com.example.adrbook.service;

import com.example.adrbook.dto.FullTextMapper;
import com.example.adrbook.dto.FullTextResult;
import com.example.adrbook.entity.FullTextIndex;
import com.example.adrbook.repo.FullTextRepo;
import com.example.adrbook.utility.DataType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FullTextService {
    @Autowired
    private FullTextRepo fullTextRepo;
    @Autowired
    private FullTextMapper ftMapper;

    public List<FullTextResult> findAllObjects(String text){
        List<FullTextIndex> fullTextIndexList = fullTextRepo.findFullTextIndexByTextContaining(text);
        return fullTextIndexList.stream()
                .map(ftMapper::FullTextIndexToResult)
                .collect(Collectors.toList());
    }

    public List<FullTextResult> findAllObjects(String text, DataType dataType){
        List<FullTextIndex> fullTextIndexList = fullTextRepo.findFullTextIndexByTextContainingAndDataType(text, dataType);
        return fullTextIndexList.stream()
                .map(ftMapper::FullTextIndexToResult)
                .collect(Collectors.toList());
    }
}
