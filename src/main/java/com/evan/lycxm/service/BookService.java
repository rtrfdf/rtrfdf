package com.evan.lycxm.service;


import com.evan.lycxm.config.RedisConfig;
import com.evan.lycxm.dao.BookDAO;
import com.evan.lycxm.entity.Book;
import com.evan.lycxm.entity.Category;
import com.evan.lycxm.redis.RedisService;
import com.evan.lycxm.util.CastUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.List;

/**
 * @author Evan
 * @date 2019/4
 */
@Service
public class BookService {
    @Autowired
    private BookDAO bookDAO;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private RedisService redisService;

  public List<Book> list() {
       List<Book> books;
        String key = "booklist";
        Object bookCache = redisService.get(key);

       if (bookCache == null) {
           Sort sort = new Sort(Sort.Direction.DESC, "id");
            books = bookDAO.findAll(sort);
            redisService.set(key, books);
      } else {
           books = CastUtils.objectConvertToList(bookCache, Book.class);
        }
        return books;
    }

//   直接用注解实现缓存
//    @Cacheable(value = RedisConfig.REDIS_KEY_DATABASE)
//    public List<Book> list() {
//       List<Book> books;
  //      Sort sort = new Sort(Sort.Direction.DESC, "id");
    //    books = bookDAO.findAll(sort);
 //       return books;
 //  }

    public void addOrUpdate(Book book) {
        redisService.delete("booklist");
        bookDAO.save(book);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        redisService.delete("booklist");
    }

    public void deleteById(int id) {
        redisService.delete("booklist");
        bookDAO.deleteById(id);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        redisService.delete("booklist");
    }

    public List<Book> listByCategory(int cid) {
        Category category = categoryService.get(cid);
        return bookDAO.findAllByCategory(category);
    }

    public List<Book> Search(String keywords) {
        return bookDAO.findAllByTitleLikeOrAuthorLike('%' + keywords + '%', '%' + keywords + '%');
    }
}
