package com.climber.elastic.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.climber.elastic.Stu;



@Repository
public interface StudentRepository extends ElasticsearchRepository<Stu, Long> {
	
	 /**
     * @param stuId
     * @return
     */
	Stu getByStuId(String stuId);
	
    /**
     * @param stuName
     * @return
     */
    List<Stu> getListByStuName(String stuName);

    /**
     * @param stuName
     * @param pageable
     * @return
     */
    Page<Stu> getPageByStuName(String stuName, Pageable pageable);
    
}
