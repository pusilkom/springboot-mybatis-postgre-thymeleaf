package com.pusilkom.demo.controller;

import com.github.dandelion.datatables.core.ajax.DataSet;
import com.github.dandelion.datatables.core.ajax.DatatablesCriterias;
import com.github.dandelion.datatables.core.ajax.DatatablesResponse;
import com.pusilkom.demo.dto.form.cmd.InstitusiCmd;
import com.pusilkom.demo.dto.form.search.InstitusiSearchForm;
import com.pusilkom.demo.dto.table.InstitusiItem;
import com.pusilkom.demo.dto.view.InstitusiDetail;
import com.pusilkom.demo.model.Institusi;
import com.pusilkom.demo.service.InstitusiService;
import com.pusilkom.demo.util.DebugUtil;
import com.pusilkom.demo.util.RestValidatorUtil;
import com.pusilkom.demo.validator.form.cmd.InstitusiCmdValidator;
import com.pusilkom.demo.validator.form.search.InstitusiSearchFormValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Created by fahri on 1/7/17.
 */
@Controller
public class InstitusiController {

    Logger log = LoggerFactory.getLogger(this.getClass());

    final static String ERROR_MSG = "Failed to process";

    @Autowired InstitusiService institusiService;

    @Autowired InstitusiCmdValidator institusiCmdValidator;
    @Autowired InstitusiSearchFormValidator institusiSearchFormValidator;

    @Autowired DebugUtil d;
    @Autowired RestValidatorUtil rv;

    @InitBinder("institusiCmd")
    protected void registerInstitusiCmdValidator(WebDataBinder binder) {
        binder.setValidator(institusiCmdValidator);
    }

    @InitBinder("institusiSearchForm")
    protected void registerInstitusiSearchFormValidator(WebDataBinder binder) {
        binder.setValidator(institusiSearchFormValidator);
    }

    @GetMapping(value = "/institusi")
    public String indexGet(@Valid InstitusiSearchForm searchForm, BindingResult result,
                           Model uiModel) {
        if (result.hasErrors()) {
            return "institusi/list";
        }

        return "institusi/list";
    }

    @GetMapping(value = "/institusi/add")
    public String getInstitusiAdd(Model uiModel) {
        InstitusiCmd institusiCmd = new InstitusiCmd();
        institusiCmd.setFlagAktif(true);

        uiModel.addAttribute("institusiCmd", institusiCmd);

        return "institusi/add";
    }

    @PostMapping(value = "/institusi/add")
    public String postInstitusiAdd(@Valid InstitusiCmd institusiCmd, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            return "institusi/add";
        }

        try {
            institusiService.saveCmd(institusiCmd);
        } catch (Exception e) {
            attributes.addFlashAttribute("ERROR", "Gagal tambah institusi");
            return "redirect:/institusi";
        }

        attributes.addFlashAttribute("SUCCESS", "Berhasil tambah institusi");
        return "redirect:/institusi";
    }

    @GetMapping(value = "/institusi/edit/{id}")
    public String editGet(@PathVariable Long id, Model uiModel, RedirectAttributes redirectAttributes) {
        InstitusiCmd cmd = institusiService.getInstitusiCmdByIdInstitusi(id);
        if (cmd == null) {
            redirectAttributes.addFlashAttribute("ERROR", "Institusi tidak ditemukan");
            return "redirect:/institusi";
        }

        uiModel.addAttribute("institusiCmd", cmd);

        return "institusi/edit";
    }

    @PostMapping(value = "/institusi/edit/{id}")
    public String postInstitusiEdit(@PathVariable Long id, @Valid InstitusiCmd institusiCmd, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            return "institusi/edit";
        }

        try {
            institusiService.saveCmd(institusiCmd);
        } catch (Exception e) {
            attributes.addFlashAttribute("ERROR", "Gagal ubah institusi");
            return "redirect:/institusi";
        }

        attributes.addFlashAttribute("SUCCESS", "Berhasil ubah institusi");
        return "redirect:/institusi";
    }

    @RequestMapping(value = "/institusi/table", method = RequestMethod.POST)
    @ResponseBody
    public DatatablesResponse<InstitusiItem> postTableSearch(InstitusiSearchForm searchForm, HttpServletRequest httpServletRequest) {

        DatatablesCriterias criterias = DatatablesCriterias.getFromRequest(httpServletRequest);
        searchForm.setCriterias(criterias);

        DataSet<InstitusiItem> dataSet = null;
        try {
            log.info(">>> searchForm : {}", d.toString(searchForm));

            dataSet = institusiService.getDataSet(searchForm);

        } catch (Exception e) {
            log.error("TABLE INSTITUSI : ", e);

        }

        return DatatablesResponse.build(dataSet, criterias);
    }

}
