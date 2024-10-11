package org.zs.phm3.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class RestResponseStatusExceptionResolver extends AbstractHandlerExceptionResolver {
    private static final Logger logger = LoggerFactory.getLogger(RestResponseStatusExceptionResolver.class);

    @Override
    protected ModelAndView doResolveException(HttpServletRequest request,
                                              HttpServletResponse response, Object handler, Exception ex) {
        try {
            if (ex instanceof IllegalArgumentException) {
                return handleIllegalArgument((IllegalArgumentException) ex, request, response, handler);
            }
            if (ex instanceof HttpMessageNotReadableException) {
                return this.handleHttpMessageNotReadable((HttpMessageNotReadableException)ex, request, response, handler);
            }
        } catch (Exception handlerException) {
            logger.warn("Handling of [{}] resulted in Exception", ex.getClass().getName(), handlerException);
        }
        return null;
    }

    private ModelAndView handleIllegalArgument(IllegalArgumentException ex,
                                               final HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        final String accept = request.getHeader(HttpHeaders.ACCEPT);

        response.sendError(HttpServletResponse.SC_CONFLICT);
        response.setHeader("ContentType", accept);

        final ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("error", prepareErrorResponse(accept));
        return modelAndView;
    }

    /** Prepares error object based on the provided accept type.
     * @param accept The Accept header present in the request.
     * @return The response to return
     * @throws JsonProcessingException
     */
    private String prepareErrorResponse(String accept) throws JsonProcessingException {
        final Map<String, String> error = new HashMap<>();
        error.put("Error", "Application specific error message");

        String response = null;
        if(MediaType.APPLICATION_JSON_VALUE.equals(accept)) {
            response = new ObjectMapper().writeValueAsString(error);
        }
/*        else {
            response = new XmlMapper().writeValueAsString(error);
        }*/
        return response;
    }

    protected ModelAndView handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpServletRequest request, HttpServletResponse response, @Nullable Object handler) throws IOException {
        response.sendError(400);
//        return new ModelAndView();

        final ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("error", prepareErrorResponse(ex.getHttpInputMessage().toString()));
        return modelAndView;
    }

/*    @Nullable
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, @Nullable Object handler, Exception ex) {
        try {
            if (ex instanceof HttpRequestMethodNotSupportedException) {
                return this.handleHttpRequestMethodNotSupported((HttpRequestMethodNotSupportedException)ex, request, response, handler);
            }

            if (ex instanceof HttpMediaTypeNotSupportedException) {
                return this.handleHttpMediaTypeNotSupported((HttpMediaTypeNotSupportedException)ex, request, response, handler);
            }

            if (ex instanceof HttpMediaTypeNotAcceptableException) {
                return this.handleHttpMediaTypeNotAcceptable((HttpMediaTypeNotAcceptableException)ex, request, response, handler);
            }

            if (ex instanceof MissingPathVariableException) {
                return this.handleMissingPathVariable((MissingPathVariableException)ex, request, response, handler);
            }

            if (ex instanceof MissingServletRequestParameterException) {
                return this.handleMissingServletRequestParameter((MissingServletRequestParameterException)ex, request, response, handler);
            }

            if (ex instanceof ServletRequestBindingException) {
                return this.handleServletRequestBindingException((ServletRequestBindingException)ex, request, response, handler);
            }

            if (ex instanceof ConversionNotSupportedException) {
                return this.handleConversionNotSupported((ConversionNotSupportedException)ex, request, response, handler);
            }

            if (ex instanceof TypeMismatchException) {
                return this.handleTypeMismatch((TypeMismatchException)ex, request, response, handler);
            }

            if (ex instanceof HttpMessageNotReadableException) {
                return this.handleHttpMessageNotReadable((HttpMessageNotReadableException)ex, request, response, handler);
            }

            if (ex instanceof HttpMessageNotWritableException) {
                return this.handleHttpMessageNotWritable((HttpMessageNotWritableException)ex, request, response, handler);
            }

            if (ex instanceof MethodArgumentNotValidException) {
                return this.handleMethodArgumentNotValidException((MethodArgumentNotValidException)ex, request, response, handler);
            }

            if (ex instanceof MissingServletRequestPartException) {
                return this.handleMissingServletRequestPartException((MissingServletRequestPartException)ex, request, response, handler);
            }

            if (ex instanceof BindException) {
                return this.handleBindException((BindException)ex, request, response, handler);
            }

            if (ex instanceof NoHandlerFoundException) {
                return this.handleNoHandlerFoundException((NoHandlerFoundException)ex, request, response, handler);
            }

            if (ex instanceof AsyncRequestTimeoutException) {
                return this.handleAsyncRequestTimeoutException((AsyncRequestTimeoutException)ex, request, response, handler);
            }
        } catch (Exception var6) {
            if (this.logger.isWarnEnabled()) {
                this.logger.warn("Failure while trying to resolve exception [" + ex.getClass().getName() + "]", var6);
            }
        }

        return null;
    }*/
}
