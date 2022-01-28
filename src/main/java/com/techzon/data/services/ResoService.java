package com.techzon.data.services;


import com.techzon.data.dto.ResoDto;

import java.util.List;

public interface ResoService {

    ResoDto addReso(ResoDto resoDto);

    List<ResoDto> getResiByUsernameDestinatario(String username);

    boolean effettuaRimborso(ResoDto resoDto);
    
    ResoDto isPresenteReso(Long id);
}
