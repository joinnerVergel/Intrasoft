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
import org.springframework.util.SocketUtils;

import com.intrasoft.Api.entidades.Activos;

@Path("/intrasoft")
public class ActivosService {
	@Autowired
	private activosRespository repository;

	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy");

	@GET
	@Path("/activos")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAll() {
		try {
			if (repository.findAll().isEmpty()) {
				return Response.status(404).entity(repository.findAll()).build();
			} else {
				return Response.status(200).entity(repository.findAll()).build();
			}
		} catch (Exception e) {
			return Response.status(500).entity("Lo sentimos... Se produjo un error en el servidor").build();
		}
	}

	@GET
	@Path("/activos/Encontrar/{nombre}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getNombre(@PathParam("nombre") String nombre) {
		try {
			if (repository.findBynombre(nombre).isEmpty()) {
				return Response.status(404).entity(repository.findBynombre(nombre)).build();
			} else {
				return Response.status(200).entity(repository.findBynombre(nombre)).build();
			}
		} catch (Exception e) {
			return Response.status(500).entity("Lo sentimos... Se produjo un error en el servidor").build();
		}
	}

	@POST
	@Path("/activos/Nuevo/")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response CrearActivo(Activos activo) {
		try {
			if (activo.getSerial() == null || activo.getFecBaja() == null || activo.getFecCompra() == null
					|| activo.getEstadoActual() == null || activo.getNombre() == null
					|| activo.getNumeroInterno() == null) {
				return Response.status(400).entity("Error...Faltan los datos minimos para crear el activo").build();
			}
			int x = activo.getFecBaja().compareTo(activo.getFecCompra());
			if (x == 0 || x < 0) {
				repository.save(activo);
				return Response.status(200).entity("Activo Creado Exitosamente").build();
			} else {
				return Response.status(500)
						.entity("Error... La fecha de Baja (" + sdf.format(activo.getFecBaja().getTime())
								+ ") es superior a la Fecha de Compra (" + sdf.format(activo.getFecCompra().getTime())
								+ ")")
						.build();
			}
		} catch (Exception e) {
			return Response.status(500).entity("Lo sentimos... Se produjo un error en el servidor").build();
		}

	}

	@PUT
	@Path("/activos/Actualizar/{serial}/{sInterno}/{dia}-{mes}-{anio}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response ActualizarActivo(@PathParam("serial") String serial, @PathParam("sInterno") Integer sInterno,
			@PathParam("dia") Integer dia, @PathParam("mes") Integer mes, @PathParam("anio") Integer anio) {
		try {
			
			if (serial==null ||sInterno==null ||dia==null||mes==null||anio==null) {
				return Response.status(400).entity("Error...Faltan los datos minimos para actualizar el activo").build();
			}
			Activos busqueda = repository.findByserial(serial);
			if (busqueda != null) {
				System.out.println(">>>Anio:" + anio);
				Date FechaBaja = new Date(anio - 1900, mes - 1, dia);
				busqueda.setNumeroInterno(sInterno);
				busqueda.setFecBaja(FechaBaja);
				int x = busqueda.getFecBaja().compareTo(busqueda.getFecCompra());
				if (x == 0 || x < 0) {
					repository.save(busqueda);
					return Response.status(200).entity("Activo Actualizado Exitosamente").build();
				} else {
					return Response.status(500)
							.entity("Error... La fecha de Baja (" + sdf.format(busqueda.getFecBaja().getTime())
									+ ") es superior a la Fecha de Compra ("
									+ sdf.format(busqueda.getFecCompra().getTime()) + ")")
							.build();
				}

			} else {
				return Response.status(404).entity("No existe el activo con serial " + serial).build();
			}

		} catch (Exception e) {
			return Response.status(500).entity("Lo sentimos... Se produjo un error en el servidor").build();
		}

	}

}
