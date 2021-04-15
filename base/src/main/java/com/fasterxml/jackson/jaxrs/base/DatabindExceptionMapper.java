package com.fasterxml.jackson.jaxrs.base;

import com.fasterxml.jackson.core.JacksonException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

/**
 * Implementation if {@link ExceptionMapper} to send down a "400 Bad Request"
 * response in the event that unmappable JSON is received.
 *<p>
 * Note that {@link javax.ws.rs.ext.Provider} annotation was include up to
 * Jackson 2.7, but removed from 2.8 (as per [jaxrs-providers#22])
 */
public class JsonMappingExceptionMapper implements ExceptionMapper<JacksonException> {
    @Override
    public Response toResponse(JacksonException exception) {

        return Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).type("text/plain").build();
    }
}