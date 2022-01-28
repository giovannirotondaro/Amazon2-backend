package com.techzon.data.services.impl;


import com.techzon.data.dao.ResoDao;
import com.techzon.data.dao.UserDao;
import com.techzon.data.dto.ResoDto;
import com.techzon.data.entities.Reso;
import com.techzon.data.entities.User;
import com.techzon.data.services.ResoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResoServiceImpl implements ResoService {

    @Autowired
    private ResoDao resoDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ResoDto addReso(ResoDto resoDto) {
        Reso reso =modelMapper.map(resoDto,Reso.class);
        Reso saved=resoDao.save(reso);
        return modelMapper.map(saved, ResoDto.class);
    }



    @Override
    public List<ResoDto> getResiByUsernameDestinatario(String username){
        List<Reso> resi = resoDao.findAllByUsernameDestinatario(username);
/*
        System.out.println(resi.get(0).getUsernameDestinatario());
        System.out.println(resi.get(0).getId());
        System.out.println(resi.get(0).getMotivoReso());
        System.out.println(resi.get(0).getUsernameMittente());
        System.out.println(resi.get(0).getNumeroOrdine().getProdotto().getPrezzo());
*/
        return resi.stream().map(a -> modelMapper.map(a, ResoDto.class)).collect(Collectors.toList());
    }

    @Override
    public boolean effettuaRimborso(ResoDto resoDto) {
        User cliente = userDao.findByUsername(resoDto.getUsernameMittente());
        User venditore = userDao.findByUsername(resoDto.getUsernameDestinatario());
        
        if(venditore.getSaldo() < resoDto.getPrezzo()){

            return false;
        }



        cliente.setSaldo(cliente.getSaldo()+resoDto.getPrezzo());
        venditore.setSaldo(venditore.getSaldo()-resoDto.getPrezzo());

        userDao.save(cliente);
        userDao.save(venditore);


        return true;
    }
    
    @Override
    public ResoDto isPresenteReso(Long id) {
        Reso reso = resoDao.resoByNumeroOrdine(id);
        if(reso==null)
            return null;
        return modelMapper.map(reso, ResoDto.class);
    }



}
