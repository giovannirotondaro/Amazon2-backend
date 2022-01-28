package com.techzon.data.services;

import java.util.List;
import com.techzon.data.dto.ProvinciaDto;


public interface ProvinciaService {

	List<ProvinciaDto> getAllName();
	List<ProvinciaDto> getByRegione(String regione);
}
