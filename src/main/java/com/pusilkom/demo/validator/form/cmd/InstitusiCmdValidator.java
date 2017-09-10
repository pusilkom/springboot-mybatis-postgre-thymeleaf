package com.pusilkom.demo.validator.form.cmd;

import com.pusilkom.demo.dto.form.cmd.InstitusiCmd;
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
public class InstitusiCmdValidator implements Validator {
    @Autowired
    ValidatorWebUtil validatorWebUtil;
    @Autowired
    ValidatorUtil validatorUtil;

    @Override
    public boolean supports(Class<?> type) {
        return InstitusiCmd.class.isAssignableFrom(type);
    }
    
    @Override
    public void validate(Object o, Errors errors) {
        InstitusiCmd cmd = (InstitusiCmd) o;

        validatorWebUtil.validateNotBlank("kodeInstitusi", errors, cmd.getKodeInstitusi());
        validatorWebUtil.validateNotDuplicate("kodeInstitusi", errors, cmd);
        validatorWebUtil.validateNotBlank("nama", errors, cmd.getNama());
        validatorWebUtil.validateNotBlank("namaSingkat", errors, cmd.getNamaSingkat());
        validatorWebUtil.validateNotBlank("flagAktif", errors, cmd.getFlagAktif());
    }
     
}
