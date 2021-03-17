package com.egova.api.convert;

import com.egova.data.designer.QueryContext;
import com.egova.exception.ExceptionUtils;
import com.flagwind.application.Application;
import org.springframework.context.expression.MapAccessor;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

public class ParamConverter {

    private static ExpressionParser parser = new SpelExpressionParser();
    private static TemplateParserContext parserContext = new TemplateParserContext("${", "}");
    private static final String FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final DateTimeFormatter DEFAULT_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static String convert(String json){
        QueryContext queryContext = new QueryContext();
        List<ParamConfigurer> configurers = Application.resolveAll(ParamConfigurer.class);
        configurers.forEach(configurer->  configurer.configure(null, queryContext));
        return convert(queryContext.getContext(), json);
    }

    private static String convert(Map<String, Object> data, String json) {
        if (!json.contains(parserContext.getExpressionPrefix())) {
            return json;
        } else {
            try {
                StandardEvaluationContext context = new StandardEvaluationContext();
                context.addPropertyAccessor(new MapAccessor());
                context.setRootObject(data);
                return (String)parser.parseExpression(json, parserContext).getValue(context, String.class);
            } catch (Exception var3) {
                throw ExceptionUtils.runtime("动态替换api请求参数异常", var3);
            }
        }
    }
}
