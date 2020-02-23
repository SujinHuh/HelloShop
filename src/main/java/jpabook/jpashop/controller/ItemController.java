package jpabook.jpashop.controller;

import jpabook.jpashop.domain.item.Album;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.domain.item.Movie;
import jpabook.jpashop.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;


@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/items/new")
    public String creatForm(Model model) {
        model.addAttribute("itemNew");
        return "items/newItemForm";
    }


    @GetMapping("/items/newBook")
    public String createBookFrom(Model model) {
        model.addAttribute("bookForm", new BookForm());
        return "items/createBookItemFrom";
    }

    @PostMapping("/items/newBook")
    public String create(@Valid BookForm bookForm, BindingResult result) {

        if (result.hasErrors()) {
            return "items/createBookItemFrom";
        }
//파라미터로 넘기
        Book book = new Book();
        book.setName(bookForm.getName());
        book.setPrice(bookForm.getPrice());
        book.setStockQuantity(bookForm.getStockQuantity());
        book.setAuthor(bookForm.getAuthor());
        book.setIsbn(bookForm.getIsbn());

        itemService.saveItem(book);
        return "redirect:/";

    }

    @GetMapping("/items/newMovie")
    public String createMovieFrom(Model model) {
        model.addAttribute("movieForm", new MovieForm());
        return "items/createMovieItemFrom";
    }

    @PostMapping("/items/newMovie")
    public String createMovie(@Valid MovieForm movieForm, BindingResult result) {

        if (result.hasErrors()) {
            return "items/createMovieItemFrom";
        }

        Movie movie = new Movie();
        movie.setName(movieForm.getName());
        movie.setPrice(movieForm.getPrice());
        movie.setStockQuantity(movieForm.getStockQuantity());
        movie.setDirector(movieForm.getDirector());
        movie.setActor(movieForm.getActor());

        itemService.saveItem(movie);
        return "redirect:/";
    }

    @GetMapping("/items/newAlbum")
    public String createAlbumFrom(Model model) {
        model.addAttribute("albumForm", new AlbumFrom());
        return "items/createAlbumItemFrom";
    }

    @PostMapping("items/newAlbum")
    public String createAlbum(@Valid AlbumFrom albumFrom, BindingResult result) {

        if (result.hasErrors()) {
            return "items/createAlbumItemFrom";
        }

        Album album = new Album();
        album.setName(albumFrom.getName());
        album.setPrice(albumFrom.getPrice());
        album.setStockQuantity(albumFrom.getStockQuantity());
        album.setArtist(albumFrom.getArtist());
        album.setEtc(album.getEtc());

        itemService.saveItem(album);
        return "redirect:/";
    }


    @GetMapping("/items")
    public String list(Model model) {
        List<Item> items = itemService.findItems();
        model.addAttribute("items", items);
        return "items/itemList";
    }

    @GetMapping("/items/{itemId}/edit")
    public String updateItemFrom(@PathVariable("itemId") Long itemId, Model model) {
        //Book item = (Book) itemService.findOne(itemId);


        Item item = itemService.findOne(itemId);

        BookForm bookForm = new BookForm();
       // if (item.getId() == bookForm.getId()) {
            bookForm.setId(item.getId());
            bookForm.setName(item.getName());
            bookForm.setPrice(item.getPrice());
            bookForm.setStockQuantity(item.getStockQuantity());
            //bookForm.getClass((bookForm)item.getA)//setAuthor(item.getClass().);
//        form.setAuthor((Book) item.);
//        form.setIsbn(item.getIsbn());
//form.getClass().equals()
                    // assertEquls()
                            model.addAttribute("form", bookForm);
        return "items/updateItemFrom";

    }

    @PostMapping("/items/{itemId}/edit")
    public String updateItem(@PathVariable Long itemId, @ModelAttribute("form") BookForm form) {

        itemService.updateItem(itemId, form.getName(), form.getPrice(), form.getStockQuantity());
        return "redirect:/items";
    }


}
