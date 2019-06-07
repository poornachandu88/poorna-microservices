package com.poorna.controller;

import com.poorna.dao.QuotesRepository;
import com.poorna.model.Quote;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class QuoteController {

	private static final Logger LOGGER = LoggerFactory.getLogger(QuoteController.class);
	
	
	@Autowired
    private QuotesRepository quotesRepository;

   
    
  @GetMapping("/{username}")
    public List<String> getQuotes(@PathVariable("username") final String username) {

        return getQuotesByUserName(username);
    }

    @PostMapping("/add")
    public Quote add(@RequestBody final Quote quote) {
    	
     
        return quotesRepository.save(quote);
    }


    @PostMapping("/delete/{username}")
    public String delete(@PathVariable("username") final String username) {

        Quote quote = quotesRepository.findQuoteByUserName(username);
     String usernameinf=quote.getUserName();
        quotesRepository.delete(quote);

        return "Deleted "+usernameinf +" Successfully";
    }


    private List<String> getQuotesByUserName(@PathVariable("username") String username) {
        return quotesRepository.findByUserName(username)
                .stream()
                .map(Quote::getQuote)
                .collect(Collectors.toList());
    }



}
