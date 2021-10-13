package com.qds.ulinzi.web.rest;

import com.qds.ulinzi.service.MonnifyPaymentService;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("payment")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(ref="Payment")
public class MonnifyPaymentResource {

    @Inject
    MonnifyPaymentService monnifyPaymentService;

    @Path("/notify/disbursement")
    @POST
    public Response notifyDisbursement(String resposne){
        monnifyPaymentService.handleDisbursement(resposne);
        return Response.ok().build();
    }

    @Path("/notify/settlement")
    @POST
    public Response notifySettlement(String resposne){
        monnifyPaymentService.handleSettlement(resposne);
        return Response.ok().build();
    }
}
