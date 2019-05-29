package com.jzt.sso.exception;


import com.jzt.sso.utils.R;
import io.jsonwebtoken.ExpiredJwtException;
import org.apache.ibatis.exceptions.TooManyResultsException;
import org.mybatis.spring.MyBatisSystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.NativeWebRequest;

import javax.servlet.http.HttpServletRequest;

//import org.apache.shiro.authz.AuthorizationException;
//import org.apache.shiro.authz.UnauthorizedException;


/**
 * @author bgy
 * @since 2018-5-10
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 自定义异常
     */
    @ExceptionHandler({CustomException.class,ExpiredJwtException.class})
    public R handleRRException(CustomException e) {
        e.printStackTrace();
        R msg = new R();
        msg.put("code", e.getCode());
        msg.put("msg", e.getMessage());
        return msg;
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public R handleDuplicateKeyException(DuplicateKeyException e) {
        e.printStackTrace();
        logger.error(e.getMessage(), e);
        return R.error("违反唯一约束");
    }

    @ExceptionHandler(TooManyResultsException.class)
    public R handleTooManyResultsException(TooManyResultsException e) {
        e.printStackTrace();
        R msg = new R();
        msg.put("code",0);
        msg.put("msg", "期望一条数据,但发现多条数据,数据库存在脏数据");
        return msg;
    }



    @ExceptionHandler(MyBatisSystemException.class)
    public R handleMyBatisSystemException(MyBatisSystemException e) {
        e.printStackTrace();
        R msg = new R();
        msg.put("code",0);
        msg.put("msg", e.getMessage());
        e.printStackTrace();
        return msg;
    }



    @ExceptionHandler(MissingServletRequestParameterException.class)
    public R missingParameter(MissingServletRequestParameterException e) {
        e.printStackTrace();
        R msg = new R();
        msg.put("code", 0);
        msg.put("msg", e.getMessage());
        return msg;
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.OK)
    public R unaccessDeniedException(NativeWebRequest request, AccessDeniedException e) {
        e.printStackTrace();
        R msg = new R();
        msg.put("code", 500);
        msg.put("msg", "没有该权限，不允许操作。");
        return msg;
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public R notConverter(HttpMessageNotReadableException e) {
        e.printStackTrace();
        R msg = new R();
        msg.put("code", 0);
        msg.put("msg", e.getMessage());
        return msg;
    }

    @ExceptionHandler(NullPointerException.class)
    public R handleNullpointer(NullPointerException e) {
        e.printStackTrace();
        R msg = new R();
        msg.put("code", 0);
        msg.put("msg", "空指针异常待处理");
        return msg;
    }

    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public R notSuportMethod(HttpServletRequest request, Exception ex) throws Exception {
        ex.printStackTrace();
        R msg = new R();
        msg.put("code", 0);
        msg.put("msg", ex.getMessage());
        return msg;
    }

    @ExceptionHandler(value = Exception.class)
    public R defaultErrorHandler(HttpServletRequest request, Exception ex) throws Exception {
        ex.printStackTrace();
        logger.error(ex.getMessage(), ex);
        return R.error(ex.getCause().getMessage());
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public R bindException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();

        String errorMesssage = "校验失败:";

        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            errorMesssage += fieldError.getDefaultMessage();
        }

        return  R.error( errorMesssage);
    }


}
