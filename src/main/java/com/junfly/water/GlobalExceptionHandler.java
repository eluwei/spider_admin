package com.junfly.water;

import com.junfly.common.result.ResultInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;

/**
 * 错误信息handler
 * Created by bj on 2016/12/20.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResultInfo<String> handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        logger.error("缺少请求参数", e);
        ResultInfo<String> r = new ResultInfo<>();
        r.setMessage(e.getMessage());
        r.setCode(ResultInfo.ERROR);
        r.setData("缺少请求参数");
        return r;
    }

    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResultInfo<String> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        logger.error("参数解析失败", e);
        ResultInfo<String> r = new ResultInfo<>();
        r.setMessage(e.getMessage());
        r.setCode(ResultInfo.ERROR);
        r.setData("参数解析失败");
        return r;
    }

    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultInfo<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        logger.error("参数验证失败", e);
        ResultInfo<String> r = new ResultInfo<>();
        r.setMessage(e.getMessage());
        r.setCode(ResultInfo.ERROR);
        r.setData("参数验证失败");
        return r;
    }

    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public ResultInfo<String> handleBindException(BindException e) {
        logger.error("参数绑定失败", e);
        ResultInfo<String> r = new ResultInfo<>();
        r.setMessage(e.getMessage());
        r.setCode(ResultInfo.ERROR);
        r.setData("参数绑定失败");
        return r;
    }

    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public ResultInfo<String> handleServiceException(ConstraintViolationException e) {
        logger.error("参数验证失败", e);
        ResultInfo<String> r = new ResultInfo<>();
        r.setMessage(e.getMessage());
        r.setCode(ResultInfo.ERROR);
        r.setData("参数验证失败");
        return r;
    }

    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidationException.class)
    public ResultInfo<String> handleValidationException(ValidationException e) {
        logger.error("参数验证失败", e);
        ResultInfo<String> r = new ResultInfo<>();
        r.setMessage(e.getMessage());
        r.setCode(ResultInfo.ERROR);
        r.setData("参数验证失败");
        return r;
    }

    /**
     * 405 - Method Not Allowed
     */
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResultInfo<String> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        logger.error("不支持当前请求方法", e);
        ResultInfo<String> r = new ResultInfo<>();
        r.setMessage(e.getMessage());
        r.setCode(ResultInfo.ERROR);
        r.setData("不支持当前请求方法");
        return r;
    }

    /**
     * 415 - Unsupported Media Type
     */
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResultInfo<String> handleHttpMediaTypeNotSupportedException(Exception e) {
        logger.error("不支持当前媒体类型", e);
        ResultInfo<String> r = new ResultInfo<>();
        r.setMessage(e.getMessage());
        r.setCode(ResultInfo.ERROR);
        r.setData("不支持当前媒体类型");
        return r;
    }

    /**
     * 500 - Internal Server Error
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ServiceJsonException.class)
    @ResponseBody
    public ResultInfo<String> handleServiceException(HttpServletRequest req, ServiceJsonException e) {
        logger.error("业务逻辑异常", e);
        ResultInfo<String> r = new ResultInfo<>();
        r.setMessage(e.getMessage());
        r.setCode(ResultInfo.ERROR);
        r.setData("业务逻辑异常");
        return r;
    }

    /**
     * 操作数据库出现异常:名称重复，外键关联
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResultInfo<String> handleException(DataIntegrityViolationException e) {
        logger.error("操作数据库出现异常:", e);
        ResultInfo<String> r = new ResultInfo<>();
        r.setMessage(e.getMessage());
        r.setCode(ResultInfo.ERROR);
        r.setData("操作数据库出现异常");
        return r;
    }
}
