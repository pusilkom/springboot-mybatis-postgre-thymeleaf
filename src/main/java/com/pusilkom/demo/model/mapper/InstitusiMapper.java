package com.pusilkom.demo.model.mapper;

import com.pusilkom.demo.dto.form.search.InstitusiSearchForm;
import com.pusilkom.demo.dto.table.InstitusiItem;
import com.pusilkom.demo.dto.view.InstitusiDetail;
import com.pusilkom.demo.model.Institusi;
import com.pusilkom.demo.model.InstitusiExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by fahri on 1/7/17.
 */
public interface InstitusiMapper extends BaseMapper<Institusi, Long, InstitusiExample> {

    @Select("SELECT * FROM institusi WHERE kode_institusi = #{kodeInstitusi} LIMIT 1")
    @ResultMap("InstitusiDetailResultMap")
    InstitusiDetail getInstitusiDetailByKodeInstitusiCara1(@Param("kodeInstitusi")String kodeInstitusi);


    InstitusiDetail getInstitusiDetailByKodeInstitusiCara3(@Param("kodeInstitusi")String kodeInstitusi);

    List<InstitusiItem> getListInstitusiItemBySearchForm(@Param("searchForm") InstitusiSearchForm searchForm);
    Long getTotalInstitusiItemBySearchForm(@Param("searchForm") InstitusiSearchForm searchForm);
}
