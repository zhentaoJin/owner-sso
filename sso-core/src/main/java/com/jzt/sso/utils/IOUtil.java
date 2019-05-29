/**
 * FileName: IOUtil
 * Author:   liujh
 * Date:     2018/9/28 17:30
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.jzt.sso.utils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStreamReader;

public class IOUtil {

    public static String getRequestBody(HttpServletRequest request){
        final StringBuffer body = new StringBuffer();
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(request.getInputStream());
            char[] buffer = new char[1024];
            int len = 0;
            while ((len = inputStreamReader.read(buffer)) != (-1)){
                body.append(buffer);
                buffer = new char[1024];
            }
            inputStreamReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return body.toString();
    }
}
