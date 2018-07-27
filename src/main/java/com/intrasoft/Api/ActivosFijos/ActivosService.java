package com.intrasoft.Api.ActivosFijos;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import com.intrasoft.Api.entidades.Activos;

@Path("/intrasoft")
public class ActivosService {
	@Autowired
	private activosRespository repository;
	
	
	@GET
	@Path("/activos")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Activos> getAll (){
		return repository.findAll();
	}
}
