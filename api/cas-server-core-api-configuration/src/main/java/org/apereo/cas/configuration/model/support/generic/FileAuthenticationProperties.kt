package org.apereo.cas.configuration.model.support.generic

import org.apereo.cas.configuration.model.core.authentication.PasswordEncoderProperties
import org.apereo.cas.configuration.model.core.authentication.PrincipalTransformationProperties
import org.apereo.cas.configuration.support.RequiresModule
import org.springframework.boot.context.properties.NestedConfigurationProperty
import org.springframework.core.io.Resource

import java.io.Serializable

/**
 * This is [FileAuthenticationProperties].
 *
 * @author Misagh Moayyed
 * @since 5.0.0
 */
@RequiresModule(name = "cas-server-support-generic")
class FileAuthenticationProperties : Serializable {
    /**
     * File resource where user accounts are kept.
     */
    var filename: Resource? = null
    /**
     * Separator character that distinguishes between usernames and passwords in the file.
     */
    var separator = "::"

    /**
     * Password encoder properties.
     */
    @NestedConfigurationProperty
    var passwordEncoder = PasswordEncoderProperties()

    /**
     * Principal transformation settings for this authentication.
     */
    @NestedConfigurationProperty
    var principalTransformation = PrincipalTransformationProperties()

    /**
     * Authentication hanler name used to verify credentials in the file.
     */
    var name: String? = null

    companion object {

        private const val serialVersionUID = 4031366217090049241L
    }
}
