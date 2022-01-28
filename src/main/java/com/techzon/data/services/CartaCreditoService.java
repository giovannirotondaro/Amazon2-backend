package com.techzon.data.services;

import java.util.List;
import com.techzon.data.dto.CartaCreditoDto;

public interface CartaCreditoService {
	
	List<CartaCreditoDto> getAll();
	CartaCreditoDto add(CartaCreditoDto cartaCreditoDto);
	boolean existWithNumeroCarta(String numeroCarta);
	CartaCreditoDto getByNumeroCarta(String numeroCarta);
	void deleteByCardId(Long id);
	CartaCreditoDto getById(Long id);
}
