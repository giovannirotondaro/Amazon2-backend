package com.techzon.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.techzon.data.dto.CartaCreditoDto;
import com.techzon.data.dto.UserDto;
import com.techzon.data.services.CartaCreditoService;
import com.techzon.data.services.UserService;

@RestController
@RequestMapping("/cartaCredito")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CartaCreditoController {
  
  @Autowired
  private UserService userService;
  
  @Autowired
  private CartaCreditoService cartaCreditoService;
  
  @PostMapping("/addCarta")
  public ResponseEntity<String> addCarta(@RequestBody CartaCreditoDto cartaCreditoDto, @RequestParam("username") String username){
    UserDto userDto = userService.getByUsername(username);
    
    if(cartaCreditoService.existWithNumeroCarta(cartaCreditoDto.getNumeroCarta())) {
      CartaCreditoDto c = cartaCreditoService.getByNumeroCarta(cartaCreditoDto.getNumeroCarta());
      if(c.equals(cartaCreditoDto)) {
        c.getUtenti().add(userDto);
        cartaCreditoService.add(c);
        return ResponseEntity.ok("Carta aggiunta");
      }
      else
        return ResponseEntity.ok("Parametri non validi per questo numero di carta");
    }
    cartaCreditoDto.setUtenti(new ArrayList<UserDto>());
    cartaCreditoDto.getUtenti().add(userDto);
    cartaCreditoService.add(cartaCreditoDto);
    return ResponseEntity.ok("Carta aggiunta");
  }

  @GetMapping("/getCartaByUsername")
  public ResponseEntity<List<CartaCreditoDto>> getCarta(@RequestParam("username") String username){
    UserDto userDto = userService.getByUsername(username);
    List<CartaCreditoDto> carte = new ArrayList<CartaCreditoDto>();
    for(CartaCreditoDto carta : cartaCreditoService.getAll()) {
      for(UserDto user : carta.getUtenti()) {
        if(user.getId() == userDto.getId()) {
          carte.add(carta);
          break;
        }
      }
    }
    return ResponseEntity.ok(carte);
  }
  
  @DeleteMapping("/deleteCarta")
  public HttpStatus deleteCarta(@RequestParam("idCarta") Long idCarta, @RequestParam("idUser") Long idUser){
    CartaCreditoDto cartaCreditoDto = cartaCreditoService.getById(idCarta);
    int index = 0;
    for(int i=0; i<cartaCreditoDto.getUtenti().size(); i++) {
      if(cartaCreditoDto.getUtenti().get(i).getId() == idUser) {
        index = i;
        break;
      }
    }
    if(cartaCreditoDto.getUtenti().size() == 1) {
      cartaCreditoService.deleteByCardId(idCarta);
    }
    else {
      cartaCreditoDto.getUtenti().remove(index);
      cartaCreditoService.add(cartaCreditoDto);
    }
    return HttpStatus.OK;
  }
}