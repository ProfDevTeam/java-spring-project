package uz.pdp.pdpspring6thlessonhomework2nd.config;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import uz.pdp.pdpspring6thlessonhomework2nd.entity.Staff;

import java.util.Optional;
import java.util.UUID;

public class SpringSecurityAuditAwareImpl implements AuditorAware<UUID> {
    @Override
    public Optional<UUID> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication !=null
                && authentication.isAuthenticated()
                && !authentication.getPrincipal().equals("anonymousUser")){
            Staff employee = (Staff) authentication.getPrincipal();
            return Optional.of(employee.getId());
        }
        return Optional.empty();
    }
}
