package com.example.adrbook.repo;

import com.example.adrbook.entity.FullTextIndex;
import com.example.adrbook.utility.DataType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FullTextRepo   extends JpaRepository<FullTextIndex, Long> {
    List<FullTextIndex> findFullTextIndexByTextContaining(String text);
    List<FullTextIndex> findFullTextIndexByTextContainingAndDataType(String text, DataType dataType);
}
