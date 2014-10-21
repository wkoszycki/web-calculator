package com.cinemacity.calculator.service;

import com.cinemacity.calculator.validation.CalculatorValidator;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import org.apache.commons.jexl2.JexlContext;
import org.apache.commons.jexl2.JexlEngine;
import org.apache.commons.jexl2.JexlException;
import org.apache.commons.jexl2.MapContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Wojciech Koszycki <wojciech.koszycki@coi.gov.pl>
 */
@SessionScoped
public class CalculatorService implements Serializable {

    protected static final String ILLEGAL_ARGUMENT = "IllegalArgumentException";
    private final Logger logger = LoggerFactory.getLogger(CalculatorService.class);
    private JexlContext ctx;

    /**
     * Initializes this object.
     */
    @PostConstruct
    public void init() {
        logger.info("Creating Calculation service");
        this.ctx = new MapContext();
    }

    /**
     * Calculates value from given math string. Throwing exception is expensive,
     * so instead we will simply return String value.
     *
     * @param mathString mathematical string
     * @return IllegalArgumentException when math String is not correct or when
     * JexlException occurs otherwise calculated value
     */
    public String calculateString(String mathString) {
        if (!CalculatorValidator.isMathStringValid(mathString)) {
            return ILLEGAL_ARGUMENT;
        }
        try {
            Object result = new JexlEngine().createExpression(normalize(mathString)).evaluate(ctx);
            return result.toString();
        } catch (JexlException e) {
            logger.error("Error during paring expression: " + mathString);
            return ILLEGAL_ARGUMENT;
        }
    }

    /**
     * Normalizes input String to be parsed by Expression Engine.
     *
     * @param mathString mathematical String
     * @return normalized String
     */
    private String normalize(String mathString) {
        //TODO: Change it to Pattern Matcher to be more efficient.
        return mathString
            .replaceAll("\\{", "(")
            .replaceAll("\\}", ")")
            .replaceAll("\\[", "(")
            .replaceAll("\\]", ")");
    }

    /**
     * Simple getter.
     *
     * @return Illegal argument String value
     */
    public static String getILLEGAL_ARGUMENT() {
        return ILLEGAL_ARGUMENT;
    }

}
