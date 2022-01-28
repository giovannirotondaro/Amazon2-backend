package com.techzon.data.services;

import com.techzon.data.dto.*;
import java.util.List;

public interface PreferitoService {

	List<PreferitoDto> getAllByUtente(UserDto userDto);
	PreferitoDto getPreferitoByProdotto(ProdottoDto prodottoDto);

	Boolean addPreferito(PreferitoDto preferitoDto);

	Boolean rimuoviPreferito(PreferitoDto preferito);
}
