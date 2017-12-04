/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mdsol.go.admin.go.admin.service;

import com.mdsol.go.admin.go.admin.modelo.Cliente;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author martdominguez
 */
@Path("cliente")
@Stateless
public class ClienteResource {

    @PersistenceContext(unitName = "GO_PU")
    EntityManager em;
    
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of GenericResource
     */
    public ClienteResource() {
    }

    /**
     * Retrieves representation of an instance of com.mdsol.go.admin.go.admin.service.GenericResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarLista() {
        List<Cliente> lista = em.createQuery("SELECT c FROM Cliente c").getResultList();
        return Response.ok(lista).build();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") Integer id) {
        Cliente c = em.find(Cliente.class,id);
        return Response.ok(c).build();
    }

    /**
     * PUT method for updating or creating an instance of GenericResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response actualizar(Cliente c) {
        Cliente c1 = em.merge(c);
        return Response.ok(c1).build();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response crear(Cliente c) {
        em.persist(c);
        return Response.ok().build();

    }

}
