package top.devildyw.satokenlearn.satoken_common.handler;

import cn.dev33.satoken.util.SaResult;
import kotlin.Result;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.cuit.epoch.exception.AppException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

/**
 * @author Devil
 * @since 2022-10-30-17:01
 */
@RestControllerAdvice
@Log4j2
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public SaResult error(Exception e) {
        log.error(e.getMessage());
        e.printStackTrace();
        return SaResult.error();
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public SaResult error(ConstraintViolationException e) {
        log.error(e.getMessage());
        e.printStackTrace();
        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
        StringBuilder res = new StringBuilder("参数异常: ");
        constraintViolations.forEach(c -> res.append(c.getMessage()).append(" "));
        return SaResult.error(res.toString().trim());
    }

    @ExceptionHandler(AppException.class)
    public SaResult error(AppException e) {
        log.error(e.getMessage());
        e.printStackTrace();
        return SaResult.error(e.getMessage());
    }
    @ExceptionHandler(value = {MethodArgumentNotValidException.class, BindException.class})
    public SaResult argumentError(Exception e) {
        e.printStackTrace();
        BindingResult bindingResult = null;
        if (e instanceof MethodArgumentNotValidException) {
            bindingResult = ((MethodArgumentNotValidException) e).getBindingResult();
        } else if (e instanceof BindException) {
            bindingResult = ((BindException) e).getBindingResult();
        }
        StringBuilder msg = new StringBuilder();
        assert bindingResult != null;
        bindingResult.getFieldErrors().forEach((fieldError) ->
                msg.append(fieldError.getDefaultMessage()).append(" ")
        );
        log.error(msg);
        return SaResult.error(msg.toString().trim());
    }
}
