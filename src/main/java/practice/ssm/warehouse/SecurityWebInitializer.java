package practice.ssm.warehouse;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * Java 配置注册 DelegateFilter, 只需要继承AbstractSecurityWebApplicationInitializer。
 * 可以重载它的 appendFilters()或insertFilters()方法来注册自己选择 的Filter，
 * 但是要注册DelegatingFilterProxy的话，我们并不需 要重载任何方法。
 *
 * DelegateFilter 拦截请求，实现Spring-Security的Web安全管理。
 * 具体配置需实现WebSecurityConfigurer接口，并注解 @EnableWebMVCSecurity 。
 */
public class SecurityWebInitializer extends AbstractSecurityWebApplicationInitializer {
}
