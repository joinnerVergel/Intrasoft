package com.intrasoft.Api.ActivosFijos;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.intrasoft.Api.entidades.Activos;

public interface activosRespository extends MongoRepository<Activos, String>  {
	Activos findBy_id(ObjectId _id);
	List<Activos> findBynombre(String nombre);
	Activos findByserial(String serial);
}
