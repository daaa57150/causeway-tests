package domainapp.webapp;

import org.apache.causeway.applib.CausewayModuleApplibChangeAndExecutionLoggers;
import org.apache.causeway.applib.CausewayModuleApplibMixins;
import org.apache.causeway.core.config.presets.CausewayPresets;
import org.apache.causeway.core.runtimeservices.CausewayModuleCoreRuntimeServices;
import org.apache.causeway.persistence.jpa.eclipselink.CausewayModulePersistenceJpaEclipselink;
import org.apache.causeway.security.bypass.authorization.AuthorizorBypass;
import org.apache.causeway.security.keycloak.CausewayModuleSecurityKeycloak;
import org.apache.causeway.testing.h2console.ui.CausewayModuleTestingH2ConsoleUi;
import org.apache.causeway.viewer.restfulobjects.jaxrsresteasy.CausewayModuleViewerRestfulObjectsJaxrsResteasy;
import org.apache.causeway.viewer.wicket.applib.CausewayModuleViewerWicketApplibMixins;
import org.apache.causeway.viewer.wicket.viewer.CausewayModuleViewerWicketViewer;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

import domainapp.modules.hello.HelloWorldModule;

@Configuration
@Import({
        CausewayModuleApplibMixins.class,
        CausewayModuleApplibChangeAndExecutionLoggers.class,

        CausewayModuleCoreRuntimeServices.class,


//        CausewayModuleSecurityShiro.class,
//        CausewayModuleExtSpringSecurityOAuth2.class,
        CausewayModuleSecurityKeycloak.class,
//        CausewayModuleSecurityBypass.class,
        AuthorizorBypass.class,
        //AuthorizorShiro.class,

        CausewayModulePersistenceJpaEclipselink.class,
        CausewayModuleViewerRestfulObjectsJaxrsResteasy.class,
        CausewayModuleViewerWicketApplibMixins.class,
        CausewayModuleViewerWicketViewer.class,

        CausewayModuleTestingH2ConsoleUi.class,
        HelloWorldModule.class
})
@PropertySources({
    @PropertySource(CausewayPresets.NoTranslations),
})
public class AppManifest {
}
