package com.techzon.data.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.techzon.data.dao.CartaCreditoDao;
import com.techzon.data.dto.CartaCreditoDto;
import com.techzon.data.entities.CartaCredito;
import com.techzon.data.services.CartaCreditoService;

@Service
public class CartaCreditoServiceImpl implements CartaCreditoService {
	
	@Autowired
	private CartaCreditoDao cartaCreditoDao;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<CartaCreditoDto> getAll() {
		List<CartaCredito> carte = cartaCreditoDao.findAll();
		return carte.stream().map(carta -> modelMapper.map(carta, CartaCreditoDto.class)).collect(Collectors.toList());
	}

	@Override
	public CartaCreditoDto add(CartaCreditoDto cartaCreditoDto) {
		CartaCredito cartaCredito = modelMapper.map(cartaCreditoDto, CartaCredito.class);
		CartaCredito card = cartaCreditoDao.save(cartaCredito);
		return modelMapper.map(card, CartaCreditoDto.class);
	}

	@Override
	public boolean existWithNumeroCarta(String numeroCarta) {
		return cartaCreditoDao.existsByNumeroCarta(numeroCarta);
	}

	@Override
	public CartaCreditoDto getByNumeroCarta(String numeroCarta) {
		CartaCreditoDto c = modelMapper.map(cartaCreditoDao.findByNumeroCarta(numeroCarta), CartaCreditoDto.class);
		return c;
	}
	
	@Override
	@Transactional
	public void deleteByCardId(Long id) {
		cartaCreditoDao.deleteById(id);
	}

	@Override
	public CartaCreditoDto getById(Long id) {
		Optional<CartaCredito> p = cartaCreditoDao.findById(id);
		if(p.isPresent()) {
			return modelMapper.map(p.get(), CartaCreditoDto.class);
		}else {
		    throw new RuntimeException();
		}
	}
}
