package com.intrasoft.Api.entidades;

import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public class Activos {
	
	@Id
	private ObjectId _id;

	private String nombre;
	private String descripcion;
	private String tipo;
	private String serial;
	private Integer numeroInterno;
	private double peso;
	private double alto; 
	private double ancho;
	private double largo; 
	private double valorCompra;
	private Date fecCompra;
	private Date fecBaja;
	private String estadoActual; 
	private String color;
	private Personas PersonaResponsable;
	private Areas AreaAsignada;

	

	/**
	 * @return the personaResponsable
	 */
	public Personas getPersonaResponsable() {
		return PersonaResponsable;
	}
	/**
	 * @param personaResponsable the personaResponsable to set
	 */
	public void setPersonaResponsable(Personas personaResponsable) {
		PersonaResponsable = personaResponsable;
	}
	/**
	 * @return the areaAsignada
	 */
	public Areas getAreaAsignada() {
		return AreaAsignada;
	}
	/**
	 * @param areaAsignada the areaAsignada to set
	 */
	public void setAreaAsignada(Areas areaAsignada) {
		AreaAsignada = areaAsignada;
	}
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * @return the id_
	 */
	public String getId_() {
		return _id.toHexString();
	}
	
	/**
	 * @param _id the id_ to set
	 */
	public void setId_(ObjectId _id) {
		this._id = _id;
	}
	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}
	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	/**
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}
	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	/**
	 * @return the serial
	 */
	public String getSerial() {
		return serial;
	}
	/**
	 * @param serial the serial to set
	 */
	public void setSerial(String serial) {
		this.serial = serial;
	}
	/**
	 * @return the numeroInterno
	 */
	public Integer getNumeroInterno() {
		return numeroInterno;
	}
	/**
	 * @param numeroInterno the numeroInterno to set
	 */
	public void setNumeroInterno(Integer numeroInterno) {
		this.numeroInterno = numeroInterno;
	}
	/**
	 * @return the peso
	 */
	public double getPeso() {
		return peso;
	}
	/**
	 * @param peso the peso to set
	 */
	public void setPeso(double peso) {
		this.peso = peso;
	}
	/**
	 * @return the alto
	 */
	public double getAlto() {
		return alto;
	}
	/**
	 * @param alto the alto to set
	 */
	public void setAlto(double alto) {
		this.alto = alto;
	}
	/**
	 * @return the ancho
	 */
	public double getAncho() {
		return ancho;
	}
	/**
	 * @param ancho the ancho to set
	 */
	public void setAncho(double ancho) {
		this.ancho = ancho;
	}
	/**
	 * @return the largo
	 */
	public double getLargo() {
		return largo;
	}
	/**
	 * @param largo the largo to set
	 */
	public void setLargo(double largo) {
		this.largo = largo;
	}
	/**
	 * @return the valorCompra
	 */
	public double getValorCompra() {
		return valorCompra;
	}
	/**
	 * @param valorCompra the valorCompra to set
	 */
	public void setValorCompra(double valorCompra) {
		this.valorCompra = valorCompra;
	}
	/**
	 * @return the fecCompra
	 */
	public Date getFecCompra() {
		return fecCompra;
	}
	/**
	 * @param FecCompra the fecCompra to set
	 */
	public void setFecCompra(Date FecCompra) {
		fecCompra = FecCompra;
	}
	/**
	 * @return the fecBaja
	 */
	public Date getFecBaja() {
		return fecBaja;
	}
	/**
	 * @param FecBaja the fecBaja to set
	 */
	public void setFecBaja(Date FecBaja) {
		fecBaja = FecBaja;
	}
	/**
	 * @return the estadoActual
	 */
	public String getEstadoActual() {
		return estadoActual;
	}
	/**
	 * @param estadoActual the estadoActual to set
	 */
	public void setEstadoActual(String estadoActual) {
		this.estadoActual = estadoActual;
	}
	/**
	 * @return the color
	 */
	public String getColor() {
		return color;
	}
	/**
	 * @param color the color to set
	 */
	public void setColor(String color) {
		this.color = color;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Activos [_id=" + _id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", tipo=" + tipo
				+ ", serial=" + serial + ", numeroInterno=" + numeroInterno + ", peso=" + peso + ", alto=" + alto
				+ ", ancho=" + ancho + ", largo=" + largo + ", valorCompra=" + valorCompra + ", FecCompra=" + fecCompra
				+ ", FecBaja=" + fecBaja + ", estadoActual=" + estadoActual + ", color=" + color + "]";
	}
	
	
	
	
}
