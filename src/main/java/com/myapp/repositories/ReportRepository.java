package com.myapp.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.myapp.models.Report;

public interface ReportRepository extends BaseRepository<Report>, PagingAndSortingRepository<Report, Long> {

}
