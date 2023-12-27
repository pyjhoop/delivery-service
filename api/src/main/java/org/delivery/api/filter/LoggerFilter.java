package org.delivery.api.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class LoggerFilter implements Filter {


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //여기서 request를 읽어버리면 컨트롤러에서 리퀘스트를 읽을 수 없데

        var req = new ContentCachingRequestWrapper((HttpServletRequest) request);
        var res = new ContentCachingResponseWrapper((HttpServletResponse) response);


        chain.doFilter(req, res); // doFilter를 기준으로 위에서 req, res의 값을 사용하면 다음 에서는 사용할 수 없어.

        // request 정보 header, body정보를 찍어주면 좋음
        var headerNames = req.getHeaderNames();
        var headerValues = new StringBuilder();

        headerNames.asIterator().forEachRemaining(headerKey ->{
            var headerValue = req.getHeader(headerKey);

            headerValues.append("[").append(headerKey).append(" : ").append(headerValue).append("] ");
        });

        var requestBody = new String(req.getContentAsByteArray());
        var uri = req.getRequestURI();
        var method = req.getMethod();
        log.info(">>>> uri : {} , method : {} , header : {}, body : {}",uri, method, headerValues.toString(), requestBody);


        // response 정보
        var responseHeaderValues = new StringBuilder();

        res.getHeaderNames().forEach(headerKey->{
            var headerValue = res.getHeader(headerKey);

            responseHeaderValues.append("[").append(headerKey).append(" : ").append(headerValue).append("] ");
        });

        var responseBody = new String(res.getContentAsByteArray()); // 여기서 response를 한번 읽어서 응답할때 바디 값이 사라짐.

        log.info("<<<<< uri : {} , method : {} , header : {} , body : {}",uri, method, responseHeaderValues, responseBody);

        // 그래서 여기에 추가해줘야함. 이거 없으면 body가 비어져서 나감.
        res.copyBodyToResponse();


    }

}
