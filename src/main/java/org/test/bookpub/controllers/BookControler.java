package org.test.bookpub.controllers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.test.bookpub.entity.Book;
import org.test.bookpub.repository.BookRepository;

import java.beans.PropertyEditorSupport;

@RestController
@RequestMapping("/books")
public class BookControler {

    protected final Log logger = LogFactory.getLog(getClass());

    @Autowired
    private BookRepository bookRepository;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Iterable<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    @InitBinder
    @RequestMapping(value = "/{isbn}", method = RequestMethod.GET)
    public Book getBook(@PathVariable Isbn isbn) {

        String isbnLocal = isbn.getIsbn();
        logger.info("ISBN=" + isbnLocal);
        
        return bookRepository.findBookByIsbn(isbn.getIsbn());
    }

}

