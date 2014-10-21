package com.cinemacity.calculator.service;

import com.cinemacity.calculator.exception.InvalidMathStringException;
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
     * it could be optimized by simply return String value but: optimize only if
     * needed.
     *
     * @param mathString mathematical string
     * @return calculated value
     * @throws com.cinemacity.calculator.exception.InvalidMathStringException
     * when mathematical expression is invalid
     */
    public String calculateString(String mathString) throws InvalidMathStringException {
        if (!CalculatorValidator.isMathStringValid(mathString)) {
            throw new InvalidMathStringException();
        }
        try {
            Object result = new JexlEngine().createExpression(normalize(mathString)).evaluate(ctx);
            return result.toString();
        } catch (JexlException e) {
            throw new InvalidMathStringException();
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

}
