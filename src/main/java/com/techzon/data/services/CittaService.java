package com.techzon.data.services;

import java.util.List;
import com.techzon.data.dto.CittaDto;

public interface CittaService {
	List<CittaDto> getByProvincia(String provincia);
}
