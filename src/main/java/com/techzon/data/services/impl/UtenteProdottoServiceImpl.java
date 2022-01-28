package com.techzon.data.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.techzon.data.dao.UtenteProdottoDao;
import com.techzon.data.dto.CorriereDto;
import com.techzon.data.dto.UserDto;
import com.techzon.data.dto.UtenteProdottoDto;
import com.techzon.data.entities.Corriere;
import com.techzon.data.entities.User;
import com.techzon.data.entities.UtenteProdotto;
import com.techzon.data.entities.UtenteProdotto.Stato;
import com.techzon.data.services.UtenteProdottoService;

@Service
public class UtenteProdottoServiceImpl implements UtenteProdottoService{
	
	@Autowired
	private UtenteProdottoDao utenteProdottoDao;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	@Transactional
	public List<UtenteProdottoDto> getAllByUtente(UserDto userDto) {
		User utente = modelMapper.map(userDto, User.class);
		List<UtenteProdotto> prodotti = utenteProdottoDao.findAllByUser(utente);
		return prodotti.stream().map(prod -> modelMapper.map(prod, UtenteProdottoDto.class)).collect(Collectors.toList());
	}

	@Override
	public List<UtenteProdottoDto> getAllByStato(Stato stato) {
		List<UtenteProdotto> prodotti = utenteProdottoDao.findAllByStato(stato);
		return prodotti.stream().map(prod -> modelMapper.map(prod, UtenteProdottoDto.class)).collect(Collectors.toList());
	}

	@Override
	public List<UtenteProdottoDto> getAllByCorriere(CorriereDto corriereDto) {
		Corriere corriere = modelMapper.map(corriereDto, Corriere.class);
		List<UtenteProdotto> prodotti = utenteProdottoDao.findAllByCorriere(corriere);
		return prodotti.stream().map(prod -> modelMapper.map(prod, UtenteProdottoDto.class)).collect(Collectors.toList());
	}

	@Override
	public UtenteProdottoDto getUtenteProdotto(Long id) {
		Optional<UtenteProdotto> p = utenteProdottoDao.findById(id);
		if(p.isPresent()) {
			return modelMapper.map(p.get(), UtenteProdottoDto.class);
		}else {
		    throw new RuntimeException();
		}
	}

	@Override
	public UtenteProdottoDto addUtenteProdottoDto(UtenteProdottoDto utenteProdottoDto) {
		UtenteProdotto utenteProdotto = modelMapper.map(utenteProdottoDto, UtenteProdotto.class);
		UtenteProdotto saved = utenteProdottoDao.save(utenteProdotto);
		return modelMapper.map(saved, UtenteProdottoDto.class);
	}

	@Override
	public Boolean rimuoviUtenteProdotto(UtenteProdottoDto utenteProdottoDto) {
		UtenteProdotto p=modelMapper.map(utenteProdottoDto,UtenteProdotto.class);
		if(utenteProdottoDao.findById(p.getId()) == null){
			return false;
		}
		utenteProdottoDao.delete(p);
		return true;
	}

	
	@Override
	public UtenteProdottoDto getByIdProdotto(Long id) {
		Optional<UtenteProdotto> p = utenteProdottoDao.trovaOrdine(id);
		if(p.isPresent()) {
			return modelMapper.map(p.get(), UtenteProdottoDto.class);
		}else {
			throw new RuntimeException();
		}
	}
}
