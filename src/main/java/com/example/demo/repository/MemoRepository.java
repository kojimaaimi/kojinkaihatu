package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

import com.example.demo.entity.Memo;

@Repository
public interface MemoRepository extends JpaRepository<Memo, Long> {
    List<Memo> findByLoginId(Long loginId);
}
