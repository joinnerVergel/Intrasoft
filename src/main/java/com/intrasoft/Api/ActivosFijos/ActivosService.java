package com.intrasoft.Api.ActivosFijos;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
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
			return Response.status(500).entity("Lo sentimos... Se produjo un error en el servidor").build();
		}
	}
	
	@GET
	@Path("/activos/Encontrar/{nombre}")
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
			return Response.status(500).entity("Lo sentimos... Se produjo un error en el servidor").build();
		}
	}
	
	@POST
	@Path("/activos/Nuevo/")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response CrearActivo (Activos activo){
		try {
			   repository.save(activo);
				return Response.status(200)
						.entity("Activo Creado Exitosamente")
						.build();
			
		} catch (Exception e) {
			return Response.status(500).entity("Lo sentimos... Se produjo un error en el servidor").build();
		}
		
	}
	
	
	@PUT
	@Path("/activos/Actualizar/{serial}/{sInterno}/{dia}-{mes}-{anio}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response ActualizarActivo (@PathParam("serial") String serial, @PathParam("sInterno") Integer sInterno,
			@PathParam("dia") Integer dia,@PathParam("mes") Integer mes,@PathParam("anio") Integer anio){
		try {
			Activos busqueda=repository.findByserial(serial);
			if(busqueda!=null){
				Date FechaBaja=new Date(anio,mes,dia);
				busqueda.setNumeroInterno(sInterno);
				busqueda.setFecBaja(FechaBaja);
				repository.save(busqueda);
					return Response.status(200)
							.entity("Activo Actualizado Exitosamente")
							.build();
			}else{
				return Response.status(400)
						.entity("No existe el activo con serial "+serial)
						.build();
			}
			
			
		} catch (Exception e) {
			return Response.status(500).entity("Lo sentimos... Se produjo un error en el servidor").build();
		}
		
	}
	
	
}
