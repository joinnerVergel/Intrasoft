package com.intrasoft.Api.ActivosFijos;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.intrasoft.Api.entidades.Activos;

@Path("/intrasoft")
public class ActivosService {
	@Autowired
	private activosRespository repository;

	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy");

	private static final Logger logger = LogManager.getLogger(ActivosService.class);

	/**
	 * Metodo que retorna la lista de todos los activos fijos existentes
	 * 
	 * @return Lista de todos los Activos
	 */
	@GET
	@Path("/activos")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAll() {
		try {
			if (repository.findAll().isEmpty()) {
				logger.info("CONSULTA >> Lista de activos vacia...");
				return Response.status(404).entity(repository.findAll()).build();
			} else {
				logger.info("CONSULTA >> Mostrando Lista de activos ...");
				return Response.status(200).entity(repository.findAll()).build();
			}
		} catch (Exception e) {
			logger.error("ERROR >> Se produjo un error en el servidor ...");
			return Response.status(500).entity("Lo sentimos... Se produjo un error en el servidor").build();
		}
	}

	/**
	 * Metodo que permite Encontrar los activos fijos por nombre
	 * 
	 * @param nombre
	 *            - parametro en la url correpondiente al nombre del activo
	 * @return Response - lista con los activos encontrados por el nombre
	 *         indicado
	 */
	@GET
	@Path("/activos/Encontrar/{nombre}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getNombre(@PathParam("nombre") String nombre) {
		try {
			if (repository.findBynombre(nombre).isEmpty()) {
				logger.info("CONSULTA >> Lista de activos por nombre vacia...");
				return Response.status(404).entity(repository.findBynombre(nombre)).build();
			} else {
				logger.info("CONSULTA >> Mostrando Lista de activos por nombre ...");
				return Response.status(200).entity(repository.findBynombre(nombre)).build();
			}
		} catch (Exception e) {
			logger.error("ERROR >> Se produjo un error en el servidor ...");
			return Response.status(500).entity("Lo sentimos... Se produjo un error en el servidor").build();
		}
	}

	/**
	 * Metodo que permite crear activos fijos
	 * 
	 * @param activo
	 *            - Json con la estructura de los activosFijos
	 * @return Response
	 */
	@POST
	@Path("/activos/Nuevo/")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response CrearActivo(Activos activo) {
		try {
			if (activo.getSerial() == null || activo.getFecBaja() == null || activo.getFecCompra() == null
					|| activo.getEstadoActual() == null || activo.getNombre() == null
					|| activo.getNumeroInterno() == null) {
				logger.error("ERROR >> Faltan los datos minimos para crear el activo ...");
				return Response.status(400).entity("Error...Faltan los datos minimos para crear el activo").build();
			}
			int x = activo.getFecBaja().compareTo(activo.getFecCompra());
			if (x == 0 || x < 0) {
				repository.save(activo);
				logger.info("INSERT >> Activo Creado Exitosamente...");
				return Response.status(200).entity("Activo Creado Exitosamente").build();
			} else {
				logger.error("Error >> La fecha de Baja (" + sdf.format(activo.getFecBaja().getTime())
						+ ") es superior a la Fecha de Compra (" + sdf.format(activo.getFecCompra().getTime()) + ")");
				return Response.status(500)
						.entity("Error... La fecha de Baja (" + sdf.format(activo.getFecBaja().getTime())
								+ ") es superior a la Fecha de Compra (" + sdf.format(activo.getFecCompra().getTime())
								+ ")")
						.build();
			}
		} catch (Exception e) {
			logger.error("ERROR >> Se produjo un error en el servidor ...");
			return Response.status(500).entity("Lo sentimos... Se produjo un error en el servidor").build();
		}

	}

	/**
	 * Metodo que permite actualizar el numero interno y la fecha de baja de un
	 * activo fijo
	 * 
	 * @param serial
	 *            - Codigo del Activo que se actualizará
	 * @param sInterno
	 *            - Nuevo valor para el Numero interno del activo
	 * @param dia
	 *            - dia de la fecha de baja
	 * @param mes
	 *            - mes de la fecha de baja
	 * @param anio
	 *            - año de la fecha de baja
	 * @return Response
	 */
	@PUT
	@Path("/activos/Actualizar/{serial}/{sInterno}/{dia}-{mes}-{anio}")
	public Response ActualizarActivo(@PathParam("serial") String serial, @PathParam("sInterno") Integer sInterno,
			@PathParam("dia") Integer dia, @PathParam("mes") Integer mes, @PathParam("anio") Integer anio) {
		try {

			if (serial == null || sInterno == null || dia == null || mes == null || anio == null) {
				logger.error("ERROR >> Faltan los datos minimos para actualizar el activo ...");
				return Response.status(400).entity("Error...Faltan los datos minimos para actualizar el activo")
						.build();
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
					logger.info("UPDATE >> Activo Actualizado Exitosamente...");
					return Response.status(200).entity("Activo Actualizado Exitosamente").build();
				} else {
					logger.error("ERROR >> La fecha de Baja (" + sdf.format(busqueda.getFecBaja().getTime())
							+ ") es superior a la Fecha de Compra (" + sdf.format(busqueda.getFecCompra().getTime())
							+ ")");
					return Response.status(500)
							.entity("Error... La fecha de Baja (" + sdf.format(busqueda.getFecBaja().getTime())
									+ ") es superior a la Fecha de Compra ("
									+ sdf.format(busqueda.getFecCompra().getTime()) + ")")
							.build();
				}

			} else {
				logger.info("Error >> No existe el activo con serial...");
				return Response.status(404).entity("No existe el activo con serial " + serial).build();
			}

		} catch (Exception e) {
			logger.error("ERROR >> Se produjo un error en el servidor ...");
			return Response.status(500).entity("Lo sentimos... Se produjo un error en el servidor").build();
		}
	}

	/**
	 * @param serial  del activo a eliminar
	 * @return Response
	 */
	@DELETE
	@Path("/activos/Eliminar/{serial}")
	public Response ActualizarActivo(@PathParam("serial") String serial) {
		try {
			if (serial == null) {
				logger.error("ERROR >> Falta el serial del activo a leiminar ...");
				return Response.status(400).entity("Error...Faltan los datos minimos para actualizar el activo")
						.build();
			}
			Activos busqueda = repository.findByserial(serial);
			if (busqueda != null) {
				repository.delete(busqueda);
				logger.info("DELETE >> Activo eliminado Exitosamente...");
				return Response.status(200).entity("Activo eliminado Exitosamente").build();
			} else {
				logger.info("Error >> No existe el activo con serial...");
				return Response.status(404).entity("No existe el activo con serial " + serial).build();
			}
		} catch (Exception e) {
			logger.error("ERROR >> Se produjo un error en el servidor ...");
			return Response.status(500).entity("Lo sentimos... Se produjo un error en el servidor").build();
		}
	}

}
