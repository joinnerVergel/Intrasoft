package com.intrasoft.Api.entidades;

public class Areas {
 private Integer idArea;
 private String nombreArea;
 private  Ciudades Ciudad;
/**
 * 
 */
public Areas() {
}
/**
 * @return the idArea
 */
public Integer getIdArea() {
	return idArea;
}
/**
 * @param idArea the idArea to set
 */
public void setIdArea(Integer idArea) {
	this.idArea = idArea;
}
/**
 * @return the nombreArea
 */
public String getNombreArea() {
	return nombreArea;
}
/**
 * @param nombreArea the nombreArea to set
 */
public void setNombreArea(String nombreArea) {
	this.nombreArea = nombreArea;
}
/**
 * @return the ciudad
 */
public Ciudades getCiudad() {
	return Ciudad;
}
/**
 * @param ciudad the ciudad to set
 */
public void setCiudad(Ciudades ciudad) {
	Ciudad = ciudad;
}


 
}
