package com.jets.controller.binder;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

/**
 * Binder for Date objects
 * @author M. ALI
 * @author Hamada Abdrabou
 */
@Controller
public class DateBinder {
	
	/**
     * Used for mapping Date Objects from submitted forms to java.util.Date
     * @param binder used to register editors
     */
    @InitBinder
    public void initDateBinder(WebDataBinder binder){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        CustomDateEditor customEditor = new CustomDateEditor(dateFormat, true);
        binder.registerCustomEditor(Date.class, customEditor);
    }

}
