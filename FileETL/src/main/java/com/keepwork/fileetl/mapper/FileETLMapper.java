package com.keepwork.fileetl.mapper;

import com.keepwork.fileetl.model.FileETL;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface FileETLMapper {

    List<FileETL> Sel();
}
