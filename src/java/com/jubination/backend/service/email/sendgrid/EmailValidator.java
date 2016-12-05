/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jubination.backend.service.email.sendgrid;
 
import java.util.regex.Matcher;
import java.util.regex.Pattern;
 

public class EmailValidator {
    private Pattern pattern;
	private Matcher matcher;
 
	private final String EMAIL_PATTERN = 
		"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
		+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
 
	public EmailValidator() {
		pattern = Pattern.compile(EMAIL_PATTERN);
	}
        
	public boolean validate(final String username) {
 
		matcher = pattern.matcher(username);
		return matcher.matches();
 
	}
}
