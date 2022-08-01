package com.imgSelect.backEnd.model;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface ImgMsgRepository extends CrudRepository<ImgMsg, Long> {
    List<ImgMsg> findByMsg(String msg);
    ImgMsg findById(long id);
    List findAllByOrderByIdDesc();

}
