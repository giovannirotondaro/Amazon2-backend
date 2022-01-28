package com.techzon.utilities;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.techzon.data.dto.ProdottoDto;

public class Matching {
	
	public static List<ProdottoDto> apply(String ricerca, List<ProdottoDto> prodotti) {
		List<ProdottoDto> prodottiTrovati = new ArrayList<ProdottoDto>();
		String prodottoDaRicercare = "";
		
		for(ProdottoDto p : prodotti) {
			if(ricerca.equals(p.getTitolo())){
				prodottiTrovati.add(p);
				prodottoDaRicercare = p.getTitolo();
				break;
			}
		}
        for(ProdottoDto prodotto : prodotti) {
        	if(!prodotto.getTitolo().equals(prodottoDaRicercare)) {
	            StringTokenizer str = new StringTokenizer(ricerca, " -,.?=/()&%$�!|{}[]�#�_;:<>'");
	            while (str.hasMoreTokens()) {
	                String stringaAttuale = str.nextToken().toLowerCase();
	
	                Pattern pattern = Pattern.compile(stringaAttuale, Pattern.CASE_INSENSITIVE);
	                Matcher matcher = pattern.matcher(prodotto.getTitolo());
	                boolean resMatch = matcher.find();
	                if (stringaAttuale.length() == 1 || prodotto.getTitolo().length() == 1)
	                    continue;
	                if (resMatch) {
	                    prodottiTrovati.add(prodotto);
	                    break;
	                }
	                else if (stringaAttuale.contains(prodotto.getTitolo().toLowerCase())) {
	                	prodottiTrovati.add(prodotto);
	                	break;
	                }
	            }
        	}
        }
        return prodottiTrovati;
    }
}
