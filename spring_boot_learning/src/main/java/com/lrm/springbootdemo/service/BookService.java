package com.lrm.springbootdemo.service;


import com.lrm.springbootdemo.domain.Book;
import com.lrm.springbootdemo.domain.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

/**
 * Created by limi on 2017/8/14.
 */
@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;


    /**
     * 查询所有的书单列表
     * @return
     */
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    /**
     * 分页查询书单列表
     * @return
     */
    public Page<Book> findAllByPage(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    /**
     * 提交一个书单信息
     * @param book
     * @return
     */
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    /**
     * 获取一条书单信息
     * @param id
     * @return
     */
    public Book findOne(long id) {
        return bookRepository.getOne(id);
    }

    /**
     * 删除一条书单信息
     * @param id
     */
    public void delete(long id) {
        bookRepository.deleteById(id);
    }


    /**
     * 根据author查询一个书单列表
     * @param author
     * @return
     */
    public List<Book> findByAuthor(String author) {
        return bookRepository.findByAuthor(author);
    }

    /**
     * 根据 author and status 查询一个书单列表
     * @param author
     * @param status
     * @return
     */
    public List<Book> findByAuthorAndStatus(String author, int status) {
        return bookRepository.findByAuthorAndStatus(author, status);
    }

    /**
     *
     * @param des
     * @return
     */
    public List<Book> findByDescriptionEndsWith(String des) {
        return bookRepository.findByDescriptionContains(des);
    }


    /**
     * 自定义查询
     * @param len
     * @return
     */
    public List<Book> findByJPQL(int len) {
        return bookRepository.findByJPQL(len);
    }

    /**
     * 自定义更新
     * @param status
     * @param id
     * @return
     */

    @Transactional
    public int updateByJPQL(int status, long id) {

        return bookRepository.updateByJPQL(status, id);
    }


    /**
     * 自定义删除
     * @param id
     * @return
     */
    @Transactional
    public int deleteByJPQL(long id) {

        return bookRepository.deleteByJPQL( id);
    }


    /**
     * 测试事务操作方法
     * @param id
     * @param status
     * @param uid
     * @return
     */
//    @Transactional
    public int deleteAndUpdate(long id, int status, long uid) {

        int dcount = bookRepository.deleteByJPQL(id);

        int ucount = bookRepository.updateByJPQL(status,uid);

        return dcount+ucount;
    }



}
