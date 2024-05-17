package br.com.postechfiap.jlappproducao.infra.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import br.com.postechfiap.jlappproducao.shared.filter.MdcInterceptor;

@Component
public class WebConfig implements WebMvcConfigurer {

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new MdcInterceptor());
  }
}
