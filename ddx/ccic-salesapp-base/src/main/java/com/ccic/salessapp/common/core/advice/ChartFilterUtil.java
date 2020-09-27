package com.ccic.salessapp.common.core.advice;

import com.ccic.salessapp.common.core.exception.PlatformBaseException;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChartFilterUtil {

    private static List<Pattern> patterns = new ArrayList();
    private static List<Object[]> XSSPatterns = new ArrayList();

    static {
        getXssPatternList();
        getPatterns();
    }

    private static List<Object[]> getXssPatternList() {
        XSSPatterns.add(new Object[]{"<(no)?script[^>]*>.*?</(no)?script>", Pattern.CASE_INSENSITIVE});
        XSSPatterns.add(new Object[]{"<(no)?iframe[^>]*>.*?</(no)?iframe>", Pattern.CASE_INSENSITIVE});
        XSSPatterns.add(new Object[]{"eval\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL});
        XSSPatterns.add(new Object[]{"expression\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL});
        XSSPatterns.add(new Object[]{"(javascript:|vbscript:|view-source:)*", Pattern.CASE_INSENSITIVE});
        XSSPatterns.add(new Object[]{"<(\"[^\"]*\"|\'[^\']*\'|[^\'\">])*>", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL});
        XSSPatterns.add(new Object[]{"(window\\.location|window\\.|\\.location|document\\.cookie|document\\.|alert\\(.*?\\)|window\\.open\\()*", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL});
        XSSPatterns.add(new Object[]{"<+\\s*\\w*\\s*(oncontrolselect|oncopy|onerror|oncut|ondataavailable|ondatasetchanged|ondatasetcomplete|ondblclick|ondeactivate|ondrag|ondragend|ondragenter|ondragleave|ondragover|ondragstart|ondrop|οnerrοr=|onerroupdate|onfilterchange|onfinish|onfocus|onfocusin|onfocusout|onhelp|onkeydown|onkeypress|onkeyup|onlayoutcomplete|onload|onlosecapture|onmousedown|onmouseenter|onmouseleave|onmousemove|onmousout|onmouseover|onmouseup|onmousewheel|onmove|onmoveend|onmovestart|onabort|onactivate|onafterprint|onafterupdate|onbefore|onbeforeactivate|onbeforecopy|onbeforecut|onbeforedeactivate|onbeforeeditocus|onbeforepaste|onbeforeprint|onbeforeunload|onbeforeupdate|onblur|onbounce|oncellchange|onchange|onclick|oncontextmenu|onpaste|onpropertychange|onreadystatechange|onreset|onresize|onresizend|onresizestart|onrowenter|onrowexit|onrowsdelete|onrowsinserted|onscroll|onselect|onselectionchange|onselectstart|onstart|onstop|onsubmit|onunload)+\\s*=+", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL});
        XSSPatterns.add(new Object[]{"( and | exec |insert |select |delete |update |count | chr | char | hex | by | substr | white | union | limit | group |\\\\)*", Pattern.CASE_INSENSITIVE});
        return XSSPatterns;
    }

    private static synchronized List<Pattern> getPatterns() {
        for (Object[] arr : XSSPatterns) {
            for (Object o : arr) {
                patterns.add(Pattern.compile((String) arr[0], (Integer) arr[1]));
            }
        }
        return patterns;
    }

    public static String stripXss(String value) {
        try {
            if (StringUtils.isNotBlank(value)) {
                for (Pattern pattern : patterns) {
                    Matcher matcher = pattern.matcher(value);
                    if (matcher.find()) {
                        value = matcher.replaceAll("");
                    }
                }
            }
            return value;
        }catch (Exception e){
           throw new PlatformBaseException("特殊字符过滤失败",-1);
        }
    }
}
