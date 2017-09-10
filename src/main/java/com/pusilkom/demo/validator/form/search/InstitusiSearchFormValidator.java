package com.pusilkom.demo.validator.form.search;

import com.pusilkom.demo.dto.form.search.InstitusiSearchForm;
import com.pusilkom.demo.util.ValidatorUtil;
import com.pusilkom.demo.util.ValidatorWebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 *
 * @author Ruliantyo
 */
@Component
public class InstitusiSearchFormValidator implements Validator {
    @Autowired
    ValidatorWebUtil validatorWebUtil;
    @Autowired
    ValidatorUtil validatorUtil;

    @Override
    public boolean supports(Class<?> type) {
        return InstitusiSearchForm.class.isAssignableFrom(type);
    }
    
    @Override
    public void validate(Object o, Errors errors) {
        InstitusiSearchForm cmd = (InstitusiSearchForm) o;

//        validatorWebUtil.validateNotBlank("kodeInstitusi", errors, cmd.getKodeInstitusi());
//        validatorWebUtil.validateNotBlank("nama", errors, cmd.getNama());
//        validatorWebUtil.validateNotBlank("namaSingkat", errors, cmd.getNamaSingkat());
//        validatorWebUtil.validateNotBlank("flagAktif", errors, cmd.getFlagAktif());
    }
     
}
