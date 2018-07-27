package com.intrasoft.Api.ActivosFijos;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import com.intrasoft.Api.entidades.Activos;

@Path("/intrasoft")
public class ActivosService {
	@Autowired
	private activosRespository repository;
	
	
	@GET
	@Path("/activos")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAll (){
		try {
			if(repository.findAll().isEmpty()){
				return Response.status(404)
						.entity(repository.findAll())
						.build();
			}else{
				return Response.status(200)
						.entity(repository.findAll())
						.build();
			}
		} catch (Exception e) {
			return Response.status(500).build();
		}
	}
	
	@GET
	@Path("/activos/{nombre}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getNombre (@PathParam("nombre") String nombre){
		try {
			if(repository.findBynombre(nombre).isEmpty()){
				return Response.status(404)
						.entity(repository.findBynombre(nombre))
						.build();
			}else{
				return Response.status(200)
						.entity(repository.findBynombre(nombre))
						.build();
			}
		} catch (Exception e) {
			return Response.status(500).build();
		}
		
		
	}
	
	
}
