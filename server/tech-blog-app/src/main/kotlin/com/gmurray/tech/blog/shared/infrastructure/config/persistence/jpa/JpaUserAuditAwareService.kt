package com.gmurray.tech.blog.shared.infrastructure.config.persistence.jpa

import org.springframework.data.domain.AuditorAware
import java.util.Optional

class JpaUserAuditAwareService: AuditorAware<String> {

    override fun getCurrentAuditor(): Optional<String> {
        return Optional.of("JPA_USER")
    }

}