package com.cinemacity.calculator.service;

import com.cinemacity.calculator.exception.InvalidMathStringException;
import com.cinemacity.calculator.validation.CalculatorValidator;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Wojciech Koszycki <wojciech.koszycki@gmail.com>
 */
@SessionScoped
public class CalculatorService implements Serializable {

    private final Logger logger = LoggerFactory.getLogger(CalculatorService.class);
    List<String> history;

    /**
     * Initializes this object.
     */
    @PostConstruct
    public void init() {
        logger.info("Creating Calculation service");
        history = new ArrayList<>();
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
            ScriptEngineManager mgr = new ScriptEngineManager();
            ScriptEngine engine = mgr.getEngineByName("JavaScript");
            Double result = (Double) engine.eval(normalize(mathString));
            history.add(mathString);
            if ((result == Math.floor(result)) && !Double.isInfinite(result)) {
                return String.valueOf(result.intValue());
            }
            return result.toString();
        } catch (ScriptException ex) {
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

    /**
     * Prints operations history.
     *
     * @return operations history with index, coma separated
     */
    public String printHistory() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < history.size(); i++) {
            sb.append(i + 1)
                .append(". ")
                .append(history.get(i))
                .append(" ,");
        }
        return sb.toString();
    }

}
