package com.qds.ulinzi;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.servers.Server;
import org.eclipse.microprofile.openapi.annotations.servers.ServerVariable;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.ws.rs.core.Application;

@OpenAPIDefinition(
        info = @Info(
                description = "This is the payment api",
                title = "Payment Integration",
                version = "1.0.0"
        ),
        tags = {
                @Tag(
                        name = "Account",
                        description = "Operations related to account."
                ),@Tag(
                        name = "Payment",
                        description = "Operations related to payment notifications from monnify."
                )
        },
        servers = {
                @Server(
                        description = "Development Server", url = "http://localhost:{port}",
                        variables = {
                                @ServerVariable(name = "port", defaultValue = "8181", enumeration = {"8181"})
                        }
                )
        }
)
public class PaymentApplication extends Application {
}
