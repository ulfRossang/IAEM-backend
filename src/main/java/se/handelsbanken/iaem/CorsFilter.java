package se.handelsbanken.iaem;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.container.PreMatching;
import jakarta.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
@PreMatching
public class CorsFilter implements ContainerRequestFilter, ContainerResponseFilter {

    @Override
    public void filter(ContainerRequestContext req) throws IOException {
        if ("OPTIONS".equalsIgnoreCase(req.getMethod())) {
            req.abortWith(jakarta.ws.rs.core.Response.ok().build());
        }
    }

    @Override
    public void filter(ContainerRequestContext req, ContainerResponseContext res) throws IOException {
        res.getHeaders().add("Access-Control-Allow-Origin", "*");
        res.getHeaders().add("Access-Control-Allow-Headers", "Content-Type, Authorization");
        res.getHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
    }
}
