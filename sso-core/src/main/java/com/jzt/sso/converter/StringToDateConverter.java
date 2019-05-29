package com.jzt.sso.converter;

import com.jzt.sso.utils.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class StringToDateConverter implements Converter<String, Date> {

    private static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    private static final String YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
    private static final String YYYY_MM_DD = "yyyy-MM-dd";
    private static final String YYYY = "yyyy";
    private static final String YYYY_MM = "yyyy-MM";
    private static final String chineseDateFormat = "yyyy年MM月dd日 HH:mm";
    private final String datePattern = "(\\d{1,4}[-|\\/|年|\\.]\\d{1,2}[-|\\/|月|\\.]\\d{1,2}([日|号])?(\\s)*" +
            "(\\d{1,2}([点|时])?((:)?\\d{1,2}(分)?((:)?\\d{1,2}(秒)?)?)?)?(\\s)*(PM|AM)?)";

    /**
     * @see Converter#convert(Object)
     */
    @Override
    public Date convert(String source) {
        if (StringUtils.isBlank(source)) {
            return null;
        }
        source = source.trim();
        try {
            if (source.contains("-")) {
                String pattern = YYYY_MM_DD_HH_MM_SS;
                if (source.contains("ss")) {
                    //formatter = new SimpleDateFormat(HMS_DATE_FORMAT);
                    pattern = YYYY_MM_DD_HH_MM_SS;
                } else if (source.contains(":")) {
//                    formatter = new SimpleDateFormat(HM_DATE_FORMAT);
                    pattern = YYYY_MM_DD_HH_MM;
                } else if (source.contains("-")) {
//                    formatter = new SimpleDateFormat(YYYY_MM);
                    pattern = YYYY_MM_DD;
                }
                //Date dtDate = formatter.parse(source);
                Date dtDate = DateUtils.parse(source, pattern);
                return dtDate;
            } else if (source.matches("^\\d+$")) {
                Long lDate = new Long(source);
                return new Date(lDate);
            } else if (source.matches(datePattern)) {
                SimpleDateFormat formatter = new SimpleDateFormat(chineseDateFormat);
                return formatter.parse(source);
            }
        } catch (Exception e) {
            throw new RuntimeException(String.format("parser %s to Date fail", source));
        }
        throw new RuntimeException(String.format("parser %s to Date fail", source));
    }
}
