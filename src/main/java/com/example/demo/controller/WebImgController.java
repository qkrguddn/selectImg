package com.example.demo.controller;

import com.example.demo.model.ImgMsgRepository;
import com.example.demo.model.ImgMsg;;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;

import javax.validation.Valid;
import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.List;

@Controller
public class WebImgController {
    @Autowired
    private ImgMsgRepository imgMsgRepository;
    private static final Logger LOG = LoggerFactory.getLogger(WebImgController.class);

    @GetMapping("/webImg")
    public String imgForm(Model model) {
        WebImg webImg = new WebImg(0, "1");
        model.addAttribute("webImg", webImg);
        return "webImg";
    }

    @PostMapping("/webImg")
    public String imgSubmit(@ModelAttribute WebImg webImg, Model model) {
        model.addAttribute("webImg", webImg);
        LOG.info(webImg.getContent());
        ImgMsg data = new ImgMsg(webImg.getContent());
//        imgMsgRepository.deleteAll();
        imgMsgRepository.save(data);
        int pageNumber = webImg.getPageCount();
        webImg.setPageCount(++pageNumber);
        File file = new File("./src/main/resources/static/frame");
        String[] directories = file.list(new FilenameFilter() {
            @Override
            public boolean accept(File current, String name) {
                return new File(current, name).isDirectory();
            }
        });
        Arrays.sort(directories);
        System.out.println("mysql Msg: " + data.getMsg() + "  mysql id: " + data.getId() +
                "  imgMsgRepository 수:  " + imgMsgRepository.count() + " pageNumber:  " + pageNumber);
        System.out.println("배열 종류:  ");
        if (pageNumber == directories.length) {
            pageNumber = directories.length - 1;
            webImg.setPageCount(pageNumber);
        }
        webImg.setFrameName(directories[pageNumber]);
        System.out.println(Arrays.toString(directories) + "  frameName:  " + webImg.getFrameName());
        for (ImgMsg mysqlData : imgMsgRepository.findAll()) {
            LOG.info(mysqlData.idData());
        }
        model.addAttribute("webImg", webImg);
        return "webImg";
    }

    @PostMapping("/webImg2")
    public String imgSubmit2(@ModelAttribute WebImg webImg, Model model) {
        model.addAttribute("webImg", webImg);
        int pageNumber = webImg.getPageCount();
        System.out.println("imgMsgRepository 수:  " + imgMsgRepository.count());
        webImg.setPageCount(--pageNumber);
        File file = new File("./src/main/resources/static/frame");
        String[] directories = file.list(new FilenameFilter() {
            @Override
            public boolean accept(File current, String name) {
                return new File(current, name).isDirectory();
            }
        });
        Arrays.sort(directories);
        if (pageNumber == -1) {
            pageNumber = 0;
            webImg.setPageCount(pageNumber);
        }
        webImg.setFrameName(directories[pageNumber]);
        System.out.println("pageNumber: " + pageNumber + " frameName: " + webImg.getFrameName()
                  + "\n" + "arr: " + Arrays.toString(directories));
        //findById() mysql의 마지막 id넣기
        webImg.setFinalId(Long.valueOf(String.valueOf(imgMsgRepository.findAllByOrderByIdDesc().get(0))));
        Long finalId = webImg.getFinalId();
        ImgMsg mysqlData = imgMsgRepository.findById(finalId.intValue());
        LOG.info(mysqlData.idData());
        webImg.setMysqlData(mysqlData.idData());
        imgMsgRepository.deleteById(finalId);//mysql의 마지막 id넣기.
        return "webImg";
    }
}