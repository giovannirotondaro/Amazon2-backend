package com.techzon.data.services;

import java.util.List;
import com.techzon.data.dto.CorriereDto;

public interface CorriereService {
	CorriereDto getByUsername(String username);
	CorriereDto getByEmail(String email);
	CorriereDto addCorriere(CorriereDto corriereDto);
	List<CorriereDto> getAll();
}
