package org.zs.phm3.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.zs.phm3.models.user.UserEntity;
import org.zs.phm3.repository.user.UserRepository;

import javax.servlet.*;
import java.io.IOException;

@Component
public class LastSeenFilter implements Filter {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        /*

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getName() != null) {
            UserEntity userEntity = userRepository.getByLogin(auth.getName());
            userEntity.setLastSeen(System.currentTimeMillis());
            userRepository.save(userEntity);
        }*/
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
