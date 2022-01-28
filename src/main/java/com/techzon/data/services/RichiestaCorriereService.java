package com.techzon.data.services;

import java.util.List;
import com.techzon.data.dto.RichiestaCorriereDto;

public interface RichiestaCorriereService {
	RichiestaCorriereDto add(RichiestaCorriereDto richiestaCorriereDto);
	List<RichiestaCorriereDto> getAll();
	Boolean deleteRichiestaCorriere(Long id);
	RichiestaCorriereDto getRichiestaCorriere(Long id);
	RichiestaCorriereDto getRichiestaCorriereEmail(String email);
	List<RichiestaCorriereDto> getAllByhannoColloquio(boolean hannoColloquio);
} 
