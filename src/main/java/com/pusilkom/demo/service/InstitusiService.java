package com.pusilkom.demo.service;

import com.github.dandelion.datatables.core.ajax.DataSet;
import com.pusilkom.demo.dto.form.cmd.InstitusiCmd;
import com.pusilkom.demo.dto.form.search.InstitusiSearchForm;
import com.pusilkom.demo.dto.table.InstitusiItem;
import com.pusilkom.demo.dto.view.InstitusiDetail;
import com.pusilkom.demo.model.Institusi;
import com.pusilkom.demo.model.InstitusiExample;
import com.pusilkom.demo.model.mapper.InstitusiMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by fahri on 1/7/17.
 */


@Service
@Transactional(readOnly = true)
public class InstitusiService {

    Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired InstitusiMapper institusiMapper;

    public Institusi getById(Long id) {
        return institusiMapper.selectByPrimaryKey(id);
    }

    @Transactional(readOnly = false)
    public void saveCmd(InstitusiCmd institusiCmd) {

        boolean isNew = (institusiCmd.getId() == null);

        //business logic di sini

        this.save(institusiCmd.toInstitusi());
    }

    @Transactional(readOnly = false)
    public void delete(Long id) {
        institusiMapper.deleteByPrimaryKey(id);
    }

    @Transactional(readOnly = false)
    public void save(Institusi institusi) {
        if (institusi.getId() == null) {
            institusiMapper.insert(institusi);
        } else {
            institusiMapper.updateByPrimaryKey(institusi);
        }
    }

    // Contoh prototype grid dgn DataTable
    public DataSet<InstitusiItem> getDataSet(InstitusiSearchForm searchForm) {
        List<InstitusiItem> listItem = institusiMapper.getListInstitusiItemBySearchForm(searchForm);
        Long totalItemFiltered = institusiMapper.getTotalInstitusiItemBySearchForm(searchForm);
        Long totalItem = totalItemFiltered;

        return new DataSet<InstitusiItem>(listItem, totalItem, totalItemFiltered);
    }

    public InstitusiDetail getInstitusiDetailByKodeInstitusiCara1(String kodeInstitusi) {

        // 1) Query ada di @Mapper pada method Mapper Java
        return institusiMapper.getInstitusiDetailByKodeInstitusiCara1(kodeInstitusi);
    }

    public InstitusiDetail getInstitusiDetailByKodeInstitusiCara2(String kodeInstitusi) {

        // 2) Query ada di sini. Dibuat secara programmatically menggunakan ModelExample
        InstitusiExample example = new InstitusiExample();
        example.createCriteria()
                .andKodeInstitusiEqualTo(kodeInstitusi);

        List<Institusi> list = institusiMapper.selectByExample(example);
        Institusi institusi = list.get(0);

        InstitusiDetail institusiDetail = null;
        if (institusi != null) {
            institusiDetail = new InstitusiDetail(institusi);
        }

        return institusiDetail;
    }

    public InstitusiDetail getInstitusiDetailByKodeInstitusiCara3(String kodeInstitusi) {

        // 3) Query ada di Mapper XML. Lokasi : src/main/resources/com/pusilkom/euis/model/mapper/custom/InstitusiMapper.xml
        // Perhatikan sekali lagi, mapper xml utk custom query ada di folder 'custom'
        return institusiMapper.getInstitusiDetailByKodeInstitusiCara3(kodeInstitusi);
    }

    public InstitusiCmd getInstitusiCmdByIdInstitusi(Long idInstitusi){
        Institusi institusi = getById(idInstitusi);
        if (institusi == null) {
            return null;
        }

        InstitusiCmd cmd = new InstitusiCmd();
        cmd.setId(institusi.getId());
        cmd.setKodeInstitusi(institusi.getKodeInstitusi());
        cmd.setNama(institusi.getNama());
        cmd.setNamaSingkat(institusi.getNamaSingkat());
        cmd.setFlagAktif(institusi.getFlagAktif());

        return cmd;
    }

    public boolean hasDuplicateInstitusiByInstitusiCmd(InstitusiCmd institusiCmd) {
        boolean isEdit = (institusiCmd.getId() != null);
        InstitusiExample InstitusiExample = new InstitusiExample();
        com.pusilkom.demo.model.InstitusiExample.Criteria criteria = InstitusiExample.createCriteria();
        criteria.andKodeInstitusiEqualTo(institusiCmd.getKodeInstitusi());

        if(isEdit) criteria.andIdNotEqualTo(institusiCmd.getId());

        List<Institusi> list = institusiMapper.selectByExample(InstitusiExample);

        return !list.isEmpty();
    }
}
